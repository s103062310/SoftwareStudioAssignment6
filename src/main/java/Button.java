package main.java;

import processing.core.PApplet;

public class Button {
	
	private PApplet parent;
	private int x, y, width, height, amount;
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
		this.width = 200;
		this.height = 50;
	}
	
	public void display(){
		this.parent.noStroke();
		if(this.click) this.parent.fill(10, 160, 250, 200);
		else if(this.over) this.parent.fill(180, 210, 180, 200);
		else this.parent.fill(130, 180, 150, 200);
		this.parent.rect(this.x, this.y, this.width, this.height);
		
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
	
	public boolean inBtn(int x, int y){
		if(x>=this.x&&x<=this.x+this.width&&y>=this.y&&y<=this.y+this.height)
			return true;
		else return false;
	}
	
}
