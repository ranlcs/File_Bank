import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Graphics;
import javax.imageio.ImageIO;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class FileBank extends JPanel implements MouseListener{
	private Image retour=null;
	private Client client = new Client();
	private Connexion connexion = new Connexion();
	private SignUp signUp = new SignUp();
	private CardLayout carte = new CardLayout();
	private FileBank fb = this;
	private String pagePrec = "connexion";
	private boolean admin=false;
	private String id="";


	public FileBank(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException c){
			System.out.println(c);
		}
		try{
			retour = ImageIO.read(getClass().getResource("sary/retour.png"));
		}catch(Exception e){
			System.out.println("File not found");
		}
		setLayout(carte);
		carte.addLayoutComponent(signUp,"signUp");
		carte.addLayoutComponent(connexion,"connexion");
		carte.addLayoutComponent(client,"client");

		add(client); 
		add(connexion);
		add(signUp);
		addMouseListener(this);


		//pour voir la navigation
		carte.show(this,"connexion");
		signUp.addValiderActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				if(signUp.verifierDonne()){
					carte.show(fb,"client");
					pagePrec = "signUp";
				}
			}
		});

		connexion.addSignUpActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				carte.show(fb,"signUp");
				pagePrec = "connexion";
			}
		});
		connexion.addButActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				if(connexion.verifierDonne()){
					pagePrec = "connexion";
					carte.show(fb,"client");
				}
			}
		});
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(retour!=null)
			g.drawImage(retour,0,0,null);
	}

	@Override
	public void mouseClicked(MouseEvent m){
		if(0<m.getX() && m.getX()<retour.getWidth(null) && 0<m.getY() && m.getY()<retour.getHeight(null))
			carte.show(fb,pagePrec);

	}
	@Override
	public void mouseReleased(MouseEvent m) { }
	@Override
	public void mousePressed(MouseEvent m) { }
	@Override
	public void mouseEntered(MouseEvent m) { }
	@Override
	public void mouseExited(MouseEvent m) { }




}
	