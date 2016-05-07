package main.java;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * This class is used for the visualization of the network. Depending on your
 * implementation, you might not need to use this class or create a class on
 * your own. I used the class to draw the circle and re-arrange nodes and links.
 * You will need to declare other variables.
 */
public class Network {

	private PApplet parent;
	private ArrayList<Character> members;
	private float x, y, r;
	private boolean bold;

	public Network(PApplet parent) {
		this.parent = parent;
		this.members = new ArrayList<Character>();
		this.x = 600;
		this.y = 370;
		this.r = 250;
		this.bold = false;
	}

	public void display() {
		this.parent.noFill();
		if(this.bold) this.parent.strokeWeight(5);
		else this.parent.strokeWeight(3);
		this.parent.stroke(145, 200, 65, 200);
		this.parent.arc(this.x, this.y, this.r * 2, this.r * 2, 0, PApplet.TWO_PI);
		
		for (Character ch : this.members) {
			for (Character c : ch.getTargets()) {
				if(c.inCircle()) ch.showLink(c, this.x, this.y);
			}
		}
	}

	public void setBold(boolean b) {
		this.bold = b;
	}
	
	public void addMember(Character c) {
		if (!c.inCircle()) {
			this.members.add(c);
			c.setIncircle(true);
			this.rearrange();
		}
	}
	
	public void removeMember(Character c){
		c.setIncircle(false);
		c.reset();
		this.members.remove(this.members.indexOf(c));
		this.rearrange();
	}
	
	public boolean inCircle(int x, int y) {
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}

	public void clean() {
		for (Character ch : this.members) {
			ch.reset();
			ch.setIncircle(false);
		}
		this.members.clear();
	}

	public void addAll(ArrayList<Character> characters) {
		for (Character ch : characters) {
			if (!members.contains(ch))
				this.addMember(ch);
		}
	}

	private void rearrange(){
		float angle = PApplet.TWO_PI / this.members.size();
		float i = 0;
		for (Character ch : this.members) {
			ch.setCirclePosition(this.x + this.r * PApplet.cos(angle*i), this.y + this.r * PApplet.sin(angle*i));
			i++;
		}
	}
	
}
