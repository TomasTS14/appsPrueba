
package commons;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import entities.Day;
import entities.Week;

public class LectorAEMET {
	private static Week week;
	private static ArrayList<Day> dayList;
	private static LectorAEMET instance;
	
	private LectorAEMET() {};
	
	
	public static LectorAEMET getInstance() {
		if (instance == null) instance = new LectorAEMET();
		else { return instance; }

		return instance;
	}
	public static Week getWeek() {
		return week;
	}
	public static ArrayList<Day> getArrayList(){
		return dayList;
	}
	public static  void loadDayList() throws MalformedURLException, SAXException, IOException, ParserConfigurationException, ParseException {
		dayList = new ArrayList<>();
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse (new URL("http://www.aemet.es/xml/municipios/localidad_22125.xml").openStream());
		ArrayList<String> rangos;
		NodeList daysList = doc.getElementsByTagName("dia");
		
		for (int i = 0 ; i < daysList.getLength(); i++) {
			Element dayElement =(Element) daysList.item(i);
			//FORMATEO DE FECHA
			String dateString = daysList.item(i).getAttributes().item(0).getNodeValue(); //parte del xml que contiene la fecha
			Date date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
			//// trayendo rangos:
			rangos = new ArrayList<>();
			if (dayElement.hasChildNodes()) {
				NodeList listaRangos =  dayElement.getElementsByTagName("prob_precipitacion");
				
				
				
				for (int j = 0; j<listaRangos.getLength(); j++) {
					int probabilidad;
					String range="";
					
					if (j == 0) {
						range = "Todo el dia";
					}
					else {
						if (listaRangos.item(j).hasAttributes()) {
							range = listaRangos.item(j).getAttributes().item(0).getNodeValue();
							
						}else {
							range = "Todo el d??a";
						}
					}
				

					if(listaRangos.item(j).getTextContent() != "") {
						 probabilidad = Integer.parseInt(listaRangos.item(j).getTextContent());
					}else {
						probabilidad = 0;
					}
					String rangeWithData = range + ": " + probabilidad;
					rangos.add(rangeWithData);
					
				}
			}
					
			dayList.add(new Day(date, rangos));
		}
		week = new Week(dayList);
		

	}
	

}
