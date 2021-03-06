package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class Week {
	
	private ArrayList<Day> week;
	private Iterator<Day> it;
	
	public Week() { week = new ArrayList<>();};
	public Week(ArrayList< Day> week) { this.week = week;}
	
	public ArrayList<Day> getWeekArray(){
		return week;
	}
	public void setWeekArray(ArrayList<Day> week) {
		this.week = week;
	} 
	
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		
		it = week.iterator();
		
		Day dayAux = it.next();
		
		while (it.hasNext()) {
			strbldr.append(dayAux.getDateString());
			dayAux = it.next();
		}
		return strbldr.toString();
		
	}

}
