import java.io.File;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Uploader extends JFrame{
	JFileChooser fichier;
	public static String emplacement = "D:/Projet/java/SourceMesClasses/Image";

	public Uploader(){ 
		setSize(400,400);
		setLocation(50,50);
		setVisible(false);
		fichier = new JFileChooser();
		fichier.setCurrentDirectory(new File(emplacement));
		int returnVal = fichier.showDialog(this,"Envoyer");
		add(fichier);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			emplacement = fichier.getSelectedFile().toString();
			envoyerFichier();
			setVisible(false);
		}
		else
			setVisible(false);
	}
	public JFileChooser getFichier(){
		return fichier;
	}
	
	public String getEmplacement(){
		fichier.setCurrentDirectory(new File(emplacement));
		return emplacement;
	}

	public void envoyerFichier(){
		System.out.println("le saika uploadena: "+getEmplacement());
		
	}	
}