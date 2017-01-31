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

public class CountRank implements Rank {
	
	public List<Site> getCounts(Parser page, List<Site> sites){
		
		NodeList nodes = nodeFilter(page);

		for(int i=0; i<nodes.size(); i++) 
	        {
	            Node node = nodes.elementAt(i);
	            
	            Site site = new Site();
	            site.setCountryRank(Integer.valueOf(node.toPlainTextString()));
	            
	            sites.add(site);
	        }
		return sites;
	}

	@Override
	public NodeList nodeFilter(Parser page) {

		NodeList nodes = new NodeList(); 
		NodeFilter attribute = new AndFilter(new TagNameFilter("div"), 
				new HasAttributeFilter("class", "count"));
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
