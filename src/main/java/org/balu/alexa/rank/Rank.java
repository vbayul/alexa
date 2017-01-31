package org.balu.alexa.rank;

import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public interface Rank {

	public NodeList nodeFilter(Parser page);
}
