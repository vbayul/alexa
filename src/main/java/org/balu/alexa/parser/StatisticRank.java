package org.balu.alexa.parser;

import java.util.List;

import org.balu.alexa.GetParserObject;
import org.balu.alexa.object.Site;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class StatisticRank implements Rank{

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

	@Override
	public NodeList nodeFilter(Parser page, String steps) {
		
		NodeList nodes = new NodeList(); 
		NodeFilter attribute = new AndFilter(new TagNameFilter("strong"), 
				new HasAttributeFilter("class","metrics-data align-vmiddle"));
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
