package org.balu.alexa.rank;

import java.util.List;

import org.balu.alexa.model.Site;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class UrlRank implements Rank {
	
	public List<Site> getCountryRankStatisticURL(Parser page, List<Site> sites)
	{		
		NodeList nodes = nodeFilter(page);
		int index = 0;
		
		for(int i = 0; i<nodes.size(); i++) 
		{
			Node node = nodes.elementAt(i);
		
			if (checkNode(node))
			{
				String statURL = node.getText().substring(8, node.getText().length()-1);

				Site site = sites.get(index);
				
				site.setStatURL("http://alexa.com"+statURL);
				site.setURL("http://"+node.toPlainTextString());

				sites.set(index, site);
				index++;
			}
		}
		return sites;
	}
	
	private boolean checkNode(Node node)
	{
		if (!node.toHtml().contains("/siteinfo/"))
			return false;
		if (node.toHtml().contains("Site Metrics"))
			return false;
		if (node.toHtml().contains("Site Overviews"))
			return false;
		return true;
	}
	
	@Override
	public NodeList nodeFilter(Parser page) {
		
		NodeList nodes = new NodeList();
		NodeFilter attribute = new AndFilter(new TagNameFilter("a"), 
        		new HasAttributeFilter("href"));
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
