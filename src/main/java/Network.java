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

	public Network(PApplet parent){
		this.parent = parent;
		members = new ArrayList<Character>();
	}

	public void display(){
		this.parent.noFill();
		this.parent.strokeWeight(3);
		this.parent.stroke(65, 180, 0, 200);
		parent.arc(600, 350, 500, 500, 0, this.parent.TWO_PI);
	}
	
	public void addMember(Character c){
		members.add(c);
		Collections.sort(members);
		for(Character ch : members){
			
		}
	}
	
	public ArrayList<Character> getMembers(){
		return this.members;
	}
	
}
