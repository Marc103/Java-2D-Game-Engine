package dev.marcos_ferreira_tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage 
	evilMountains, 
	greenWallVeg, 
	brownWallVeg, 
	bloodMetalWall, 
	lava, 
	blueLava, 
	skull,
	greenDevil;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/upload_2017-3-3_17-59-0.png"));
		evilMountains = sheet.crop(97, 283, 185, 93);
		greenWallVeg = sheet.crop(491, 0, 185, 88);
		brownWallVeg = sheet.crop(680, 0, 185, 88);
		bloodMetalWall = sheet.crop(728, 91, 93, 93);
		lava = sheet.crop(416,618,46,46);
		blueLava = sheet.crop(616,618,46,46);
		skull = sheet.crop(646, 357, 16, 19);
		greenDevil = sheet.crop(388, 187, 92, 93);
	}
}
