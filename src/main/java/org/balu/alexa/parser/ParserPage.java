package org.balu.alexa.parser;

import java.util.ArrayList;
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

public class ParserPage {

	public List<Site> getParserList(Parser page)
	{
		List<Site> sites = new ArrayList<Site>();
		
		sites = getCountryRankStatisticURL(page, sites);
		sites = getSiteGlobalRank(sites);

		return sites;
	}

	private List<Site> getCountryRankStatisticURL(Parser page, List<Site> sites)
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
	
	private List<Site> getSiteGlobalRank(List<Site> sites)
	{
		GetParserObject parserFactory = new GetParserObject();
		
		for (int i = 0; i < sites.size(); i++) {
			Site site = sites.get(i);
			String URL = site.getStatURL();
			
			Parser pageGlobalRank = parserFactory.getPage(URL);
			site = ParsGlobalRank(pageGlobalRank, site);
			
			sites.set(i, site);
		}
		return sites;
	}
	
	private Site ParsGlobalRank(Parser page, Site site)
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
	
	private NodeList nodeFilter(Parser page, String step)
	{
		NodeList nodes = null; 
		NodeFilter atrb = null;
		
		if (step.equals("GlobalRank"))
		{
			atrb = new AndFilter(new TagNameFilter("strong"), 
					new HasAttributeFilter("class","metrics-data align-vmiddle"));
		}
	    else
	    {
	        atrb= new AndFilter(new TagNameFilter("a"), 
	        		new HasAttributeFilter("href"));
	    }
			
        try 
        {
			nodes = page.parse(atrb);
		} 
        catch (ParserException e)
        {
			e.printStackTrace();
		}

		return nodes;
	}
}