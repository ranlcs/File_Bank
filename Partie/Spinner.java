import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.MatOfRect;
import org.opencv.core.Core;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class Spinner extends JPanel implements Runnable,ComponentListener{
	private static String EMPL;
	public static final int COTE = 1;
	private Mat image;
	private Mat echelle;
	private Mat rotEchelle;
	private boolean tourne = true;

	public Spinner(){
		super();
		EMPL = getClass().getResource("sary/").toString().substring(6);
		try{
			System.load("D:/Projet/Java/SourceMesClasses/opencv/bin/opencv_java450.dll");
		}catch(Exception e){
			System.out.println("le dll est introuvable");
			System.exit(0);
		}
		image = Imgcodecs.imread(EMPL+"tournePetit.jpg");
		echelle = Imgcodecs.imread(EMPL+"tournePetit.jpg");
		rotEchelle = new Mat();
		new Thread(this).start();
		setSize(500,500);
		addComponentListener(this);
	}

	@Override 
	public void run(){
		int cp = 0;
		while(true){
			if(tourne){
				cp=(cp+4)%360;
				rotEchelle = rotate(echelle,cp);
				try{
					Thread.sleep(40);
				}catch(Exception e){
					System.out.println("echec de sleep");
				}
			}
			repaint();
		}
	}




	//si possible définir sans openCV: getRotationMatrix2D  ET  warpAffine
	public Mat rotate(Mat org,double angle){
		int max = (org.cols()<org.rows()?org.rows():org.cols());
		Mat res = new Mat(max,max,(int)org.elemSize(),new Scalar(new double[]{255,0,0}));
		Mat rot = Imgproc.getRotationMatrix2D(new Point(org.cols()/2,org.rows()/2),angle,1);
		Imgproc.warpAffine(org,res,rot,new Size(max,max));
		return res;
	}
	public void setTourne(){
		tourne = !tourne;
	}













	//Dessin
	public void dessinerPixel(Graphics gr,Mat img,int x,int y,int debutX,int debutY){
		byte[] px = new byte[(int)img.elemSize()];
		img.get(y,x,px);
		int r=0,g=0,b=0;
		r = Byte.toUnsignedInt(px[2]);
		g = Byte.toUnsignedInt(px[1]);
		b = Byte.toUnsignedInt(px[0]);
		double brill = (0.212*r + 0.715*g + 0.072*b);
		if(brill>60 && tourne){
			gr.setColor(Color.RED);
			gr.fillRect(debutX+x*COTE,debutY+y*COTE,COTE,COTE);
		}
	}

	public void dessinerMat(Graphics g,Mat img,int debutX,int debutY){
		for(int i=0;i<img.rows();i++)
			for(int j=0;j<img.cols();j++)
				dessinerPixel(g,img,j,i,debutX,debutY);
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(!rotEchelle.empty())
			dessinerMat(g,rotEchelle,0,0);
	}





	//DEFINIR SANS OPENCV AUSSI:resize
	@Override	
	public void componentResized(ComponentEvent e){
		double facteur=e.getComponent().getHeight();
		double w = e.getComponent().getWidth();
		if(facteur<w){
			facteur = facteur/image.rows();
		}
		else
			facteur = w/image.cols();
		Imgproc.resize(image,echelle,new Size(0,0),facteur,facteur);
		repaint();
	}
	@Override
    public void componentMoved(ComponentEvent e){}
	@Override
    public void componentShown(ComponentEvent e){}
	@Override
    public void componentHidden(ComponentEvent e){}

} 