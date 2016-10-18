package org.balu.alexa.save;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.balu.alexa.object.Site;

public class SaveToFileCSV implements SaveToFile{

	@Override
	public void saveToFile(List<Site> listSite, int count) {
		createCSV();
		saveToCSV(listSite, count);
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
	
	private void saveToCSV(List<Site> listSite, int count)
	{
		try(FileWriter writer = new FileWriter("result.csv", false))
		{
			for (int i = 0; i <listSite.size(); i++) {
				Site site = listSite.get(i);
				if (Integer.parseInt(site.getCountrRank()) <= count)
				{
					writer.write(site.getCountrRank()+";"+site.getURL()+";"
							+site.getGlobalRank()+";"+site.getStatURL()+System.getProperty("line.separator"));
				}
			}
		}
		catch (IOException e) {
		}		
	}

}
