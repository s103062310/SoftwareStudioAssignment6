package main.java;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * This class is used to store states of the characters in the program. You will
 * need to declare other variables depending on your implementation.
 */

public class Character{

	private float x, y, r, nx, ny;
	private int value, id;
	private String name, colour;
	private MainApplet parent;
	public ArrayList<Character> targets;
	private boolean isdrag, inCircle;

	public Character(MainApplet parent, String name, String colour, int id) {
		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.id = id;
		this.r = 25;
		this.x = 50 + (id / 10) * 60;
		this.y = 50 + (id % 10) * 60;
		this.nx = 50 + (id / 10) * 60;
		this.ny = 50 + (id % 10) * 60;
		this.isdrag = false;
		this.inCircle = false;
		this.targets = new ArrayList<Character>();
	}
	
	public void display() {

		int hi = PApplet.unhex(this.colour.substring(1, 9));
		this.parent.fill(hi, 200);
		this.parent.ellipse(this.nx, this.ny, this.r*2, this.r*2);
		/*
		this.parent.fill(153, 153, 255);
		this.parent.rect(x + 10, y - 25, name.length() * 10, 25, 12, 12, 12, 12);

		this.parent.fill(173, 254, 220);
		this.parent.textSize(12);
		this.parent.text(name, x + 18, y - 8);
		
		for (Character character : targets) {
			this.parent.line(this.x, this.y, character.x, character.y);
			this.parent.strokeWeight(this.value);
		}*/
	}
	
	public void setLinkWeight(int value) {
		this.value = value;
	}
	
	public void setIncircle(boolean b){
		this.inCircle = b;
	}
	
	public boolean inCircle(){
		return this.inCircle;
	}
	
	public void setDrag(boolean b){
		this.isdrag = b;
	}
	
	public boolean isDrag(){
		return this.isdrag;
	}
	
	public void setPosition(float x, float y) {
		this.nx = x;
		this.ny = y;
	}
	
	public void setCirclePosition(float x, float y){
		this.x = x;
		this.y = y;
		this.setPosition(this.x, this.y);
	}
	
	public void returnToSite(){
		this.nx = this.x;
		this.ny = this.y;
	}
	
	public void reset(){
		this.x = 50 + (this.id / 10) * 60;
		this.y = 50 + (this.id % 10) * 60;
		this.setPosition(this.x, this.y);
	}

	public void addTarget(Character target) {
		this.targets.add(target);
	}

	public ArrayList<Character> getTargets() {
		return this.targets;
	}
	
	public boolean inCircle(int x, int y){
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}
	
	public void showAllLink(){
		for (Character character : targets) {
			
			float midx=(character.x+this.x)/2;
			float midy=(character.y+this.y)/2;
			
			float x1=600+(midx-600)*3;
			float y1=370+(midy-370)*3;
			
			this.parent.curve(x1,y1,this.x, this.y, character.x, character.y,x1,y1);
			this.parent.strokeWeight(this.value);
			
		}
	}
}
