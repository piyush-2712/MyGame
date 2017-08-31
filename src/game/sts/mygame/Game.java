package game.sts.mygame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	private Display display;
	public int width, height;
	public String title;
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;



	}

	private BufferStrategy bs;
	private Graphics g;


	public boolean running=false;
	private Thread thread;

	private void init(){
		System.out.println("2");
		display= new Display(title,width,height);
	}

	public synchronized void start(){
		System.out.println("1");
		if(running)
			return;
		running=true;
		thread= new Thread(this);
		thread.start();

	}

	public synchronized void stop(){
		System.out.println("3");
		running =false;
		try{
			thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}

	}




	public void run(){
		System.out.println("4");
		init();

		while(running){
			tick();
			render();
		}
		stop();
	}

	private void render() {
		// TODO Auto-generated method stub
		bs=display.getCanvas().getBufferStrategy();

		if(bs==null){
			display.getCanvas().createBufferStrategy(4);
			return;

		}
		g=bs.getDrawGraphics();
		g.fillRect(0, 0, width, height);

g.clearRect(0, 0, width, height);

g.setColor(Color.blue);

g.fillRect(40, 40, 30, 20);


g.setColor(Color.green);

g.fillRect(80, 80, 10, 10);
		bs.show();
		g.dispose();
	}

	private void tick() {
		// TODO Auto-generated method stub

	}
}
