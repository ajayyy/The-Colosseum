package ajaycamden.game.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map {
	int x,y;
	BufferedImage[] images = new BufferedImage[9];//the map (this will be large)
	GameScreen panel;
//	boolean pastx,pasty,pastwidth,pastheight;
	int imageon;//what image are we on;
	public Map(GameScreen panel){
		this.panel = panel;
		//load map image;
		try {
//			for(int i=0;i<images.length;i++){
//				images[i] = ImageIO.read(Map.class.getResourceAsStream("/ajaycamden/res/mapsections/game map "+i+".png"));
//			}
			this.images[0] = ImageIO.read(Map.class.getResourceAsStream("/ajaycamden/res/mapsections/game map 0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(Graphics g){
		//code to check the image we are on
		int xon = 0;
		if(-panel.character.x>images[imageon].getWidth()){
			if(-panel.character.x>images[imageon].getWidth()*2){
				xon = 2;
			}else{
				xon = 1;
			}
		}
		int yon = 0;
		if(-panel.character.y>images[imageon].getHeight()){
			if(-panel.character.y>images[imageon].getHeight()*2){
				yon = 2;
			}else{
				yon = 1;
			}
		}
		imageon = xon + (yon*3);
		
		
//		if(images[imageon] == null){
//			try {
//				images[imageon] = ImageIO.read(Map.class.getResourceAsStream("/ajaycamden/res/mapsections/game map " + imageon + ".png"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
		//loading other chunks
		loadchunks(xon,yon);
		
		
		
		x = (panel.getWidth()/2 - panel.character.image.getHeight()/2) + panel.character.x;
		
		y = (panel.getHeight()/2 - panel.character.image.getWidth()/2) + panel.character.y;
		
//		if(x>0){
//			x = 0;
////			pastx = true;
////			panel.character.pastx = panel.getWidth()/2 - panel.character.image.getWidth()/2;
//		}else if(x+images[imageon].getWidth()*3<panel.getWidth()){
////			x = panel.getWidth()-images[imageon].getWidth()*3;
////			pastwidth = true;
////			panel.character.pastx = panel.getWidth()/2 + panel.character.image.getWidth()/2;
//		}else{
//			x = (panel.getWidth()/2 - panel.character.image.getHeight()/2) + panel.character.x;
//		}
//		if(y>0){
//			//pasty = true;
//			//panel.character.pasty = panel.getHeight()/2 - panel.character.image.getHeight()/2 - y;
////			y=0;
//		}else if(y+images[0].getHeight()*3<=panel.getHeight()){
////			y = panel.getHeight()-images[0].getHeight()*3;
//			//pastheight = true;
//			//panel.character.pasty = panel.getHeight()/2 + panel.character.image.getHeight()/2;
//		}else{
//			y = (panel.getHeight()/2 - panel.character.image.getWidth()/2) + panel.character.y;
//		}
		
		for(int i=0;i<images.length;i++){
			if(images[i] != null){
//				System.out.println(i + " " + x+((i<3?i:i-(i>6?6:3))*images[imageon].getWidth()) + " " + y+((i/3)*images[imageon].getHeight()));
				g.drawImage(images[i], x+((i<3?i:i-(i>6?6:3))*images[imageon].getWidth()), y+((i/3)*images[imageon].getHeight()), null);
			}
		}
		
		
	}
	
	public void loadchunks(int xon, int yon){//LOAD DIAGONALS TOO AND MAKE GAME MAP 6 WORK
		if(xon<2 && Math.abs((-panel.character.x) - images[imageon].getWidth()*(xon+1)) < 1000){
			if(images[imageon+1]==null){
				images[imageon+1] = images[imageon];
				new Thread(new ChunkLoader(this, imageon+1)).start();
			}
		}
		if(xon>0 && Math.abs(-panel.character.x - images[imageon].getWidth()*(xon)) < 1000){
			if(images[imageon-1]==null){
				images[imageon-1] = images[imageon];
				new Thread(new ChunkLoader(this, imageon-1)).start();
			}
		}
		if(yon<2 && Math.abs(-panel.character.y - images[imageon].getHeight()*(yon+1)) < 1000){
			if(images[imageon+3]==null){
				images[imageon+3] = images[imageon];
				new Thread(new ChunkLoader(this, imageon+3)).start();
			}
		}
		if(yon>0 && Math.abs(-panel.character.y - images[imageon].getHeight()*yon) < 1000){
			if(images[imageon-3]==null){
				images[imageon-3] = images[imageon];
				new Thread(new ChunkLoader(this, imageon-3)).start();
			}
		}
		
		//deletes old images
		
		if(xon<2 && Math.abs((-panel.character.x) - images[imageon].getWidth()*(xon+1)) > 100){
			if(images[imageon+1]==null){
				images[imageon+1] = null;
			}
		}
		if(xon>0 && Math.abs(-panel.character.x - images[imageon].getWidth()*(xon)) > 100){
			if(images[imageon-1]==null){
				images[imageon-1] = null;
			}
		}
		if(yon<2 && Math.abs(-panel.character.y - images[imageon].getHeight()*(yon+1)) > 1000){
			if(images[imageon+3]==null){
				images[imageon+3] = null;
			}
		}
		if(yon>0 && Math.abs(-panel.character.y - images[imageon].getHeight()*yon) > 1000){
			if(images[imageon-3]==null){
				images[imageon-3] = null;
			}
		}
	}
	
//	public boolean past(){
//		return pastx || pasty || pastwidth || pastheight;
//	}
}
