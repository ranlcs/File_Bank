import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.awt.Insets;


// TextField qui peut cacher un mot de passe et utiliser le hint de Android.  getText a été surchargé mais pas setText


public class TextTrans extends JTextField{
	
	protected Color colHint;
	protected String hint;
	protected boolean vide = true;
	protected Color fore;
	protected boolean pass = false;
	protected String textePass = "";
	protected boolean premier=true;
	protected Color back = new Color(109,115,141);
	protected String hintCar = "*";
	protected Color colTxt = Color.BLACK;

	public TextTrans(int lon,String tex){
		super(lon);
		hint = tex;
		colHint = Color.LIGHT_GRAY;
		vide = true;
		fore=new Color(188,193,213);
		setForeground(colHint);
		setText(hint);
		((AbstractDocument)getDocument()).setDocumentFilter(new DocFilter());
		// setPassword();
	}	
	public TextTrans(String tex,String hint){
		super(tex);
		this.hint = hint;
		colHint = Color.LIGHT_GRAY;
		vide = false;
		fore=new Color(188,193,213);
		setForeground(fore);
		setBackground(back);
		setText(tex);
		setMargin(new Insets(0,13,0,0));
		((AbstractDocument)getDocument()).setDocumentFilter(new DocFilter());
		// setPassword();
	}
	public TextTrans(String tex){
		this(tex,"");
	}
	public TextTrans(int lon){
		this(lon,"");
	}
	public void setHint(String str){
		hint = str;
		if(vide)
			setText(hint);
	}
	public void setFore(Color c){
		if(!vide)
			super.setForeground(c);
		fore = c;
	}

	public void setHintColor(Color c){
		if(vide)
			setForeground(c);
		colHint = c;
	}


	private String genPassCach(){
		String res = "";
		for(int i=0;i<textePass.length();i++)
			res=res+"*";
		return res;
	}

	public void setPassword(){
		if(!vide && pass){
			pass = !pass;                          //   mettre a cause de setText appel replace avec * si true
			setText(textePass);
		}
		else if(!vide && !pass){
			textePass = super.getText();
			setText(genPassCach());                 // marche car appel replace avec text (pas *)
			pass = !pass;
		}
		else
			pass = !pass;
	}

	@Override
	public String getText(){
		String res="";
		if(!vide)
			res = getTextSup();
		if(!vide && pass)
			res = textePass;
		return res;
	}
	@Override
	public void setText(String f){
		if(f.equals("")){
			super.setText(hint);
			if(pass){
				pass=false;
				setText(hint);
				pass=true;
			}
			vide=true;
		}
		else
			super.setText(f);
	}

	public String getTextSup(){
		return super.getText();
	}

	class DocFilter extends DocumentFilter{
		private TextTrans tt;

		public DocFilter(){
			// this.tt=tt;
		}

		@Override
		public void insertString(DocumentFilter.FilterBypass fb,int offset,String text,AttributeSet attr) throws BadLocationException{
			super.insertString(fb,offset,text,attr);
			setForeground(colTxt);
		}

		@Override 
		public void remove(DocumentFilter.FilterBypass fb,int offset,int length) throws BadLocationException{
			if(!vide){              //   cas ou le texte est NORMAL
				super.remove(fb,offset,length);          //  suppression normal d' une lettre ou plusieurs
				if(pass){
					textePass = textePass.substring(0,offset)+textePass.substring(offset+length);
				}
				
				if(getTextSup().length()==0){
					super.replace(fb,0,0,hint,null);
					setForeground(colHint);
					vide=true;
				}
			}
		} 

		public void replace(DocumentFilter.FilterBypass fb,int offset,int length,String text,AttributeSet attr) throws BadLocationException{
			if(vide){
				if(!pass)
					super.replace(fb,0,hint.length(),text,attr);
				else{
					super.replace(fb,0,hint.length(),hintCar,attr);
					textePass = text;
				}
				setForeground(colTxt);
				vide = false;
			}
			else{
				setForeground(colTxt);
				if(!pass)
					super.replace(fb,offset,length,text,attr);
				else{
					super.replace(fb,offset,length,hintCar,attr);
					textePass = textePass.substring(0,offset)+text+textePass.substring(offset+length);
				}
			}
		}
	}
}