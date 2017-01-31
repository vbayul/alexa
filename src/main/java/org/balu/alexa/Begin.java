package org.balu.alexa;

import java.util.List;

import org.balu.alexa.model.Parameter;
import org.balu.alexa.model.Site;
import org.balu.alexa.parser.Parsers;
import org.balu.alexa.save.SaveFactory;
import org.balu.alexa.save.SaveToFile;
import org.balu.alexa.url.ConstrycrotURL;
import org.balu.alexa.url.UrlFactory;

public class Begin {

	public static void main(String[] args){
		
		SaveToFile saveToFile;
		ConstrycrotURL pageURL;
		ParsInputParam parsInputParam = new ParsInputParam(args);
		Parsers parser = new Parsers(); 
		SaveFactory saveFactory = new SaveFactory();
		UrlFactory urlFactory = new UrlFactory();
		
		Parameter param = parsInputParam.getParam();
		pageURL = urlFactory.countryPageURL(param.getCountryCode());
		
		List<Site> sites = parser.parsingPages(pageURL, param);
		
		saveToFile = saveFactory.saveFileFactory(param.getFileFormat());
		saveToFile.saveToFile(sites, param.getSiteCount());
		
	}
}
