package main.java;

public class Link {
	
	private Character target;
	private int value;
	// constructor, t : �s��������Av : �ӽu���ʲ�
	public Link(Character t, int v){
		this.target = t;
		this.value = v;
	}
	
	public void showLink(MainApplet p, Character s){			
		
		if(!this.target.inNet()) return;
		// ���`�I�����I(midx,midy)
		float midx = (s.getX()+target.getX()) / 2;	
		float midy = (s.getY()+target.getY()) / 2;
		// (600,370)�O��ߩҦb�A(x1,y1)�B���I�B��� �T�I�@�u
		float x1 = 600 + (midx-600)*3;
		float y1 = 370 + (midy-370)*3;
		
		p.stroke(100, 100, 100, 200);
		// ���קK�u�Ӳʼv�T��ı�ĪG�A�N�u���ʲ�(value)���u�ʽվA
		p.strokeWeight((this.value/10+1)*2);
		// (x1,y1)�����u���W�V�I�A���u����ݤ��O�s����`�I : (s.getX(),s.getY())�A(target.getX(),target.getY())�C
		p.curve(x1, y1, s.getX(), s.getY(), target.getX(), target.getY(), x1, y1);
	}
	
}
