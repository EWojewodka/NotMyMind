package com.wojewodka.bit.loadsave;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.wojewodka.bit.utils.StringUtils;

public class SaveUtils {

	private static final String GAME_FOLDER = System.getProperty("user.home") + "/NotMyMind";

	private static final String SAVE_FOLDER = GAME_FOLDER + "/saves";

	/**
	 * Create necessary folders if doesn't exists. </br>
	 * Return true if save folder exists or creating of his is successed.
	 * 
	 * @return
	 * @throws IOException
	 */
	private static boolean createSaveFolder() throws IOException {
		File gf = new File(GAME_FOLDER);

		if (!gf.exists()) {
			gf.mkdir();
		}

		File saveFolder = new File(SAVE_FOLDER);
		return saveFolder.exists() ? true : saveFolder.mkdir();
	}

	public static File getSaveFolder() {
		try {
			createSaveFolder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new File(SAVE_FOLDER);
	}

	/**
	 * Create save file with name {@link Date} in format: </br>
	 * <code>yyyy_MM-dd HH:mm:ss</code>
	 */
	public static File createNewSave() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
		return createNewSave(sdf.format(new Date()));
	}

	public static File createNewSave(String title) {
		try {

			File f = new File(getSaveFolder().getPath() + "/" + title + ".xml");
			return createNewSave(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File createNewSave(File file) {
		try {
			file.createNewFile();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			setNewSaveDate(doc);
			writeFile(doc, file);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void writeFile(Document doc, File file) throws TransformerException {
		DOMSource source = new DOMSource(doc);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(source, new StreamResult(file));
	}

	public static void setNewSaveDate(Document doc) {
		NodeList savesNodes = doc.getElementsByTagName("save");
		Element saveElement = null;
		if (savesNodes.getLength() > 0) {
			saveElement = (Element) savesNodes.item(0);
		} else {
			saveElement = doc.createElement("save");
		}
		saveElement.setAttribute("last_save", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		doc.appendChild(saveElement);
	}

	public static File[] getAllSaves() {
		return getSaveFolder().listFiles();
	}

	public static List<String> getLastSaves(int limit) {
		return getLastSaves(limit, false);
	}

	/**
	 * Currently this method return file's names sorted by alphabet. </br>
	 * TODO: Change this sorting for sort by file metadata.
	 * 
	 * @param limit
	 * @param extension
	 * @return
	 */
	public static List<String> getLastSaves(int limit, boolean extension) {
		List<File> listSave = Arrays.asList(getSaveFolder().listFiles());
		Collections.sort(listSave, new FileDateComparator());
		List<String> collect = listSave.parallelStream().limit(limit).map(x -> x.getName())
				.collect(Collectors.toList());
		if (!extension) {
			List<String> newList = new ArrayList<>(limit);

			ListIterator<String> it = collect.listIterator();
			while (it.hasNext()) {
				String next = it.next();
				newList.add(next.substring(0, next.indexOf(".xml")));
			}
			return newList;
		}
		return collect;
	}

	public static File getSave(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}

		if (!name.endsWith(".xml")) {
			name += ".xml";
		}
		return new File(getSaveFolder() + "/" + name);
	}

	public static void loadSave(String name) throws Exception {
		File f = getSave(name);
		if (f == null)
			return;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(f);
		doc.getDocumentElement().normalize();
		System.out.println(doc.getElementsByTagName("User"));
	}

}

class FileDateComparator implements Comparator<File> {

	@Override
	public int compare(File f1, File f2) {
		long f1Modify = f1.lastModified();
		long f2Modify = f2.lastModified();

		if (f1Modify > f2Modify) {
			return -1;
		} else if (f1Modify == f2Modify) {
			return 0;
		} else {
			return 1;
		}
	}

}
