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
	
	private int getSiteCount(String[] inputParamArr)
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
	
	private String getCountryCode(String[] inputParams)
	{
		String countryCode = DefCountryCode;

		if (inputParams.length != 0 
				&& inputParams[inputParams.length-1].length()==2)
		{
			if (countryParsing(inputParams))
				countryCode = inputParams[inputParams.length-1];
		}
		
		return countryCode;
	}
	
	private boolean countryParsing(String[] inputParams)
	{
		return inputParams[inputParams.length-1].matches("^[A-Z][A-Z]");
	}
	
	
	
	private String getFileFormat(String[] inputParamArr)
	{
		String fileFormat = DefFileFormat;
		
		for (int i = 0; i < inputParamArr.length; i++) {
			if (inputParamArr[i].equals("-format"))
				fileFormat = inputParamArr[i+1];
		}
		
		return fileFormat;
	}
	
	private int getPageCount(int siteCount)
	{
		int pageCont;

		if (siteCount % DefCountSiteOnPage == 0)
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
