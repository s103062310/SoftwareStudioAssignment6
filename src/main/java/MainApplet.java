package main.java;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/

@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	private JSONObject data;
	private JSONArray nodes, links;
	private ArrayList<Character> characters;
	private Character dragging;
	private Network circle;
	private Button clean, addAll;
	private int keycode=1;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		size(width, height);
		circle = new Network(this);
		addAll = new Button(this, 950, 70, 50, "ADD ALL");
		clean = new Button(this, 950, 170, 60, "CLEAN");
		characters = new ArrayList<Character>();
		dragging = null;
		loadData();
		smooth();
	}
	
	public void draw() {
		background(255);
		
		fill(0);
		textSize(50);
		text("Star Wars "+keycode, 460, 70);
		
		circle.display();
		clean.display();
		addAll.display();
		
		for (Character character : characters)
			character.display();
	}
	
	public void keyPressed(){
		if(keyCode==97){
			file = "starwars-episode-1-interactions.json";
		} else if(keyCode==98){
			file = "starwars-episode-2-interactions.json";	
		} else if(keyCode==99){
			file = "starwars-episode-3-interactions.json";	
		} else if(keyCode==100){
			file = "starwars-episode-4-interactions.json";	
		} else if(keyCode==101){
			file = "starwars-episode-5-interactions.json";	
		} else if(keyCode==102){
			file = "starwars-episode-6-interactions.json";	
		} else if(keyCode==103){
			file = "starwars-episode-7-interactions.json";	
		}
		if(keyCode>=97 && keyCode<=103){
			keycode=keyCode-96;
		}
		setup();
	}
	
	public void mouseClicked(){
		if(clean.inBtn(mouseX, mouseY)) circle.clean();
		if(addAll.inBtn(mouseX, mouseY)) circle.addAll(characters);
	}
	
	public void mousePressed(){
		if(clean.inBtn(mouseX, mouseY)) clean.setClick(true);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setClick(true);
		for(Character ch : characters){
			if(ch.inBtn(mouseX, mouseY)){
				ch.setDrag(true);
				dragging = ch;
				break;
			}
		}
	}
	
	public void mouseReleased(){
		if(clean.inBtn(mouseX, mouseY)) clean.setClick(false);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setClick(false);
		circle.setBold(false);
		if(dragging!=null&&dragging.isDrag()){
			if(!dragging.inNet()&&circle.inCircle(mouseX, mouseY)) circle.addMember(dragging);
			else if(dragging.inNet()&&!circle.inCircle(mouseX, mouseY)) circle.removeMember(dragging);
			else dragging.returnToSite();
			dragging.setDrag(false);
			dragging.setOver(false);
		}
	}
	
	public void mouseMoved(){
		if(clean.inBtn(mouseX, mouseY)) clean.setOver(true);
		else clean.setOver(false);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setOver(true);
		else addAll.setOver(false);
		for(Character ch : characters){
			if(ch.inBtn(mouseX, mouseY)) ch.setOver(true);
			else ch.setOver(false);
		}
	}
	
	public void mouseDragged(){
		if(circle.inCircle(mouseX, mouseY)) circle.setBold(true);
		else circle.setBold(false);
		if(dragging!=null&&dragging.isDrag()) dragging.setPosition(mouseX, mouseY);
	}

	private void loadData(){
		data = loadJSONObject(path + file);
		
		nodes = data.getJSONArray("nodes");
		for (int i = 0; i < nodes.size(); i++) {
			JSONObject n = nodes.getJSONObject(i);
			Character ch = new Character(this, n.getString("name"), n.getString("colour"), i);
			characters.add(ch);
		}
		
		links = data.getJSONArray("links");
		for (int i = 0; i < links.size(); i++) {
			JSONObject l = links.getJSONObject(i);
			int source = l.getInt("source");
			int target = l.getInt("target");
			characters.get(source).addTarget(characters.get(target));
			characters.get(source).setLinkWeight(l.getInt("value"));
		}
	}

}
