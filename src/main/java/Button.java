package main.java;

import processing.core.PApplet;

public class Button {
	
	private PApplet parent;
	private int x, y, width, height, amount;	// 按鈕位置、長寬與字的位移量
	private String text;						// 按鈕上的字
	private boolean over, click;				// 滑入與按下的flag
	
	// Constructor
	public Button(PApplet p, int x, int y, int amount, String text){
		this.parent = p;
		this.x = x;
		this.y = y;
		this.amount = amount;
		this.text = text;
		this.over = false;
		this.click = false;
		this.width = 200;
		this.height = 50;
	}
	
	// 畫出畫面
	public void display(){
		// 按鈕主體
		this.parent.noStroke();
		if(this.click) this.parent.fill(10, 160, 250, 200);
		else if(this.over) this.parent.fill(180, 210, 180, 200);
		else this.parent.fill(130, 180, 150, 200);
		this.parent.rect(this.x, this.y, this.width, this.height);
		
		// 按鈕的字
		this.parent.fill(255);
		this.parent.textSize(22);
		this.parent.text(this.text, this.x+this.amount, this.y+35);
	}
	
	// 設定是否滑入
	public void setOver(boolean b){
		this.over = b;
	}
	
	// 設定是否按下
	public void setClick(boolean b){
		this.click = b;
	}
	
	// 判斷是否於有效區域內
	public boolean inBtn(int x, int y){
		if(x>=this.x&&x<=this.x+this.width&&y>=this.y&&y<=this.y+this.height)
			return true;
		else return false;
	}
	
}
