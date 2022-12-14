package dev.marcos_ferreira_tilegame.states;

import java.awt.Graphics;

import dev.marcos_ferreira_tilegame.Game;
import dev.marcos_ferreira_tilegame.entities.creatures.Player;
import dev.marcos_ferreira_tilegame.worlds.World;

public class GameState extends State {

	private Player player;
	private World world;
	
	public GameState(Game game){
		super(game);
		player = new Player(game,100,100);
		world = new World("/worlds/world1.txt");
		
	}
	
	@Override
	public void tick() {
		world.tick();
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

}
