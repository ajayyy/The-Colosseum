package ajaycamden.game.main;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ChunkLoader implements Runnable{
	Map map;
	int image;
	
	public ChunkLoader(Map map, int image){
		this.map = map;
		this.image = image;
	}

	@Override
	public void run() {
		try {
			map.images[image] = ImageIO.read(Map.class.getResourceAsStream("/ajaycamden/res/mapsections/game map " + (image) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
