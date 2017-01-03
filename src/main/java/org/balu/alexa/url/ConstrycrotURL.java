package org.balu.alexa.url;

import org.apache.commons.lang3.StringUtils;

public abstract class ConstrycrotURL {
	
	public abstract String getURL(int pageCount, String countryCode);
	
	@SuppressWarnings("unchecked")
	public String getPageNumber(int pageCount)
	{
		if (pageCount > 0) 
			return StringUtils.join(";",pageCount);
		else
			return "";
	}

}
