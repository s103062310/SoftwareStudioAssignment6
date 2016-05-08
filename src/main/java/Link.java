package main.java;

public class Link {
	
	private Character target;
	private int value;
	
	public Link(Character t, int v){
		this.target = t;
		this.value = v;
	}
	
	public void showLink(MainApplet p, Character s){			
		
		if(!this.target.inNet()) return;
		
		float midx = (s.getX()+target.getX()) / 2;
		float midy = (s.getY()+target.getY()) / 2;
		
		float x1 = 600 + (midx-600)*3;
		float y1 = 370 + (midy-370)*3;
		
		p.stroke(100, 100, 100, 200);
		p.strokeWeight((this.value/10+1)*2);
		p.curve(x1, y1, s.getX(), s.getY(), target.getX(), target.getY(), x1, y1);
	}
	
}
