import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.BorderFactory;

public class InterfFichier extends JPanel implements MouseListener{

	protected String idFile="";

	private PanelImage sary=new PanelImage();
	private PanelString titre=new PanelString();

	public InterfFichier(){
		super();
		setLayout(new BorderLayout());

		add(sary,BorderLayout.WEST);
		JPanel temp = new JPanel(new GridLayout(0,1));
		temp.add(titre);
		add(temp,BorderLayout.CENTER);
		addMouseListener(this);

		// setPreferredSize(new Dimension(2000,PanelImage.COTE));
	}
	public InterfFichier(String id){
		this();
		idFile=id;
	}
	public InterfFichier(String id,Image sary,String titre){
		this();
		this.idFile=id;
		this.sary.setImage(sary);
		this.titre.setText(titre);
	}

	public InterfFichier(Image im,String t){
		this();
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
	}

	@Override
	public void setBackground(Color c){

	}

	@Override
	public void mouseClicked(MouseEvent m){
		System.out.println("tsindry le manana id = "+idFile);
	}
	@Override
	public void mousePressed(MouseEvent m) { }
	@Override
	public void mouseReleased(MouseEvent m) { }
	@Override
	public void mouseEntered(MouseEvent m) { 
		setBorder(BorderFactory.createLineBorder(Constante.BORDER,3));
	}
	@Override
	public void mouseExited(MouseEvent m) { 
		setBorder(null);
	}

}