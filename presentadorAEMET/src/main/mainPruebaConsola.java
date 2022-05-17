package main;

import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import commons.LectorAEMET;
import entities.Week;

public class mainPruebaConsola {

	public static void main(String[] args) {
		
		try {
			LectorAEMET lector = LectorAEMET.getInstance();
					lector.loadDayList();
			Week week = lector.getWeek();
			System.out.println(week);
		} catch (SAXException | IOException | ParserConfigurationException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
