
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TypeBehavior {

	private JTextField input;
	private JList<String> result;
	private DefaultListModel<String> l;
	private PrefixTree tree;
	
	public void behavior(JTextField input, JList<String> result, DefaultListModel<String> l, PrefixTree tree) {
		this.input = input;
		this.result = result;
		this.l = l;
		this.tree = tree;
		input.addKeyListener(new CustomKeyListener());
		result.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(result.getSelectedIndex()!=-1) {
					input.setText(result.getSelectedValue());
					l.removeAllElements();
			        ArrayList<String> res = tree.prefixSearch(input.getText());
			  		for(String tag : res ) {
			  			l.addElement(tag);
				  	}
				    result.setVisible(true); 
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

	class CustomKeyListener implements KeyListener{
	      public void keyTyped(KeyEvent e) {
	    	  //statusLabel.setText("Entered text: " + textField.getText());
	      }
	      public void keyPressed(KeyEvent e) {
	         //if(e.getKeyCode() == KeyEvent.VK_ENTER){
	         //   statusLabel.setText("Entered text: " + textField.getText());
	         //}
	      }
	      public void keyReleased(KeyEvent e) {
	    	  l.removeAllElements();
	    	  ArrayList<String> res = null;
	    	  res = tree.prefixSearch(input.getText());
	  		  if(input.getText().length()!=0&&res!=null) {
	  			  res = tree.prefixSearch(input.getText());
	  			  for(String tag : res ) {
	  				  l.addElement(tag);
		  		  }
		  		  result.setVisible(true); 
	  		  }
	  		  else {
	  			  result.setVisible(false);
	  		  }
	      }
	}  
}
