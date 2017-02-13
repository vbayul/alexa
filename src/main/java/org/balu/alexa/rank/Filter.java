package org.balu.alexa.rank;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Filter {
	
	public NodeList nodeFilter(Parser page, String step) {
		
		NodeList nodes = new NodeList();
		NodeFilter attribute = null;
		
		if (step.equals("count")){
			attribute = new AndFilter(new TagNameFilter("div"), 
					new HasAttributeFilter("class", "count"));
		}
		if (step.equals("global")){
			attribute = new AndFilter(new TagNameFilter("strong"), 
				new HasAttributeFilter("class","metrics-data align-vmiddle"));
		}
		if (step.equals("url")){
			attribute = new AndFilter(new TagNameFilter("a"), 
        		new HasAttributeFilter("href"));
		}
		
        try 
        {
			nodes = page.parse(attribute);
		} 
        catch (ParserException e)
        {
			e.printStackTrace();
		}

		return nodes;
	}
}
