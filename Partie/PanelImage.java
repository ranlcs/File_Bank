import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Dimension;

public class PanelImage extends JPanel{
	public static final int COTE =100;
	public static final int MARGE = 10;

	private Image sary=null;
	private float coeff=1;
	public PanelImage(){
		try{
			sary=ImageIO.read(new File("d:/Projet/Java/SourceMesClasses/FileBank/bin/sary/unavailable.jpg"));
		}catch(Exception e){
			System.out.println("tsy hita le sary");
		}
		setPreferredSize(new Dimension(COTE,COTE));
		coeff=sary.getWidth(null)/sary.getHeight(null);
		setBackground(Constante.BACKGROUND);
	}

	public PanelImage(Image i){
		this();
		if(i!=null)
			sary=i;
	}

	public Image getImage(){
		return sary;
	}

	public void setImage(Image f){
		if(f!=null)
			sary=f;
	}

	@Override 
	public void paint(Graphics g){
		super.paint(g);
		float h=(float)(COTE-MARGE)/sary.getHeight(null);
		float w=(float)(COTE-MARGE)/sary.getWidth(null);
		if(h<w){
			w=sary.getWidth(null)*h;
			h=sary.getHeight(null)*h;
		}
		else if(w<=h){
			h=sary.getHeight(null)*w;
			w=sary.getWidth(null)*w;
		}
		g.drawImage(sary,(int)MARGE/2,(int)MARGE/2,(int)w,(int)h,0,0,sary.getWidth(null),sary.getHeight(null),null);
	}
}