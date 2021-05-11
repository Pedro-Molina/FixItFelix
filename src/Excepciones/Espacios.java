package Excepciones;

import javax.swing.JOptionPane;

public class Espacios extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public Espacios() {
		super("no tiene que haber espacios");
	}
	
	public void avisar() {
		JOptionPane.showMessageDialog(null, "no se pueden usar espacios", "ExcepcionEspacios",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
