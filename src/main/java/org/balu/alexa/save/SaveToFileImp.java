package org.balu.alexa.save;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.balu.alexa.object.Site;

public class SaveToFileImp implements SaveToFile {

	public void createCSV() {
		try(FileWriter writer = new FileWriter("result.csv", false))
		{
			 writer.write("country rank;site URL;globalrank;link to Alexa's"+System.getProperty("line.separator"));
		}
		catch (IOException e) {
		}
	}
	public void saveToCSV(List<Site> listSite, int count) {
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

	public void createHTML() {
		try(FileWriter writer = new FileWriter("result.html", false))
		{
			
			 writer.write("<!DOCTYPE html><html><head></head><body><table border=1>"
					 +"<tr><td>country rank</td><td>site URL</td><td>globalrank</td><td>link to Alexa's</td></tr>"
					 +System.getProperty("line.separator"));
		}
		catch (IOException e) {
		}
	}

	public void saveToHTML(List<Site> listSite, int count) {
	
		try(FileWriter writer = new FileWriter("result.html", true))
		{
			for (int i = 0; i < listSite.size(); i++) {
				Site site = listSite.get(i);
				if (Integer.parseInt(site.getCountrRank()) <= count)
				{
					writer.write("<tr><td>"+site.getCountrRank()+"</td><td>"+site.getURL()+"</td>"
			 		+ "<td>"+site.getGlobalRank()+"</td><td>"+site.getStatURL()+"</td></tr>"
			 		+System.getProperty("line.separator"));
				}
			}
		}
		catch (IOException e) {
		}
	}

	public void closeHTML() {
		try(FileWriter writer = new FileWriter("result.html", true))
		{
			 writer.write("</table></body></html>");
		}
		catch (IOException e) {
		}
	}


}
