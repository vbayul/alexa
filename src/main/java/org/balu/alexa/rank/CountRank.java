package org.balu.alexa.rank;

import java.util.List;

import org.balu.alexa.model.Site;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public class CountRank{
	
	public List<Site> getCounts(Parser page, List<Site> sites){
		
		Filter filter = new Filter();
		NodeList nodes = filter.nodeFilter(page,"count");

		for(int i=0; i<nodes.size(); i++) 
	        {
	            Node node = nodes.elementAt(i);
	            
	            Site site = new Site();
	            site.setCountryRank(Integer.valueOf(node.toPlainTextString()));
	            
	            sites.add(site);
	        }
		return sites;
	}
}
