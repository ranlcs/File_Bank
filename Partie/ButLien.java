import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ButLien extends JButton implements MouseListener{

	public ButLien(String t){
		super(t);
		setBackground(Constante.BACKGROUND);
		setForeground(Constante.TEXT_COLOR);
		setFont(new Font("Helvetica",Font.BOLD,12));
		addMouseListener(this);
		setBorderPainted(false);
	}

	@Override
	public void mouseEntered(MouseEvent m){
		setForeground(Constante.TEXT_OVER);
		repaint();
	}
	@Override
	public void mouseExited(MouseEvent m){
		setForeground(Constante.TEXT_COLOR);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent m){}
	@Override
	public void mouseClicked(MouseEvent m){}
	@Override
	public void mousePressed(MouseEvent m){}
}