package concesionario.servidor.BaseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



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
	private static final String COLUMNAS_TABLA_CLIENTE = "(dni string PRIMARY KEY, nickname string, contrasenia string, tipoUsuario int, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, matriculaCoche string)";
	private static final String TABLA_EMPLEADO = "Empleados";
	private static final String COLUMNAS_TABLA_EMPLEADO = "(dni string PRIMARY KEY, nickname string, contrasenia string, tipoUsuario int, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, tipoEmpleado int)";
	private static final String TABLA_COMERCIAL = "Comerciales";
	private static final String COLUMNAS_TABLA_COMERCIAL = "(dni string PRIMARY KEY, nickname string, contrasenia string, tipoUsuario int, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, cochesVendidos int, importeObtenido int, horas int, tipoEmpleado int)";
	private static final String TABLA_MECANICO = "Mecanicos";
	private static final String COLUMNAS_TABLA_MECANICO = "(dni string PRIMARY KEY, nickname string, contrasenia string, tipoUsuario int, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, horas int, tipoEmpleado int)";
	private static final String TABLA_PIEZAS = "Piezas"; 
	private static final String COLUMNAS_TABLA_PIEZAS = "(codigo string PRIMARY KEY, nombre string, stock int)";
	private static final String TABLA_COCHES_VENDIDOS = "CochesVendidos";
	private static final String COLUMNAS_TABLA_COCHES_VENDIDOS = "(codigoVenta string PRIMARY KEY, nombreVendedor string, dniComprador string , marca string, modelo string)";	
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
				statement.executeUpdate("create table " + TABLA_PIEZAS + COLUMNAS_TABLA_PIEZAS);
				statement.executeUpdate("create table " + TABLA_COCHES_VENDIDOS + COLUMNAS_TABLA_COCHES_VENDIDOS);
				
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
			statement.executeUpdate("drop table if exists " + TABLA_PIEZAS);
			statement.executeUpdate("drop table if exists " + TABLA_COCHES_VENDIDOS);
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

//METODOS INSERT:	

	// Tabla USUARIOS:
	public static boolean usuariosInsert(Statement st, String nombre, String contrasenia) {
		String sentSQL = "";
		try {
			sentSQL = "insert into usuarios values ('" + secu(nombre) + "', '" + contrasenia + "')";
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

//	// Tabla USUARIOS CONTRASENYA:
//	public static Usuario usuarioSelect(Statement st, String txtNombreUsuario) {
//		String sentSQL = "";
//		Usuario user = null;
//		try {
//			sentSQL = "select * from " + TABLA_USUARIO + " where nombre='" + txtNombreUsuario + "'";
//			ResultSet rs = st.executeQuery(sentSQL);
//			if (rs.next()) {
//				String nombre = rs.getString("nombre");
//				String pass = rs.getString("contrasenia");
//				int tipo = rs.getInt("tipo");
//				user = new Usuario(nombre, pass, tipo);
//			}
//			rs.close();
//		} catch (SQLException e) {
//			lastError = e;
//			e.printStackTrace();
//		}
//		return user;
//	}

	
				
//METODOS UPDATE:	

	// Tabla USUARIOS CONTRASENYA:
	public static boolean usuariosUpdate(Statement st, String nombre, String contrasenia) {
		String sentSQL = "";
		try {
			sentSQL = "update usuarios set nombre= '" + nombre + "' where contrasenia='" + contrasenia + "'";
			int val = st.executeUpdate(sentSQL);
			if (val != 1) { // Se tiene que modificar 1 - error si no
				return false;
			}
			return true;
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	

//METODOS DELETE:

	// Tabla USUARIOS CONTRASENYA:
	public static boolean usuariosDelete(Statement st, String nombre) {
		String sentSQL = "";
		try {
			sentSQL = "delete from usuarios where codigo= '" + secu(nombre) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla JUNTAKIDES UNICO:
	public static boolean juntakidesUnicoDelete(Statement st, String nombre) {
		String sentSQL = "";
		try {
			sentSQL = "delete from Juntakides where nombreJuntakide= '" + nombre + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla JUNTAKIDES UNICO:
	public static boolean juntakidesEliminadosUnicoDelete(Statement st, String nombre, String nombreAMPA) {
		String sentSQL = "";
		try {
			sentSQL = "delete from JuntakidesEliminados where nombreJuntakide= '" + nombre + "' AND nombreAMPA= '" + nombreAMPA + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
		
	//Tabla LLAMADA - OBSERVACIONES:
	public static boolean llamadaDelete(Statement st, String codigoLlamada) {
		String sentSQL = "";
		try {
			sentSQL = "delete from Observaciones where cod_Id= '" + codigoLlamada + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

	//Tabla JUNTAKIDES TODOS:
	public static boolean juntakidesTodosDelete(Statement st, String nombreAmpa) {
		String sentSQL = "";
		try {
			sentSQL = "delete from Juntakides where nombreAMPA= '" + nombreAmpa + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla AMPAS:
		public static boolean AMPASDelete(Statement st, String nombreAmpa) {
			String sentSQL = "";
			try {
				sentSQL = "delete from AMPAS where nombreAMPA= '" + nombreAmpa + "'";
				int val = st.executeUpdate(sentSQL);
				return (val == 1);
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}
		}
	
	//Tabla COLEGIOS:
	public static boolean colegioDelete(Statement st, String nombreColegio) {
		String sentSQL = "";
		try {
			sentSQL = "delete from Colegios where nombreCentro= '" + nombreColegio + "'";
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
