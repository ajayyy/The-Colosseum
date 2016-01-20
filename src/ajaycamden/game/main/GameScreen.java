package ajaycamden.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameScreen extends Canvas implements KeyListener, Runnable{
	boolean up,down,left,right;
	private static final long serialVersionUID = -3136897083534239697L;
	Character character;
	Main main;
	boolean fullscreen = true;
	int oldwidth = -1,oldheight;
	boolean paused;
	PauseMenu pauseMenu;
	Map map;
	BufferStrategy s;
	boolean running = true;
	long last;
	int targetfps = 70;
	long timeperupdate = 1000/targetfps;
	
	public GameScreen(Main main){//INIT CODE IN INIT METHOD
		this.main = main;
	}
	
	public void init(){//INIT CODE HERE
		character = new Character(0, 0, this);//0 0 FOR NOW
		//is 0 0 the starting coordinates?
		pauseMenu = new PauseMenu();
		map = new Map(this);
		Thread game = new Thread(this);
		game.start();
	}
	
	public void run(){
		
		main.addKeyListener(this);
		this.addKeyListener(this);
		
		while(running){
			s = getBufferStrategy();
			if(s!=null){
				Graphics2D g = (Graphics2D) s.getDrawGraphics();
				last = System.currentTimeMillis();
				
				
				paint(g);
				
				
				
				
				try {
					if(System.currentTimeMillis()-last < timeperupdate)
						Thread.sleep(timeperupdate - (System.currentTimeMillis()-last));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				s.show();
			}else{
				createBufferStrategy(3);
			}
		}
	}
	
	public void paint(Graphics g){
//		if((oldwidth !=getWidth() || oldheight != getHeight()) && oldwidth != -1){//window size change
//			
//		}
		oldwidth = getWidth();
		oldheight = getHeight();
		if(paused){
			pauseMenu.update(g, this);
			return;
		}
		g.setColor(new Color(0,162,232));
		g.fillRect(0,0,getWidth(),getHeight());
		map.tick(g);
		character.draw(g);
		
		
		collide();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			paused = !paused;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
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
	
	public void collide(){
		
		if(up){
			character.y+=character.speed;
			if(character.y<0){
				character.y = 0;
			}
			
		}
		if(down){
			character.y-=character.speed;
			if(character.y>main.getHeight()){
				character.y = main.getHeight();
			}
		}
		if(left){
			character.x+=character.speed;
			if(character.x<0){
				character.x =0;
			}
		}
		if(right){
			character.x-=character.speed;
			if(character.x>main.getWidth()){
				character.x = main.getWidth();
			}
		}
	}

	//ignore
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
