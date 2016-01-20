package ajaycamden.game.main;

import java.awt.Color;
import java.awt.Graphics;

public class PauseMenu {
	
	public void update(Graphics g, GameScreen screen){
		g.setColor(new Color(50,75,255));
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		//draw pause menu here
		
		//here we need to put in a clicked button in the pause menu that takes the player back to the main menu and a resume button too
	}
}
