package org.balu.alexa;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Begin {

	public static void getCountryRank(Parser parser, int page)
	{

		try {
			/*
			String url = "";
			if (page > 0) 
				url=";"+page;	*/
	        parser.setEncoding("utf-8");
	        
	        NodeFilter atrb1 = new AndFilter(new TagNameFilter("div"), 
	        	    new HasAttributeFilter("class", "count"));
	        //System.out.println(pa1.extractAllNodesThatMatch(atrb1));
	        NodeList nodeList = parser.parse(atrb1);

	        for(int i=0; i<nodeList.size(); i++) {
	            Node node = nodeList.elementAt(i);
	            System.out.println(/*i +" "+ node.toHtml()+" - "+*/ node.toPlainTextString());
	        }
	        
	        NodeFilter atrb2 = new AndFilter(new TagNameFilter("a"), 
	        	    new HasAttributeFilter("href"));
	        //System.out.println(pa2.extractAllNodesThatMatch(atrb2));
	        parser.reset();
	        NodeList nodeList1 = parser.parse(atrb2);

	        //System.out.println(nodeList1.size());
	        for(int i=0; i<nodeList1.size(); i++) {
	            Node node1 = nodeList1.elementAt(i);
	            if (node1.toHtml().contains("/siteinfo/") == true 
	            		&& node1.toHtml().contains("Site Metrics") == false
	            		&& node1.toHtml().contains("Site Overviews") == false)
	            {
	            	System.out.println(/*i +" " + node1.toHtml() +" - "+ */node1.getText().substring(8, node1.getText().length()-1)+" - "+ node1.toPlainTextString());
	            	linkStat.add(node1.getText().substring(8, node1.getText().length()-1));
	            }
	        }


	    } catch (ParserException e) {
	        e.printStackTrace();
	    }
	}
	
	private static List<String> linkStat = new ArrayList<String>();	
	
	public static void getLinkName(int page)
	{

		try
		{
			String url = "";
			if (page > 0) 
				url=";"+page;
			
	        Parser parser1 = new Parser("http://www.alexa.com/topsites/countries"+url+"/UA");
	        parser1.setEncoding("utf-8");
	        NodeFilter atrb2 = new AndFilter(new TagNameFilter("a"), 
	        	    new HasAttributeFilter("href"));
	        NodeList nodeList1 = parser1.parse(atrb2);
	        
	        for(int i=0; i<nodeList1.size(); i++) {
	            Node node1 = nodeList1.elementAt(i);
	            if (node1.toHtml().contains("/siteinfo/") == true 
	            		&& node1.toHtml().contains("Site Metrics") == false
	            		&& node1.toHtml().contains("Site Overviews") == false)
	            {
	            	System.out.println(i +" " + node1.toHtml() +" - "+ node1.getText().substring(8, node1.getText().length()-1)+" - "+ node1.toPlainTextString());
	            	linkStat.add(node1.getText().substring(8, node1.getText().length()-1));
	            }
	        }
	    } catch (ParserException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void getStatByLink()
	{
		for (int i = 0; i < linkStat.size(); i++) {
			getGlobalRank(linkStat.get(i));
		}
	}
	
	public static void main(String[] args) throws ParserException {
		int count = 25;
		int pageCont = 0;
		
		if (count%25==0)
		{
			pageCont = count/25;
		}
		else
		{
			pageCont = count/25+1;
		}
		String url = "";		
		// перенести в метод и передавать ему лишь нужное количество
		for (int i = 0; i < pageCont; i++) {

			if (i > 0) 
				url=";"+i;
	        Parser parser = new Parser("http://www.alexa.com/topsites/countries"+url+"/UA");
			getCountryRank(parser,i);
		}
		
		//getStatByLink();
	}
	
	
	public static void getGlobalRank(String url)
	{

		try
		{
			
	        Parser parser1 = new Parser("http://www.alexa.com"+url);
	        parser1.setEncoding("utf-8");
	        NodeFilter atrb2 = new AndFilter(new TagNameFilter("strong"), 
	        	    new HasAttributeFilter("class","metrics-data align-vmiddle"));
	        NodeList nodeList1 = parser1.parse(atrb2);
	        
	        for(int i=0; i<nodeList1.size(); i++) {
	            Node node1 = nodeList1.elementAt(i);
	            /*if (node1.toHtml().contains("/siteinfo/") == true 
	            		&& node1.toHtml().contains("Site Metrics") == false
	            		&& node1.toHtml().contains("Site Overviews") == false)
	            {*/
	            if (node1.getPreviousSibling().toHtml().contains("title='Global rank icon'"))
	            {
	            	System.out.println(i +" "/* + node1.toHtml() /*+" - "+ node1.getText()
	            	/*.substring(8, node1.getText().length()-1)*/+" - "+ node1.getNextSibling().getNextSibling().getNextSibling().getText());
	            }
	        }
	    } catch (ParserException e) {
	        e.printStackTrace();
	    }
	}

}
