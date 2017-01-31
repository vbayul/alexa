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

public class GlobalRank implements Rank{

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
		NodeList nodes = nodeFilter(page);
		
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

	@Override
	public NodeList nodeFilter(Parser page) {
		
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
