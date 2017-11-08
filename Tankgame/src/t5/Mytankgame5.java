package t5;
/*
 * 自己坦克能自由移动，能连续发子弹
 * 击中敌人坦克后，敌人坦克消失
 * 做出敌人坦克爆炸效果 
 */
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

public class Mytankgame5 extends JFrame {
	Mypanel mp=null;
          public static void main(String[] args) {
        	  Mytankgame5  mytankgame5=new  Mytankgame5();
		}
      public     Mytankgame5(){
    	  mp=new Mypanel();
    	  this.add(mp);
    	  Thread t=new Thread(mp);
    	  t.start();
    	  this.addKeyListener(mp);
    	  this.setSize(450, 350);
    	  this.setLocation(300, 300);
    	  this.setVisible(true);
    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
}
class Mypanel extends JPanel implements KeyListener,Runnable{
	Hero hero=null;
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	Vector<Burst> bmob=new Vector<Burst>();
	int ensize=3;
	Image image1=null;
	Image image2=null;
	Image image3=null;
	public Mypanel()
	{
		hero=new Hero(100,100);
		
		for(int i=0;i<ensize;i++)
		{
			EnemyTank ens=new EnemyTank((i+1)*110,0);
			Thread n=new Thread(ens);
			n.start();
//			Shot 	s=new Shot(ens.x+8,ens.y-1,0);
//			ens.ss.add(s);
//			Thread n2=new Thread(s);
//			n2.start();
			ets.add(ens);
			ens.setColor(0);
		}
		try {
			image1=ImageIO.read(new File("baozha1.jpg"));
			image2=ImageIO.read(new File("baozha2.jpg"));
			image3=ImageIO.read(new File("baozha3.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/baozha1.jpg"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/baozha2.jpg"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/baozha3.jpg"));
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 450, 350);
		if(hero.islive){
		this.drawTank(hero.getX(), hero.getY(), g, 1, hero.direct); 
		}
		for(int i=0;i<hero.ss.size();i++){
			  Shot myshot  =   hero.ss.get(i);
		if(myshot!=null&&myshot.islive==true){
		g.fillOval(myshot.x, myshot.y, 3, 3);
		}
		     if(myshot.islive==false)
		     {
		    	 hero.ss.remove(myshot);
		     }
		}
		//画出炸弹
		for(int i=0;i<bmob.size();i++)
		{
			//System.out.println("bmob.size()="+bmob.size());
			Burst b=bmob.get(i);
			if(b.life>4)
			{
				g.drawImage(image1, b.x, b.y,30, 30,this);
			}else if(b.life>2) {
				g.drawImage(image2, b.x, b.y,30, 30,this);
			}else{
				g.drawImage(image3, b.x, b.y,30, 30,this);
			}
			b.lifeDaow();
			if(b.life==0){
				bmob.remove(b);
			}
		}//画敌人的坦克
		for (int i = 0; i < ets.size(); i++) 
		{
			  EnemyTank et=ets.get(i);
			  if(et.islive){
		       this.drawTank(et.getX(),et.getY(), g,0, et.getDirect());	
		     //  System.out.println("坦克子弹有:"+et.ss.size());
		       for(int j=0;j<et.ss.size();j++){
		    	   Shot emshot=et.ss.get(j);
		    	   if(emshot.islive){
		    		 //  System.out.println("第"+i+"坦克的"+j+"颗子弹x="+emshot.x);
		    		   g.fillOval(emshot.x,emshot.y, 3, 3);
		    	   }else {
					et.ss.remove(emshot);
				}
		       }
		       
		}
		}
	}
	//判断敌人的子弹是否击中我
	public void hitme()
	{
		for (int i = 0; i < this.ets.size(); i++) {
			EnemyTank e=ets.get(i);
			for (int j = 0; j < e.ss.size(); j++) {
				Shot emshot=e.ss.get(j);
				if(emshot.islive){
				this.HitTank(emshot,hero);
				}
			}
		}
	}
	//判断我的子弹是否击中敌人的坦克
	public void hitEmTank()
	{
				for(int i=0;i<hero.ss.size();i++)
				{
				Shot myshot=hero.ss.get(i);
				if(myshot.islive)
				{
					for(int j=0;j<ets.size();j++)
					{
						EnemyTank et=ets.get(j);
						if(et.islive)
						{
							this.HitTank(myshot, et);
						}
					}
				}
				}
	}
	public void HitTank(Shot  s,Tank e)
	{
		
		switch (e.direct) {
		case 0:
		case 3:
				if(s.x>e.x&&s.x<e.x+20&&s.y>e.y&&s.y<=30)
				{
					s.islive=false;
					e.islive=false;
					Burst  a=new Burst(e.x, e.y);
					bmob.add(a);
				}
			break;
		case 1:
		case 2:
			if(s.x>e.x&&s.x<e.x+30&&s.y>e.y&&s.y<e.y+20)
			{
				s.islive=false;
				e.islive=false;
				Burst  a=new Burst(e.x, e.y);
				bmob.add(a);
			}
			break;
		}
		
	}
	public void drawTank(int x,int y,Graphics g,int type,int direct)
	{
		switch (type) {
		case 0:
           			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		switch(direct){
	//炮筒方向
		case 0://向上
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y,5, 30, false);
			g.fill3DRect(x+5, y+5, 11, 20, false);
		    g.fillOval(x+4, y+10,11	, 11);
		   g.fill3DRect( x+9,y, 1, 15, false);
		   g.fillOval( x+8,y-1, 3, 2);
		   break;
		case 1://向右
			g.fill3DRect(x-5, y+5, 30, 5, false);
			g.fill3DRect(x-5, y+20, 30, 5, false);
			g.fill3DRect(x, y+10, 20, 11, false);
			g.fillOval(x+4, y+9,11	, 11);
			g.fill3DRect( x+9,y+14, 15, 1, false);
			g.fillOval( x+23,y+12, 2, 4);
			break;
		case 2://向左
			g.fill3DRect(x-5, y+5, 30, 5, false);
			g.fill3DRect(x-5, y+20, 30, 5, false);
			g.fill3DRect(x, y+10, 20, 11, false);
			g.fillOval(x+4, y+9,11	, 11);
			g.fill3DRect( x-5,y+14, 15, 1, false);
			g.fillOval( x-6,y+12,2, 4);
			break;
		case 3://向下
			g.fill3DRect(x, y, 5, 30, false);
			g.fill3DRect(x+15, y,5, 30, false);
			g.fill3DRect(x+5, y+5, 11, 20, false);
		    g.fillOval(x+4, y+10,11	, 11);
		   g.fill3DRect( x+9,y+15, 1, 15, false);
		   g.fillOval( x+8,y+29, 3, 2);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W :
			this.hero.up();
			this.hero.direct=0;
			break;
		case KeyEvent.VK_D:
			this.hero.Right();
			this.hero.direct=1;
			break;
		case KeyEvent.VK_S:
			this.hero.down();
			this.hero.direct=3;
			break;
		case KeyEvent.VK_A:
			this.hero.left();
			this.hero.direct=2;
			break;		
		}
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			if(hero.ss.size()<=4){
			this.hero .ShotEnemy();
			}
		}
		
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
	while(true){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.hitEmTank();
		this.hitme();
		this.repaint();
	}
		
	}
}

