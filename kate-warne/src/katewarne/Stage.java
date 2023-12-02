package katewarne;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Iterator;


public class Stage extends Thread {
	
      	
	 private boolean isRunning = false;
	 public static boolean getKey = false;
	 private Key key;  // Key 클래스 인스턴스 추가
	 public synchronized void startStage() {
	        if (!isRunning) {
	            isRunning = true;
	            start();
	        }
	    }
	// 생성자에서 Key 클래스 인스턴스 초기화
	public Stage() {
		key = Key.getInstance();
	}
	//단서 담을 리스트
	private ArrayList<Clue> itemList = new ArrayList<>();
	//몬스터 담을 리스트
	private ArrayList<Suspect> susList = new ArrayList<>();
	//스테이지 클리어 메시지 위치 초기화
	private int x = 440;
	private int y = 0;
	
	public static boolean stageClear = false;
	
	public static boolean eatGrape = false;
	
	//루프 탈출용 변수
	private boolean stop = false;
	
	public ArrayList<Suspect> getSusList() {
		return susList;
	}
	
	public ArrayList<Clue> getItemList(){
		return itemList;
	}
	public void makeItems(Clue item) {
		itemList.add(item);
	}
	
	public void makeSuspect(Suspect suspect) {
		susList.add(suspect);
	}
	//아이템을 그려줌
	public void drawItems(Graphics g) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i) instanceof Papers) {
				//itemList안에 Papers 클래스의 인스턴스가 있는 경우 실행
				Papers pap = (Papers)itemList.get(i);
				g.drawImage(pap.getPapers(), pap.getPos_x(),pap.getPos_y(),40,40,null);
			}
			else if (itemList.get(i) instanceof Box) {
				Box bo = (Box)itemList.get(i);
				g.drawImage(bo.getBox(),bo.getPos_x(),bo.getPos_y(),40,40,null);
			}
		}
	}
	//용의자를 그려줌
	public void drawSuspect(Graphics g) {
		for (int i = 0; i < susList.size(); i++) {
			if (susList.get(i) instanceof Guest) {
				//itemList안에 Papers 클래스의 인스턴스가 있는 경우 실행
				Guest guest = (Guest)susList.get(i);
				g.drawImage(guest.getGuest(), guest.getX(),guest.getY(),40,40,null);
			}
			else if (susList.get(i) instanceof Lawyer) {
				Lawyer lawyer = (Lawyer)susList.get(i);
				g.drawImage(lawyer.getGuest(), lawyer.getX(),lawyer.getY(),40,40,null);
			}
		}
	}
	
	// 아이템을 모두 획득한 경우 호출되는 메서드
	public void handleGameSuccess() {
		Object[] options = {"예", "다시하기"};
		String message = "Game Clear!\n다시 방으로 이동하시겠습니까?";

		int result = JOptionPane.showOptionDialog(
			    null, 
			    message, 
			    "게임 클리어", 
			    JOptionPane.YES_NO_OPTION, 
			    JOptionPane.INFORMATION_MESSAGE, 
			    null, 
			    options, 
			    options[1]
			);


	    // 확인 버튼을 눌렀을 때 GuestRoom으로
	    if (result == JOptionPane.OK_OPTION) {
	        Main.getInstance().switchToGuestRoom();

	    }

	    // 추가: 게임 클리어 시 초기화 작업 수행
	    Main.detec.setPosition(30, 390); // 탐정의 초기 위치로 이동
	    itemList.clear(); // 아이템 리스트 초기화
	    susList.clear(); // 용의자 리스트 초기화

	    // 새로운 Stage 스레드 생성 및 시작
	    Main.stage = new Stage();
	    Main.stage.startStage();
	}
	
	// 아이템 획득 효과
	public void eatItems() {
	    for (int i = itemList.size() - 1; i >= 0; i--) {
	        Clue item = itemList.get(i);
	        int itemX = item.getPos_x();
	        int itemY = item.getPos_y();
	        int itemWidth = 20;
	        int itemHeight = 50;

	        int detectiveX = Main.detec.getPos_X();
	        int detectiveY = Main.detec.getPos_Y();

	        if ((detectiveX >= itemX && detectiveX <= itemX + itemWidth && detectiveY <= itemY + itemHeight && detectiveY >= itemY - itemHeight) ||
	                (detectiveX + 40 >= itemX && detectiveY >= itemY && detectiveX + 40 <= itemX + itemWidth && detectiveY <= itemY + itemHeight && detectiveY >= itemY - itemHeight)) {
	            // 아이템을 획득한 경우 
	            handleItemAcquisition(item);
	            //itemList.remove(i);
	        }
	    }
	}

	// 아이템 획득 후의 동작 처리
	private void handleItemAcquisition(Clue item) {
		itemList.remove(item);
	    // 기타 게임 클리어 등의 처리
	    checkGameStatus();
	}

	// 게임 클리어 등의 처리
	private void checkGameStatus() {
	    // itemList가 비어있으면 "성공" 
	    if (itemList.isEmpty()) {	   
	        
	        key.setKeyImage(key.getSuccessfulGames(), true); // 키를 증가시키는 함수 추가
	        getKey =true;
	        //MainFrame.getInstance().setKeyStatus(getKey);
	        GuestRoom guestRoomInstance = GuestRoom.getInstance();
            if (guestRoomInstance != null) {
                guestRoomInstance.setKeyStatus(getKey);
            }
	        handleGameSuccess(); 

	    }
	}

	
	
	
	//용의자 공격
	public void removeSuspect(Suspect suspect) {
        susList.remove(suspect);
    }
	// 게임 오버 메서드 
	public void gameOver() {
	    JOptionPane.showMessageDialog(null, "게임 오버", "게임 오버", JOptionPane.INFORMATION_MESSAGE);

	    // 초기화 작업 수행
	    Main.detec.setPosition(30, 390); 
	    itemList.clear(); 
	    susList.clear(); 
	    
	    // 게임 재시작
	    //stop = true; // 스레드 정지
	    Main.stage.interrupt();

	    Main.stage = new Stage();
	    Main.stage.startStage();
	}


	//스레드 실행시 아이템을 생성하도록
	@Override
	public void run() {
		makeItems(new Papers(78, 435));
		makeItems(new Papers(210, 435));
		makeItems(new Papers(70, 110));
		makeItems(new Box(220, 120));
		makeItems(new Papers(630, 250));
		makeItems(new Box(530, 120));
		makeItems(new Box(500, 433));
		makeItems(new Papers(620, 435));
		
		susList.add(Main.s1);
		susList.add(Main.s2);
		susList.add(Main.s3);
		
		
		//아이템 있을때까지 eatItems
		while (itemList.size() > 0 && !isInterrupted()) {
		    eatItems();
		    Main.detec.checkLaddering();
		    Main.detec.drop();
		    
		    
		    // 용의자와 만나면 게임오버
		    for (Suspect suspect : susList) {
		        if (Math.abs(Main.detec.getPos_X() - suspect.getX()) < 40 && Math.abs(Main.detec.getPos_Y() - suspect.getY()) < 42) {
		            gameOver();
		            return; 
		        }
		    }
	
		    
		}
		
	}
}