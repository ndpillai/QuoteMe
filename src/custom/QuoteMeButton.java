package custom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import library.FontLibrary;

public class QuoteMeButton extends JButton{
	private static final long serialVersionUID = 7074393176317490987L;

	private ImageIcon image;
	private Image i;
	private int x, y;
	
	private final int mFontSize;
	
	public QuoteMeButton(String name, Image inUp, Image inDown, int inFontSize, int x, int y) {
		super(new ImageIcon(inUp));
		this.x = x;
		this.y = y;
		i = inUp;
		image = new ImageIcon(i.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH));
		setIcon(image);
		mFontSize = inFontSize;
		
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, mFontSize));
		setBorder(BorderFactory.createEmptyBorder());
		setText(name);
		setHorizontalTextPosition(AbstractButton.CENTER);

		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				i = inUp;
				refresh();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				i = inDown;
				refresh();
			}
		});
		
	//	setOpaque(false);
	//	setBackground(new Color(0,0,0,0));
	}
	
	public void refresh() {
		image = new ImageIcon(i.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH));
		setIcon(image);
	}
}
