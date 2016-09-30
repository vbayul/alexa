package org.balu.alexa.save;

import java.util.List;

import org.balu.alexa.object.Site;

public interface SaveToFile {

	public void createCSV();
	public void saveToCSV(List<Site> site, int count);
	public void createHTML();
	public void saveToHTML(List<Site> site, int count);
	public void closeHTML();
}
