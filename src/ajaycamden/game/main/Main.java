package ajaycamden.game.main;

import javax.swing.JFrame;

public class Main extends JFrame{

	private static final long serialVersionUID = -6497945520379939279L;

	//MAIN CLASS
	
	public Main(){
		this.setTitle("The Colleseum");
		this.setLocationRelativeTo(null);
		this.setSize(640, 480);
		this.setUndecorated(true);
		this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainMenu panel = new MainMenu(this);
		this.add(panel);
		this.addMouseListener(panel);
		this.addKeyListener(panel);
		
		this.setVisible(true);
	}
	
//	GameScreen panel = new GameScreen(this);
//	this.add(panel);
//	this.addKeyListener(panel);
//	panel.init();
	
	public static void main(String args[]){
		new Main();
	}
	
}
