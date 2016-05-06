package main.java;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private ArrayList<Character> members;
	private int x, y, r;
	private boolean bold;

	public Network(PApplet parent){
		this.parent = parent;
		this.members = new ArrayList<Character>();
		this.x = 600;
		this.y = 370;
		this.r = 250;
		this.bold = false;
	}

	public void display(){
		this.parent.noFill();
		if(this.bold) this.parent.strokeWeight(5);
		else this.parent.strokeWeight(3);
		this.parent.stroke(145, 200, 65, 200);
		this.parent.arc(this.x, this.y, this.r*2, this.r*2, 0, PApplet.TWO_PI);
	}
	
	public void addMember(Character c){
		if(!this.members.contains(c)){
			this.members.add(c);
			Collections.sort(this.members);
			float angle = 360/this.members.size();
			float i=0;
			for(Character ch : this.members){
				ch.setPosition(this.x+this.r*PApplet.cos(angle*i), this.y+this.r*PApplet.sin(angle*i));
				i++;
			}
		}
	}
	
	public ArrayList<Character> getMembers(){
		return this.members;
	}
	
	public boolean inCircle(int x, int y){
		float disX = this.x - x;
		float disY = this.y - y;
		if(PApplet.sqrt(PApplet.sq(disX)+PApplet.sq(disY))<=this.r) return true;
		else return false;
	}
	
	public void setBold(boolean b){
		this.bold = b;
	}
	
	public void clean(){
		for(Character ch : this.members){
			ch.setPosition(0, 0);
		}
		this.members.clear();
	}
	
	public void addAll(ArrayList<Character> characters){
		for(Character ch : characters){
			if(!members.contains(ch)) this.addMember(ch);
		}
	}
	
}
