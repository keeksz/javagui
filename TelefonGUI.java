package Main;

import java.io.*;
import java.util.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

public class TelefonGUI {
	private JFrame frmTelefonknyv;
	private ArrayList<Telefon> telefonok;
	private HashSet<String> varosok;
	private JTextPane textPane;
	
	public TelefonGUI() {
		this.telefonok = new ArrayList<Telefon>();
		this.varosok = new HashSet<String>();

		frmTelefonknyv = new JFrame();
		frmTelefonknyv.setTitle("Telefonkönyv");
		frmTelefonknyv.setBounds(100, 100, 450, 467);
		frmTelefonknyv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTelefonknyv.getContentPane().setLayout(null);
		frmTelefonknyv.setResizable(false);
	
		textPane = new JTextPane();
		textPane.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textPane.setBounds(32, 27, 374, 324);
		frmTelefonknyv.getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("K");
		btnNewButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				beolvas();
			}
		});
		btnNewButton.setBounds(21, 376, 122, 23);
		frmTelefonknyv.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("T-Com");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print(telefonok.size());
				textPane.setText("");
				String txt = "" ;
				for (int i = 0 ; i < telefonok.size() ; i++)
				{
					Telefon temp = telefonok.get(i) ;
					if (temp.getSzam().startsWith("0630"))
					{
						txt = txt + temp + "\n"; 
					}
				}
				textPane.setText(txt) ;
			
				
				try {
					FileWriter wfile = new FileWriter("kenes.txt");
				
					for (Telefon t : telefonok) {
						if (t.getSzam().startsWith("0630")) {
							wfile.write(t.toString() + "\n");
						}
					}
					
					wfile.close();
				}
				catch (Exception e2) {}
			}
		});
		btnNewButton_1.setBounds(150, 376, 89, 23);
		frmTelefonknyv.getContentPane().add(btnNewButton_1);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int index = comboBox.getSelectedIndex() ;
				if (index == 0)
				{
					String txt = "" ;
					for (int i = 0 ; i < telefonok.size() ; i++)
					{
						Telefon temp = telefonok.get(i) ;
						if (temp.getSzam().startsWith("0630"))
						{
							txt = txt + temp.getNev() + "\t" + temp.format() + "\n"  ;
						}
					}
					textPane.setText(txt) ;
				}
				else if (index == 1)
				{
					String txt = "" ;
					for (int i = 0 ; i < telefonok.size() ; i++)
					{
						Telefon temp = telefonok.get(i) ;
						if (temp.getSzam().startsWith("0620"))
						{
							txt = txt + temp.getNev() + "\t" + temp.format() + "\n"  ; 
						}
					}
					textPane.setText(txt) ;					
				}
				else
				{
					String txt = "" ;
					for (int i = 0 ; i < telefonok.size() ; i++)
					{
						Telefon temp = telefonok.get(i) ;
						if (temp.getSzam().startsWith("0670"))
						{
							txt = txt + temp.getNev() + "\t" + temp.format() + "\n"  ;
						}
					}
					textPane.setText(txt) ;
				}
			}
		});
		comboBox.setBounds(249, 376, 157, 22);
		comboBox.addItem("T-COM");
		comboBox.addItem("TELENOR");
		comboBox.addItem("VODAFONE");
		frmTelefonknyv.getContentPane().add(comboBox);
	}
	
	public void beolvas() {
		try {
			FileReader rfile = new FileReader("telefon.txt");
			BufferedReader bf = new BufferedReader(rfile);
			String line = bf.readLine();
			
			do {
				Telefon t = new Telefon(line);
				telefonok.add(t);
				line = bf.readLine();
			} while (line != null);
			
		}
		catch (Exception e) {
			System.out.println("Nem tudom megnyitni a file-t.");
		}
		
		String text = "";
		for (Telefon t : telefonok) {
			text += t.toString() + "\n";
		}
		textPane.setText(text);
		
		/*
		for (Telefon t : telefonok) {
			if (!varosok.contains(t.getVaros())) {
				varosok.add(t.getVaros());
			}
			
			System.out.print(t.getNev() + " - ");
			System.out.println(t.isValid() ? "Valid" : "Not valid");
		}
		System.out.println("");
		
		for (String v : varosok) {
			System.out.println(v);
			
			for (Telefon t : telefonok) {
				if (t.getVaros().equals(v)) {
					System.out.print("\t");
					System.out.println(t);
				}
			}
		}
		
		Collections.sort(telefonok);
		System.out.println("");
		
		for (Telefon t : telefonok) {
			System.out.println(t);
		}
		*/
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelefonGUI window = new TelefonGUI();
					window.frmTelefonknyv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
