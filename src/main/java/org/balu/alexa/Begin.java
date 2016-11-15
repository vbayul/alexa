package org.balu.alexa;

import java.util.ArrayList;
import java.util.List;

import org.balu.alexa.object.Parameter;
import org.balu.alexa.object.Site;
import org.balu.alexa.parser.ParserPage;
import org.balu.alexa.save.SaveToFile;
import org.balu.alexa.save.SaveToFileCSV;
import org.balu.alexa.save.SaveToFileHTML;
import org.htmlparser.Parser;

public class Begin {

	public static void main(String[] args)
	{
		List<Site> sites = new ArrayList<Site>();

		SaveToFile saveToFile;
		ConstryctorURL pageURL = new ConstryctorURL();
		ParserPage parserPage = new ParserPage();
		ParserFactory parserFactory = new ParserFactory();
		ParsInputParam parsInputParam = new ParsInputParam(args);
		
		Parameter param = parsInputParam.getParam();

		for (int i = 0; i < param.getPageCount(); i++) 
		{	
			String URL= pageURL.getURL(i, param.getCountryCode());
			Parser page = parserFactory.getPage(URL);
			
			List<Site> tempSites = parserPage.getParserList(page);
	        sites.addAll(tempSites);
		}

		if (param.getFileFormat().equals("html"))
		{
			saveToFile = new SaveToFileHTML();
		}
		else
		{
			saveToFile = new SaveToFileCSV();
		}
		
		saveToFile.saveToFile(sites, param.getSiteCount());
		
	}
}
