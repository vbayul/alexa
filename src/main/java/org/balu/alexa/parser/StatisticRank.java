package org.balu.alexa.parser;

import java.util.List;

import org.balu.alexa.GetParserObject;
import org.balu.alexa.object.Site;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public class StatisticRank extends Rank{

	public List<Site> getSiteGlobalRank(List<Site> sites)
	{
		GetParserObject parserFactory = new GetParserObject();
		
		for (int i = 0; i < sites.size(); i++) {
			Site site = sites.get(i);
			String URL = site.getStatURL();
			
			Parser pageGlobalRank = parserFactory.getPage(URL);
			site = parsGlobalRank(pageGlobalRank, site);
			
			sites.set(i, site);
		}
		return sites;
	}
	
	private Site parsGlobalRank(Parser page, Site site)
	{	
		NodeList nodes = nodeFilter(page, "GlobalRank");
		
		for(int i=0; i<nodes.size(); i++) 
		{
			Node node = nodes.elementAt(i);

		    if (node.getPreviousSibling().toHtml().contains("title='Global rank icon'"))
		    {
		    	String rank = node.getNextSibling().getNextSibling().getNextSibling().getText().replaceAll(",", "");
		        site.setGlobalRank(rank);
		    }
		}

		return site;
	}
}
