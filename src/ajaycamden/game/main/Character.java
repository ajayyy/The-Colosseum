package ajaycamden.game.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character {
	int x,y;
	BufferedImage image;
	GameScreen panel;
//	int speed = 10;
	int speed = 20;
	
	public Character(int x, int y, GameScreen panel){
		this.panel = panel;
		try {
			this.image = ImageIO.read(Character.class.getResourceAsStream("/ajaycamden/res/playermodels/Charactor base.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = -1000;
		this.y = -1000;
	}
	
	public void draw(Graphics g){
//		if(panel.map.pastx){
//			g.drawImage(image, pastx, panel.getHeight()/2 - image.getWidth()/2, null);
//			if(pastx>panel.getWidth()/2 + image.getWidth()/2){
//				panel.map.pastx = false;
//			}
//			
//		}else if(panel.map.pastwidth){
//			g.drawImage(image, pastx, panel.getHeight()/2 - image.getWidth()/2, null);
//			if(pastx<panel.getWidth()/2 - image.getWidth()/2){//REMOVE GLITCHES
//				panel.map.pastwidth = false;
//			}
//		}else if(panel.map.pasty){
//			g.drawImage(image, panel.getWidth()/2 - image.getHeight()/2, pasty, null);
//			if(pasty>=panel.getHeight()/2 + image.getHeight()/2){
//				panel.map.pasty = false;
//				y-= pasty - (panel.getHeight()/2 + image.getHeight()/2);
//			} 
//		}else if(panel.map.pastheight){
//			g.drawImage(image, panel.getWidth()/2 - image.getHeight()/2, pastx, null);
//			if(pasty<panel.getHeight()/2 - image.getWidth()/2){
//				panel.map.pastwidth = false;
//			}
//		}else{
		g.drawImage(image, panel.getWidth()/2 - image.getHeight()/2, panel.getHeight()/2 - image.getWidth()/2, null);
//		}
	}
	public void move(){
		
	}
}
