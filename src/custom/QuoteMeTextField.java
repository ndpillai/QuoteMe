package custom;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.Document;

import library.FontLibrary;
import resources.Constants;

public class QuoteMeTextField extends JTextField {

	public QuoteMeTextField() {
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setHorizontalAlignment(JTextField.CENTER);
	}

	public QuoteMeTextField(String text) {
		super(text);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setHorizontalAlignment(JTextField.CENTER);
	}

	public QuoteMeTextField(int columns) {
		super(columns);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setHorizontalAlignment(JTextField.CENTER);
	}

	public QuoteMeTextField(String text, int columns) {
		super(text, columns);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setHorizontalAlignment(JTextField.CENTER);
	}

	public QuoteMeTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		setFont(FontLibrary.getFont(Constants.fontString, Font.PLAIN, 16));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setHorizontalAlignment(JTextField.CENTER);
	}

}
