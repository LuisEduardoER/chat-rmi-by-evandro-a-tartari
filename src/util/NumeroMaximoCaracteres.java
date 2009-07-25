package util;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 * 
 * @author evandro.tartari
 *
 */
public class NumeroMaximoCaracteres extends PlainDocument {   
    /**
     * 
     */
    private static final long serialVersionUID = -5345956100765302098L;
    private int iMaxLength;   
    
    public NumeroMaximoCaracteres(int maxlen) {   
        super();   
        iMaxLength = maxlen;   
    }   
  
    public void insertString(int offset, String str, AttributeSet attr)   
                    throws BadLocationException {   
        if (str == null) return;   
  
        if (iMaxLength <= 0)         
        {   
            super.insertString(offset, str, attr);   
            return;   
        }   
  
        int ilen = (getLength() + str.length());   
        if (ilen <= iMaxLength)       
            super.insertString(offset, str, attr);      
        }   
}  
