package dev.marcos_ferreira_tilegame.tile;

import dev.marcos_ferreira_tilegame.gfx.Assets;

public class LavaTile extends Tile {

	public LavaTile(int id) {
		super(Assets.blueLava, id);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
