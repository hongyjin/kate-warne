package katewarne;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	// 점프 시 왼쪽 or 오른쪽 방향키가 눌렸는지 확인하기 위해 선언
	public static boolean pressedLeftKey = false;
	public static boolean pressedRightKey = false;
	
	//공격 키
	 public static boolean pressedAttackKey = false;
	 private static final int attackRange = 80;
	 
	//키 눌렀을 때
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed: " + e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Main.detec.moveToLeft();
			pressedLeftKey = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Main.detec.moveToRight();
			pressedRightKey = true;
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			Main.detec.checkLaddering();
			Main.detec.upper();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Main.detec.lower();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Main.detec.jump();
		}
	    else if (e.getKeyCode() == KeyEvent.VK_A) {
            // 모든 용의자에게 공격을 가함
            for (Suspect suspect : Main.stage.getSusList()) {
                int distance = Math.abs(Main.detec.getPos_X() - suspect.getX());
                int h_distance = Math.abs(Main.detec.getPos_Y() - suspect.getY());
                if (distance <= attackRange && h_distance <= attackRange) {
                    // 추가: 용의자가 공격을 받은 경우 제거
                    Main.stage.removeSuspect(suspect);
                }
            }
        }
	}
	//키에서 뗐을 떄 
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key Released: " + e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pressedLeftKey = false;
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pressedRightKey = false;
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Main.detec.descend();
		}
	}
}
