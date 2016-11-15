package org.balu.alexa;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class ParserFactory {
	
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
