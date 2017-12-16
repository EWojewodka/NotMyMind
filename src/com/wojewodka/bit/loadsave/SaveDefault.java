package com.wojewodka.bit.loadsave;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wojewodka.bit.misc.Nodeable;
import com.wojewodka.bit.user.User;
import com.wojewodka.bit.utils.StageUtils;

public class SaveDefault extends Save {

	private File file;

	private User user;

	public SaveDefault(File f) {
		this.file = f;
	}

	public SaveDefault(File f, User user) {
		this.file = f;
		this.user = user;
	}

	public File getFile() {
		return file;
	}

	public User getUser() {
		return user;
	}

	@Override
	public void save() throws Exception {
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = null;

		if (!file.exists()) {
			SaveUtils.createNewSave(file);
		}

		doc = docBuilder.parse(file);

		List<Nodeable> objectsToSave = StageUtils.getNodeGamplayData();
		for (Nodeable o : objectsToSave) {
			Element node = o.toNode(doc);
			NodeList elByName = doc.getElementsByTagName(node.getNodeName());
			
			if (elByName.getLength() == 0) {
				doc.appendChild(node);
			} else {
				Node oldVersion = elByName.item(0);
				oldVersion.getParentNode().replaceChild(node, oldVersion);
			}
		}

		writeFile(doc);
	}

	private void writeFile(Document doc) throws TransformerException {
		DOMSource source = new DOMSource(doc);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(source, new StreamResult(file));
	}

	public static void main(String[] args) throws Exception {
		SaveDefault saveDefault = new SaveDefault(SaveUtils.getSave("test-save"));
		saveDefault.save();
	}

}
