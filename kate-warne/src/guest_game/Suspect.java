package guest_game;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class Suspect extends Thread {
	
	 boolean isRight = true;
	//위치
	private int x, y;
	// 한계 위치
	private int limitLeft_X, limitRight_X;
	// 루프 탈출 변수
	private boolean stop = false;
	//생성자
	public Suspect(int x, int y, int limitLeft_X, int limitRight_X) {
		this.x = x;
		this.y = y;
		this.limitLeft_X = limitLeft_X;
		this.limitRight_X = limitRight_X;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
    public void run() {
        // Timer 및 TimerTask를 사용하여 주기적으로 위치를 업데이트하고 화면을 다시 그림
        Timer timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!stop) {
                    if (isRight) {
                    	moveSmoothly();
                    }
                    else {
                    	moveLeft();
                    }
                }
            }
        }, 0, 140); // 용의자 움직이는 속도 

        // 일정 시간이 지난 후에 타이머 중지 
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 타이머 중지
        timer.cancel();
    }
	
	private void moveSmoothly() {
	    int deltaX = 5; // 움직임 속도

	    if (x < limitRight_X ) {
	        x += deltaX;
	    } else {
	    	isRight = false;
	        moveLeft(); 
	    }
	}
	private void moveLeft() {
		int deltaX = 5;
		if (x > limitLeft_X ) {
	        x -= deltaX;
	    } else {
	    	isRight = true;
	        moveSmoothly(); 
	    }
	}
	
	public void close() {
		this.stop = true;
	}
	
}
