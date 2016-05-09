package main.java;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/

@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	
	private String path = "main/resources/";						// ����ɮ׸��|
	private String file = "starwars-episode-1-interactions.json";	// ����ɦW
	private JSONObject data;										// ��ƪ���(�Ĥ@�h)
	private JSONArray nodes, links;									// ��ư}�C(�ĤG�h)
	private ArrayList<Character> characters;						// �Ҧ�����}�C
	private Character dragging;										// ���Q�즲���}�⪫��
	private Network circle;											// ���Y��
	private Button clean, addAll;									// ���s
	private int keycode=1;											// ����
	private final static int width = 1200, height = 650;			// �����j�p
	
	// ��l��
	public void setup() {
		size(width, height);
		circle = new Network(this);
		addAll = new Button(this, 950, 70, 50, "ADD ALL");
		clean = new Button(this, 950, 170, 60, "CLEAN");
		characters = new ArrayList<Character>();
		dragging = null;
		loadData();
		smooth();
		Ani.init(this);
	}
	
	// �e�X�e��
	public void draw() {
		// �I��
		background(255);
		
		// ���D
		fill(0);
		textSize(50);
		text("Star Wars "+keycode, 460, 70);
		
		// ���Y��P���s
		circle.display();
		clean.display();
		addAll.display();
		
		// �Ҧ����⪫��
		for (Character character : characters){
			character.display();
		}
		
		// ����W�r
		for (Character character : characters){
			if(character.isOver()) character.showName();
		}
	}
	
	// ��L����(���U)
	public void keyPressed(){
		// �]�w����ɦW
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
		
		// �]�w����
		if(keyCode>=97 && keyCode<=103){
			keycode=keyCode-96;
		}
		setup();
	}
	
	// �ƹ��I��: Ĳ�o���s�\��
	public void mouseClicked(){
		if(clean.inBtn(mouseX, mouseY)) circle.clean();
		if(addAll.inBtn(mouseX, mouseY)) circle.addAll(characters);
	}
	
	// �ƹ����U: ���s���U�P�w + ����즲�P�w (�H�ƹ���m�O�_�b���İϰ줺�P�_(inBtn))
	public void mousePressed(){
		// �C�����ťH�Хܶ}�l���s�\��
		if(clean.inBtn(mouseX, mouseY)) clean.setClick(true);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setClick(true);
		
		// ��O���b�즲���O��
		for(Character ch : characters){
			if(ch.inBtn(mouseX, mouseY)){
				ch.setDrag(true);
				dragging = ch;
				break;
			}
		}
	}
	
	// �ƹ��P�}: ���s�P�}�P�w + ����즲�����P�w�P�����ʧ@
	public void mouseReleased(){
		// �C��^�_�H�Хܵ������s�\��
		if(clean.inBtn(mouseX, mouseY)) clean.setClick(false);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setClick(false);
		
		// �P�}�ƹ��@�w���|�϶��u�ܲ�(�S���ǳƭn�N����[�J���Y��)
		circle.setBold(false);
		
		// �Y�����⥿�Q�즲: �[�J���Y��B�h�X���Y��B�^��쥻��m�A�æ^�_�Q�즲�̪��A
		if(dragging!=null&&dragging.isDrag()){
			if(!dragging.inNet()&&circle.inCircle(mouseX, mouseY)) circle.addMember(dragging);
			else if(dragging.inNet()&&!circle.inCircle(mouseX, mouseY)) circle.removeMember(dragging);
			else if(dragging.inNet()) dragging.returnToSite();
			else dragging.reset();
			dragging.setDrag(false);
			dragging.setOver(false);
		}		
	}
	
	// �ƹ�����: ��ЬO�_�b���s�����İϰ줺���P�w
	public void mouseMoved(){
		// �C���ܲL�H�Хܷƹ������s���İϤ�(�i��)
		if(clean.inBtn(mouseX, mouseY)) clean.setOver(true);
		else clean.setOver(false);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setOver(true);
		else addAll.setOver(false);
		
		// �ϧ��ܤj�H�Хܷƹ���󨤦⦳�İϤ�(�i�I��)
		for(Character ch : characters){
			if(ch.inBtn(mouseX, mouseY)) ch.setOver(true);
			else ch.setOver(false);
		}
	}
	
	// �ƹ��즲: �O�_�i�J�[�J���Y�ꤧ���İϰ줺�P�w + �ήɧ��ܨ��⪫���m
	public void mouseDragged(){
		if(circle.inCircle(mouseX, mouseY)) circle.setBold(true);
		else circle.setBold(false);
		if(dragging!=null&&dragging.isDrag()) dragging.setPosition(mouseX, mouseY);
	}

	// Ū�����: �̷�lab�ұФ�k�̧Ǧs��data
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
			int value = l.getInt("value");
			characters.get(source).addTarget(new Link(characters.get(target), value));
		}
	}

}
