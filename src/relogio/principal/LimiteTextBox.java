package relogio.principal;


import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimiteTextBox extends PlainDocument {

  private static final long serialVersionUID = 1L;
  private int limit;
  private boolean soNumeros;
  private boolean primeiraPrint = true;

  public LimiteTextBox(int limit, boolean soNumeros) {
   super();
   this.limit = limit;
   this.soNumeros = soNumeros;
   }

  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;
   
    boolean contemSoNumeros = true;
    char[] strChar = str.toCharArray();
    
    //para no caso de str for mais q um digito de comprimento, ja que Pattern.matches("[0-9]", CharSequence) nao esta conseguindo 
    //processar uma Sring com dois digitos
    for (int x = 0; x < strChar.length; x++) {
    	if (Pattern.matches("[0-9]", Character.toString(strChar[x])) == false) {
    		contemSoNumeros = false;
    	}
    } 
    
    if (soNumeros == true && contemSoNumeros == false && primeiraPrint == false) {	
    	return;
    }

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
      primeiraPrint = false;
    }
  }
  
  
}
