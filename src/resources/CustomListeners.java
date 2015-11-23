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
		
		public RemoveTextAdapter(JTextField jtf, String textToPlace) {
			this.jtf = jtf;
			this.textToPlace = textToPlace;
		}
		
		public RemoveTextAdapter(JTextArea jta, String textToPlace) {
			this.jta = jta;
			this.textToPlace = textToPlace;
		}
		
		public void focusGained(FocusEvent e) {
			if (jtf != null) {
				if (jtf.getText().equals(textToPlace))
					jtf.setText(""); 
			}
			if (jta != null) {
				if (jta.getText().equals(textToPlace))
					jta.setText("");
					jta.setForeground(Color.BLACK);

			}

		}
		
		public void focusLost(FocusEvent e) {
			if (jtf != null) {
				if (jtf.getText().isEmpty())
					jtf.setText(textToPlace);
			}
			if (jta != null) {
				if (jta.getText().isEmpty())
					jta.setText(textToPlace);
					jta.setForeground(Color.GRAY);
			}

		}
	}
}
