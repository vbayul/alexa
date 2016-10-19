package org.balu.alexa.save;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.balu.alexa.object.Site;

public class SaveToFileHTML implements SaveToFile{

	@Override
	public void saveToFile(List<Site> Sites, int count)
	{
		createHTML();
		saveToHTML(Sites,count);
		closeHTML();
	}

	private void createHTML() 
	{
		try(FileWriter writer = new FileWriter("result.html", false))
		{
			
			 writer.write("<!DOCTYPE html><html><head></head><body><table border=1>"
					 +"<tr><td>country rank</td><td>site URL</td><td>globalrank</td><td>link to Alexa's</td></tr>"
					 +System.getProperty("line.separator"));
		}
		catch (IOException e) {
		}
	}

	private void saveToHTML(List<Site> Sites, int count) 
	{
	
		try(FileWriter writer = new FileWriter("result.html", true))
		{
			for (int i = 0; i < Sites.size(); i++) {
				Site site = Sites.get(i);
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

	private void closeHTML() 
	{
		try(FileWriter writer = new FileWriter("result.html", true))
		{
			 writer.write("</table></body></html>");
		}
		catch (IOException e) {
		}
	}

}
