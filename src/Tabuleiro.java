import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tabuleiro extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	Peca[][] pecas = new Peca[8][8];
	
	public Tabuleiro() {
		setSize(720,725);
		setUndecorated(true);
		getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setTitle("Damas");
        setResizable(false);
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        
        inicializaComponents();
        
        setVisible(true);
	}

	private void inicializaComponents() {
		Boolean bw = true;
		for(int i = 0; i < 8 ; i++){
			bw = !bw;
			for(int j = 0; j < 8 ; j++){
				if(bw){
					JLabel celula = new JLabel();
					celula.setBounds(90 * i, 90 * j, 90, 90);
					celula.setOpaque(true);
					celula.setVisible(true);
					celula.setBackground(Color.BLACK);
					add(celula);
					if(i < 3){
						pecas[i][j] = new Peca(true,i,j);
						pecas[i][j].addActionListener(this);
						add(pecas[i][j], 1);
					} else if (i > 4){
						pecas[i][j] = new Peca(false,i,j);
						pecas[i][j].addActionListener(this);
						add(pecas[i][j], 1);
					}
				} 
				bw = !bw;
			}
		}
	}
	
	JButton mover1;
	JButton mover2;
	Boolean matar1 = false;
	Boolean matar2 = false;
	int xPecaClicada;
	int yPecaClicada;
	Boolean vez = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(mover1)){
			int k = 1;
			if(pecas[xPecaClicada][yPecaClicada].v){
				k *= -1;
			}
			if(matar1){
				pecas[xPecaClicada][yPecaClicada].setBounds(90 * (xPecaClicada-2 * k), 90 * (yPecaClicada-2), 90, 90);
				pecas[xPecaClicada-2 * k][yPecaClicada-2] = pecas[xPecaClicada][yPecaClicada];
				pecas[xPecaClicada-1 * k][yPecaClicada-1].setVisible(false);
				pecas[xPecaClicada-1 * k][yPecaClicada-1] = null;
			} else {
				pecas[xPecaClicada][yPecaClicada].setBounds(90 * (xPecaClicada-1 * k), 90 * (yPecaClicada-1), 90, 90);
				pecas[xPecaClicada-1 * k][yPecaClicada-1] = pecas[xPecaClicada][yPecaClicada];
			}
			pecas[xPecaClicada][yPecaClicada] = null;
			mover1.setVisible(false);
			mover2.setVisible(false);
			matar1 = false;
			vez = !vez;
			repaint();
		} else if(e.getSource().equals(mover2)){
			int k = 1;
			if(pecas[xPecaClicada][yPecaClicada].v){
				k *= -1;
			}
			if(matar2){
				pecas[xPecaClicada][yPecaClicada].setBounds(90 * (xPecaClicada-2 * k), 90 * (yPecaClicada+2), 90, 90);
				pecas[xPecaClicada-2 * k][yPecaClicada+2] = pecas[xPecaClicada][yPecaClicada];
				pecas[xPecaClicada-1 * k][yPecaClicada+1].setVisible(false);
				pecas[xPecaClicada-1 * k][yPecaClicada+1] = null;
			} else {
				pecas[xPecaClicada][yPecaClicada].setBounds(90 * (xPecaClicada-1 * k), 90 * (yPecaClicada+1), 90, 90);
				pecas[xPecaClicada-1 * k][yPecaClicada+1] = pecas[xPecaClicada][yPecaClicada];
			}
			pecas[xPecaClicada][yPecaClicada] = null;
			mover2.setVisible(false);
			mover2 = null;
			mover1.setVisible(false);
			mover1 = null;
			matar2 = false;
			vez = !vez;
			repaint();
		} else { 
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					if(e.getSource().equals(pecas[i][j])){
						if(pecas[i][j].v == vez){
							matar1 = false;
							matar2 = false;
							int k = 1;
							xPecaClicada = i;
							yPecaClicada = j;
							if(pecas[i][j].v){
								k *= -1;
							}
							if(j > 0){
								mover1 = new JButton();
								if(pecas[i-1 * k][j-1] != null){
									if(pecas[i][j].v != pecas[i-1 * k][j-1].v){
										mover1.setBounds(90 * (i-2 * k), 90 * (j-2), 90, 90);
										matar1 = true;
									}
								} else {
									mover1.setBounds(90 * (i-1 * k), 90 * (j-1), 90, 90);
								}
								mover1.setOpaque(true);
								mover1.setBackground(Color.GREEN);
								mover1.addActionListener(this);
								add(mover1, 1);
							}
							if(j < 7){
								mover2 = new JButton();
								if(pecas[i-1 * k][j+1] != null){
									if(pecas[i][j].v != pecas[i-1 * k][j+1].v){
										mover2.setBounds(90 * (i-2 * k), 90 * (j+2), 90, 90);
										matar2 = true;
									}
								} else {
									mover2.setBounds(90 * (i-1 * k), 90 * (j+1), 90, 90);
								}
								mover2.setOpaque(true);
								mover2.setBackground(Color.GREEN);
								mover2.addActionListener(this);
								add(mover2, 1);
							}
							repaint();
							break;
						}
					}
				}
			}
		}
	}
}