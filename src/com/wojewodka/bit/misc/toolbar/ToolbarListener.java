package com.wojewodka.bit.misc.toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.wojewodka.bit.misc.toolbar.CustomToolbar.ToolbarAction;
import com.wojewodka.bit.utils.FXMLUtils;

/**
 * Interface which is used inside {@link CustomToolbar}
 * 
 * @author wojew
 */
public interface ToolbarListener {

	List<ToolbarAction> toolbarActions = new ArrayList<>(Arrays.asList(ToolbarAction.values()));

	default List<ToolbarAction> getToolbarActions() {
		return toolbarActions;
	}

	/**
	 * Sometimes we don't need some action. </br>
	 * E.g.: when application is in SaveView let's remove "save" icon.
	 * 
	 * @param action
	 */
	default void removeAction(ToolbarAction action) {
		int size = toolbarActions.size();
		if (size == 0)
			return;

		Iterator<ToolbarAction> it = toolbarActions.iterator();
		while (it.hasNext()) {
			ToolbarAction next = it.next();
			if (next.equals(action)) {
				toolbarActions.remove(action);
			}
		}
	}

	/**
	 * <i>By default:</i> Redirect to Menu stage
	 */
	default void onBack() {
		FXMLUtils.goTo("Menu", false);
	}

	/**
	 * <i>By default:</i> close application.
	 * 
	 */
	default void onExit() {
		System.exit(0);
	}

	/**
	 * <i>By default:</i> Redirect to SaveView stage
	 */
	default void onSave() {
		FXMLUtils.goTo("SaveView", true);
	}

}
