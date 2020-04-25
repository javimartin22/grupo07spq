package concesionario.servidor.BaseDatos;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;
import concesionario.datos.ClienteFidelidad;




/**
 * Clase de gestion de base de datos del sistema de analiticas
 * 
 * @author Pablo_Gaviria
 */
public class BD {

	private static Exception lastError = null; // Informacion de ultimo error SQL ocurrido
	private static final String TABLA_USUARIO = "Usuarios";
	private static final String COLUMNAS_TABLA_USUARIO = "(nickname string PRIMARY KEY, contrasenia string, tipo int)";
	private static final String TABLA_CLIENTE = "Clientes";
	private static final String COLUMNAS_TABLA_CLIENTE = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string)";
	private static final String TABLA_EMPLEADO = "Empleados";
	private static final String COLUMNAS_TABLA_EMPLEADO = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, tipoEmpleado int)";
	private static final String TABLA_COMERCIAL = "Comerciales";
	private static final String COLUMNAS_TABLA_COMERCIAL = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, cochesVendidos int, importeObtenido int, horas int)";
	private static final String TABLA_MECANICO = "Mecanicos";
	private static final String COLUMNAS_TABLA_MECANICO = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, horas int)";
	private static final String TABLA_DEPARTAMENTO_COMPRAS = "DepartamentoCompras";
	private static final String COLUMNAS_TABLA_DEPARTAMENTO_COMPRAS = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, pedidos int)";
	private static final String TABLA_PIEZAS = "Piezas"; 
	private static final String COLUMNAS_TABLA_PIEZAS = "(codigo string PRIMARY KEY, nombre string, stock int, ubicacion string)";
	private static final String TABLA_PIEZAS_UTILIZADAS = "PiezasUtilizadas"; 
	private static final String COLUMNAS_TABLA_PIEZAS_UTILIZADAS = "(codigo string PRIMARY KEY, nombre string, unidades int, ubicacion string)";
	private static final String TABLA_COCHES_CONCESIONARIO = "CochesConcesionario";
	private static final String COLUMNAS_TABLA_COCHES_CONCESIONARIO = "(modelo string PRIMARY KEY, marca string, color string, CV int, numeroPuertas int, unidades int, precio int)";	
	private static final String TABLA_VENTAS = "Ventas";
	private static final String COLUMNAS_TABLA_VENTAS = "(fecha string, nombreVendedor string, nombreComprador string , marca string, modelo string, matricula string PRIMARY KEY)";	
	private static final String TABLA_TALLER = "Taller";
	private static final String COLUMNAS_TABLA_TALLER = "(matriculaCoche string PRIMARY KEY, marca string, modelo string, mecanico String, dniCliente string, coste double, estado int)";
	private static final String TABLA_COCHES_MATRICULADOS = "CochesMatriculados";
	private static final String COLUMNAS_TABLA_COCHES_MATRICULADOS = "(matricula string PRIMARY KEY, marca string, modelo string, anyomatriculacion int, revisiones int, cv int, nombreCliente string, numPuertas int, color string)";
	private static final String TABLA_PRESUPUESTO = "Presupuesto"; 
	private static final String COLUMNAS_TABLA_PRESUPUESTO = "(codigo string PRIMARY KEY, dniCliente string, mecanico string, marca int, modelo string, problema string, numPiezas int, piezas string, observaviones string, precio int, fecha string)";
	private static final String TABLA_TARIFAS = "Tarifas"; 
	private static final String COLUMNAS_TABLA_TARIFAS = "(idTarifa string PRIMARY KEY, nomTarifa string, precioAprox int, horas_manodeobra int)";
//	private static final String TABLA_PROVEEDORES = "Proveedores"; 
//	private static final String COLUMNAS_TABLA_PROVEEDORES = "(idProveedor string PRIMARY KEY, nomProveedor string, pais string, tipo_piezas string)";
	
	/**
	 * Inicializa una BD SQLITE y devuelve una conexion con ella
	 * 
	 * @param nombreBD Nombre de fichero de la base de datos
	 * @return Conexion con la base de datos indicada. Si hay algun error, se
	 *         devuelve null
	 */
	public static Connection initBD(String nombreBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:BD/" + nombreBD);
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Devuelve statement para usar la base de datos
	 * 
	 * @param con Conexion ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier
	 *         error
	 */
	public static Statement usarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // Poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Crea las tablas de la base de datos. Si ya existen, las deja tal cual
	 * 
	 * @param con Conexion ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier
	 *         error
	 */
	public static Statement usarCrearTablasBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // Poner timeout 30 msg
			try {
				statement.executeUpdate("create table " + TABLA_USUARIO + COLUMNAS_TABLA_USUARIO);
				statement.executeUpdate("create table " + TABLA_CLIENTE + COLUMNAS_TABLA_CLIENTE);
				statement.executeUpdate("create table " + TABLA_EMPLEADO + COLUMNAS_TABLA_EMPLEADO);
				statement.executeUpdate("create table " + TABLA_MECANICO + COLUMNAS_TABLA_MECANICO);
				statement.executeUpdate("create table " + TABLA_COMERCIAL + COLUMNAS_TABLA_COMERCIAL);
				statement.executeUpdate("create table " + TABLA_DEPARTAMENTO_COMPRAS + COLUMNAS_TABLA_DEPARTAMENTO_COMPRAS);
				statement.executeUpdate("create table " + TABLA_PIEZAS + COLUMNAS_TABLA_PIEZAS);
				statement.executeUpdate("create table " + TABLA_PIEZAS_UTILIZADAS + COLUMNAS_TABLA_PIEZAS_UTILIZADAS);
				statement.executeUpdate("create table " + TABLA_COCHES_CONCESIONARIO + COLUMNAS_TABLA_COCHES_CONCESIONARIO);
				statement.executeUpdate("create table " + TABLA_VENTAS + COLUMNAS_TABLA_VENTAS);
				statement.executeUpdate("create table " + TABLA_TALLER + COLUMNAS_TABLA_TALLER);
				statement.executeUpdate("create table " + TABLA_COCHES_MATRICULADOS + COLUMNAS_TABLA_COCHES_MATRICULADOS);
				statement.executeUpdate("create table " + TABLA_PRESUPUESTO + COLUMNAS_TABLA_PRESUPUESTO);
				statement.executeUpdate("create table " + TABLA_TARIFAS + COLUMNAS_TABLA_TARIFAS);
//				statement.executeUpdate("create table " + TABLA_PROVEEDORES + COLUMNAS_TABLA_PROVEEDORES);
			} catch (SQLException e) {
			} // Tabla ya existe. Nada que hacer
			return statement;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Reinicia en blanco las tablas de la base de datos. UTILIZAR ESTE METODO CON
	 * PRECAUCION. Borra todos los datos que hubiera ya en las tablas
	 * 
	 * @param con Conexion ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se borra correctamente, null si hay cualquier
	 *         error
	 */
	public static Statement reiniciarBD(Connection con) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); // Poner timeout 30 msg
			statement.executeUpdate("drop table if exists " + TABLA_USUARIO);
			statement.executeUpdate("drop table if exists " + TABLA_CLIENTE);
			statement.executeUpdate("drop table if exists " + TABLA_EMPLEADO);
			statement.executeUpdate("drop table if exists " + TABLA_MECANICO);
			statement.executeUpdate("drop table if exists " + TABLA_COMERCIAL);
			statement.executeUpdate("drop table if exists " + TABLA_DEPARTAMENTO_COMPRAS);
			statement.executeUpdate("drop table if exists " + TABLA_PIEZAS);
			statement.executeUpdate("drop table if exists " + TABLA_PIEZAS_UTILIZADAS);
			statement.executeUpdate("drop table if exists " + TABLA_COCHES_CONCESIONARIO);
			statement.executeUpdate("drop table if exists " + TABLA_VENTAS);
			statement.executeUpdate("drop table if exists " + TABLA_COCHES_MATRICULADOS);
			statement.executeUpdate("drop table if exists " + TABLA_TALLER);
			statement.executeUpdate("drop table if exists " + TABLA_PRESUPUESTO);
			statement.executeUpdate("drop table if exists " + TABLA_TARIFAS);
//			statement.executeUpdate("drop table if exists " + TABLA_PROVEEDORES);
			return usarCrearTablasBD(con);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Cierra la base de datos abierta
	 * 
	 * @param con Conexion abierta de la BD
	 * @param st  Sentencia abierta de la BD
	 */
	public static void cerrarBD(Connection con, Statement st) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
		}
	}

	/**
	 * Devuelve la informaci�n de excepci�n del �ltimo error producido por
	 * cualquiera de los m�todos de gesti�n de base de datos
	 */
	public static Exception getLastError() {
		return lastError;
	}

//METODOS INSERT TODOS:	
	
	//proveedores
	
//	public static boolean proveedoresInsert(Statement st, String idProveedor, String nomProveedor, String pais, String tipo_piezas) {
//		String sentSQL = "";
//		try {
//			sentSQL = "insert into " + TABLA_PROVEEDORES + " values ('" + secu(idProveedor) + "', '" + nomProveedor + "', '" + pais + "', '" + tipo_piezas +"')";
//			int val = st.executeUpdate(sentSQL);
//			if (val != 1) { // Se tiene que anyadir 1 - error si no
//				return false;
//			}
//			System.out.println("Se ha anyadido correcatente.");
//			return true;
//		} catch (SQLException e) {
//			lastError = e;
//			e.printStackTrace();
//			System.out.println("Error ya registrado.");
//			return false;
//		}
//	}

	// Tabla USUARIOS:
	public static boolean usuariosInsert(Statement st, String nombre, String contrasenia, int tipo) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_USUARIO + " values ('" + secu(nombre) + "', '" + contrasenia + "', " + tipo + ")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
			}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla CLIENTES:	
	public static boolean clientesInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_CLIENTE + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono +"')";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
			}
			System.out.println("Se ha anyadido correcatente.");
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			System.out.println("Error ya registrado.");
			return false;
		}
	}
	
	//Tabla EMPLEADO:
	public static boolean empleadosInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int tipoEmpleado) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_EMPLEADO + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + tipoEmpleado +")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
			}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla COMERCIAL:
	public static boolean comercialesInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int horas, int cochesVendidos, int importeObtenido) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_COMERCIAL + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + cochesVendidos + ", " + importeObtenido + ", " + horas  +")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
			}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla MECANICOS:
	public static boolean mecanicosInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int horas) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_MECANICO + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '"  + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + horas  +")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
		}
			System.out.println("TODO OK");
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla DEPARTAMENTO_COMPRAS:
	public static boolean departamentoComprasInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int pedidos) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_DEPARTAMENTO_COMPRAS + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + pedidos +")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
		}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla PIEZAS:
	public static boolean piezasInsert(Statement st, String codigo, String nombre, int stock, String ubicacion) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_PIEZAS + " values ('" + secu(codigo) + "', '" + nombre + "', " + stock + ", '" + ubicacion + "')";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
		}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla PIEZAS_UTILIZADAS:
		public static boolean piezasUtilizadasInsert(Statement st, String codigo, String nombre, int unidades, String ubicacion) {
			String sentSQL = "";
			try {
				sentSQL = "insert into " + TABLA_PIEZAS_UTILIZADAS + " values ('" + secu(codigo) + "', '" + nombre + "', " + unidades + ", '" + ubicacion + "')";
				int val = st.executeUpdate(sentSQL);
				if (val != 1) { // Se tiene que anyadir 1 - error si no
					return false;
			}
				return true;
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}
		}
	
	//Tabla COCHES_CONCESIONARIO
	public static boolean cochesInsert(Statement st, String modelo, String marca, String color, int CV, int numPuertas, int unidades, int precio) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_COCHES_CONCESIONARIO + " values ('" + secu(modelo) + "', '" + marca + "', '" +  color + "', " + CV + ", " + numPuertas + ", " + unidades + ", " + precio + ")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
		}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla COCHES_MATRICULADOS:
	public static boolean cochesMatriculadosInsert(Statement st, String matricula, String marca, String modelo, int anyoMatriculacion, int revisiones, int cv, String nombrePropietario, int numPuertas, String color) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_COCHES_MATRICULADOS + " values ('" + secu(matricula) + "', '" + marca + "', '" +  modelo + "', " + anyoMatriculacion + ", " + revisiones + ", " + cv + ", '" + nombrePropietario + "', " + numPuertas + ", '" + color + "')";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
		}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	
	//Tabla VENTAS:
	public static boolean cochesVendidodsInsert(Statement st, String fechaVenta, String nombreVendedor, String nombreComprador, String marca, String modelo, String matricula) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_VENTAS + " values ('" + secu(fechaVenta) + "', '" + nombreVendedor + "', '"  + nombreComprador + "', '" + marca + "', '" + modelo + "', '" + matricula + "')";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
		}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla TALLER:
	public static boolean cocheTallerInsert(Statement st, String matriculaCoche, String marca, String modelo, String mecanico, String dniCliente, double coste, int estado) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_TALLER + " values ('" + secu(matriculaCoche) + "', '" + marca + "', '" + modelo + "', '" + mecanico + "', '" + dniCliente + "', " + coste + ", " + estado + ")";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que anyadir 1 - error si no
				return false;
			}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla PRESUPUESTO:
			public static boolean PresupuestoInsert(Statement st, String codigo, String dniCliente, String mecanico, String marca, String modelo, String problema, int numPiezas, String piezas, String observaciones, int precio, String fecha) {
				String sentSQL = "";
				try {
					sentSQL = "insert into " + TABLA_PRESUPUESTO + " values ('" + secu(codigo) + "', '" + secu(dniCliente) + "', '" + mecanico + "', '" + marca + "', '" + modelo  + "', '" + problema + "', " + numPiezas + ", '" + piezas + "', '" + observaciones + "', " + precio + ", '" + fecha + "')";
					int val = st.executeUpdate(sentSQL);
					if (val != 1) { // Se tiene que anyadir 1 - error si no
						return false;
					}
					st.close();
					return true;
				} catch (SQLException e) {
					lastError = e;
					e.printStackTrace();
					return false;
				}
			}
		
			//Tabla TARIFAS:
			public static boolean TarifaInsert(Statement st, String idTarifa, String nomTarifa, int precioAprox, int horas_manodeobra ) {
				String sentSQL = "";
				try {
					sentSQL = "insert into " + TABLA_TARIFAS + " values ('" + secu(idTarifa) + "', '" + secu(nomTarifa) + "', '" + precioAprox + "', '"  + horas_manodeobra + "')";
					int val = st.executeUpdate(sentSQL);
					if (val != 1) { // Se tiene que anyadir 1 - error si no
						return false;
					}
					return true;
				} catch (SQLException e) {
					lastError = e;
					e.printStackTrace();
					return false;
				}
			}
			
			

//METODOS SELECT:

	// Tabla USUARIOS:
	public static Usuario usuarioSelect(Statement st, String nickname) {
		String sentSQL = "";
		Usuario user = null;
		try {
			sentSQL = "select * from " + TABLA_USUARIO + " where nickname='" + nickname + "'";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs.next()) {
				String nombre = rs.getString("nickname");
				String pass = rs.getString("contrasenia");
				int tipo = rs.getInt("tipo");
				user = new Usuario(nombre, pass, tipo);
			}
			rs.close();
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
		}
		return user;
	}
	
	// Tabla CLIENTES:
		public static Cliente clienteSelect(Statement st, String nickname) {
			String sentSQL = "";
			Cliente client = null;
			try {
				sentSQL = "select * from " + TABLA_CLIENTE + " where nickname= '" + nickname + "'";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String dni = rs.getString("dni");
					String nick = rs.getString("nickname");
					String contrasenya = rs.getString("contrasenia");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String sexo = rs.getString("sexo");
					String email = rs.getString("email");
					String ciudad = rs.getString("ciudad");
					int codigoPostal = rs.getInt("codigoPostal");
					String direccion = rs.getString("dir");
					String numeroTelefono = rs.getString("numTelefono");
					client = new Cliente(dni, nick, 3, contrasenya, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, numeroTelefono);
				}
				rs.close();
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
			}
			return client;
		}
		
	//Tabla EMPLEADOS:
		
		//Todas:
//		public static ResultSet proveedoresSelect(Statement st, String cod) {
//			String sentSQL = "";
//			ResultSet rs = null;
//			try {
//				sentSQL = "select * from " + TABLA_PROVEEDORES +" where idProveedor= '" + cod + "'";;
//				rs = st.executeQuery(sentSQL);		
//				
//			} catch (Exception e) {
//				lastError = e;
//				e.printStackTrace();
//			}
//			return rs;
//		}
		//Todas:
		public static ResultSet empleadosTodasSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_EMPLEADO;
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
				
				
		//Busqueda mediante CODIGO:
		public static Empleado empleadoSelect(Statement st, String nickname) {
			String sentSQL = "";
			Empleado empleado = null;
			try {
				sentSQL = "select * from " + TABLA_EMPLEADO + " where nickname= '" + nickname + "' ";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String dni = rs.getString("dni");
					String nick = rs.getString("nickname");
					String contrasenya = rs.getString("contrasenia");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String sexo = rs.getString("sexo");
					String email = rs.getString("email");
					String ciudad = rs.getString("ciudad");
					int codigoPostal = rs.getInt("codigoPostal");
					String direccion = rs.getString("dir");
					String numeroTelefono = rs.getString("numTelefono");
					String NSS = rs.getString("NSS");
					String numeroCuenta = rs.getString("numeroCuenta");
					int tipoEmpleado = rs.getInt("tipoEmpleado");
					int sueldo = rs.getInt("sueldo");
					empleado = new Empleado(nick, contrasenya, 1, dni, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, NSS, numeroCuenta, sueldo, numeroTelefono, tipoEmpleado);
				}
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return empleado;
		}
		
		//Busqueda mediante CODIGO:
				public static Mecanico mecanicoSelect(Statement st, String nickname) {
					String sentSQL = "";
					Mecanico mecanico = null;
					try {
						sentSQL = "select * from " + TABLA_MECANICO + " where nickname= '" + nickname + "' ";
						ResultSet rs = st.executeQuery(sentSQL);
						if (rs.next()) {
							String dni = rs.getString("dni");
							String nick = rs.getString("nickname");
							String contrasenya = rs.getString("contrasenia");
							String nombre = rs.getString("nombre");
							String apellido = rs.getString("apellido");
							String sexo = rs.getString("sexo");
							String email = rs.getString("email");
							String ciudad = rs.getString("ciudad");
							int codigoPostal = rs.getInt("codigoPostal");
							String direccion = rs.getString("dir");
							String numeroTelefono = rs.getString("numTelefono");
							String NSS = rs.getString("NSS");
							String numeroCuenta = rs.getString("numeroCuenta");
							int sueldo = rs.getInt("sueldo");
							int horas = rs.getInt("horas");
							mecanico = new Mecanico(nick, contrasenya, 1, dni, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, NSS, numeroCuenta, sueldo, numeroTelefono, horas);
						}
					} catch (Exception e) {
						lastError = e;
						e.printStackTrace();
					}
					return mecanico;
				}
				
		//Busqueda mediante CODIGO:
		public static Comercial ComercialSelect(Statement st, String nickname) {
			String sentSQL = "";
			Comercial comercial = null;
			try {
				sentSQL = "select * from " + TABLA_COMERCIAL + " where nickname= '" + nickname + "' ";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String dni = rs.getString("dni");
					String nick = rs.getString("nickname");
					String contrasenya = rs.getString("contrasenia");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String sexo = rs.getString("sexo");
					String email = rs.getString("email");
					String ciudad = rs.getString("ciudad");
					int codigoPostal = rs.getInt("codigoPostal");
					String direccion = rs.getString("dir");
					String numeroTelefono = rs.getString("numTelefono");
					String NSS = rs.getString("NSS");
					String numeroCuenta = rs.getString("numeroCuenta");
					int sueldo = rs.getInt("sueldo");
					int cochesVendidos = rs.getInt("cochesVendidos");
					int importeObtenido = rs.getInt("importeObtenido");
					int horas = rs.getInt("horas");
					comercial = new Comercial(nick, contrasenya, dni, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, NSS, numeroCuenta, sueldo, numeroTelefono, 1, cochesVendidos, importeObtenido, horas);
				}
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return comercial;
		}
		
	//Tabla DEPARTAMENTO_COMPRAS:
	public static DepartamentoCompras departamentoCompraSelect(Statement st, String nickname) {
		String sentSQL = "";
		DepartamentoCompras depar = null;
		try {
			sentSQL = "select * from " + TABLA_DEPARTAMENTO_COMPRAS + " where nickname= '" + nickname + "' ";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs.next()) {
				String dni = rs.getString("dni");
				String nick = rs.getString("nickname");
				String contrasenya = rs.getString("contrasenia");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String sexo = rs.getString("sexo");
				String email = rs.getString("email");
				String ciudad = rs.getString("ciudad");
				int codigoPostal = rs.getInt("codigoPostal");
				String direccion = rs.getString("dir");
				String numeroTelefono = rs.getString("numTelefono");
				String NSS = rs.getString("NSS");
				String numeroCuenta = rs.getString("numeroCuenta");
				int sueldo = rs.getInt("sueldo");
				int pedidos = rs.getInt("pedidos");
				depar = new DepartamentoCompras(nick, contrasenya, dni, nombre, apellido, sexo, email, ciudad, codigoPostal, direccion, NSS, numeroCuenta, sueldo, numeroTelefono, pedidos);
			}
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return depar;
	}
	
	//Tabla PIEZAS:
		//Todas:
		public static ResultSet piezasTodasSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_PIEZAS + " order by codigo";
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		public static ResultSet piezaMecanicoFiltroSelect(Statement st, int tipo, String restriccion) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				switch (tipo) {
				case 0:
					sentSQL = "select * from " + TABLA_PIEZAS + " where codigo= '" + restriccion + "'";
					break;
				case 1:
					sentSQL = "select * from " + TABLA_PIEZAS + " where nombre= '" + restriccion + "'";
					break;
				case 2: 
					sentSQL = "select * from " + TABLA_PIEZAS + " where stock= " + restriccion + "";
					break;
				}
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		
		//Busqueda mediante CODIGO:
		public static Pieza piezaSelect(Statement st, String codigo) {
			String sentSQL = "";
			Pieza pieza = null;
			try {
				sentSQL = "select * from " + TABLA_PIEZAS + " where codigo= '" + codigo + "' ";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String cod = rs.getString("codigo");
					String nombre = rs.getString("nombre");
					int unidades = rs.getInt("stock");
					String ubicacion = rs.getString("ubicacion");
					pieza = new Pieza(cod, nombre, unidades, ubicacion);
				}
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return pieza;
		}
		
	//Tabla PIEZAS_UTILIZADAS:
		//Todas:
			public static ResultSet piezasUtilizadasTodasSelect(Statement st) {
				String sentSQL = "";
				ResultSet rs = null;
				try {
					sentSQL = "select * from " + TABLA_PIEZAS_UTILIZADAS + " order by codigo";
					rs = st.executeQuery(sentSQL);
				} catch (Exception e) {
					lastError = e;
					e.printStackTrace();
				}
				return rs;
			}
			
			public static ResultSet piezasUtilizadasFiltroSelect(Statement st, int tipo, String restriccion) {
				String sentSQL = "";
				ResultSet rs = null;
				try {
					switch (tipo) {
					case 0:
						sentSQL = "select * from " + TABLA_PIEZAS_UTILIZADAS + " where unidades< " + restriccion + " order by codigo";
						break;
					case 1:
						sentSQL = "select * from " + TABLA_PIEZAS_UTILIZADAS + " where unidades> " + restriccion + " order by codigo";
						break;
					case 2:
						sentSQL = "select * from " + TABLA_PIEZAS_UTILIZADAS + " where codigo= '" + restriccion + "'";
						break;
					}					
					rs = st.executeQuery(sentSQL);
				} catch (Exception e) {
					lastError = e;
					e.printStackTrace();
				}
				return rs;
			}
				
				
			//Busqueda mediante CODIGO:
			public static Pieza piezaUtilizadaSelect(Statement st, String codigo) {
				String sentSQL = "";
				Pieza pieza = null;
				try {
					sentSQL = "select * from " + TABLA_PIEZAS_UTILIZADAS + " where codigo= '" + codigo + "' ";
					ResultSet rs = st.executeQuery(sentSQL);
					if (rs.next()) {
						String cod = rs.getString("codigo");
						String nombre = rs.getString("nombre");
						int unidades = rs.getInt("unidades");
						String ubicacion = rs.getString("ubicacion");
						pieza = new Pieza(cod, nombre, unidades, ubicacion);
					}
				} catch (Exception e) {
					lastError = e;
					e.printStackTrace();
				}
					return pieza;
			}
			
	// Tabla COCHE_CONCESIONARIO:
	public static CocheConcesionario cocheConcesionarioSelect(Statement st, String modelo) {
		String sentSQL = "";
		CocheConcesionario coche = null;
		try {
			sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where modelo='" + modelo + "'";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs.next()) {
				String model = rs.getString("modelo");
				String marca = rs.getString("marca");
				int precio = rs.getInt("precio");
				int unidades = rs.getInt("unidades");
				int CV = rs.getInt("CV");
				int numPuertas = rs.getInt("numeroPuertas");
				String color = rs.getString("color");
				coche = new CocheConcesionario(marca, model, precio, CV, numPuertas, color, unidades);
			}
			rs.close();
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
		}
		return coche;
	}
	
	//Tabla COCHES_CONCESIONARIO:
		//Todos:
		public static ResultSet cochesTodosSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO;
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		public static ResultSet cochesConcesionarioFiltroSelect(Statement st, String restriccion, int tipo) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				switch (tipo) {
				case 0:
					sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where marca= '" + restriccion + "'";
					break;
				case 1:
					sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where color= '" + restriccion + "'";
					break;
				case 2:
					sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where CV= " + restriccion;
					break;
				case 3:
					sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where precio< " + restriccion;
					break;
				}
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
	//Busqueda mediante MARCA:
		public static ResultSet cochesMarcaSelect(Statement st, String marca) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where marca = '" + marca + "'";
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		//Busqueda mediante MODELO:
		public static ResultSet cochesModeloSelect(Statement st, String modelo) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_COCHES_CONCESIONARIO + " where modelo = '" + modelo + "'";
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
	//Tabla COCHES_MATRICULADOS:
		//Todos:
		public static ResultSet cochesMatricTodosSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_COCHES_MATRICULADOS;
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		public static ResultSet cochesMatriculadosFiltroSelect(Statement st, int tipo, String restriccion) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				switch (tipo) {
				case 0:
					sentSQL = "select * from " + TABLA_COCHES_MATRICULADOS + " where matricula= '" + restriccion + "'";
					break;
				case 1:
					sentSQL = "select * from " + TABLA_COCHES_MATRICULADOS + " where marca= '" + restriccion + "'";
					break;
				case 2:
					sentSQL = "select * from " + TABLA_COCHES_MATRICULADOS + " where nombreCliente= '" + restriccion + "'";
					break;
				}
				
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		
	//Tabla TALLER: 
		//Todos:
		public static ResultSet cochesTallerSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_TALLER;
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		//Busqueda mediante MECANICO:
		public static ResultSet cochesTallerMecanicoSelect(Statement st, String mecanico) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_TALLER + " where mecanico= '" + mecanico + "'";
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		public static ResultSet cochesTallerFiltroSelect(Statement st, int tipo, String restriccion) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				switch (tipo) {
				case 0:
					sentSQL = "select * from " + TABLA_TALLER + " where matriculaCoche= '" + restriccion + "'";
					break;
				case 1:
					sentSQL = "select * from " + TABLA_TALLER + " where mecanico= '" + restriccion + "'";
					break;
				case 2:
					sentSQL = "select * from " + TABLA_TALLER + " where coste< " + restriccion;
					break;
				case 3:
					sentSQL = "select * from " + TABLA_TALLER + " where estado= '" + restriccion + "'";
					break;
				}
				
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		public static CocheTaller cocheTalleSelect(Statement st, String matricula) {
			String sentSQL = "";
			CocheTaller cocheTaller = null;
			try {
				sentSQL = "select * from " + TABLA_TALLER + " where matriculaCoche= '" + matricula + "'";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String matriculaCoche = rs.getString("matriculaCoche");
					String marca = rs.getString("marca");
					String mecanico = rs.getString("mecanico");
					String modelo = rs.getString("modelo");
					String dniCliente = rs.getString("dniCliente");
					double coste = rs.getDouble("coste");
					int estado = rs.getInt("estado");
					cocheTaller = new CocheTaller(matriculaCoche, marca, modelo, mecanico, dniCliente, coste, estado);
				}
				st.close();
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return cocheTaller;
		}
		
		//Tabla Ventas:
 		//Busqueda mediante Matricula:
 				public static Venta ventaSelect(Statement st, String matricula) {
 					String sentSQL = "";
 					Venta venta = null;
 					try {
 						sentSQL = "select * from " + TABLA_VENTAS + " where matricula= '" + matricula + "' ";
 						ResultSet rs = st.executeQuery(sentSQL);
 						if (rs.next()) {
 							String fecha = rs.getString("fecha");
 							String nicknameComercial = rs.getString("nombreVendedor");
 							String marca = rs.getString("marca");
 							String modelo = rs.getString("modelo");
 							String nombreComprador = rs.getString("nombreComprador");
 							String matricul = rs.getString("matricula");
 							venta = new Venta(fecha, modelo, marca, matricul, nicknameComercial, nombreComprador);
 						}
 						st.close();
 					} catch (Exception e) {
 						lastError = e;
 						e.printStackTrace();
 					}
 					return venta;
 				}
 				
 		public static ResultSet ventasTodasSelect(Statement st) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_VENTAS;
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}	
 		
 		public static ResultSet ventasMarcaSelect(Statement st, String marca) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_VENTAS + " where marca= '" + marca + "'";
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}
 		
 		public static ResultSet ventasModeloSelect(Statement st, String modelo) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_VENTAS + " where modelo= '" + modelo + "'";
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}
 		
 		public static ResultSet ventasComercialSelect(Statement st, String comercial) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_VENTAS + " where nombreVendedor= '" + comercial + "'";
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}
 		
 		//Tabla PRESUPUESTO:
 		public static Presupuesto presupuestoDNIClienteSelect(Statement st, String dni) {
 			String sentSQL = "";
 			Presupuesto presupuesto = null;
 			try {
 				sentSQL = "select * from " + TABLA_PRESUPUESTO + " where dniCliente= '" + dni + "' ";
 				ResultSet rs = st.executeQuery(sentSQL);
 				if (rs.next()) {
 					String codigo = rs.getString("codigo");
 					String dniCliente = rs.getString("dniCliente");
 					String mecanico = rs.getString("mecanico");
 					String marca = rs.getString("marca");
 					String modelo = rs.getString("modelo");
 					String problema = rs.getString("problema");
 					int numPiezas = rs.getInt("numPiezas");
 					String listaPiezas = rs.getString("piezas");
 					String observaciones = rs.getString("observaviones");
 					int precio = rs.getInt("precio");
 					String fecha = rs.getString("fecha");
 					presupuesto = new Presupuesto(codigo, dniCliente, mecanico, marca, modelo, problema, numPiezas, listaPiezas, observaciones, precio, fecha);
				}
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return presupuesto;
		}
 		
 		public static Presupuesto presupuestoCodigoSelect(Statement st, String cod) {
 			String sentSQL = "";
 			Presupuesto presupuesto = null;
 			try {
 				sentSQL = "select * from " + TABLA_PRESUPUESTO + " where codigo= '" + cod + "' ";
 				ResultSet rs = st.executeQuery(sentSQL);
 				if (rs.next()) {
 					String codigo = rs.getString("codigo");
 					String dniCliente = rs.getString("dniCliente");
 					String mecanico = rs.getString("mecanico");
 					String marca = rs.getString("marca");
 					String modelo = rs.getString("modelo");
 					String problema = rs.getString("problema");
 					int numPiezas = rs.getInt("numPiezas");
 					String listaPiezas = rs.getString("piezas");
 					String observaciones = rs.getString("observaviones");
 					int precio = rs.getInt("precio");
 					String fecha = rs.getString("fecha");
 					presupuesto = new Presupuesto(codigo, dniCliente, mecanico, marca, modelo, problema, numPiezas, listaPiezas, observaciones, precio, fecha);
				}
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return presupuesto;
		}
 		
 		public static ResultSet presupuestosTodosSelect(Statement st) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_PRESUPUESTO;
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}	
 		
 		public static ResultSet presupuestosFiltroCodigoSelect(Statement st, String codigo) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_PRESUPUESTO + " where codigo= '" + codigo + "'";
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}	
 		
 		public static ResultSet presupuestosFiltroClienteSelect(Statement st, String cliente) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_PRESUPUESTO + " where dniCliente= '" + cliente + "'";
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}	
 		
 		public static ResultSet presupuestosFiltroProblemaSelect(Statement st, String problema) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_PRESUPUESTO + " where problema= '" + problema + "'";
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}	
 		
 		//Tabla TARIFAS:
 		public static ResultSet tarifaPrecioSelect(Statement st, int precio) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_TARIFAS + " where precioAprox< '" + precio + "'";
 				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return rs;
		}
 		
 		public static ResultSet tarifaPrecioMinSelect(Statement st, int precio) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_TARIFAS + " where precioAprox> '" + precio + "'";
 				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return rs;
		}
 		
 		public static ResultSet tarifaHorasMaxSelect(Statement st, int horas) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_TARIFAS + " where horas_manodeobra< '" + horas + "'";
 				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return rs;
		}
 		
 		public static ResultSet tarifaHorasMinSelect(Statement st, int horas) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_TARIFAS + " where horas_manodeobra> '" + horas + "'";
 				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return rs;
		}
 		
 		public static Tarifa tarifaIdSelect(Statement st, String id_tarifa) {
 			String sentSQL = "";
 			Tarifa tarifa = null;
 			try {
 				sentSQL = "select * from " + TABLA_TARIFAS + " where idTarifa= '" + id_tarifa + "' ";
 				ResultSet rs = st.executeQuery(sentSQL);
 				if (rs.next()) {
 					String idTarifa = rs.getString("idTarifa");
 					String nomTarifa = rs.getString("nomTarifa");
 					int precioAprox	= rs.getInt("precioAprox");
 					int horas_manodeobra = rs.getInt("horas_manodeobra");
 					tarifa = new Tarifa(idTarifa, nomTarifa, precioAprox, horas_manodeobra);
				}
 				st.close();
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
 			return tarifa;
		}
 		
 		
 		public static ResultSet tarifasTodosSelect(Statement st) {
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select * from " + TABLA_TARIFAS;
 				rs = st.executeQuery(sentSQL);
 			} catch (Exception e) {
 				lastError = e;
 				e.printStackTrace();
 			}
 			return rs;
 		}	
 		
 		//tabla fidelidad
 		public static ResultSet fidelidadSelect(Statement st){
 			String sentSQL = "";
 			ResultSet rs = null;
 			try {
 				sentSQL = "select dniCliente, count(*) as fidelidad from " + TABLA_TALLER + " group by dniCliente";
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			} 			
 			return rs;
 		}

//METODOS DELETE:

	// Tabla USUARIOS:
	public static boolean usuariosDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from usuarios where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla CLIENTE:
	public static boolean clientesDelete(Statement st, String dni) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_CLIENTE + " where dni= '" + secu(dni) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla EMPLEADO:
	public static boolean empleadosDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_EMPLEADO + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla MECANICO:
	public static boolean mecanicosDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_MECANICO + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla COMERCIAL:
	public static boolean comercialesDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_COMERCIAL + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla COMERCIAL:
	public static boolean departamentoComprasDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_DEPARTAMENTO_COMPRAS + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	// Tabla PIEZA:
		public static boolean piezaDelete(Statement st, String codigo) {
			String sentSQL = "";
			try {
				sentSQL = "delete from " + TABLA_PIEZAS + " where codigo= '" + secu(codigo) + "'";
				int val = st.executeUpdate(sentSQL);
				return (val == 1);
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}
		}
	
		// Tabla PIEZA:
		public static boolean piezaUtilizadaDelete(Statement st, String codigo) {
			String sentSQL = "";
			try {
				sentSQL = "delete from " + TABLA_PIEZAS_UTILIZADAS + " where codigo= '" + secu(codigo) + "'";
				int val = st.executeUpdate(sentSQL);
				return (val == 1);
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}
		}
				
		
	//Tabla COCHE:
	public static boolean cochesDelete(Statement st, String modelo) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_COCHES_CONCESIONARIO + " where modelo= '" + secu(modelo) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla TALLER:
		public static boolean cocheTallerDelete(Statement st, String matricula) {
			String sentSQL = "";
			try {
				sentSQL = "delete from " + TABLA_TALLER + " where matriculaCoche= '" + secu(matricula) + "'";
				System.out.println(sentSQL);
				int val = st.executeUpdate(sentSQL);
				System.out.println("ha pasado o se queda en update");
				System.out.println(val);
				return (val == 1);
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}	
		}
	
	//Tabla VENTAS:
	public static boolean ventasDelete(Statement st, String codigoVenta) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_VENTAS + " where codigoVenta= '" + secu(codigoVenta) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}	
	}
	
	//TABLA TARIFA:
	public static boolean tarifasDelete(Statement st, String idTarifa) {
		String sentSQL = "";
		try {
			System.out.println("llega delete");
			sentSQL = "delete from " + TABLA_TARIFAS + " where idTarifa= '" + secu(idTarifa) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}	
	}
	
	/////////////////////////////////////////////////////////////////////
	// Metodos privados //
	/////////////////////////////////////////////////////////////////////

	// Devuelve el string "securizado" para volcarlo en SQL
	// (Implementacion 1) Sustituye ' por '' y quita saltos de l�nea
	// (Implementacion 2) Mantiene solo los caracteres seguros en espa�ol
	private static String secu(String string) {
		// Implementacion (1)
		// return string.replaceAll( "'", "''" ).replaceAll( "\\n", "" );
		// Implementacion (2)
		StringBuffer ret = new StringBuffer();
		for (char c : string.toCharArray()) {
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ��������������.,:;-_(){}[]-+*=<>'\"�?�!&%$@#/\\0123456789"
					.indexOf(c) >= 0)
				ret.append(c);
		}
		return ret.toString();
	}
}
