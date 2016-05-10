package main.java;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

/**
 * This class is used to store states of the characters in the program. You will
 * need to declare other variables depending on your implementation.
 */

public class Character{

	private float x, y, r, nx, ny;			// �O�s�����m���ܫe�� x �y�СA���ܫe�� y �y�СA�b�|�A�����e�� x �y�СA�����e�� y �y�СC
	private int id;							// �Ө��⪺�s���C
	private String name, colour;			// �Ө��⪺�W�r�A�C��C
	private MainApplet parent;
	private ArrayList<Link> targets;		// �Ҧ��P�Ө���۳s�����ⳣ�s�� targets ���C
	private boolean isdrag, inNet, over;	// �O�_���Q�즲�A�O�_�b���Y�ꤺ�A�O�_��Х��m���W�C

	// constructor
	public Character(MainApplet parent, String name, String colour, int id) {
		this.parent = parent;
		this.name = name;
		this.colour = colour;
		this.id = id;
		// �̷�id�A��X�Ӹ`�I�b�����C����m�C�C�榳10�Ӹ`�I�C
		this.x = 50 + (id / 10) * 60;
		this.y = 50 + (id % 10) * 60;
		this.nx = 50 + (id / 10) * 60;
		this.ny = 50 + (id % 10) * 60;
		this.isdrag = false;				
		this.inNet = false;					// �@�}�l�����󥪰��C���A���b���Y�ꤺ�C
		// �N�Ө���ҳs�����Ҧ�����A�P������ link �� value �s�� targets ���C
		this.targets = new ArrayList<Link>();
		//Ani.init(this.parent);
	}
	// �]�m�Ө���ꪺ�S�ʡC
	public void display() {
		int hi = PApplet.unhex(this.colour.substring(1, 9));		// �N hex color �ন rgb
		this.parent.fill(hi, 200);
		if(this.over) this.r = 25;									// ���в���Ө����W�ɡA�Ӷ��j���b�|=25
		else this.r = 20;											// ���ɤ@����ꪺ�b�|=20
		this.parent.ellipse(this.nx, this.ny, this.r*2, this.r*2);	// �e�X�Ө����
	}
	// ��� inNet�A�H��ܸӨ���O�_������Y�ꤺ�C
	public void setInNet(boolean b){
		this.inNet = b;
	}
	// Ū�� inNet�C
	public boolean inNet(){
		return this.inNet;
	}
	// ��� isdrag�A�H��ܸӨ���O�_���Q�즲�C
	public void setDrag(boolean b){
		this.isdrag = b;
	}
	// Ū�� isdrag�C
	public boolean isDrag(){
		return this.isdrag;
	}
	// ��� over�A�H��ܴ�ЬO�_�����Ө��⤧�W�C
	public void setOver(boolean b){
		this.over = b;
	}
	// Ū�� over�C
	public boolean isOver(){
		return this.over;
	}
	// ����Q�즲�ɡA���]���m
	public void setPosition(float x, float y) {
		this.nx = x;
		this.ny = y;
	}
	// �]�m�Ө���b���Y�ꤤ����m
	public void setCirclePosition(float x, float y){
		this.x = x;
		this.y = y;
		this.setPosition(this.x, this.y);
	}
	// ���o�Ө����e�� x �y��
	public float getX(){
		return this.nx;
	}
	// ���o�Ө����e�� y �y��
	public float getY(){
		return this.ny;
	}
	// ����Q�즲��A�^��쥻�b���Y�ꪺ��m
	public void returnToSite(){
		this.setPosition(this.x, this.y);
	}
	// �^�_��@�}�l�b�����C����m
	public void reset(){
		// ��X�Ө���b�����C����l��m (x,y) 
		this.x = 50 + (this.id / 10) * 60;
		this.y = 50 + (this.id % 10) * 60;
		// �ʵe�k��
		Ani.to(this, 1, "nx", this.x);
		Ani.to(this, 1, "ny", this.y);
	}
	// �N�P�Ө���۳s������[�J targets �� ArrayList
	public void addTarget(Link l) {
		this.targets.add(l);
	}
	// ���o����P�Ө���۳s��������
	public ArrayList<Link> getTargets() {
		return this.targets;
	}
	// �H����ꪺ�b�|�@���d��̾ڡA�P�_�Ө����O�_�b�I���d��
	public boolean inBtn(int x, int y){
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}
	// ��ܸ`�I���W�r�P��r�϶�
	public void showName(){
		this.parent.fill(180, 210, 180);
		this.parent.rect(this.nx, this.ny-20, this.name.length()*25, 40, 15, 15, 15, 15);	// ��|��15������Ϊ��|�Ө����s������

		this.parent.fill(255);
		this.parent.textSize(24);
		this.parent.text(this.name, this.nx+20, this.ny+10);
	}
}
