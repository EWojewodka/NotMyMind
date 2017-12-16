package com.wojewodka.bit.developer;

import java.util.ListIterator;

import com.wojewodka.bit.misc.toolbar.CustomToolbar;
import com.wojewodka.bit.utils.FXMLUtils;
import com.wojewodka.bit.utils.StageUtils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DeveloperController {

	public void toggleToolbar() {
		Pane parent = (Pane) StageUtils.getParent();
		ObservableList<Node> children = parent.getChildren();
		ListIterator<Node> it = children.listIterator();
		while (it.hasNext()) {
			Node child = it.next();
			if (child instanceof CustomToolbar) {
				children.remove(child);
				return;
			}
		}

		FXMLUtils.addToolbar(parent);
	}

}
