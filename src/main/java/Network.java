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

	private MainApplet parent;
	private ArrayList<Character> members;	// 在關係圓中的角色物件
	private float x, y, r;					// 圓的中心位置與半徑
	private boolean bold;					// 圓的邊框粗細

	// Constructor
	public Network(MainApplet parent) {
		this.parent = parent;
		this.members = new ArrayList<Character>();
		this.x = 600;
		this.y = 370;
		this.r = 250;
		this.bold = false;
	}

	// 顯示畫面
	public void display() {
		// 關係圓主體
		this.parent.noFill();
		if(this.bold) this.parent.strokeWeight(5);
		else this.parent.strokeWeight(3);
		this.parent.stroke(145, 200, 65, 200);
		this.parent.arc(this.x, this.y, this.r * 2, this.r * 2, 0, PApplet.TWO_PI);
		
		// 在圓裡的角色之間的關係線
		for (Character ch : this.members) {
			for (Link l : ch.getTargets()){
				l.showLink(this.parent, ch);
			}
		}
	}

	// 設定圓的粗細
	public void setBold(boolean b) {
		this.bold = b;
	}
	
	// 加角色進關係圓
	public void addMember(Character c) {
		if (!c.inNet()) {
			this.members.add(c);
			c.setInNet(true);
			this.rearrange();
		}
	}
	
	// 從關係圓中刪除角色
	public void removeMember(Character c){
		c.setInNet(false);
		c.reset();
		this.members.remove(this.members.indexOf(c));
		this.rearrange();
	}
	
	// 判斷游標是否在圓的有效範圍內
	public boolean inCircle(int x, int y) {
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}

	// 將圓內角色清空
	public void clean() {
		for (Character ch : this.members) {
			ch.setInNet(false);
			ch.reset();
		}
		this.members.clear();
	}

	// 將所有未加入的角色全加進圓中
	public void addAll(ArrayList<Character> characters) {
		for (Character ch : characters) {
			if (!members.contains(ch))
				this.addMember(ch);
		}
	}

	// 圓內角色位置重排
	private void rearrange(){
		// 將一個圓周平分給所有人
		float angle = PApplet.TWO_PI / this.members.size();
		float i = 0;
		for (Character ch : this.members) {
			// 使用極座標轉換設定在圓內的新位置
			ch.setCirclePosition(this.x + this.r * PApplet.cos(angle*i), this.y + this.r * PApplet.sin(angle*i));
			i++;
		}
	}
	
}
