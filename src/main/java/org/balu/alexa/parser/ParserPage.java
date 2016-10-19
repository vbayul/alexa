package org.balu.alexa.parser;

import java.util.ArrayList;
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

public class ParserPage {

	public List<Site> getParserList(Parser Page, int page)
	{
		List<Site> Sites = new ArrayList<Site>();
		
		Sites = getSiteCountryRank(Page);
		
		Page.reset();
		
		Sites = getStatisticURL(Page,Sites);

		for (int i = 0; i < Sites.size(); i++) {
			Site site = Sites.get(i);
			site = getSiteGlobalRank(site);
			Sites.set(i, site);
		}
		
		return Sites;
	}
	
	private List<Site> getStatisticURL(Parser Page, List<Site> listSite)
	{
		try
		{
		// получаем ссылкуна статистику и название сайта
        NodeFilter atrb2 = new AndFilter(new TagNameFilter("a"), 
        	    new HasAttributeFilter("href"));
        NodeList nodeList1 = Page.parse(atrb2);

        int iList = 0;
        for(int i=0; i<nodeList1.size(); i++) 
        {
            Node node = nodeList1.elementAt(i);
            
            if (node.toHtml().contains("/siteinfo/") == true 
            		&& node.toHtml().contains("Site Metrics") == false
            		&& node.toHtml().contains("Site Overviews") == false)
            	{
            	String statURL = node.getText().substring(8, node.getText().length()-1);
            	Site site = listSite.get(iList);
            	
            	site.setStatURL("http://alexa.com"+statURL);
            	site.setURL("http://"+node.toPlainTextString());
            	
            	listSite.set(iList, site);
            	iList = iList+1;
            	}
        	}
		} catch (ParserException e) {
        	e.printStackTrace();
    	}
		return listSite;
	}
	
	private List<Site> getSiteCountryRank(Parser Page)
	{
		List<Site> listSite = new ArrayList<Site>();
		try {
			Page.setEncoding("utf-8");
	        
	        // глобальное значение для страны
	        NodeFilter atrb1 = new AndFilter(new TagNameFilter("div"), 
	        	    new HasAttributeFilter("class", "count"));
	        NodeList nodeList = Page.parse(atrb1);

	        for(int i=0; i<nodeList.size(); i++) 
	        {
	            Node node = nodeList.elementAt(i);
	            
	            Site site = new Site();
	            site.setCountrRank(node.toPlainTextString());
	            
	            listSite.add(site);
	        }
	    } catch (ParserException e) {
	        e.printStackTrace();
	    }
		return listSite;
	}
	
	private Site getSiteGlobalRank(Site site)
	{
		try
		{
	        Parser PageStatistic = new Parser(site.getStatURL());

	        PageStatistic.setEncoding("utf-8");
	        NodeFilter atrb2 = new AndFilter(new TagNameFilter("strong"), 
	        	    new HasAttributeFilter("class","metrics-data align-vmiddle"));
	        NodeList nodeList = PageStatistic.parse(atrb2);
	        
	        for(int i=0; i<nodeList.size(); i++) {
	            Node node = nodeList.elementAt(i);

	            if (node.getPreviousSibling().toHtml().contains("title='Global rank icon'"))
	            {
	            	String rank = node.getNextSibling().getNextSibling().getNextSibling().getText().replaceAll(",", "");
	            	site.setGlobalRank(rank);
	            }
	        }
	    } catch (ParserException e) {
	        e.printStackTrace();
	        site.setGlobalRank("test");
	    }
		
		return site;
	}
}
