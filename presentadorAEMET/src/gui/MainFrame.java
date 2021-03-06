package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		setSize(670,390);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		
		
		//initialize components
		leftPanel = new JPanel();
			leftPanel.setSize(190, 350);
			leftPanel.setBackground(Color.white);
		rightPanel = new JPanel();
			rightPanel.setSize(190,350);
			rightPanel.setBackground(Color.white);
			btnDataPanel = new JPanel();
			infoDiaTA = new JTextArea(10, 40);
			infoDiaTA.setEditable(false);
			
		lista = new JList<>();
		showDayDataBtn = new JButton("Mostrar datos del dia");
			showDayDataBtn.setBackground(Color.WHITE);
		//metodos de carga;
		try {
			cargarLista(cargarSemana());
		} catch (SAXException | IOException | ParserConfigurationException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			lista.setSize(200, 300);
			lista.setMinimumSize(new Dimension(250, 300));
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane listaPane = new JScrollPane(lista);
			JScrollPane infoDiaPane = new JScrollPane(infoDiaTA);
		
			///BORDES
			
		lista.setBorder(BorderFactory.createTitledBorder("Selecci??n de d??as"));
		infoDiaTA.setBorder(BorderFactory.createTitledBorder("Info d??a"));

			
		//layouts
		setLayout(new FlowLayout(FlowLayout.LEFT));
			LayoutManager rightPanelLayout =  new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
			rightPanel.setLayout(rightPanelLayout);

		btnDataPanel.setLayout(new FlowLayout());
		
		//agrego paneles
		
		add(leftPanel);
			leftPanel.add(showDayDataBtn);
		add(rightPanel);
			rightPanel.add(listaPane);
			rightPanel.add(Box.createVerticalStrut(5));
			rightPanel.add(infoDiaPane);
			
			
			

			
			
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
