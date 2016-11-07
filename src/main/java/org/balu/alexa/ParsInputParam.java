package org.balu.alexa;

import org.balu.alexa.object.Parameter;

public class ParsInputParam {
	
	private int DefSiteCount = 25;
	private String DefCountryCode = "";
	private String DefFileFormat ="html";
	private int DefCountSiteOnPage = 25;
	
	private Parameter param = new Parameter();  

	public ParsInputParam(String[] args) {
		
		int siteCount =  getSiteCount(args);
		
		param.setSiteCount(siteCount);
		param.setCountryCode(getCountryCode(args));
		param.setFileFormat(getFileFormat(args));
		param.setPageCount(getPageCount(siteCount));
	}
	
	public Parameter getParam() {
		return param;
	}
	
	public int getSiteCount(String[] inputParamArr)
	{
		int siteCount = DefSiteCount;
		
		for (int i = 0; i < inputParamArr.length; i++) 
		{
			if (inputParamArr[i].equals("-count"))
			{
				siteCount = Integer.parseInt(inputParamArr[i+1]);
			}
		}
		
		return	siteCount;
	}
	
	public String getCountryCode(String[] inputParamArr)
	{
		String countryCode = DefCountryCode;

		if (inputParamArr.length != 0 
				&& inputParamArr[inputParamArr.length-1].length()==2)
		{
			if (inputParamArr[inputParamArr.length-1].matches("^[A-Z][A-Z]"))
				countryCode = inputParamArr[inputParamArr.length-1];
		}
		
		return countryCode;
	}
	
	public String getFileFormat(String[] inputParamArr)
	{
		String fileFormat = DefFileFormat;
		
		for (int i = 0; i < inputParamArr.length; i++) {
			if (inputParamArr[i].equals("-format"))
				fileFormat = inputParamArr[i+1];
		}
		
		return fileFormat;
	}
	
	public int getPageCount(int siteCount)
	{
		int pageCont;

		if (siteCount%DefCountSiteOnPage==0)
		{
			pageCont = siteCount/DefCountSiteOnPage;
		}
		else
		{
			pageCont = siteCount/DefCountSiteOnPage+1;
		}
		return pageCont;
	}
}
