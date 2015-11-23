package resources;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CustomListeners {
	public static class RemoveTextAdapter extends FocusAdapter{
		String textToPlace;
		JTextField jtf;
		JTextArea jta;
		boolean focusGained = false;
		
		public RemoveTextAdapter(JTextField jtf, String textToPlace) {
			this.jtf = jtf;
			this.textToPlace = textToPlace;
		}
		
		public RemoveTextAdapter(JTextArea jta, String textToPlace) {
			this.jta = jta;
			this.textToPlace = textToPlace;
		}
		
		public void focusGained(FocusEvent e) {
			System.out.println("FocusEvent GAINED");
			if (jtf != null) {
				System.out.println("Not null");
				if (jtf.getText().equals(textToPlace)) {
					jtf.setText(""); 
				}
			}
			if (jta != null) {
				if (jta.getText().equals(textToPlace)) {
					System.out.println("is textToPlace");
					jta.setText("");
					jta.setForeground(Color.BLACK);
				}

			}

		}
		
		public void focusLost(FocusEvent e) {
			System.out.println("FocusEvent LOST");
			if (jtf != null) {
				if (jtf.getText().equals("")) {
					jtf.setText(textToPlace);
				}
			}
			if (jta != null) {
				System.out.println("Not null");
				if (jta.getText().equals("")) {
					System.out.println("jta.getText()" + jta.getText());
					System.out.println("is empty");
					jta.setText(textToPlace);
					jta.setForeground(Color.GRAY);
				}
			}

		}
	}
}
