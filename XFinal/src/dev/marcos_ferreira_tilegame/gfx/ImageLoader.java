package dev.marcos_ferreira_tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			System.exit(1);
			e.printStackTrace();
		}
		return null;
		
	}
}
