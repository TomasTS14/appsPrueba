package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import commons.LectorAEMET;
import entities.Day;
import entities.Week;

public class MainFrame extends JFrame {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel btnDataPanel;
	
	private JButton showDayDataBtn;
	
	private JList<Day> lista;
	
	private JTextArea infoDiaTA;
	
	public MainFrame() {
		super("DATOS AEMET");
		setSize(320,400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setResizable(false)
		
		//initialize components
		leftPanel = new JPanel();
			leftPanel.setSize(190, 350);
		rightPanel = new JPanel();
			rightPanel.setSize(190,350);
			btnDataPanel = new JPanel();
			infoDiaTA = new JTextArea(10, 40);
			infoDiaTA.setEditable(false);
			
		lista = new JList<>();
		showDayDataBtn = new JButton("Mostrar Datos");
//			lista.setSize(200, 300);
//			lista.setMinimumSize(new Dimension(250, 300));
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

			
		//layouts
		setLayout(new FlowLayout(FlowLayout.LEFT));
		rightPanel.setLayout(new GridLayout(3, 1));
		btnDataPanel.setLayout(new FlowLayout());
		
		//agrego paneles
		
		add(leftPanel);
			leftPanel.add(showDayDataBtn);
		add(rightPanel);
			
			
			
		//metodos de carga;
			try {
				cargarLista(cargarSemana());
				rightPanel.add(lista);
				rightPanel.add(infoDiaTA);
			
			
			} catch (SAXException | IOException | ParserConfigurationException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//actionListeners: 
			
			showDayDataBtn.addActionListener((a)->{
				
				infoDiaTA.setText(lista.getSelectedValue().wholeToString());
			});
			

			 
		
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
