package t5;

import java.util.Vector;
 class Burst
 {
	int x,y;
	int life=6;
	boolean islive=true;
	public Burst(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void lifeDaow(){
		if(life>0){
			life--;
		}else {
			this.islive=false;
		}
	}
	
 } 
class Shot implements Runnable
{
      int x;
      int y;
      int direct;
      int speed=5;
      boolean islive=true;
      public Shot(int x,int y,int direct)
      {
    	  this.x=x;
    	  this.y=y;
    	  this.direct=direct;
      }
	public void run() {
		while(true){
			
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}
		switch(direct){
		case 0:
			y-=speed;
			break;
		case 1:
			x+=speed;
			break;
		case 2:
			x-=speed;
			break;
		case 3:
			y+=speed;
			break;
		}
		//System.out.println("子弹坐标x="+x+"y="+y);
		if(x<3||x>397||y<3||y>393){
			islive=false;
			break;
		}
		}
		
	}
      
}
class Tank
{
	int x=0;
	int y=0;
	int Color=1;
	int direct=3;
	int speed=3;
	boolean islive=true;
	public int getColor() {
		return Color;
	}

	public void setColor(int color) {
		Color = color;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public Tank(int x,int y)
	{
	this.x=x;
	this.y=y;
	}
	
}
class Hero extends Tank
{
	Vector<Shot> ss=new Vector<Shot>();
        Shot  shot=null;
	public Hero(int x,int y)
	{
		super(x, y);	
	}
	//移动方法
	public void ShotEnemy()
	{
		switch(this.direct)
		{
		case 0:
			shot=new Shot(x+8,y-1,0);
			ss.add(shot);
			break;
		case 1:
			shot=new Shot(x+23,y+12,1);
			ss.add(shot);
			break;
		case 2:
			shot=new Shot( x-6,y+12,2);
			ss.add(shot);
			break;
		case 3:
			shot=new Shot(x+8,y+29,3);
			ss.add(shot);
			break;
		}
		Thread t=new Thread(shot);
		t.start();
	}
	public void up()
	{
		y-=speed;
	}
	public void down()
	{
		y+=speed;
	}
	public void Right()
	{
		x+=speed;
	}
	public void left()
	{
		x-=speed;
	}
}
class EnemyTank extends Tank implements Runnable
{
	
	int times=0;
	 Vector<Shot> ss=new Vector<Shot>();
	 Shot shot=null;
   public 	EnemyTank(int x,int y){
	   super(x,y);
	 
   }
public void run() {
	while(true)
	{
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(ss.size()<5){
			switch(direct)
		{
		case 0:
			for(int i=0;i<40;i++){
			if(y>0){
				y-=speed;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			shot=new Shot(x+8,y-1,0);
			ss.add(shot);
			break;
		case 1:
			for(int i=0;i<40;i++){
			if(x<400){
				x+=speed;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			shot=new Shot(x+23,y+12,1);
			ss.add(shot);
			break;
		case 2:
			for(int i=0;i<40;i++){
				if(x>0){
					x-=speed;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			shot=new Shot( x-6,y+12,2);
			ss.add(shot);
			break;
		case 3:
			for(int i=0;i<40;i++){
				if(y<300){
					y+=speed;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			
			shot=new Shot(x+8,y+29,3);
			ss.add(shot);
			break;
		}
		Thread t=new Thread(shot);
		t.start();
		}	
//		this.times++;
//		if(times%2==0)
//		{
//			if(islive)
//			{System.out.println("e恩恩"+times%2);
//				if(ss.size()<5)
//				{
//					System.out.println("ss.size()<5="+ss.size());
//					Shot shot=null;
//					switch (direct) {
//					case 0:
//						shot=new Shot(x+8,y-1,0);
//						ss.add(shot);
//						break;
//					case 1:
//						shot=new Shot(x+23,y+12,1);
//						ss.add(shot);
//						break;
//					case 2:
//						shot=new Shot( x-6,y+12,2);
//						ss.add(shot);
//						break;
//					case 3:
//						shot=new Shot(x+8,y+29,3);
//						ss.add(shot);
//						break;
//					}
//					Thread t=new Thread(shot);
//					t.start();
//				}
//			}
//		}
		this.direct=(int)(Math.random()*4);
		if(this.islive==false)
		{
			break;
		}
	}
	
}
   
}

















