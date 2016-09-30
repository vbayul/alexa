package org.balu.alexa;

import java.util.ArrayList;
import java.util.List;

import org.balu.alexa.object.Site;
import org.balu.alexa.parser.ParserPage;
import org.balu.alexa.save.SaveToFile;
import org.balu.alexa.save.SaveToFileImp;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class Begin {

	private static int countryCount = 100;
	private static String countryCode = "";
	private static String format ="html";
	private static int pageCont = 0;
	
	public static void main(String[] args) throws ParserException {

		// проверка указан ли параметр
		for (int i = 0; i < args.length; i++) {
			String string = args[i];
			if (string.equals("-count"))
				countryCount = Integer.parseInt(args[i+1]);
			if (string.equals("-format"))
				format = args[i+1];
		}
		
		// проверка внесена ли страна в строку параметров
		if (args[args.length-1].length()==2 && args[args.length-1].matches("^[A-Z][A-Z]"))
			countryCode = args[args.length-1]; 

		// вычисляем какое количество страниц необходимо распарсить
		if (countryCount%25==0)
		{
			pageCont = countryCount/25;
		}
		else
		{
			pageCont = countryCount/25+1;
		}
		
		// объявляем объект для вычисления нужного URL
		GetURL getURL = new GetURL();
		List<Site> listSite = new ArrayList<Site>();
		ParserPage parserPage = new ParserPage();

		SaveToFile saveToFile = new SaveToFileImp();

		// подготовка нужного типа файла
		if (format.equals("html"))
		{
			saveToFile.createHTML();
		}
		else
		{
			saveToFile.createCSV();
		}
		
		// перенести в метод и передавать ему лишь нужное количество
		for (int i = 0; i < pageCont; i++) {
				
			getURL.getPgeURl(i, countryCode);
	        Parser parser = new Parser(getURL.getPgeURl(i, countryCode));
	        listSite = parserPage.getParserList(parser, pageCont);
			if (format.equals("html"))
			{
				saveToFile.saveToHTML(listSite, countryCount);
			}
			else
			{
				saveToFile.saveToCSV(listSite, countryCount);
			}
		}
		
		// закрытие файла, в слаче если хтмл
		if (format.equals("html"))
		{
			saveToFile.closeHTML();
		}
	}
}
