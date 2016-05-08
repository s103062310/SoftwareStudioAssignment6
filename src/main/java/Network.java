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
	private ArrayList<Character> members;	// �b���Y�ꤤ�����⪫��
	private float x, y, r;					// �ꪺ���ߦ�m�P�b�|
	private boolean bold;					// �ꪺ��زʲ�

	// Constructor
	public Network(MainApplet parent) {
		this.parent = parent;
		this.members = new ArrayList<Character>();
		this.x = 600;
		this.y = 370;
		this.r = 250;
		this.bold = false;
	}

	// ��ܵe��
	public void display() {
		// ���Y��D��
		this.parent.noFill();
		if(this.bold) this.parent.strokeWeight(5);
		else this.parent.strokeWeight(3);
		this.parent.stroke(145, 200, 65, 200);
		this.parent.arc(this.x, this.y, this.r * 2, this.r * 2, 0, PApplet.TWO_PI);
		
		// �b��̪����⤧�������Y�u
		for (Character ch : this.members) {
			for (Link l : ch.getTargets()){
				l.showLink(this.parent, ch);
			}
		}
	}

	// �]�w�ꪺ�ʲ�
	public void setBold(boolean b) {
		this.bold = b;
	}
	
	// �[����i���Y��
	public void addMember(Character c) {
		if (!c.inNet()) {
			this.members.add(c);
			c.setInNet(true);
			this.rearrange();
		}
	}
	
	// �q���Y�ꤤ�R������
	public void removeMember(Character c){
		c.setInNet(false);
		c.reset();
		this.members.remove(this.members.indexOf(c));
		this.rearrange();
	}
	
	// �P�_��ЬO�_�b�ꪺ���Ľd��
	public boolean inCircle(int x, int y) {
		if (PApplet.dist(this.x, this.y, x, y)<=this.r) return true;
		else return false;
	}

	// �N�ꤺ����M��
	public void clean() {
		for (Character ch : this.members) {
			ch.setInNet(false);
			ch.reset();
		}
		this.members.clear();
	}

	// �N�Ҧ����[�J��������[�i�ꤤ
	public void addAll(ArrayList<Character> characters) {
		for (Character ch : characters) {
			if (!members.contains(ch))
				this.addMember(ch);
		}
	}

	// �ꤺ�����m����
	private void rearrange(){
		// �N�@�Ӷ�P�������Ҧ��H
		float angle = PApplet.TWO_PI / this.members.size();
		float i = 0;
		for (Character ch : this.members) {
			// �ϥη��y���ഫ�]�w�b�ꤺ���s��m
			ch.setCirclePosition(this.x + this.r * PApplet.cos(angle*i), this.y + this.r * PApplet.sin(angle*i));
			i++;
		}
	}
	
}
