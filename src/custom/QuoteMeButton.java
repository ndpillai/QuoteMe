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
//	private final Image mUp;
//	private final Image mDown;
	
	private final int mFontSize;
	
	public QuoteMeButton(String name, Image inUp, Image inDown, int inFontSize) {
		super(new ImageIcon(inUp));
		image = new ImageIcon(inUp);
	//	toDraw = mUp = inUp;
	//	mDown = inDown;
		mFontSize = inFontSize;
		
		setFont(FontLibrary.getFont("fonts/AmarilloUSAF.ttf", Font.PLAIN, mFontSize));
		setBorder(BorderFactory.createEmptyBorder());
		setText(name);
		setHorizontalTextPosition(AbstractButton.CENTER);

		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				image = new ImageIcon(inUp);
				refresh();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				image = new ImageIcon(inDown);
				refresh();
			}
		});
		
	//	setOpaque(false);
	//	setBackground(new Color(0,0,0,0));
	}
	
	public void refresh() {
		setIcon(image);
	}
}
