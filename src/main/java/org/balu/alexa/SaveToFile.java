package org.balu.alexa;

import java.util.List;

public interface SaveToFile {

	public void saveToCSV(List<Site> site);
	public void saveToHTML(List<Site> site);
}
