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
	
	private String path = "main/resources/";						// 資料檔案路徑
	private String file = "starwars-episode-1-interactions.json";	// 資料檔名
	private JSONObject data;										// 資料物件(第一層)
	private JSONArray nodes, links;									// 資料陣列(第二層)
	private ArrayList<Character> characters;						// 所有角色陣列
	private Character dragging;										// 正被拖曳的腳色物件
	private Network circle;											// 關係圓
	private Button clean, addAll;									// 按鈕
	private int keycode=1;											// 集數
	private final static int width = 1200, height = 650;			// 視窗大小
	
	// 初始化
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
	
	// 畫出畫面
	public void draw() {
		// 背景
		background(255);
		
		// 標題
		fill(0);
		textSize(50);
		text("Star Wars "+keycode, 460, 70);
		
		// 關係圓與按鈕
		circle.display();
		clean.display();
		addAll.display();
		
		// 所有角色物件
		for (Character character : characters){
			character.display();
		}
		
		// 角色名字
		for (Character character : characters){
			if(character.isOver()) character.showName();
		}
	}
	
	// 鍵盤控制(按下)
	public void keyPressed(){
		// 設定資料檔名
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
		
		// 設定集數
		if(keyCode>=97 && keyCode<=103){
			keycode=keyCode-96;
		}
		setup();
	}
	
	// 滑鼠點選: 觸發按鈕功能
	public void mouseClicked(){
		if(clean.inBtn(mouseX, mouseY)) circle.clean();
		if(addAll.inBtn(mouseX, mouseY)) circle.addAll(characters);
	}
	
	// 滑鼠按下: 按鈕按下判定 + 角色拖曳判定 (以滑鼠位置是否在有效區域內判斷(inBtn))
	public void mousePressed(){
		// 顏色變藍以標示開始按鈕功能
		if(clean.inBtn(mouseX, mouseY)) clean.setClick(true);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setClick(true);
		
		// 辨別正在拖曳的是誰
		for(Character ch : characters){
			if(ch.inBtn(mouseX, mouseY)){
				ch.setDrag(true);
				dragging = ch;
				break;
			}
		}
	}
	
	// 滑鼠鬆開: 按鈕鬆開判定 + 角色拖曳結束判定與其後續動作
	public void mouseReleased(){
		// 顏色回復以標示結束按鈕功能
		if(clean.inBtn(mouseX, mouseY)) clean.setClick(false);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setClick(false);
		
		// 鬆開滑鼠一定不會使圓圈線變粗(沒有準備要將角色加入關係圓)
		circle.setBold(false);
		
		// 若有角色正被拖曳: 加入關係圓、退出關係圓、回到原本位置，並回復被拖曳者狀態
		if(dragging!=null&&dragging.isDrag()){
			if(!dragging.inNet()&&circle.inCircle(mouseX, mouseY)) circle.addMember(dragging);
			else if(dragging.inNet()&&!circle.inCircle(mouseX, mouseY)) circle.removeMember(dragging);
			else if(dragging.inNet()) dragging.returnToSite();
			else dragging.reset();
			dragging.setDrag(false);
			dragging.setOver(false);
		}		
	}
	
	// 滑鼠移動: 游標是否在按鈕的有效區域內的判定
	public void mouseMoved(){
		// 顏色變淺以標示滑鼠位於按鈕有效區內(可按)
		if(clean.inBtn(mouseX, mouseY)) clean.setOver(true);
		else clean.setOver(false);
		if(addAll.inBtn(mouseX, mouseY)) addAll.setOver(true);
		else addAll.setOver(false);
		
		// 圖形變大以標示滑鼠位於角色有效區內(可點選)
		for(Character ch : characters){
			if(ch.inBtn(mouseX, mouseY)) ch.setOver(true);
			else ch.setOver(false);
		}
	}
	
	// 滑鼠拖曳: 是否進入加入關係圓之有效區域內判定 + 及時改變角色物件位置
	public void mouseDragged(){
		if(circle.inCircle(mouseX, mouseY)) circle.setBold(true);
		else circle.setBold(false);
		if(dragging!=null&&dragging.isDrag()) dragging.setPosition(mouseX, mouseY);
	}

	// 讀取資料: 依照lab所教方法依序存取data
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
