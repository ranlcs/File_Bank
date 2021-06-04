import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JPanel implements ActionListener{
	private String[] titre={"Suggestion","Preference","Historique","Autre"};

	private Fichiers[] files=new Fichiers[titre.length];
	private TextTrans recherche = new TextTrans(14,"Recherche");
	private JButton butRecherche = new JButton("Recherche");
	private ButLien uploader = new ButLien("Uploader");
	private ButLien admin = new ButLien("Admin");
	private boolean adminB = true;
	private Uploader up;


	public Client(){
		super();
		setLayout(new BorderLayout());
		JPanel haut =new JPanel();
		haut.add(recherche);
		haut.add(butRecherche);
		add(haut,BorderLayout.NORTH);
		setBackground(Constante.BACKGROUND);
		haut.setBackground(Constante.BACKGROUND);

		JPanel centre=new JPanel();
		centre.setLayout(new BoxLayout(centre,BoxLayout.Y_AXIS));
		centre.setBackground(Constante.BACKGROUND);
		for(int i=0;i<titre.length;i++){
			files[i] = new Fichiers();
			files[i].setBackground(Constante.BACKGROUND);
			files[i].setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5),titre[i],TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(Font.SANS_SERIF,Font.BOLD,17),Color.RED));
			centre.add(files[i]);
		}
		JScrollPane t = new JScrollPane(centre);
		t.setBackground(Constante.BACKGROUND);
		add(t,BorderLayout.CENTER);

		JPanel bas = new JPanel();
		bas.setBackground(Constante.BACKGROUND);
		bas.add(uploader);
		bas.add(admin);
		add(bas,BorderLayout.SOUTH);
		uploader.addActionListener(this);
	}

	public void setAdmin(){
		adminB=!adminB;
		admin.setVisible(adminB);
	}

	@Override
	public void actionPerformed(ActionEvent a){
		up = new Uploader();
	}
	public void addAdminActionListener(ActionListener a){
		admin.addActionListener(a);
	}
}