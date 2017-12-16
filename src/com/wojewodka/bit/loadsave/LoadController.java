package com.wojewodka.bit.loadsave;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.wojewodka.bit.misc.toolbar.ToolbarListener;
import com.wojewodka.bit.utils.RegionUtils;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class LoadController implements Initializable, ToolbarListener {

	@FXML
	private ListView<String> saves;

	@FXML
	private Label menuLogo;

	public void prepareLogo() {
		RegionUtils.prepareLogo(menuLogo);
	}

	public void handleReflect(MouseEvent e) {
		Region source = (Region) e.getSource();
		RegionUtils.handleReflect(source);
	}

	public void selectNew(Event _e) throws Exception {
		if (_e instanceof KeyEvent) {
			KeyEvent e = (KeyEvent) _e;
			if (e.getCode() == KeyCode.ENTER) {
				SaveUtils.loadSave(getSelectedItem());
			}

		} else if (_e instanceof MouseEvent) {
			MouseEvent e = (MouseEvent) _e;
			if (e.getClickCount() >= 2) {
				SaveUtils.loadSave(getSelectedItem());
			}
		}
	}

	private String getSelectedItem() {
		return saves.getSelectionModel().getSelectedItem();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> lastSaves = SaveUtils.getLastSaves(100, false);
		saves.getItems().addAll(FXCollections.<String>observableArrayList(lastSaves));

		prepareLogo();
	}

}
