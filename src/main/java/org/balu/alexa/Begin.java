package org.balu.alexa;

import java.util.ArrayList;
import java.util.List;

import org.balu.alexa.object.Site;
import org.balu.alexa.parser.ParserPage;
import org.balu.alexa.save.SaveToFile;
import org.balu.alexa.save.SaveToFileCSV;
import org.balu.alexa.save.SaveToFileHTML;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class Begin {

	private static int DefSiteCount = 25;
	private static String DefCountryCode = "";
	private static String DefFileFormat ="html";
	private static int DefContSiteOnPage = 25;
	
	public static void main(String[] args) throws ParserException 
	{

		// парсим входные параметры
		ParsInputParam parsInputParam = new ParsInputParam();
		
		int siteCount = parsInputParam.getCountSite(args, DefSiteCount);
		
		int pageCont = parsInputParam.getPageCount(siteCount, DefContSiteOnPage);
		
		String countryCode =  parsInputParam.getCountry(args, DefCountryCode);
		
		String fileFormat = parsInputParam.getFormat(args, DefFileFormat);
		
		
		// объявляем объект для вычисления нужного URL
		GetURL getURL = new GetURL();

		ParserPage parserPage = new ParserPage();


		
		// массив для хранения объектов класса Сайт, которые содержат данные осайте. 
		List<Site> listSite = new ArrayList<Site>();
		
		// перенести в метод и передавать ему лишь нужное количество
		for (int i = 0; i < pageCont; i++) {	
			String URLPage = getURL.getPageURl(i, countryCode);
	        Parser Page = new Parser(URLPage);
	        listSite.addAll(parserPage.getParserList(Page, pageCont));
		}
		
		// Блок сохранения данных в файл
		SaveToFile saveToFile;

		// подготовка нужного типа файла
		if (fileFormat.equals("html"))
		{
			saveToFile = new SaveToFileHTML();
		}
		else
		{
			saveToFile = new SaveToFileCSV();
		}
		
		saveToFile.saveToFile(listSite, siteCount);
		
	}
}
