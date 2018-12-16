import javax.swing.JButton;

public class Celula extends JButton {
	private static final long serialVersionUID = 1L;
	
	Peca peca = null;
	Boolean v;
	
	public Celula(Boolean bw, Integer i, Integer j) {
		setBounds(90 * i, 90 * j, 90, 90);
		
		v = bw;
        setOpaque(true);
        setVisible(true);
	}

}
