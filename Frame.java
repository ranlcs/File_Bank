import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class Frame extends JFrame{
	

	public Frame(){
		super();
		getContentPane().add(new FileBank());
		setSize(1600,700);
		setLocation(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args){
		new Frame();
	}
}
