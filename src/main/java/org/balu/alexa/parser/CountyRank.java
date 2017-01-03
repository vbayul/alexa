package org.balu.alexa.parser;

import java.util.List;

import org.balu.alexa.object.Site;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class CountyRank implements Rank {
	
	public List<Site> getCountryRankStatisticURL(Parser page, List<Site> sites)
	{		

		NodeList nodes = nodeFilter(page, "CountryRank");
		
		int index = 1;
		for(int i=0; i<nodes.size(); i++) 
		{
			Node node = nodes.elementAt(i);
		
			if (checkNode(node))
			{
				String statURL = node.getText().substring(8, node.getText().length()-1);

				Site site = new Site();
				site.setCountrRank(index);
				site.setStatURL("http://alexa.com"+statURL);
				site.setURL("http://"+node.toPlainTextString());

				sites.add(site);
			    
				index = index+1;
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
	public NodeList nodeFilter(Parser page, String steps) {
		
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
