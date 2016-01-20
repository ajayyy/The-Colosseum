 package ajaycamden.game.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	Main main;
	Font font;
	boolean fullscreen = true;
	Rectangle playButtonRect;
	int oldwidth,oldheight;
	boolean done;

	public MainMenu(Main main){
		this.main = main;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, MainMenu.class.getResourceAsStream("/ajaycamden/res/fonts/KaushanScript-Regular.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		playButtonRect = new Rectangle();
	}
	
	
	public void paint(Graphics g){
		if(done){
			return;
		}
		g.setColor(new Color(50,50,150));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(new Color(50,75,255));
		g.fillRect(getWidth()/40,getHeight()/40,getWidth()-getWidth()/20,getHeight()-getHeight()/20);
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(getWidth()/300));
		g.drawRect(getWidth()/40,getHeight()/40,getWidth()-getWidth()/20,getHeight()-getHeight()/20);
		g2.setStroke(oldStroke);
		if(oldwidth !=getWidth() || oldheight != getHeight()){//if width or height changes
			oldwidth = getWidth();
			oldheight = getHeight();
			
			font = font.deriveFont(Font.BOLD, getHeight()/13);
			g.setFont(font);
			
			String playButton = "PLAY";
			int playBorder = 40;
			playButtonRect.setBounds((getWidth()/2-g.getFontMetrics().stringWidth(playButton)/2) - getWidth()/playBorder, 
					(getHeight()/7 + (int) (g.getFont().getSize()*2.2)) - g.getFont().getSize() - getHeight()/playBorder, 
					g.getFontMetrics().stringWidth(playButton) + (getWidth()/playBorder*2), (int) (g.getFont().getSize()*1.2) + 
					getWidth()/playBorder);
		}
		String title = "THE COLOSSEUM";
		g.setFont(font);
		
		//title
		g.setColor(Color.RED);
		int titleBorder = 40;
		g.fillRect((getWidth()/2-g.getFontMetrics().stringWidth(title)/2) - getWidth()/titleBorder, getHeight()/7 - 
				g.getFont().getSize() - getHeight()/titleBorder, g.getFontMetrics().stringWidth(title) + 
				(getWidth()/titleBorder*2), (int) (g.getFont().getSize()*1.2) + getWidth()/titleBorder);
		g.setColor(Color.BLACK);
		g.drawString(title, getWidth()/2-g.getFontMetrics().stringWidth(title)/2, getHeight()/7);
		g2.setStroke(new BasicStroke(getWidth()/300));
		g.drawRect((getWidth()/2-g.getFontMetrics().stringWidth(title)/2) - getWidth()/titleBorder, getHeight()/7 - 
				g.getFont().getSize() - getHeight()/titleBorder, g.getFontMetrics().stringWidth(title) + 
				(getWidth()/titleBorder*2), (int) (g.getFont().getSize()*1.2) + getWidth()/titleBorder);
		g2.setStroke(oldStroke);
		
		//play button
		String playButton = "PLAY";
		g.setColor(Color.RED);
//		g.fillRect((getWidth()/2-g.getFontMetrics().stringWidth(playButton)/2) - getWidth()/playBorder, (getHeight()/7 + (int) (g.getFont().getSize()*2.2)) - g.getFont().getSize() - getHeight()/playBorder, g.getFontMetrics().stringWidth(playButton) + (getWidth()/playBorder*2), (int) (g.getFont().getSize()*1.2) + getWidth()/playBorder);
		g.fillRect(playButtonRect.x, playButtonRect.y, playButtonRect.width, playButtonRect.height);
		g.setColor(Color.BLACK);
		g.drawString(playButton, getWidth()/2-g.getFontMetrics().stringWidth(playButton)/2, getHeight()/7 + (int) (g.getFont().getSize()*2.2));
		g2.setStroke(new BasicStroke(getWidth()/300));
		g.drawRect(playButtonRect.x, playButtonRect.y, playButtonRect.width, playButtonRect.height);
		g2.setStroke(oldStroke);
	}
	
	//NEED
	@Override
	public void mouseClicked(MouseEvent e) {
		if(done){
			return;
		}
		//mouseRect.setBounds(e.getX(), e.getY(), 1, 1);
		if(playButtonRect.contains(e.getX(), e.getY())){
			GameScreen panel = new GameScreen(main);
			main.remove(this);
			main.add(panel);
			main.removeKeyListener(this);
			main.setIgnoreRepaint(true);
			main.setVisible(true);
//			main.repaint();
			panel.init();
			done = true;
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			if(fullscreen){
				main.dispose();
				main.setUndecorated(false);
				main.getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
				main.setExtendedState(JFrame.MAXIMIZED_BOTH);
				main.add(this);
	            main.revalidate();
	            main.repaint();
	            main.setVisible(true);
	            fullscreen = false;
			}else{
				main.dispose();
				main.setUndecorated(true);
				main.getGraphicsConfiguration().getDevice().setFullScreenWindow(main);
				main.revalidate();
				main.repaint();
				main.setVisible(true);
                fullscreen = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	
	

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

//can we also have a credits button here?
}
