import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.event.ActionListener;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp extends JPanel implements ItemListener{
	public static final int LONG_TEXT=25;

	private TextTransFileBank mail = new TextTransFileBank(LONG_TEXT,"e-mail");
	private TextTransFileBank pass = new TextTransFileBank(LONG_TEXT,"Password");
	private TextTransFileBank pseudo = new TextTransFileBank(LONG_TEXT,"Pseudo");
	private JButton valider = new JButton("Valider");
	private JCheckBox print = new JCheckBox();
	private LabelNullFocusLost resultat = new LabelNullFocusLost("");

	public SignUp(){
		ajouterElement();
		// setBackground(Constante.BACKGROUND);
	}

	public void ajouterElement(){
		JPanel sign = new JPanel();

		sign.setPreferredSize(new Dimension(500,200));
		sign.setBackground(Constante.BACKGROUND);
		sign.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5),"Creer un compte",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(Font.SANS_SERIF,Font.BOLD,17),Color.WHITE));
		sign.setLayout(new BoxLayout(sign,BoxLayout.Y_AXIS));

		JPanel pPseudo = new JPanel();
		pPseudo.setBackground(Constante.BACKGROUND);
		pPseudo.add(pseudo);
		sign.add(pPseudo);

		JPanel pMail = new JPanel();
		pMail.setBackground(Constante.BACKGROUND);
			pMail.add(mail);
		sign.add(pMail);

		JPanel pPass = new JPanel();
		pPass.setLayout(new BoxLayout(pPass,BoxLayout.Y_AXIS));
		pPass.setBackground(Constante.BACKGROUND);
		JPanel ppPass = new JPanel();
		ppPass.setBackground(Constante.BACKGROUND);
		ppPass.add(pass);
		pPass.add(ppPass);
		JPanel pPrint = new JPanel();
		pPrint.add(print);
		JLabel temp = new JLabel("Print password");
		temp.setForeground(new Color(188,193,213));
		pPrint.add(temp);
		print.setBackground(Constante.BACKGROUND);
		pPrint.setBackground(Constante.BACKGROUND);
		pPass.add(pPrint);
		sign.add(pPass);
		print.addItemListener(this);
		pass.setPassword();


		JPanel pValider = new JPanel();
		pValider.setBackground(Constante.BACKGROUND);
		pValider.add(valider);
		pValider.add(resultat);
		resultat.setForeground(Color.RED);
		sign.add(pValider);

		GridBagLayout t = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();
		JPanel haut = new JPanel();
		JPanel bas = new JPanel();
		JPanel centre = new JPanel();
		centre.add(sign);
		setLayout(t);
		cons.gridwidth=1;
		cons.gridheight=1;
		t.setConstraints(haut,cons);
		add(haut);
		cons.gridwidth=2;
		t.setConstraints(centre,cons);
		add(centre);
		cons.gridwidth=1;
		t.setConstraints(bas,cons);
		add(bas);
	}

	public void addValiderActionListener(ActionListener a){
		valider.addActionListener(a);
	}

	public boolean verifierDonne(){
		boolean res = false;
		String tMail = mail.getText();
		String tPass = pass.getText();
		String tPseudo = pseudo.getText();

		if(vide(tMail) || vide(tPass) || vide(tPseudo)){
			resultat.requestFocus();
			resultat.setText("Tous les champs sont obligatoires");
			repaint();
		}else{
			if(tPass.length()<8){
				resultat.requestFocus();
				resultat.setText("Password de longueur au moins 8");
			}else{
				Connection file_bank=null;
				try{
					file_bank=DriverManager.getConnection("jdbc:mysql://localhost:3306/file_bank","root","12345678");
					try{
						/*PreparedStatement p = file_bank.prepareStatement("SELECT * FROM compte WHERE id = ?");
						p.setString(1,mail);
						ResulSet s = p.executeQuery();
						if(s.next()){
							System.out.println("compte deja existant");
						}else{*/

							PreparedStatement p=file_bank.prepareStatement("INSERT INTO compte (id,salt,cle,admin,nom) VALUES(?,?,?,?,?)");
							p.setString(1,tMail);
							String salt=HashPassword.generateSalt(512).get();
							String cle=HashPassword.hashPassword(tPass,salt).get();
							p.setString(2,salt);
							p.setString(3,cle);
							p.setInt(4,0);
							p.setString(5,tPseudo);
							int resUp = p.executeUpdate();
							System.out.println("resUP:"+resUp);
						// }
					}catch(SQLException e){/////misy an le erreur miverina efa ao le id de ny afa inonna
						System.out.println("Erreur sur insert: "+e);
					}			
				}catch(SQLException sql){
					System.out.println("Connection a la base:"+sql);
				}
				System.out.println("tsisy diso lo atreto");
			}
		}
		return res;
	}
	public boolean vide(String s){
		return s.equals("");
	}
	@Override
	public void itemStateChanged(ItemEvent i){
		pass.setPassword();
		pass.requestFocus();
	}
}

/*
String mail = identifiant.getText();
String pass = mdp.getText();
Connection file_bank=null;
try{
	file_bank=Connection.getConnection("jdbc:mysql://localhost:3306/file_bank","root","12345678");
	try{
		/*PreparedStatement p = file_bank.prepareStatement("SELECT * FROM compte WHERE id = ?");
		p.setString(1,mail);
		ResulSet s = p.executeQuery();
		if(s.next()){
			System.out.println("compte deja existant");
		}else{
			p.file_bank.prepareStatement("INSERT INTO compte (id,salt,cle,admin,nom) VALUES(?,?,?,?,?)");
			p.setString(1,mail);
			String salt=HashPassword.generateSalt(512).get();
			String key=HashPassword.hashPassword(pass,salt).get();
			p.setString(2,salt);
			p.setString(3,cle);
			p.setInt(4,0);
			p.setString(5,)
		// }catch(Exception e){
			System.out.println("Erreur sur insert: "+e);
		}
	}
}catch(SQLException sql){
	System.out.println("Connection a la base:"+sql);
}
*/

