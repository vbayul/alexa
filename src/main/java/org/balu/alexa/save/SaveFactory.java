package org.balu.alexa.save;

public class SaveFactory {
	
	private SaveToFile saveToFile;
	
	public SaveToFile saveFileFactory(String formatFile){
		
		if (formatFile.equals("html"))
		{
			saveToFile = new SaveToFileHTML();
		}
		else
		{
			saveToFile = new SaveToFileCSV();
		}
		
		return saveToFile;
	}

}
