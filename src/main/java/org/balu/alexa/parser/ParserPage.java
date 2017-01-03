package org.balu.alexa.parser;

import java.util.ArrayList;
import java.util.List;
import org.balu.alexa.object.Site;
import org.htmlparser.Parser;

public class ParserPage {

	public List<Site> getParserList(Parser page)
	{
		List<Site> sites = new ArrayList<Site>();
		CountyRank countryRank = new CountyRank();
		StatisticRank statisticRank = new StatisticRank();
		
		sites = countryRank.getCountryRankStatisticURL(page, sites);
		sites = statisticRank.getSiteGlobalRank(sites);

		return sites;
	}


}