package katewarne;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Papers extends Clue {

	private Image papers = new ImageIcon("./assets/images/범행 기록 문서.png").getImage();
	public Papers(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}
	public Image getPapers() {
		return papers;
	}

}
