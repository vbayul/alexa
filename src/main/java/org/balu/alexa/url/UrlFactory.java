package org.balu.alexa.url;

public class UrlFactory {
	
	private ConstrycrotURL pageURL;
	
	public ConstrycrotURL countryPageURL(String country){		
		
		if (country.equals(""))
			pageURL = new TopSiteURL();
		else
			pageURL = new CountryURL();
		
		return pageURL;
	}

}
