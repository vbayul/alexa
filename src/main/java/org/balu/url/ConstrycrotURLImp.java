package org.balu.url;

import org.apache.commons.lang3.StringUtils;

public abstract class ConstrycrotURLImp implements ConstryctorURL {
	
	@SuppressWarnings("unchecked")
	public String getPageNumber(int pageCount)
	{
		if (pageCount > 0) 
			return StringUtils.join(";",pageCount);
		else
			return "";
	}

}
