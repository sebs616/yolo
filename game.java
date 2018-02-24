package yolo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class game extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	
	public int frames = 0;
	public int time_Count = 0;
	public int seconds = 0;
	
	public boolean player_Color=false;
	public boolean on=false;
	boolean running = true;

	public static int x=800, y=600, px=x/2-15, py=y/2-15;
	public static Color color1=Color.GREEN, color2=Color.GRAY, color3=Color.DARK_GRAY;

	//player variables
	public static int speed=5, sx=speed, sy=speed;
	public int pointerx, pointery;

	public static boolean up, down, left, right, start=true;

	
	public static void main (String[] args)
	{
		JFrame frame = new JFrame("yolo");
		game yolo = new game();
		
		frame.add(yolo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.setVisible(true);
		frame.setSize(x, y);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(yolo);
			
		new Thread(yolo).start();
	}
	
	public void run()
	{
		while(running){
			Timer();
			Update();
			repaint();
			try {Thread.sleep(20);} catch (InterruptedException e){e.printStackTrace();}	
		}
	}
	
	public void Timer(){
		frames++;	
		if(frames > 50){
			frames = 0;	
			if(on){
				seconds++;
			}
		}
		
		if(on){
			player_Color = !player_Color;
			
			if (player_Color){
				color1 = Color.GREEN;
			}else{
				color1=Color.BLUE;
			}
			if (seconds >= 5){
				on = false;
				seconds = 0;
				color1 = Color.GREEN;
			}

		}
	}
	
	public void Update()
	{
		playerUpdate();
	}	
	
	public void paint (Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		//BACKGROUND
		g.setColor(color3);
		g.fillRect(0,  0,  x/2,  y/2);
		g.fillRect(x/2, y/2, x/2, y/2);

		g.setColor(color2);
		g.fillRect(x/2, 0, x/2, y/2);
		g.fillRect(0, y/2, x/2, y/2);
		
		//PLAYER
		g.setColor(color1);
		g.fillRect(px, py, 30, 30);
		
		g.setColor(Color.black);
		g.fillRect(pointerx, pointery, 10, 10);	
	}
	
	public void playerUpdate()
	{	
		if(up){py-=speed;}
		if(down){py+=speed;}
		if(left){px-=speed;}
		if(right){px+=speed;}
		
		if(px>770){ px-=speed; }
		if(py>549){ py-=speed; }
		if(px<0){ px+=speed; }
		if(py<0){ py+=speed; }
		
		PlayerDirection();
	}	
	
	public void PlayerDirection()
	{		
		if(start){pointerx=px+10; pointery=py+10; start=false;}
		if(up){pointerx=px+10; pointery=py;}
		if(right){pointerx=px+20; pointery=py+10;}
		if(left){pointerx=px; pointery=py+10;}
		if(down){pointerx=px+10; pointery=py+20;}
		
		if(up&&right){pointerx=px+20; pointery=py;}
		if(up&&left){pointerx=px; pointery=py;}
		if(down&&left){pointerx=px; pointery=py+20;}
		if(down&&right){pointerx=px+20; pointery=py+20;}
	}

	public boolean move;
	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
			up=true;
			break;
		case KeyEvent.VK_S:
			down=true;
			break;
		case KeyEvent.VK_A:
			left=true;
			break;
		case KeyEvent.VK_D:
			right=true;
			break;
		}
	}
	

	public void keyReleased(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
			up=false;
			break;
		case KeyEvent.VK_S:
			down=false;
			break;
		case KeyEvent.VK_A:
			left=false;
			break;
		case KeyEvent.VK_D:
			right=false;
			break;
		case KeyEvent.VK_E:
			on = true;
			break;
		case KeyEvent.VK_L:
			color2=Color.YELLOW;
			color3=Color.MAGENTA;
			break;
		case KeyEvent.VK_K:
			color2=Color.GRAY;
			color3=Color.DARK_GRAY;
			break;
		}
	}


	public void keyTyped(KeyEvent e) 
	{

	}
}
