package custom;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.Document;

import library.FontLibrary;
import resources.Constants;

public class QuoteMeTextField extends JTextField {

	public QuoteMeTextField() {
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 12));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
	}

	public QuoteMeTextField(String text) {
		super(text);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 12));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
	}

	public QuoteMeTextField(int columns) {
		super(columns);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 12));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
	}

	public QuoteMeTextField(String text, int columns) {
		super(text, columns);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 12));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
	}

	public QuoteMeTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 12));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
	}

}
