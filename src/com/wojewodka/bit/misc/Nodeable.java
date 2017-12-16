package com.wojewodka.bit.misc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Necessary interface for saving data in xml.
 * 
 * @author wojew
 */
public interface Nodeable {

	Element toNode(Document doc);
}
