package VentanaInicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controladores.ListenerInicio;
import manejo.Jugador;
import manejo.Puntaje;
/**
 * En este panel se muestran los 5 mejores jugadores
 */
public class PanelTop5 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel texto;
	private JTable tabla;
	private JButton boton;

	public PanelTop5() {
		super(new GridBagLayout());
		texto = new JLabel("TOP 5");
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.setBackground(Color.lightGray);
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = 1;
		grid.gridy = 0;
		grid.gridwidth = 1; // cantidad de columnas que quiero que ocupe
		grid.gridheight = 1; // cantida de filas que quiero que ocupe
		this.add(this.texto, grid);

		ImageIcon volver = new ImageIcon(getClass().getResource("/imagenes/flecha.png"));
		boton = new JButton(volver);
		boton.setBackground(Color.lightGray);
		boton.setBorder(null);
		boton.addMouseListener(new ListenerInicio());
		grid.gridx = 1;
		grid.gridy = 2;
		grid.gridwidth = 1; // cantidad de columnas que quiero que ocupe
		grid.gridheight = 1; // cantida de filas que quiero que ocupe
		grid.fill = GridBagConstraints.NONE;
		this.add(this.boton, grid);

		tabla = new JTable();
		actualizarTalba();
		grid.gridx = 1;
		grid.gridy = 1;
		grid.weightx = 1.0;
		grid.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.tabla, grid);
	}

	public void actualizarTalba() {
		Jugador top5[] = Puntaje.getInstance().getTop5();
		Object[][] datos = { { "poscion", "nombre", "puntaje" }, { 1, top5[0].getNombre(), top5[0].getPuntaje() },
				{ 2, top5[1].getNombre(), top5[1].getPuntaje() }, { 3, top5[2].getNombre(), top5[2].getPuntaje() },
				{ 4, top5[3].getNombre(), top5[3].getPuntaje() }, { 5, top5[4].getNombre(), top5[4].getPuntaje() }, };
		String[] nombrecolumnas = { "posicion", "nombre", "putaje" };
		DefaultTableModel modelo = new DefaultTableModel(datos, nombrecolumnas) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) // evita que se escirba la talba
			{
				return false;
			}
		};
		tabla.setModel(modelo);
	}
}