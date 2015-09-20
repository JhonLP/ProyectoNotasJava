package mx.itson.notaui.pantallas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mx.itson.nota.entidades.Nota;
import mx.itson.nota.negocio.NotaNegocio;
import mx.itson.nota.persistencia.NotaPersistencia;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author JhonatanLaguna
 * @author MarianaGarcia
 * @version 1.0
 * @since 2015-09-16
 *
 */

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private static JTable tblNotas;
	private JTextField txtId;
	static NotaNegocio notas = new NotaNegocio();

	/**
	 * Inicia la aplicacion
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					rellenarTabla();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Crea el modelo para la tabla de notas
	 */
	static DefaultTableModel model = new DefaultTableModel(
			new Object[][] {},
			new String[] {
					"id","Titulo"
			}
	);
	
	/**
	 * Rellena la tabla con las notas traidas desde la Base de datos
	 */
	public static void  rellenarTabla(){
		 for (int i = 0; i < tblNotas.getRowCount(); i++) {
	           model.removeRow(i);
	           i-=1;
	       }
		List<Nota> nota = notas.consultarTodos();		
		int filas = nota.size();
		String [][] notasString = new String[filas][2];
		int i = 0;
		for(Nota in : nota)
		{
			String id = String.valueOf(in.getId());
			String titulo = in.getTitulo();
			
			notasString[i][0] = id;
			notasString[i][1] = titulo;
			i++;
			

		}
		for (int row = 0; row < notasString.length; row++) {
            for (int idx = 0; idx < notasString.length; idx++) {
                model.addRow(notasString[idx]);
                
            }break; 
        }
		
		tblNotas.setModel(model);
		
	}
	/**
	 * Crea la ventana
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(209, 32, 39, 50);
		contentPane.add(lblTitulo);
		
				
		txtTitulo = new JTextField();
		txtTitulo.setBounds(250, 47, 165, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JTextPane txtDescripcion = new JTextPane();
		txtDescripcion.setBounds(208, 107, 298, 208);
		contentPane.add(txtDescripcion);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(209, 68, 200, 50);
		contentPane.add(lblDescripcion);
		
		JLabel lblNotas = new JLabel("Notas");
		lblNotas.setBounds(21, 68, 61, 16);
		contentPane.add(lblNotas);
		
		/**
		 * Crea una nota nueva si @param txtId.getText esta vacio
		 * Actualiza una nota existente si @param txtId.getText tiene el id de una nota
		 */
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(txtId.getText().equals("")){
						NotaNegocio nota = new NotaNegocio();
						nota.guardar(txtTitulo.getText(), txtDescripcion.getText());
						model.fireTableDataChanged();
						JOptionPane.showMessageDialog(null,"Se creo una nueva nota");
						rellenarTabla();
						txtTitulo.setText("");
						txtDescripcion.setText("");
						txtId.setText("");
					}else{
						Nota nota = new Nota();
						nota.setId(Integer.parseInt(txtId.getText()));
						nota.setTitulo(txtTitulo.getText());
						nota.setDescripcion(txtDescripcion.getText());
						NotaNegocio notas = new NotaNegocio();
						notas.editar(nota);
						model.fireTableDataChanged();
						JOptionPane.showMessageDialog(null,"Se guardaron los cambios");
						rellenarTabla();
					}
				}
		});
		btnGuardar.setBounds(298, 327, 117, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		btnCerrar.setBounds(402, 385, 117, 29);
		contentPane.add(btnCerrar);
		
		/**
		 * Elimina la nota selecionada por @param id
		 */
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(String.valueOf(model.getValueAt(tblNotas.getSelectedRow(), 0)));
				NotaPersistencia notas = new NotaPersistencia();
				Nota nota = notas.consultar(id);
				notas.eliminar(nota);
				model.fireTableDataChanged();
				JOptionPane.showMessageDialog(null,"Nota Eliminada");
				rellenarTabla();
			}
		});
		btnEliminar.setBackground(Color.RED);
		btnEliminar.setBounds(21, 385, 117, 29);
		contentPane.add(btnEliminar);
		
		tblNotas = new JTable();
		tblNotas.setBounds(21, 86, 175, 230);
		contentPane.add(tblNotas);
		
		
		
		/**
		 * Carga la nota a editar por su @param id
		 */
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(String.valueOf(model.getValueAt(tblNotas.getSelectedRow(), 0)));
				Nota nota = notas.consultar(id);
				txtTitulo.setText(nota.getTitulo());
				txtDescripcion.setText(nota.getDescripcion());
				txtId.setText(String.valueOf((nota.getId())));
				
			}
		});
		btnEditar.setBounds(21, 327, 117, 29);
		contentPane.add(btnEditar);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setEditable(false);
		txtId.setBounds(467, 44, 39, 26);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(445, 49, 61, 16);
		contentPane.add(lblId);
		
		/**
		 * Limpia los campos de texto para crear una nota nueva
		 */
		JButton btnNuevaNota = new JButton("Nueva Nota");
		btnNuevaNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTitulo.setText("");
				txtDescripcion.setText("");
				txtId.setText("");
			}
		});
		btnNuevaNota.setBounds(6, 6, 117, 29);
		contentPane.add(btnNuevaNota);
		

	}
}

