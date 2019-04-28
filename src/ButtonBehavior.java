
import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ButtonBehavior {

	private JList<String> result;
	private DefaultListModel<String> l;
	
	public void behavior(JButton searchButton, JList<String> result, JTextField input, DefaultListModel<String> l, HashMap<String,HashSet<String>> map,JList<String> auto) {
		this.result = result;
		this.l = l;
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				l.removeAllElements();
				HashSet<String> set = map.get(input.getText());
				if(set!=null) {
					for(String name: set) {
						name = name.replace("text/", "");
						name = name.replaceAll(".txt", "");
						l.addElement(name);
					}
				}
				else {
					l.addElement("no results found");
				}
				result.setVisible(true);
				auto.setVisible(false);
			}
		});
		result.addMouseListener(new MouseListener() {
			//open file in new window
			@Override
			public void mouseClicked(MouseEvent e) {
				if(result.getSelectedIndex()!=-1) {
					JFrame newFrame = new JFrame(result.getSelectedValue());
					newFrame.setSize(800, 800);
					newFrame.setLayout(new CardLayout(30, 30));
					JTextArea area = new JTextArea();
					area.setLineWrap(true);
					JScrollPane scroll = new JScrollPane(area);
					scroll.setSize(300, 300);
					newFrame.add(scroll);
					File file = new File("text/"+result.getSelectedValue()+".txt");
					Scanner sc = null;
					try {
						sc = new Scanner(file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					while(sc.hasNextLine()) {
						area.append(sc.nextLine());
						area.append("\n");
					}
					sc.close();
					newFrame.setVisible(true);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
	}
	
}