package org.balu.alexa.model;

public class Site{

	private int countryRank;
	private String URL;
	private int globalRank;
	private String statURL;
	
	public int getCountryRank() {
		return countryRank;
	}
	public void setCountryRank(int countryRank) {
		this.countryRank = countryRank;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public int getGlobalRank() {
		return globalRank;
	}
	public void setGlobalRank(int globalRank) {
		this.globalRank = globalRank;
	}
	public String getStatURL() {
		return statURL;
	}
	public void setStatURL(String statURL) {
		this.statURL = statURL;
	}
	@Override
	public String toString() {
		return "countryRank = "+countryRank+", URL = " + URL + ", globalRank = "+ globalRank
				+", statURL = "+statURL;
	}
		
}
