package main.java;

import processing.core.PApplet;

public class Button {
	
	private PApplet parent;
	private int x, y, amount;
	private String text;
	private boolean over, click;
	
	public Button(PApplet p, int x, int y, int amount, String text){
		this.parent = p;
		this.x = x;
		this.y = y;
		this.amount = amount;
		this.text = text;
		this.over = false;
		this.click = false;
	}
	
	public void display(){
		this.parent.noStroke();
		if(this.over) this.parent.fill(130, 180, 150, 200);
		else if(this.click) this.parent.fill(130, 180, 150, 200);
		else this.parent.fill(130, 180, 150, 200);
		this.parent.rect(this.x, this.y, 200, 50);
		
		this.parent.fill(255);
		this.parent.textSize(22);
		this.parent.text(this.text, this.x+this.amount, this.y+35);
	}
	
	public void setOver(boolean b){
		this.over = b;
	}
	
	public void setClick(boolean b){
		this.click = b;
	}
	
}
