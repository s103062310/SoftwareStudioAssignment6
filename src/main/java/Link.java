package main.java;

public class Link {
	
	private Character target;
	private int value;
	// constructor, t : 連接的角色，v : 該線的粗細
	public Link(Character t, int v){
		this.target = t;
		this.value = v;
	}
	
	public void showLink(MainApplet p, Character s){			
		
		if(!this.target.inNet()) return;
		// 找兩節點的中點(midx,midy)
		float midx = (s.getX()+target.getX()) / 2;	
		float midy = (s.getY()+target.getY()) / 2;
		// (600,370)是圓心所在，(x1,y1)、中點、圓心 三點共線
		float x1 = 600 + (midx-600)*3;
		float y1 = 370 + (midy-370)*3;
		
		p.stroke(100, 100, 100, 200);
		// 為避免線太粗影響視覺效果，將線的粗細(value)做線性調適
		p.strokeWeight((this.value/10+1)*2);
		// (x1,y1)為曲線的凹向點，曲線的兩端分別連接兩節點 : (s.getX(),s.getY())，(target.getX(),target.getY())。
		p.curve(x1, y1, s.getX(), s.getY(), target.getX(), target.getY(), x1, y1);
	}
	
}
