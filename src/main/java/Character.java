package main.java;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

/**
 * This class is used to store states of the characters in the program. You will
 * need to declare other variables depending on your implementation.
 */

public class Character{

	public float x, y, r, nx, ny;
	private int id;
	private String name, colour;
	private MainApplet parent;
	private ArrayList<Link> targets;
	private boolean isdrag, inNet, over;

	
	public Character(MainApplet parent, String name, String colour, int id) {
		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.id = id;
		this.x = 50 + (id / 10) * 60;
		this.y = 50 + (id % 10) * 60;
		this.nx = 50 + (id / 10) * 60;
		this.ny = 50 + (id % 10) * 60;
		this.isdrag = false;
		this.inNet = false;
		this.targets = new ArrayList<Link>();
		//Ani.init(this.parent);
	}
	
	public void display() {
		int hi = PApplet.unhex(this.colour.substring(1, 9));
		this.parent.fill(hi, 200);
		if(this.over) this.r = 25;
		else this.r = 20;
		this.parent.ellipse(this.nx, this.ny, this.r*2, this.r*2);
	}
	
	public void setInNet(boolean b){
		this.inNet = b;
	}
	
	public boolean inNet(){
		return this.inNet;
	}
	
	public void setDrag(boolean b){
		this.isdrag = b;
	}
	
	public boolean isDrag(){
		return this.isdrag;
	}
	
	public void setOver(boolean b){
		this.over = b;
	}
	
	public boolean isOver(){
		return this.over;
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
	
	public float getX(){
		return this.nx;
	}
	
	public float getY(){
		return this.ny;
	}
	
	public void returnToSite(){
		this.setPosition(this.x, this.y);
	}
	
	public void reset(){
		this.x = 50 + (this.id / 10) * 60;
		this.y = 50 + (this.id % 10) * 60;
		Ani.to(this, 1, "nx", this.x);
		Ani.to(this, 1, "ny", this.y);
	}

	public void addTarget(Link l) {
		this.targets.add(l);
	}
	
	public ArrayList<Link> getTargets() {
		return this.targets;
	}
	
	public boolean inBtn(int x, int y){
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}
	
	public void showName(){
		this.parent.fill(180, 210, 180);
		this.parent.rect(this.nx, this.ny-20, this.name.length()*25, 40, 15, 15, 15, 15);

		this.parent.fill(255);
		this.parent.textSize(24);
		this.parent.text(this.name, this.nx+20, this.ny+10);
	}
}
