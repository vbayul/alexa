package org.balu.alexa.parser;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public abstract class Rank {

	protected NodeList nodeFilter(Parser page, String steps)
	{
		NodeList nodes = new NodeList(); 
        try 
        {
			nodes = page.parse(attribute(steps));
		} 
        catch (ParserException e)
        {
			e.printStackTrace();
		}

		return nodes;
	}
	
	private NodeFilter attribute(String steps) 
	{
		NodeFilter attribute = null;
		if (steps.equals("GlobalRank"))
		{
			attribute = new AndFilter(new TagNameFilter("strong"), 
					new HasAttributeFilter("class","metrics-data align-vmiddle"));
		}
	    else
	    {
	    	attribute= new AndFilter(new TagNameFilter("a"), 
	        		new HasAttributeFilter("href"));
	    }
		return attribute;
	}
}
