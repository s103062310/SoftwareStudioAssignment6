package main.java;

import processing.core.PApplet;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	private Network circle;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		size(width, height);
		circle = new Network(this);
		loadData();
		smooth();
	}

	public void draw() {
		background(255);
		circle.display();
	}

	private void loadData(){

	}

}
