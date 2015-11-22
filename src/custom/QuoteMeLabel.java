package custom;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

import library.FontLibrary;

public class QuoteMeLabel extends JLabel {

	public QuoteMeLabel() {
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, 22));
	}
	
	public QuoteMeLabel(int size) {
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, size));
	}

	public QuoteMeLabel(String text) {
		super(text);
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, 22));
	}
	
	public QuoteMeLabel(String text, int size, boolean setSize) {
		super(text);
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, size));
	}

	public QuoteMeLabel(Icon image) {
		super(image);
	}

	public QuoteMeLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, 22));
	}

	public QuoteMeLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, 22));
	}

	public QuoteMeLabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, 22));
	}

	public void setFontSize(int size) {
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, size));
	}
}
