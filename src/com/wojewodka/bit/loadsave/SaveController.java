package com.wojewodka.bit.loadsave;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.wojewodka.bit.misc.alert.CustomAlert;
import com.wojewodka.bit.misc.toolbar.CustomToolbar.ToolbarAction;
import com.wojewodka.bit.misc.toolbar.ToolbarListener;
import com.wojewodka.bit.utils.RegionUtils;
import com.wojewodka.bit.utils.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class SaveController implements ToolbarListener, Initializable {

	@FXML
	private ListView<String> saves;

	@FXML
	private Label menuLogo;

	@FXML
	private Button saveButton;

	@FXML
	private TextField newSaveName;

	/**
	 * Create or overwrite save </br>
	 * Before overwrite show dialog with information.
	 * 
	 */
	public void save() {
		String result = newSaveName.getText();

		if (StringUtils.isEmpty(result)) {
			return;
		}

		File save = SaveUtils.getSave(result);
		if (save.exists()) {
			CustomAlert alert = new CustomAlert(AlertType.CONFIRMATION, new String[] { "overwrite-alert" });
			alert.setContentText("Chcesz nadpisać aktualny stan gry?");

			Optional<ButtonType> resultButton = alert.showAndWait();
			if (resultButton.get() != ButtonType.OK) {
				return;
			}
		}

		File s = SaveUtils.createNewSave(result);
		SaveDefault sd = new SaveDefault(s);
		try {
			sd.save();
		} catch (Exception e) {
			e.printStackTrace();
		}
		newSaveName.setText(null);
		initSavesList();
	}

	/**
	 * If mouse click: </br>
	 * Put in text field name of selected item. </br>
	 * If mouse is clicked twice save with this name. </br>
	 * 
	 * If click enter overwrite current selected save.
	 * 
	 * @param _e
	 * @throws Exception
	 */
	public void selectNew(Event _e) throws Exception {
		if (_e instanceof KeyEvent) {
			KeyEvent e = (KeyEvent) _e;
			if (e.getCode() == KeyCode.ENTER) {
				save();
			}

		} else if (_e instanceof MouseEvent) {
			MouseEvent e = (MouseEvent) _e;
			if (e.getClickCount() >= 2) {
				save();
			}
		}

		newSaveName.setText(getSelectedItem());
	}

	public void handleReflect(MouseEvent e) {
		Region source = (Region) e.getSource();
		RegionUtils.handleReflect(source);
	}

	/**
	 * Return name of selected save.
	 * 
	 * @return
	 */
	private String getSelectedItem() {
		return saves.getSelectionModel().getSelectedItem();
	}

	public void prepareLogo() {
		RegionUtils.prepareLogo(menuLogo);
	}

	/**
	 * Clear and fill list view by last 100 of saves name.
	 * 
	 */
	public void initSavesList() {
		ObservableList<String> items = saves.getItems();
		items.clear();
		List<String> lastSaves = SaveUtils.getLastSaves(100, false);
		items.addAll(FXCollections.<String>observableArrayList(lastSaves));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		removeAction(ToolbarAction.SAVE);
		initSavesList();

		prepareLogo();
	}

}
