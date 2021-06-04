import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexion extends JPanel implements ItemListener{
	private TextTransFileBank identifiant = new TextTransFileBank(14,"e-mail");
	private TextTransFileBank mdp = new TextTransFileBank(14,"Mot de passe");
	private JButton but = new JButton("Connexion");
	private JCheckBox check = new JCheckBox();
	private ButLien signUp = new ButLien("Sign Up");
	private ButLien forgot = new ButLien("Forgot your password?");

	public Connexion(){
		ajouterElement();
		check.addItemListener(this);
		mdp.setPassword();
	}

	public void ajouterElement(){
		JPanel con = new JPanel();
		con.setPreferredSize(new Dimension(300,200));
		con.setBackground(Constante.BACKGROUND);
		con.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5),"Connexion",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(Font.SANS_SERIF,Font.BOLD,17),Color.WHITE));
		JPanel panId = new JPanel();
		panId.setBackground(Constante.BACKGROUND);
		
		JPanel panMdp = new JPanel();
		panMdp.setBackground(Constante.BACKGROUND);

		JPanel panCheck = new JPanel();
		panCheck.setLayout(new BorderLayout());
		check.setBackground(Constante.BACKGROUND);
		JLabel temp = new JLabel("Print password");
		temp.setForeground(new Color(188,193,213));
		panCheck.setBackground(Constante.BACKGROUND);

		con.add(panId);
		panId.add(identifiant);

		con.add(panMdp);
		panMdp.add(mdp);

		con.add(panCheck);
		JPanel pPanCheck=new JPanel();
		pPanCheck.add(check);
		pPanCheck.setBackground(Constante.BACKGROUND);
		pPanCheck.add(temp);
		JPanel checkTemp = new JPanel();
		JPanel ch = new JPanel(new BorderLayout());
		ch.setBackground(Constante.BACKGROUND);
		ch.add(pPanCheck,BorderLayout.WEST);
		panCheck.add(checkTemp,BorderLayout.WEST);
		checkTemp.setBackground(Constante.BACKGROUND);
		checkTemp.setPreferredSize(new Dimension(60,0));
		panCheck.add(ch,BorderLayout.CENTER);

		JPanel panBut = new JPanel();
		panBut.setBackground(Constante.BACKGROUND);
		panBut.add(but);
		con.add(panBut);

		JPanel panDern = new JPanel();
		panDern.setBackground(Constante.BACKGROUND);
		panDern.add(signUp,BorderLayout.WEST);
		panDern.add(forgot,BorderLayout.CENTER);

		con.add(panDern);

		BoxLayout t = new BoxLayout(con,BoxLayout.Y_AXIS);
		con.setLayout(t);

		JPanel haut = new JPanel();
		JPanel bas = new JPanel();
		JPanel centre = new JPanel();
		centre.add(con);
		setLayout(new GridLayout(3,1));
		add(haut);
		add(centre);
		add(bas);
		setBackground(Constante.BACKGROUND);
	}

	@Override
	public void itemStateChanged(ItemEvent i){
		mdp.setPassword();
		mdp.requestFocus();
	}
	public void addButActionListener(ActionListener a){
		but.addActionListener(a);
	}

	public void addSignUpActionListener(ActionListener a){
		signUp.addActionListener(a);
	}

	public boolean verifierDonne(){
		String mail = identifiant.getText();
		String pass = mdp.getText();
		Connection file_bank=null;
		boolean res=false;
		try{
			file_bank=DriverManager.getConnection("jdbc:mysql://localhost:3306/file_bank","root","12345678");
			try{
				PreparedStatement p = file_bank.prepareStatement("SELECT * FROM compte WHERE id = ?");
				p.setString(1,mail);
				ResultSet s = p.executeQuery();
				if(s.next()){
					String salt=s.getString("salt");
					String cle=s.getString("cle");
					String pseudo=s.getString("nom");
					if(HashPassword.verifyPassword(pass,cle,salt)){
						res=true;
						identifiant.setText("");
						mdp.setText("");
					}
					else{
						System.out.println("diso le password");
					}

				}else{
					System.out.println("desole, vous n'etes pas des notre");
				}
			}catch(Exception e){
				System.out.println("Erreur select: "+e);
			}
		}catch(SQLException sql){
			System.out.println("Connection a la base:"+sql);
		}
		return res;
	}
}