package main.java;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

/**
 * This class is used to store states of the characters in the program. You will
 * need to declare other variables depending on your implementation.
 */

public class Character{

	private float x, y, r, nx, ny;			// 保存角色位置改變前的 x 座標，改變前的 y 座標，半徑，角色當前的 x 座標，角色當前的 y 座標。
	private int id;							// 該角色的編號。
	private String name, colour;			// 該角色的名字，顏色。
	private MainApplet parent;
	private ArrayList<Link> targets;		// 所有與該角色相連的角色都存於 targets 中。
	private boolean isdrag, inNet, over;	// 是否正被拖曳，是否在關係圓內，是否游標正置於其上。

	// constructor
	public Character(MainApplet parent, String name, String colour, int id) {
		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.id = id;
		// 依照id，算出該節點在左側列的位置。每行有10個節點。
		this.x = 50 + (id / 10) * 60;
		this.y = 50 + (id % 10) * 60;
		this.nx = 50 + (id / 10) * 60;
		this.ny = 50 + (id % 10) * 60;
		this.isdrag = false;				
		this.inNet = false;					// 一開始角色位於左側列中，不在關係圓內。
		// 將該角色所連接的所有角色，與對應的 link 的 value 存於 targets 中。
		this.targets = new ArrayList<Link>();
		//Ani.init(this.parent);
	}
	// 設置該角色圓的特性。
	public void display() {
		int hi = PApplet.unhex(this.colour.substring(1, 9));		// 將 hex color 轉成 rgb
		this.parent.fill(hi, 200);
		if(this.over) this.r = 25;									// 當游標移到該角色圓上時，該圓放大成半徑=25
		else this.r = 20;											// 平時一角色圓的半徑=20
		this.parent.ellipse(this.nx, this.ny, this.r*2, this.r*2);	// 畫出該角色圓
	}
	// 賦值 inNet，以顯示該角色是否位於關係圓內。
	public void setInNet(boolean b){
		this.inNet = b;
	}
	// 讀取 inNet。
	public boolean inNet(){
		return this.inNet;
	}
	// 賦值 isdrag，以顯示該角色是否正被拖曳。
	public void setDrag(boolean b){
		this.isdrag = b;
	}
	// 讀取 isdrag。
	public boolean isDrag(){
		return this.isdrag;
	}
	// 賦值 over，以顯示游標是否正位於該角色之上。
	public void setOver(boolean b){
		this.over = b;
	}
	// 讀取 over。
	public boolean isOver(){
		return this.over;
	}
	// 當角色被拖曳時，重設其位置
	public void setPosition(float x, float y) {
		this.nx = x;
		this.ny = y;
	}
	// 設置該角色在關係圓中的位置
	public void setCirclePosition(float x, float y){
		this.x = x;
		this.y = y;
		this.setPosition(this.x, this.y);
	}
	// 取得該角色當前的 x 座標
	public float getX(){
		return this.nx;
	}
	// 取得該角色當前的 y 座標
	public float getY(){
		return this.ny;
	}
	// 角色被拖曳後，回到原本在關係圓的位置
	public void returnToSite(){
		this.setPosition(this.x, this.y);
	}
	// 回復到一開始在左側列的位置
	public void reset(){
		// 算出該角色在左側列的初始位置 (x,y) 
		this.x = 50 + (this.id / 10) * 60;
		this.y = 50 + (this.id % 10) * 60;
		// 動畫歸位
		Ani.to(this, 1, "nx", this.x);
		Ani.to(this, 1, "ny", this.y);
	}
	// 將與該角色相連的角色加入 targets 的 ArrayList
	public void addTarget(Link l) {
		this.targets.add(l);
	}
	// 取得整條與該角色相連的角色鏈
	public ArrayList<Link> getTargets() {
		return this.targets;
	}
	// 以角色圓的半徑作為範圍依據，判斷該角色圓是否在點擊範圍
	public boolean inBtn(int x, int y){
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}
	// 顯示節點的名字與文字區塊
	public void showName(){
		this.parent.fill(180, 210, 180);
		this.parent.rect(this.nx, this.ny-20, this.name.length()*25, 40, 15, 15, 15, 15);	// 後四個15為長方形的四個角的彎曲弧度

		this.parent.fill(255);
		this.parent.textSize(24);
		this.parent.text(this.name, this.nx+20, this.ny+10);
	}
}
