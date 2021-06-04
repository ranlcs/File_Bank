

/*import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.Color;


public class Fichiers extends JScrollPane{
	private Vector<InterfFichier> files=new Vector<InterfFichier>();
	private JPanel t = new JPanel();

	public Fichiers(){
		super();
		setViewportView(t);
		t.setLayout(new BoxLayout(t,BoxLayout.Y_AXIS));
		init();
		for(int i=0;i<files.size();i++){
			t.add(Box.createVerticalStrut(10));
			t.add(files.elementAt(i));
		}
	}


	public void init(){
		for(int i=0;i<20;i++)
			files.add(new InterfFichier());

	}

	@Override
	public void setBackground(Color c){
		super.setBackground(c);
		if(files!=null){
			for(int i=0;i<files.size();i++)
				files.elementAt(i).setBackground(c);
		}
	}
}*/

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.Color;


public class Fichiers extends JPanel{
	private Vector<InterfFichier> files=new Vector<InterfFichier>();

	public Fichiers(){
		super();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		init();
		for(int i=0;i<files.size();i++){
			add(Box.createVerticalStrut(10));
			add(files.elementAt(i));
		}
	}


	public void init(){
		for(int i=0;i<20;i++)
			files.add(new InterfFichier(String.valueOf((i+1))));

	}
}