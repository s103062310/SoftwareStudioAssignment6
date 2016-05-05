package main.java;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * This class is used to store states of the characters in the program. You will
 * need to declare other variables depending on your implementation.
 */
public class Character {

	private float x, y;
	private String name, colour;
	private MainApplet parent;
	private ArrayList<Character> targets;
	private int id, value;

	public Character(MainApplet parent, String name, String colour, int x, int y, int id) {
		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.id = id;
		this.x = x;
		this.y = y;
		targets = new ArrayList<Character>();

	}

	public void setLinkWeight(int value) {
		this.value = value;
	}

	public void display() {

		int hi = this.parent.unhex(colour.substring(1, 9));
		this.parent.fill(hi);
		this.parent.ellipse(x, y, 50, 50);
		this.parent.fill(173, 254, 220);
		this.parent.text(name, x, y);
		this.parent.textSize(15);

		for (Character character : targets) {
			this.parent.line(this.x, this.y, character.x, character.y);
			this.parent.strokeWeight(this.value);
		}

	}

	public void addTarget(Character target) {
		this.targets.add(target);
	}

	public ArrayList<Character> getTargets() {
		return this.targets;
	}
}
