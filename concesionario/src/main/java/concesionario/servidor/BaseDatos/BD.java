package concesionario.servidor.BaseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import concesionario.servidor.datos.Usuario;



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
	private static final String COLUMNAS_TABLA_CLIENTE = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, matriculaCoche string)";
	private static final String TABLA_EMPLEADO = "Empleados";
	private static final String COLUMNAS_TABLA_EMPLEADO = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, tipoEmpleado int)";
	private static final String TABLA_COMERCIAL = "Comerciales";
	private static final String COLUMNAS_TABLA_COMERCIAL = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, cochesVendidos int, importeObtenido int, horas int)";
	private static final String TABLA_MECANICO = "Mecanicos";
	private static final String COLUMNAS_TABLA_MECANICO = "(dni string PRIMARY KEY, nickname string, contrasenia string, nombre string, apellido string, sexo string, email string, ciudad string, codigoPostal int, dir string, numTelefono string, NSS string, numeroCuenta string, sueldo int, horas int)";
	private static final String TABLA_PIEZAS = "Piezas"; 
	private static final String COLUMNAS_TABLA_PIEZAS = "(codigo string PRIMARY KEY, nombre string, stock int)";
	private static final String TABLA_COCHES = "Coches";
	private static final String COLUMNAS_TABLA_COCHES = "(modelo string PRIMARY KEY, marca string, unidades int)";	
	private static final String TABLA_COCHES_VENDIDOS = "CochesVendidos";
	private static final String COLUMNAS_TABLA_COCHES_VENDIDOS = "(codigoVenta string PRIMARY KEY, nombreVendedor string, dniComprador string , marca string, modelo string)";	
	private static final String TABLA_TALLER = "Taller";
	private static final String COLUMNAS_TABLA_TALLER = "(matriculaCoche string PRIMARY KEY, marca string, modelo string, mecanico String, dniCliente string, coste double, estado int)";
	
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
				statement.executeUpdate("create table " + TABLA_COCHES + COLUMNAS_TABLA_COCHES);
				statement.executeUpdate("create table " + TABLA_COCHES_VENDIDOS + COLUMNAS_TABLA_COCHES_VENDIDOS);
				statement.executeUpdate("create table " + TABLA_TALLER + COLUMNAS_TABLA_TALLER);
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
			statement.executeUpdate("drop table if exists " + TABLA_COCHES);
			statement.executeUpdate("drop table if exists " + TABLA_COCHES_VENDIDOS);
			statement.executeUpdate("drop table if exists " + TABLA_TALLER);
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
	public static boolean clientesInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String matriculaCoche) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_CLIENTE + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + matriculaCoche +"')";
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
	
	//Tabla EMPLEADO:
	public static boolean empleadosInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int tipoEmpleado) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_EMPLEADO + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + tipoEmpleado +")";
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
	public static boolean comercialesInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int cochesVendidos, int importeObtenido, int horas) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_COMERCIAL + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + cochesVendidos + ", " + importeObtenido + ", " + horas  +")";
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
	public static boolean mecanicosInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int horas) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_MECANICO + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + horas  +")";
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
	public static boolean piezasInsert(Statement st, String codigo, String nombre, int stock) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_PIEZAS + " values ('" + secu(codigo) + "', '" + nombre + "', " + stock + ")";
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
	
	//Tabla COCHES
	public static boolean cochesInsert(Statement st, String modelo, String marca, int unidades) {
		String sentSQL = "";
		try {
			sentSQL = "insert into" + TABLA_COCHES + "values ('" + secu(modelo) + "', '" + marca + "', " + unidades + ")";
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
	
	//Tabla COCHES_VENDIDOS:
	public static boolean cochesVendidodsInsert(Statement st, String codigoVenta, String nombreVendedor, String dniComprador, String marca, String modelo) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_COCHES_VENDIDOS + " values ('" + secu(codigoVenta) + "', '" + nombreVendedor + "', '" + dniComprador + "', '" + marca + "', '" + modelo + "')";
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
	
	//Tabla PIEZAS:
		//Todas:
		public static ResultSet piezasTodasSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_PIEZAS;
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		//Busqueda mediante CODIGO:
		public static ResultSet piezaSelect(Statement st, String codigo) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_PIEZAS + " where codigo= '" + codigo + "'";
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
	
	//Tabla COCHES:
		//Todos:
		public static ResultSet cochesTodosSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_COCHES;
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
				sentSQL = "select * from " + TABLA_COCHES + " where marca = '" + marca + "'";
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
				sentSQL = "select * from " + TABLA_COCHES + " where modelo = '" + modelo + "'";
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
