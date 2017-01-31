package org.balu.alexa.parser;

import java.util.ArrayList;
import java.util.List;

import org.balu.alexa.model.Site;
import org.balu.alexa.rank.CountRank;
import org.balu.alexa.rank.UrlRank;
import org.balu.alexa.rank.GlobalRank;
import org.htmlparser.Parser;

public class ParserPage {

	public List<Site> getParserList(Parser page)
	{
		List<Site> sites = new ArrayList<Site>();
		CountRank countRank = new CountRank(); 
		UrlRank countryRank = new UrlRank();
		GlobalRank globalRank = new GlobalRank();
		
		sites = countRank.getCounts(page, sites);
		page.reset();
		sites = countryRank.getCountryRankStatisticURL(page, sites);
		page.reset();
		sites = globalRank.getSiteGlobalRank(sites);

		return sites;
	}
	
}