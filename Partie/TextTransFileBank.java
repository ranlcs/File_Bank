import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;


public class TextTransFileBank extends TextTrans{
	private Color back = new Color(109,115,141);

	public TextTransFileBank(int lon,String hint){
		super(lon,hint);
		setBackground(back);
		setMargin(new Insets(0,13,0,13));
		colTxt=colHint;
	}	
	public TextTransFileBank(String tex,String hint){
		super(tex,hint);
		setBackground(back);
		setMargin(new Insets(0,13,0,13));
	}
	public TextTransFileBank(String tex){
		this(tex,"");
	}
	public TextTransFileBank(int lon){
		this(lon,"");
	}

	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Constante.BACKGROUND);
		g.fillRect(0,0,10,getHeight());
		g.fillRect(getWidth()-10,0,getWidth(),getHeight());
		g.setColor(back);
		g.fillArc(0,0,22,getHeight(),90,180);
		g.fillArc(getWidth()-22,0,21,getHeight(),270,180);
		g.setColor(Color.RED);
		g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,19,19);
		g.drawRoundRect(1,1,getWidth()-3,getHeight()-3,19,19);
	}
}