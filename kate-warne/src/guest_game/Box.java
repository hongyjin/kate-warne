package guest_game;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Box extends Clue {
	//private Image box = new ImageIcon("C:\\Users\\user\\Downloads\\자바 일러스트 모음\\택배상자.png").getImage();
	private Image box = new ImageIcon("../../../assets/images/택배상자.png").getImage();
	public Box(int pos_x, int pos_y) {
		super(pos_x, pos_y);
	}
	public Image getBox() {
		return box;
	}
}


