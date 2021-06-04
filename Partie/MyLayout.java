import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

public class MyLayout implements LayoutManager{
	public MyLayout(){
		super();
	}

	public void addLayoutComponent(String name, Component comp){}

    public void removeLayoutComponent(Component comp){}

    
    public Dimension preferredLayoutSize(Container parent){
    	Dimension d = new Dimension(0,0);
    	Component[] comp = parent.getComponents();
    	for(int i=0;i<comp.length;i++){
    		d.height=d.height+comp[i].getPreferredSize().height;
    		d.width=Math.max(d.width,comp[i].getPreferredSize().width);
    	}
    	return d;
    	// return new Dimension(1000,1000);
    }

    
    public Dimension minimumLayoutSize(Container parent){
    	Dimension d = new Dimension(0,0);
    	Component[] comp = parent.getComponents();
    	for(int i=0;i<comp.length;i++){
    		d.height=d.height+comp[i].getPreferredSize().height;
    		d.width=Math.max(d.width,comp[i].getPreferredSize().width);
    	}
    	return d;
    	// return new Dimension(1000,1000);
    }

    
    public void layoutContainer(Container parent){
    	Component[] comp=parent.getComponents();
    	int y=0;
    	for(int i=0;i<comp.length;i++){
    		comp[i].setBounds(0,y,comp[i].getPreferredSize().width,comp[i].getPreferredSize().height);
    		y=y+comp[i].getHeight();
    	}
    }
}