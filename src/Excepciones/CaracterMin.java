package Excepciones;

import javax.swing.JOptionPane;

public class CaracterMin extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CaracterMin() {
		super("deve escribir al menos dos caracteres");
	}

	public void avisar() {
		JOptionPane.showMessageDialog(null, "escribir al menos dos caracteres", "ExcepcionCharMin",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
