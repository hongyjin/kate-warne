package guest_game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Guest extends Suspect {
	//private Image GuestImg = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\최손님.png").getImage();
	private Image GuestImg = new ImageIcon("./assets/images/최손님.png").getImage();
	public Guest(int x, int y, int limitLeft_X, int limitRight_X) {
		super(x,y,limitLeft_X,limitRight_X);
	}
	public Image getGuest() {
		return GuestImg;
	}
}
