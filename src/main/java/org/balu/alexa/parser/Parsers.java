package org.balu.alexa.parser;

import java.util.ArrayList;
import java.util.List;

import org.balu.alexa.model.Parameter;
import org.balu.alexa.model.Site;
import org.balu.alexa.url.ConstrycrotURL;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class Parsers {
	
	public List<Site> parsingPages(ConstrycrotURL pageURL, Parameter param){
		
		ParserPage parserPage = new ParserPage();
		List<Site> sites = new ArrayList<Site>();
		
		for (int i = 0; i < param.getPageCount(); i++) 
		{	
			String URL = pageURL.getURL(i, param.getCountryCode());			
			Parser page = getPage(URL);
			
			List<Site> tempSites = parserPage.getParserList(page);
	        sites.addAll(tempSites);
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

}
