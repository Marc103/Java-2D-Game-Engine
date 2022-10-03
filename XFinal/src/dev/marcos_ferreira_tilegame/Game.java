package dev.marcos_ferreira_tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.marcos_ferreira_tilegame.display.Display;
import dev.marcos_ferreira_tilegame.gfx.Assets;
import dev.marcos_ferreira_tilegame.input.KeyManager;
import dev.marcos_ferreira_tilegame.states.GameState;
import dev.marcos_ferreira_tilegame.states.MenuState;
import dev.marcos_ferreira_tilegame.states.SettingsState;
import dev.marcos_ferreira_tilegame.states.State;

public class Game implements Runnable{
	private Display display;
	public String title;
	public int width,height;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	private State settingsState;
	
	//Input
	private KeyManager keyManager;
	
	public Game(String title,int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init(){
		keyManager = new KeyManager();
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		settingsState = new SettingsState(this);
		State.setState(gameState);

	}

	
	
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		if(State.getState() != null){
			State.getState().render(g);
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
		
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1_000_000_000/ fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta-- ;
			}
			
			if(timer >= 1_000_000_000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
