package alquiler_bicis_basico;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


class ConnectionSingleton {

    private static Connection con;

    public static Connection getConnection() throws SQLException {
	String url = "jdbc:mysql://127.0.0.1:3307/alquiler_bicis_basico";
	String user = "alumno";
	String password = "alumno";
	if (con == null || con.isClosed()) {
	    con = DriverManager.getConnection(url, user, password);
	}
	return con;
    }
}

public class Alquiler_bicis_basico {
  

    private JFrame frameTienda;
    private DefaultTableModel modelBici;
    private DefaultTableModel modelUsuario;
    private JTable table2;
    private static Connection con;
    private JPanel panel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

	try {

	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	} catch (InstantiationException e1) {
	    e1.printStackTrace();
	} catch (IllegalAccessException e1) {
	    e1.printStackTrace();
	} catch (UnsupportedLookAndFeelException e1) {
	    e1.printStackTrace();
	}

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Alquiler_bicis_basico window = new Alquiler_bicis_basico();
		    window.frameTienda.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public Alquiler_bicis_basico() {
	initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

	JLabel lblUsuariosBD;
	JLabel lblBicisBD;
	JButton btnMostrarUs;
	JScrollPane scrollPaneUsuario;
	JScrollPane scrollPaneBici;
	JLabel lblCUcod;
	JTable table;
	JTextField textFieldCodUsuario;
	JTextField textFieldNomUsuario;
	JLabel lblCUnom;
	JLabel lblCBcod;
	JTextField textFieldCodBici;
	JLabel lblDevCodUsu;
	JLabel lblDevCodBici;
	JLabel lblAlqCodUsu;
	JLabel lblAlqCodBici;
	JButton btnAlquilar;
	JButton btnMostrar;
	JComboBox comboBoxUsDev;
	JComboBox comboBoxUsAlq;
	JComboBox comboBoxBiciDev;
	JComboBox comboBoxBiciAlq;

	
	frameTienda = new JFrame();
	frameTienda.getContentPane().setFont(new Font("Silom", Font.BOLD, 15));
	frameTienda.setBounds(100, 100, 1200, 800);
	frameTienda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frameTienda.getContentPane().setLayout(null);
	frameTienda.setResizable(false);

	
	lblUsuariosBD = new JLabel("USUARIOS");
	lblUsuariosBD.setFont(new Font("Silom", Font.BOLD, 20));
	lblUsuariosBD.setBounds(265, 44, 117, 20);
	frameTienda.getContentPane().add(lblUsuariosBD);

	lblBicisBD = new JLabel("BICIS");
	lblBicisBD.setFont(new Font("Silom", Font.BOLD, 20));
	lblBicisBD.setBounds(846, 44, 68, 20);
	frameTienda.getContentPane().add(lblBicisBD);
	
	textFieldCodUsuario = new JTextField();
	textFieldCodUsuario.setBounds(157, 371, 85, 33);
	frameTienda.getContentPane().add(textFieldCodUsuario);
	textFieldCodUsuario.setColumns(10);
	
	textFieldNomUsuario = new JTextField();
	textFieldNomUsuario.setColumns(10);
	textFieldNomUsuario.setBounds(382, 371, 168, 33);
	frameTienda.getContentPane().add(textFieldNomUsuario);

	modelUsuario = new DefaultTableModel();
	modelUsuario.addColumn("ID");
	modelUsuario.addColumn("Nombre");
	modelUsuario.addColumn("ID Bici");
	
	table = new JTable(modelUsuario);
	table.setBounds(67, 81, 425, 144);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	frameTienda.getContentPane().add(table);

	scrollPaneUsuario = new JScrollPane(table);
	scrollPaneUsuario.setBounds(65, 76, 500, 218);
	frameTienda.getContentPane().add(scrollPaneUsuario);

	modelBici = new DefaultTableModel();
	modelBici.addColumn("ID");
	modelBici.addColumn("Estado");
	

	table2 = new JTable(modelBici);
	table2.setBounds(571, 47, 425, 144);
	table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	frameTienda.getContentPane().add(table2);

	scrollPaneBici = new JScrollPane(table2);
	scrollPaneBici.setBounds(621, 76, 500, 218);
	frameTienda.getContentPane().add(scrollPaneBici);

	comboBoxUsDev = new JComboBox();
	comboBoxUsDev.setBounds(215, 565, 85, 24);
	frameTienda.getContentPane().add(comboBoxUsDev);

	comboBoxBiciDev = new JComboBox();
	comboBoxBiciDev.setBounds(454, 565, 85, 24);
	frameTienda.getContentPane().add(comboBoxBiciDev);

	comboBoxUsAlq = new JComboBox();
	comboBoxUsAlq.setBounds(791, 565, 85, 24);
	frameTienda.getContentPane().add(comboBoxUsAlq);

	comboBoxBiciAlq = new JComboBox();
	comboBoxBiciAlq.setBounds(1018, 565, 85, 24);
	frameTienda.getContentPane().add(comboBoxBiciAlq);

	
	btnMostrar = new JButton("Mostrar");
	btnMostrar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

		try {
			
			//Relleno la tabla de usuarios
		    Connection con = ConnectionSingleton.getConnection();
		    Statement mostrarUsuario_stmt = con.createStatement();
		    ResultSet mostrarUsuario_rs = mostrarUsuario_stmt.executeQuery("SELECT * FROM usuario");
		    modelUsuario.setRowCount(0);
		    while (mostrarUsuario_rs.next()) {
		    	Object[] row = new Object[3];
		    	row[0] = mostrarUsuario_rs.getInt("cod_usuario");
		    	row[1] = mostrarUsuario_rs.getString("nombre");
		    	row[2] = mostrarUsuario_rs.getInt("cod_bici");
		    	modelUsuario.addRow(row);
		    }
		    mostrarUsuario_stmt.close();
		    mostrarUsuario_rs.close();

		    
		    //Relleno la tabla de bicis
		    Statement mostrarBici_stmt = con.createStatement();
		    ResultSet mostrarBici_rs = mostrarBici_stmt.executeQuery("SELECT * FROM bici");
		    modelBici.setRowCount(0);
		    while (mostrarBici_rs.next()) {
		    	Object[] row = new Object[4];
		    	row[0] = mostrarBici_rs.getInt("cod_bici");
		    	row[1] = mostrarBici_rs.getString("libre");
		    	modelBici.addRow(row);
		    }
		    mostrarBici_rs.close();
		    mostrarBici_stmt.close();
		    con.close();

		} catch (SQLException e1) {
		    e1.printStackTrace();
		    System.out.println("Error: bd no cargada");
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		try {

		    Connection con = ConnectionSingleton.getConnection();
		    
		    //Alquiler
		    Statement comboBoxUsAlq_stmt = con.createStatement();
		    ResultSet comboBoxUsAlq_rs = comboBoxUsAlq_stmt.executeQuery("SELECT cod_usuario FROM usuario WHERE cod_bici IS NULL");
		    
		    comboBoxUsAlq.removeAllItems();
		    while (comboBoxUsAlq_rs.next()) {
				int cod_usuario = comboBoxUsAlq_rs.getInt("cod_usuario");
				comboBoxUsAlq.addItem(cod_usuario);
		    }
		    comboBoxUsAlq_rs.close();
			comboBoxUsAlq_stmt.close();
			
			
			
			Statement comboBoxBiciAlq_stmt = con.createStatement();
		    ResultSet comboBoxBiciAlq_rs = comboBoxBiciAlq_stmt.executeQuery("SELECT cod_bici FROM bici WHERE libre='0'");
		    
		    comboBoxBiciAlq.removeAllItems();
		    while (comboBoxBiciAlq_rs.next()) {
		    	int codBici = comboBoxBiciAlq_rs.getInt("cod_bici");
		    	comboBoxBiciAlq.addItem(codBici);
		    }
			comboBoxBiciAlq_stmt.close();
		    comboBoxBiciAlq_rs.close();
		    
		    
		    //Devolución
			Statement comboBoxUsDev_stmt = con.createStatement();
		    ResultSet comboBoxUsDev_rs = comboBoxUsDev_stmt.executeQuery("SELECT cod_usuario FROM usuario WHERE cod_bici IN (SELECT cod_bici FROM bici WHERE libre='1')");
		    
		    comboBoxUsDev.removeAllItems();
		    while (comboBoxUsDev_rs.next()) {
		    	int cod_usuario = comboBoxUsDev_rs.getInt("cod_usuario");
		    	comboBoxUsDev.addItem(cod_usuario);
		    }
		    comboBoxUsDev_stmt.close();
		    comboBoxUsDev_rs.close();
		    
		   
		    Statement comboBoxBiciDev_stmt = con.createStatement();
		    ResultSet comboBoxBiciDev_rs = comboBoxBiciDev_stmt.executeQuery("SELECT cod_bici FROM bici WHERE libre='1'");
		    
		    comboBoxBiciDev.removeAllItems();
		    while (comboBoxBiciDev_rs.next()) {
		    	int cod_bici = comboBoxBiciDev_rs.getInt("cod_bici");
				comboBoxBiciDev.addItem(cod_bici);
			}
		    comboBoxBiciDev_stmt.close();
		    comboBoxBiciDev_rs.close();
		   
			con.close();
		} catch (SQLException e1) {
		    e1.printStackTrace();
		    System.out.println("Error al cargar cBox 1");
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e2) {
		    System.out.println("Error al cargar cBox 2");
		}

	    }
	});
	btnMostrar.setFont(new Font("Silom", Font.BOLD, 10));
	btnMostrar.setBounds(543, 325, 103, 20);
	btnMostrar.setVisible(false);
	frameTienda.getContentPane().add(btnMostrar);
	btnMostrar.doClick();
	
	table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int index = table.getSelectedRow();
			TableModel model = table.getModel();
			
			//Cuando seleccione una fila de la tabla, el dato  del nombre se introducira en el text field, y se podrá editar  
			textFieldCodUsuario.setText(model.getValueAt(index, 0).toString());
			textFieldNomUsuario.setText(model.getValueAt(index, 1).toString());
			textFieldCodUsuario.setEditable(false);
			
		}
	});

	lblCUcod = new JLabel("Código:");
	lblCUcod.setFont(new Font("Silom", Font.BOLD, 15));
	lblCUcod.setBounds(78, 379, 81, 17);
	frameTienda.getContentPane().add(lblCUcod);

	lblCUnom = new JLabel("Nombre:");
	lblCUnom.setFont(new Font("Silom", Font.BOLD, 15));
	lblCUnom.setBounds(297, 380, 85, 15);
	frameTienda.getContentPane().add(lblCUnom);

	lblCBcod = new JLabel("Código:");
	lblCBcod.setFont(new Font("Silom", Font.BOLD, 15));
	lblCBcod.setBounds(809, 379, 81, 17);
	frameTienda.getContentPane().add(lblCBcod);

	textFieldCodBici = new JTextField();
	textFieldCodBici.setColumns(10);
	textFieldCodBici.setBounds(902, 372, 85, 33);
	frameTienda.getContentPane().add(textFieldCodBici);

	lblDevCodUsu = new JLabel("Código usuario:");
	lblDevCodUsu.setFont(new Font("Dialog", Font.BOLD, 15));
	lblDevCodUsu.setBounds(78, 569, 138, 17);
	frameTienda.getContentPane().add(lblDevCodUsu);

	lblDevCodBici = new JLabel("Código bici:");
	lblDevCodBici.setFont(new Font("Silom", Font.BOLD, 15));
	lblDevCodBici.setBounds(349, 569, 98, 17);
	frameTienda.getContentPane().add(lblDevCodBici);

	lblAlqCodUsu = new JLabel("Código usuario:");
	lblAlqCodUsu.setFont(new Font("Silom", Font.BOLD, 15));
	lblAlqCodUsu.setBounds(657, 569, 127, 17);
	frameTienda.getContentPane().add(lblAlqCodUsu);

	lblAlqCodBici = new JLabel("Código bici:");
	lblAlqCodBici.setFont(new Font("Silom", Font.BOLD, 15));
	lblAlqCodBici.setBounds(914, 569, 99, 17);
	frameTienda.getContentPane().add(lblAlqCodBici);

	btnAlquilar = new JButton("Alquilar");
	btnAlquilar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

		try {
			
			Connection con = ConnectionSingleton.getConnection();
			
			
			//Almaceno los códigos del usuario y la bici introducidos
			int cod_usuario = (int)comboBoxUsAlq.getSelectedItem();
			int cod_bici = (int)comboBoxBiciAlq.getSelectedItem();
    		
    	
			PreparedStatement alquilarBic_stmt = con.prepareStatement("UPDATE bici SET libre=1 WHERE cod_bici=?");
			alquilarBic_stmt.setInt(1, cod_bici);
			alquilarBic_stmt.executeUpdate();
			alquilarBic_stmt.close();
			
			PreparedStatement alquilarUsuario_stmt = con.prepareStatement("UPDATE usuario SET cod_bici=? WHERE cod_USUARIO=?");
			alquilarUsuario_stmt.setInt(1, cod_bici);
			alquilarUsuario_stmt.setInt(2, cod_usuario);
			alquilarUsuario_stmt.executeUpdate();
			alquilarUsuario_stmt.close();
			con.close();
			   
			JOptionPane.showMessageDialog(null, "Alquiler completado");

		} catch (SQLException e1) {
		    System.out.println("Error: bici no alquilada");
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		btnMostrar.doClick();
		
	    }
	});
	btnAlquilar.setFont(new Font("Silom", Font.BOLD, 15));
	btnAlquilar.setBounds(835, 627, 103, 33);
	frameTienda.getContentPane().add(btnAlquilar);

	JButton btnCrearUsuario = new JButton("Crear");
	btnCrearUsuario.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

		try {
			String nombre = textFieldNomUsuario.getText();
			
			if(nombre.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Introduce un nombre", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				Connection con = ConnectionSingleton.getConnection();
				PreparedStatement crear_pstmt = con.prepareStatement("INSERT INTO usuario(cod_usuario, nombre) VALUES(?,?)");

				crear_pstmt.setInt(1, Integer.parseInt(textFieldCodUsuario.getText()));
				crear_pstmt.setString(2, textFieldNomUsuario.getText());
				crear_pstmt.executeUpdate();
				crear_pstmt.close();
				con.close();
				textFieldCodUsuario.setText("");
				textFieldNomUsuario.setText("");
				JOptionPane.showMessageDialog(null, "Usuario creado");
			}
		    

		} catch (SQLException e1) {
		    System.out.println("Error: user no creado");
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    textFieldCodUsuario.setText("");
		}catch(NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Dato inválido\n" + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		btnMostrar.doClick();
		
		
	    }
	});
	btnCrearUsuario.setFont(new Font("Silom", Font.BOLD, 15));
	btnCrearUsuario.setBounds(265, 426, 103, 33);
	frameTienda.getContentPane().add(btnCrearUsuario);

	JButton btnCrearBici = new JButton("Crear");
	btnCrearBici.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {

		try {
		    Connection con = ConnectionSingleton.getConnection();
		    PreparedStatement crear_pstmt = con.prepareStatement("INSERT INTO bici(cod_bici,libre) VALUES(?,?)");

		    crear_pstmt.setInt(1, Integer.parseInt(textFieldCodBici.getText()));
		    crear_pstmt.setString(2, "0");
		    crear_pstmt.executeUpdate();
		    crear_pstmt.close();
		    con.close();
		    textFieldCodBici.setText("");
		    JOptionPane.showMessageDialog(null, "Bicicleta creada");

		} catch (SQLException e1) {
		    System.out.println("Error: bici no creada");
		    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    textFieldCodBici.setText("");
		}catch(NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Dato inválido\n" + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		btnMostrar.doClick();
	
		
	    }
	});
	btnCrearBici.setFont(new Font("Silom", Font.BOLD, 15));
	btnCrearBici.setBounds(835, 426, 103, 33);
	frameTienda.getContentPane().add(btnCrearBici);


	JButton btnDevolver = new JButton("Devolver");
	btnDevolver.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	
	    	try {
	    		
	    		Connection con = ConnectionSingleton.getConnection();
	    		
	    		//Almaceno los códigos del usuario y la bici introducidos
	    		int cod_usuario = (int)comboBoxUsDev.getSelectedItem();
	    		int cod_bici = (int)comboBoxBiciDev.getSelectedItem();
	    		
	    		//Obtengo la bici que tiene alquilada dicho usuario
				PreparedStatement pstmt_devolver = con.prepareStatement("SELECT cod_bici FROM usuario WHERE cod_usuario=?");
				pstmt_devolver.setInt(1, cod_usuario);
				ResultSet rs_dev = pstmt_devolver.executeQuery();
				
				if(rs_dev.next()) {
					int cod_bici_bd = rs_dev.getInt("cod_bici");
					
					//Si la bici escogida en el comboBox coincide con la del usuario, realizo la devolución
					if(cod_bici_bd == cod_bici) {
						PreparedStatement pstmt_devolver_bici = con.prepareStatement("UPDATE bici SET libre=? WHERE cod_bici=?");
						PreparedStatement pstmt_devolver_usuario = con.prepareStatement("UPDATE usuario SET cod_bici=NULL WHERE cod_bici=?");

						pstmt_devolver_bici.setString(1,"0");
						pstmt_devolver_bici.setInt(2,(int)comboBoxBiciDev.getSelectedItem());
						pstmt_devolver_usuario.setInt(1,(int)comboBoxBiciDev.getSelectedItem());
						pstmt_devolver_bici.executeUpdate();
						pstmt_devolver_usuario.executeUpdate();
						pstmt_devolver_bici.close();
						pstmt_devolver_usuario.close();
						
									
						con.close();

						JOptionPane.showMessageDialog(null, "Bici correctamente devuelta");
						}else {
							JOptionPane.showMessageDialog(null, "El usuario y la bici no coinciden");
						}
				}
				
				
				
				
			}catch(SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
	    	btnMostrar.doClick();
			//actualizaBDprimLinea();

	    }
	});
	btnDevolver.setFont(new Font("Silom", Font.BOLD, 15));
	btnDevolver.setBounds(265, 627, 103, 33);
	frameTienda.getContentPane().add(btnDevolver);
	
	
	
	
	
	
	

    }

}
