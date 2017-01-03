package org.balu.url;

import org.apache.commons.lang3.StringUtils;

public class CountryURL extends ConstrycrotURLImp {

	@Override
	public String getURL(int pageCount, String countryCode) {
		
		String url = StringUtils.join("http://www.alexa.com/topsites/countries", 
				getPageNumber(pageCount), "/", countryCode);

		return url;
	}
}
