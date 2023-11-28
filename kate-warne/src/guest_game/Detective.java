package guest_game;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Detective {
	//private Image detective = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\kate.png").getImage();
	private Image detective = new ImageIcon("./assets/images/kate.png").getImage();
	public Image getDetective() {
		return detective;
	}
	//위치
	private int pos_X, pos_Y;
	//사다리를 탔는지 여부
	private boolean laddering = false;
	
	public int getPos_X() {
		return pos_X;
	}
	public void setPos_X(int pos_X) {
		this.pos_X = pos_X;
	}

	public int getPos_Y() {
		return pos_Y;
	}
	
	public void setPos_Y(int pos_Y) {
		this.pos_Y = pos_Y;
	}
	
	public void setPosition(int pos_X, int pos_Y) {
		this.pos_X = pos_X;
		this.pos_Y = pos_Y;
	}
	
	
	//키보드로 탐정 이동
	   public void moveToRight() {
	      if (pos_X <= 690)
	         pos_X += 30;
	   }
	   
	   public void moveToLeft() {
	      if (pos_X >= 0)
	         pos_X -= 30;
	   }
	   public void upper() {
	      if (laddering && pos_Y > 100)
	         pos_Y -= 35;
	   }
	   
	   public void lower() {
	      if (laddering && pos_Y <= 500)
	         pos_Y += 35;
	   }
	   //점프 (제자리 점프, 이동 점프)
	   public void jump() {
	      
	      if (KeyListener.pressedLeftKey) {
	         pos_X -= 100;
	         pos_Y -= 60;
	      }
	      else if (KeyListener.pressedRightKey) {
	         pos_X += 100;
	         pos_Y -= 60;
	      }   
	      else if (!KeyListener.pressedLeftKey) {
	         pos_Y -= 60;
	      }
	      else if (!KeyListener.pressedLeftKey) {
	         pos_Y -= 60;
	      }
	   }
	   public void descend() {
	      
	      if (KeyListener.pressedLeftKey) {
	         pos_X -= 100;
	         pos_Y += 60;
	      }
	      else if (KeyListener.pressedRightKey) {
	         pos_X += 100;
	         pos_Y += 60;
	      }   
	      else if (!KeyListener.pressedLeftKey) {
	         pos_Y += 60;
	      }
	      else if (!KeyListener.pressedRightKey) {
	         pos_Y += 60;
	      }
	   }
	
	//사다리 탔는지 확인
	public void checkLaddering() {
		if (140 <= getPos_X() && getPos_X() <= 200)
			laddering = true;
		else
			laddering = false;
	}
	
	//벽돌 벗어나는 경우	
	public void drop() {
		int gap = 30;
		if (pos_Y >= 85 - gap && pos_Y <= 85 + gap ) {
			if (pos_X >= 270 && pos_X <= 350){
				Main.stage.gameOver();
			}
			else if (pos_X >= 650) {
				setPosition(620,225);
			}
		}
		else if (pos_Y >= 225 - gap && pos_Y <= 225 + gap) {
			if (pos_X >= 210 && pos_X <= 410) {
				Main.stage.gameOver();
			}
			else if (pos_X >= 410 && pos_X <= 500) {
				setPosition(420,400);
			}
		}
		else if (pos_Y >= 400 - gap && pos_Y <= 85 + 400) {
			if (pos_X >= 270 && pos_X <= 410) {
				Main.stage.gameOver();
			}
		}
	    	}
}