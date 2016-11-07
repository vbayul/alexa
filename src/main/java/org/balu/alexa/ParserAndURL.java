package org.balu.alexa;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class ParserAndURL {

	public String getURL(int i, String countryCode)
	{
		String URL ="";
		String page = ""; 
		if (i > 0) 
			page=";"+i;
		
		if (countryCode.equals(""))		
		{
			if (i>0)
				URL ="http://www.alexa.com/topsites/global"+page;
			else
				URL ="http://www.alexa.com/topsites/";
		}
		else
			URL ="http://www.alexa.com/topsites/countries"+page+"/"+countryCode;	
		return URL;
	}

	public Parser getPage(String URL)
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
