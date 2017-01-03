package org.balu.url;

import org.apache.commons.lang3.StringUtils;

public class TopSiteURL extends ConstrycrotURLImp{

	@Override
	public String getURL(int pageCount, String countryCode) {
		
		String URL = "http://www.alexa.com/topsites/";

		if (pageCount > 0)
			URL =  StringUtils.join(URL,"global",getPageNumber(pageCount));

		return URL;
	}
}
