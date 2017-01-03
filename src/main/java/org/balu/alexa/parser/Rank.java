package org.balu.alexa.parser;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public interface Rank {

	public NodeList nodeFilter(Parser page, String steps);
}
