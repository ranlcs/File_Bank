import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;

public class PanelString extends JPanel{
	private static final int TAILLE_TEXTE=14;
	private static final int DEBUT_X=10;
	private static final int DEBUT_Y=20;
	private static final int INTER=20;
	private static final int ESPACE=5;

	private static String txtDef="mbola tsisy nefa zavatra lava be no tiako soratra eto.-Toa ohatran' ny mbola tsy ampy ihany ny halavany.";
	private String[] txt;

	public PanelString(){
		txt=txtDef.split(" ");
		setBackground(Constante.BACKGROUND);
	}
	public PanelString(String t){
		this();
		txt=t.split(" ");
	}

	public void setText(String t){
		txt=t.split("");
	}

	public String getText(){
		String t = txt[0];
		for(int i=1;i<txt.length;i++)
			t = t+" "+txt[i];
		return t;
	}

	@Override 
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Constante.TEXT_COLOR);
		g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,TAILLE_TEXTE));
		int x=DEBUT_X;
		int y=DEBUT_Y;
		for(int i=0;i<txt.length;i++){
			int lon = (int)(0.58*TAILLE_TEXTE*txt[i].length());
			if(x+lon>getWidth()){
				x=DEBUT_X;
				y=y+INTER;
			}
			g.drawString(txt[i],x,y);
			x=x+lon+ESPACE;
		}
	}
}