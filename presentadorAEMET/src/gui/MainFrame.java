package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import commons.LectorAEMET;
import entities.Day;
import entities.Week;

public class MainFrame extends JFrame{

	private JPanel leftPanel;
	private JPanel rightPanel;
	
	private JButton refreshBtn;
	
	private JList<Day> lista;
	
	public MainFrame() {
		super("DATOS AEMET");
		setSize(400,350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setResizable(false)
		
		//initialize components
		leftPanel = new JPanel();
			leftPanel.setSize(190, 350);
		rightPanel = new JPanel();
			rightPanel.setSize(190,350);
		refreshBtn = new JButton("Actualizar");
		lista = new JList<>();
			lista.setSize(200, 300);
			lista.setMinimumSize(new Dimension(250, 300));
		
		
		//layouts
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//agrego paneles
		add(leftPanel);
			leftPanel.add(refreshBtn);
		add(rightPanel);
			
			
		//metodos de carga;
			try {
				cargarLista(cargarSemana());
				rightPanel.add(lista);
			} catch (SAXException | IOException | ParserConfigurationException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	private void cargarLista (Week week) {
		ArrayList<Day> listaArray = week.getWeekArray();
		
		lista = new JList(listaArray.toArray());
		
	}
	private Week cargarSemana() throws MalformedURLException, SAXException, IOException, ParserConfigurationException, ParseException {
		LectorAEMET lector = LectorAEMET.getInstance();
		lector.loadDayList();
		return lector.getWeek();
	}

}
