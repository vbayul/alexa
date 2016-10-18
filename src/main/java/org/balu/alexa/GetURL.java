package org.balu.alexa;

public class GetURL {

	public String getPageURl(int i, String countryCode)
	{
		String URL ="";
		String page = ""; 
		if (i > 0) 
			page=";"+i;
		
		if (countryCode.equals(""))		
		{
			if (i>0)
				{URL ="http://www.alexa.com/topsites/global"+page;
			System.out.println(URL);}
			
			else
				URL ="http://www.alexa.com/topsites/";
		}
		else
			URL ="http://www.alexa.com/topsites/countries"+page+"/"+countryCode;	
		return URL;
	}
}
