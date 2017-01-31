package org.balu.alexa.model;

public class Parameter {
	
	private int SiteCount ;
	private String CountryCode ;
	private String FileFormat ;
	private int PageCount;
	
	public int getSiteCount() {
		return SiteCount;
	}
	public void setSiteCount(int siteCount) {
		SiteCount = siteCount;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getFileFormat() {
		return FileFormat;
	}
	public void setFileFormat(String fileFormat) {
		FileFormat = fileFormat;
	}
	public int getPageCount() {
		return PageCount;
	}
	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}
}
