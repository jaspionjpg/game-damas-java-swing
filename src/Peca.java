import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Peca extends JButton {
	private static final long serialVersionUID = 1L;
	
	Integer tipoPeca;
	Boolean v;
	
	public Peca(Boolean bw, Integer i, Integer j) {
		setBounds(90 * i, 90 * j, 90, 90);
		tipoPeca = 1;
		setContentAreaFilled(false);  
		if(bw){
			setIcon(new ImageIcon(new ImageIcon("branca.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT))); 
		} else {
			setIcon(new ImageIcon(new ImageIcon("preta.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT))); 
		}
		v = bw;
	}

}