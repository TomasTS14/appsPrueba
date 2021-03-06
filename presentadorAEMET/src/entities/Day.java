package entities;

import java.util.ArrayList;
import java.util.Date;

public class Day {
	
	private Date date;
	private ArrayList<String> ranges; 
	
	public Day(Date date, ArrayList<String> ranges) {
		this.date = date; this.ranges = ranges;
	}
	
	public Day(Date date) {
		this.date = date;
	}
	public void setRange(ArrayList<String> range) {
		this.ranges = ranges;
	}
	public Date getDate() {
		return date;
	}
	public ArrayList<String> getRanges() {
		return ranges;
	}
	public String getRange(int i) {
		return ranges.get(i);
	}
	public String getDateString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append(this.getDate().toString().substring(0, 11)+"\n");
		return strbldr.toString();
	}
	public String wholeToString() {
		StringBuilder strBldr = new StringBuilder();
		strBldr.append(this.getDate().toString().substring(0, 11)+"\n");
		
		for (String range: ranges) {
		strBldr.append(range+"% probabilidad precipitacion"+"\n");
		}
		return strBldr.toString();

	}
	@Override
	
	public String toString() {
		StringBuilder strBldr = new StringBuilder();
		strBldr.append(this.getDate().toString().substring(0, 11)+"\n");
		
		
		return strBldr.toString();
		
	}
	

}
