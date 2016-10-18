package org.balu.alexa;

public class ParsInputParam {

	public int getCountSite(String[] inputParamArr, int defCount)
	{
		int siteCount = defCount;
		// проверка указан ли параметры формата и количества сайтов
		for (int i = 0; i < inputParamArr.length; i++) 
		{
			if (inputParamArr[i].equals("-count"))
				siteCount = Integer.parseInt(inputParamArr[i+1]);
		}
		return	siteCount;
	}
	
	public String getFormat(String[] inputParamArr, String defFormat)
	{
		String fileFormat = defFormat;
		for (int i = 0; i < inputParamArr.length; i++) {
			if (inputParamArr[i].equals("-format"))
				fileFormat = inputParamArr[i+1];
		}
		return fileFormat;
	}
	
	public String getCountry(String[] inputParamArr, String defCountryCode)
	{
		String countryCode = defCountryCode;
		// проверка внесена ли страна в строку параметров
		if (inputParamArr[inputParamArr.length-1].length()==2 && inputParamArr[inputParamArr.length-1].matches("^[A-Z][A-Z]"))
			countryCode = inputParamArr[inputParamArr.length-1];
		return countryCode;
	}
	
	public int getPageCount(int siteCount, int DefContSiteOnPage)
	{
		int pageCont;
		// вычисляем какое количество страниц необходимо распарсить
		if (siteCount%DefContSiteOnPage==0)
		{
			pageCont = siteCount/DefContSiteOnPage;
		}
		else
		{
			pageCont = siteCount/DefContSiteOnPage+1;
		}
		return pageCont;
	}
	
}
