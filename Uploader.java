import java.io.File;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Uploader extends JFrame{
	public static String emplacement = "D:/Projet/java/SourceMesClasses/Image";

	private JFileChooser fichier;
	private String format;
	private String nom;
	private Image sary=null;
	private String emplTemp = "";

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
		createMiniSary();
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
		setNomFormat();
	}	

	public int dernier(String t,char a){
		int nb=t.length()-1;
		boolean hita=false;
		while(nb>0 && !hita){
			nb--;
			if(t.charAt(nb)==a)
				hita=true;
		}
		return nb;
	}	
	public int dernier(String t,String a){
		return dernier(t,a.charAt(0));
	}

	public void setNomFormat(){
		int der = dernier(getEmplacement(),File.separator);
		String nomFormat = getEmplacement().substring(der+1);
		der = dernier(nomFormat,".");
		nom = nomFormat.substring(0,der);
		if(der<=0)
			nom=nomFormat;
		if(der>0)
			format = nomFormat.substring(der+1);
	}

	public void createMiniSary(){
		setNomFormat();
		switch(format){
			case "PNG":case "png":case "jpeg":case "JPG":case "jpg":case "bmp":{
				Image im =null;
				try{
					im = ImageIO.read(new File(emplacement));
				}catch(Exception e){
					System.out.println("file not found: "+e);
				}
				int max = im.getWidth(null)>im.getHeight(null) ? im.getWidth(null) : im.getHeight(null);
				float facteur = (float)100/max;   // facteur de reduction ???? reduire
				float width = im.getWidth(null)*facteur;
				float height = im.getHeight(null)*facteur;
				System.out.println("fact="+facteur+" w="+width+" h="+height);
				BufferedImage enregis = new BufferedImage((int)width,(int)height,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = enregis.createGraphics();
				g2d.drawImage(im,0,0,(int)width,(int)height,null);
				// emplTemp = getClass().;
				try{
					ImageIO.write(enregis,format,new File(emplacement+"Mini."+format));
				}catch(Exception e){
					System.out.println("Echec de Creation mini: "+e);
				}
			}break;
		}
	}
}