package org.balu.alexa.save;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.balu.alexa.model.Site;

public class SaveToFileCSV implements SaveToFile{

	@Override
	public void saveToFile(List<Site> Sites, int count) {
		createCSV();
		saveToCSV(Sites, count);
	}
	
	private void createCSV() 
	{
		try(FileWriter writer = new FileWriter("result.csv", false))
		{
			 writer.write("country rank;site URL;globalrank;link to Alexa's"+System.getProperty("line.separator"));
		}
		catch (IOException e) {
		}
	}
	
	private void saveToCSV(List<Site> Sites, int count)
	{
		try(FileWriter writer = new FileWriter("result.csv", false))
		{
			for (int i = 0; i <Sites.size(); i++) {
				Site site = Sites.get(i);
				if (site.getCountryRank() <= count)
				{
					writer.write(site.getCountryRank()+";"+site.getURL()+";"
							+site.getGlobalRank()+";"+site.getStatURL()+System.getProperty("line.separator"));
				}
			}
		}
		catch (IOException e) {
		}		
	}

}
