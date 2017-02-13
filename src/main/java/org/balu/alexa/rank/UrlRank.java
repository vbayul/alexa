package org.balu.alexa.rank;

import java.util.List;

import org.balu.alexa.model.Site;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public class UrlRank {
	
	public List<Site> getCountryRankStatisticURL(Parser page, List<Site> sites)
	{		
		Filter filter = new Filter();
		NodeList nodes = filter.nodeFilter(page, "url");
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
	
}
