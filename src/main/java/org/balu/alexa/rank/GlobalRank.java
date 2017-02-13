package org.balu.alexa.rank;

import java.util.List;

import org.balu.alexa.model.Site;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GlobalRank{

	public List<Site> getSiteGlobalRank(List<Site> sites)
	{
		
		for (int i = 0; i < sites.size(); i++) {
			Site site = sites.get(i);
			Parser pageGlobalRank = getPage(site.getStatURL());
			site = parsGlobalRank(pageGlobalRank, site);
			
			System.out.println(site);
			sites.set(i, site);
		}
		return sites;
	}
	
	private Parser getPage(String URL)
	{
		Parser page = null;
		try 
		{
			page = new Parser(URL);
		} 
		catch (ParserException e) 
		{
			e.printStackTrace();
		}
		return page;
	}
	
	private Site parsGlobalRank(Parser page, Site site)
	{	
		Filter filter = new Filter();
		NodeList nodes = filter.nodeFilter(page,"global");
		
		for(int i = 0; i < nodes.size(); i++) 
		{
			Node node = nodes.elementAt(i);

		    if (node.getPreviousSibling().toHtml().contains("title='Global rank icon'"))
		    {
		    	String rank = node.getNextSibling().getNextSibling().getNextSibling().getText().replaceAll(",", "");
		        site.setGlobalRank(Integer.valueOf(rank.trim()));
		    }
		}

		return site;
	}
}
