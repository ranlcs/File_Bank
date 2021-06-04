import javax.swing.JLabel;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class LabelNullFocusLost extends JLabel implements FocusListener{
	public LabelNullFocusLost(){
		super();
		addFocusListener(this);
		setFocusable(true);
	}
	public LabelNullFocusLost(String r){
		super(r);
		addFocusListener(this);
		setFocusable(true);
	}

	@Override
	public void focusGained(FocusEvent f) {}
	@Override
	public void focusLost(FocusEvent f) {
		super.setText("");
	}

}