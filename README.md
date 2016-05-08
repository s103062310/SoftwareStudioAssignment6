# Software Studio Assignment 6

## Notes
+ You will have to import the libraries on your own. All the libraries are provided on iLMS.
+ Even you followed the design TA shown in the video, you still have to explain the control of the program in the next section.

## Explanation of the Design
Explain your design in this section.  

### Operation
+ 按鍵盤數字鍵1~7可切換集數
+ 滑鼠滑進角色圓上會顯示名條並變大
+ 角色圓可拖曳，若拖進關係圓內可加入分析，拖出圓外可移出分析，其餘回到原本的固定位置(圓上或左側)
+ 關係圓內每次加入新角色都會重新排列一次
+ Add All 按鈕: 將剩餘角色全部加入分析
+ Clear 按鈕: 將關係圓清空
+ 按鈕滑入與按下會有顏色改變，使使用者更清楚目前狀態

### Visualization
+ 每條關係根據資料程度有不同粗細

### Implementation

#### MainApplet.java
+ 定義視窗內所有物件並顯示
+ 使用 ArrayList 管理所有成員
+ 使用 keyPressed() 控制鍵盤操作
+ 使用 mouseClicked()、mousePressed()、mouseReleased()、mouseMoved()、mouseDragged() 控制滑鼠操作

#### Character.java

#### Link.java

#### Network.java
+ 定義關係圓外觀與狀態(bold) => 透過 inBtn(...)
+ 使用 ArrayList 管理現有的分析成員
+ 可動態加入或刪除成員
+ 顯示現有成員之間所有關係線
+ 實作兩個按鈕的功能
+ 使用極座標轉換設定在圓內的新位置以達到重新排列之效果

#### Button.java
+ 只定義 Button 統一外觀與狀態(over or click) => 透過 inBtn(...)
+ 功能部分於 MainApplet 使用 processing 內建滑鼠動作偵測 method 來實作

## Problems encountered and solved
+ 用到許多lab上沒接觸過的 processing 功能，透過瀏覽官方網站之 tutorial & examples 學習
