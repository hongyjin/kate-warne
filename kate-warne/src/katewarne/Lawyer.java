package katewarne;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Lawyer extends Suspect {
	//private Image LawyerImg = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\김변호.png").getImage();
	private Image LawyerImg = new ImageIcon("./assets/images/김변호.png").getImage();
	public Lawyer(int x, int y, int limitLeft_X, int limitRight_X) {
		super(x,y,limitLeft_X,limitRight_X);
	}
	public Image getGuest() {
		return LawyerImg;
	}
}
