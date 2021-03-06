package concesionario.servidor.BaseDatos;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import concesionario.datos.CitaComercial;
import concesionario.datos.CitaTaller;
import concesionario.datos.Cliente;
import concesionario.datos.CocheConcesionario;
import concesionario.datos.CocheTaller;
import concesionario.datos.Comercial;
import concesionario.datos.DepartamentoCompras;
import concesionario.datos.Empleado;
import concesionario.datos.HerramientasTaller;
import concesionario.datos.HorasEmpleados;
import concesionario.datos.Mecanico;
import concesionario.datos.Pieza;
import concesionario.datos.Presupuesto;
import concesionario.datos.Proveedor;
import concesionario.datos.SolicitudCompra;
import concesionario.datos.Tarifa;
import concesionario.datos.Usuario;
import concesionario.datos.Venta;




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
	private static final String TABLA_PROVEEDORES = "Proveedores"; 
	private static final String COLUMNAS_TABLA_PROVEEDORES = "(idProveedor string PRIMARY KEY, nombre string, pais string, tipo string)";
	private static final String TABLA_PIEZAS_PROVEEDORES = "PiezasProveedor"; 
	private static final String COLUMNAS_TABLA_PIEZAS_PROVEEDORES = "(codigo string PRIMARY KEY, nombre string, tiempo int, tipo string, codProveedor string)";
	private static final String TABLA_CITAS_COMERCIAL = "CitasComercial"; 
	private static final String COLUMNAS_TABLA_CITAS_COMERCIAL = "(nombre string, dniCliente string, fecha string, hora string, comercial string)";
	private static final String TABLA_CITAS_TALLER = "CitasTaller"; 
	private static final String COLUMNAS_TABLA_CITAS_TALLER = "(nombre string, dniCliente string, fecha string, hora string, mecanico string, problema string)";
	private static final String TABLA_HERRAMIENTAS = "Herramientas"; 
	private static final String COLUMNAS_TABLA_HERRAMIENTAS = "(codigo string PRIMARY KEY, nombre string, tipo string ,tiempo int, codProveedor string)";
	private static final String TABLA_PROVEEDORES_HERRAMIENTAS = "ProveedorHerramienta"; 
	private static final String COLUMNAS_TABLA_PROVEEDORES_HERRAMIENTAS = "(idProveedor string PRIMARY KEY, nombre string, pais string, tipo string)";
	private static final String TABLA_HERRAMIENTAS_TALLER = "HerramientasTaller"; 
	private static final String COLUMNAS_TABLA_HERRAMIENTAS_TALLER = "(codigo string PRIMARY KEY, nombre string, stock int, ubicacion string)";
	private static final String TABLA_HORAS_EMPLEADO = "HorasEmpleado"; 
	private static final String COLUMNAS_TABLA_HORAS_EMPLEADO = "(nickname string PRIMARY KEY, horas int, minutos int)";
	private static final String TABLA_HORAS_EMPLEADO_TEMPORAL = "HorasEmpleadoTemporal"; 
	private static final String COLUMNAS_TABLA_HORAS_EMPLEADO_TEMPORAL = "(nickname string PRIMARY KEY, horas int, minutos int)";
	private static final String TABLA_SOLICITUD_COMPRA = "SolicitudCompra"; 
	private static final String COLUMNAS_TABLA_SOLICITUD_COMPRA = "(codigo string PRIMARY KEY, nombre string,tipo string, unidades int)";
			
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
				statement.executeUpdate("create table " + TABLA_PROVEEDORES + COLUMNAS_TABLA_PROVEEDORES);
				statement.executeUpdate("create table " + TABLA_PIEZAS_PROVEEDORES + COLUMNAS_TABLA_PIEZAS_PROVEEDORES);
				statement.executeUpdate("create table " + TABLA_CITAS_COMERCIAL + COLUMNAS_TABLA_CITAS_COMERCIAL);
				statement.executeUpdate("create table " + TABLA_CITAS_TALLER + COLUMNAS_TABLA_CITAS_TALLER);
				statement.executeUpdate("create table " + TABLA_HERRAMIENTAS + COLUMNAS_TABLA_HERRAMIENTAS);
				statement.executeUpdate("create table " + TABLA_PROVEEDORES_HERRAMIENTAS + COLUMNAS_TABLA_PROVEEDORES_HERRAMIENTAS);
				statement.executeUpdate("create table " + TABLA_HERRAMIENTAS_TALLER + COLUMNAS_TABLA_HERRAMIENTAS_TALLER);
				statement.executeUpdate("create table " + TABLA_HORAS_EMPLEADO + COLUMNAS_TABLA_HORAS_EMPLEADO);
				statement.executeUpdate("create table " + TABLA_HORAS_EMPLEADO_TEMPORAL + COLUMNAS_TABLA_HORAS_EMPLEADO_TEMPORAL);
				statement.executeUpdate("create table " + TABLA_SOLICITUD_COMPRA + COLUMNAS_TABLA_SOLICITUD_COMPRA);

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
			statement.executeUpdate("drop table if exists " + TABLA_PROVEEDORES);
			statement.executeUpdate("drop table if exists " + TABLA_PIEZAS_PROVEEDORES);
			statement.executeUpdate("drop table if exists " + TABLA_CITAS_COMERCIAL);
			statement.executeUpdate("drop table if exists " + TABLA_CITAS_TALLER);
			statement.executeUpdate("drop table if exists " + TABLA_HERRAMIENTAS);
			statement.executeUpdate("drop table if exists " + TABLA_PROVEEDORES_HERRAMIENTAS);
			statement.executeUpdate("drop table if exists " + TABLA_HERRAMIENTAS_TALLER);
			statement.executeUpdate("drop table if exists " + TABLA_HORAS_EMPLEADO);
			statement.executeUpdate("drop table if exists " + TABLA_HORAS_EMPLEADO_TEMPORAL);
			statement.executeUpdate("drop table if exists " + TABLA_SOLICITUD_COMPRA);

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
	/**
	 * Metodo para insertar las solitudes de compra en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo de solicitud)
	 * @param nombre (Nombre de lo que se desee comprar)
	 * @param tipo (Tipo de lo que se desee comprar)
	 * @param unidades (Cantidad de lo que se desee comprar)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean solicitudInsert(Statement st, String codigo ,String nombre, String tipo, int unidades) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_SOLICITUD_COMPRA + " values ('" + secu(codigo)+ "', '" + nombre  + "', '" + tipo + "', '" + unidades +"')";
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
	
	/**
	 * Metodo para insertar las herramientas en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo de la herramienta)
	 * @param nombre (Nombre de la herramienta)
	 * @param tipo (Tipo de herramienta)
	 * @param tiempo (Tiempo que tarda la herramienta en llegar)
	 * @param codProveedor (Proveedor de la herramienta)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean herramientasInsert(Statement st, String codigo, String nombre, String tipo, String tiempo, String codProveedor) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_HERRAMIENTAS + " values ('" + secu(codigo) + "', '" + nombre + "', '" + tipo + "', '" + tiempo +"', '" +codProveedor+"')";
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
	
	/**
	 * Metodo para meter los proveedores de herramientas en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param idProveedor (Identificativo del proveedor)
	 * @param nomProveedor (Nombre del proveedor)
	 * @param pais (Pais del porveedor)
	 * @param tipo (Tipo de proveedor)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean proveedoresHerramientasInsert(Statement st, String idProveedor, String nomProveedor, String pais, String tipo) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_PROVEEDORES_HERRAMIENTAS + " values ('" + secu(idProveedor) + "', '" + nomProveedor + "', '" + pais + "', '" + tipo +"')";
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
	
	/**
	 * Metodo para insertar los proveedores de piezas en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param idProveedor (Identificativo del proveedor)
	 * @param nomProveedor (Nombre del proveedor)
	 * @param pais (Pais del proveedor)
	 * @param tipo_piezas (Tipo de pieza)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean proveedoresInsert(Statement st, String idProveedor, String nomProveedor, String pais, String tipo_piezas) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_PROVEEDORES + " values ('" + secu(idProveedor) + "', '" + nomProveedor + "', '" + pais + "', '" + tipo_piezas +"')";
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
	
	/**
	 * Metodo para insertar las piezas de los proveedores en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo de la pieza del proveedor)
	 * @param nombre (Nombre de la pieza del proveedor)
	 * @param tiempo (Tiempo que tarda en llegar la pieza)
	 * @param tipo  (Tipo de pieza del proveedor)
	 * @param codProveedor (Identificativo del proveedor)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean piezasProveedoresInsert(Statement st, String codigo, String nombre, int tiempo , String tipo, String codProveedor) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_PIEZAS_PROVEEDORES + " values ('" + secu(codigo) + "', '" + nombre + "', " + tiempo + ", '" + tipo +"', '"+ codProveedor +"')";
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


	// Tabla USUARIOS:
	/**
	 * Metodo para insertar usuarios en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param nombre (Nickname del usuario)
	 * @param contrasenia (Contrasenia del usuario)
	 * @param tipo (Tipo de usuario, ya que disponemos de mecanicos, comerciales, departamentos de compra clientes...)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo para insertar clientes en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param dni (DNI del cliente)
	 * @param nickname (Nickname del cliente)
	 * @param contrasenia (Contrasenia del cliente)
	 * @param nombre (Nombre del cliente)
	 * @param apellido (Apellido del cliente)
	 * @param sexo (Sexo del cliente)
	 * @param email (Email del cliente)
	 * @param ciudad (Ciudad del cliente)
	 * @param codigoPostal (Codigo Postal del cliente)
	 * @param dir (Direccion del cliente)
	 * @param numTelefono (Numero de Telefono del cliente)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean clientesInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_CLIENTE + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '" + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono +"')";
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
	/**
	 * Metodo para insertar empleados en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param dni (DNI del empleado)
	 * @param nickname (Nickname del empleado)
	 * @param contrasenia (Contrasenia del empleado)
	 * @param nombre (Nombre del empleado)
	 * @param apellido (Apellido del empleado)
	 * @param sexo (Sexo del empleado)
	 * @param email (Email del empleado)
	 * @param ciudad (Ciudad del empleado)
	 * @param codigoPostal (Codigo Postal del empleado)
	 * @param dir (Direccion del empleado)
	 * @param numTelefono (Numero de Telefono del empleado)
	 * @param NSS (Numero de Seguridad Social del empleado)
	 * @param numeroCuenta (Numero de Cuenta del empleado)
	 * @param sueldo (Sueldo)
	 * @param tipoEmpleado (Tiempo trabajado del empleado)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo para insertar los usuario de tipo comercial en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param dni (DNI del comercial)
	 * @param nickname (Nickname del comercial)
	 * @param contrasenia (Contrasenia del comercial)
	 * @param nombre (Nombre del comercial)
	 * @param apellido (Apellido del comercial)
	 * @param sexo (Sexo del comercial)
	 * @param email (Emain del comercial)
	 * @param ciudad (Ciudad del comercial)
	 * @param codigoPostal (Codigo Postal del comercial)
	 * @param dir (Direccion del comercial)
	 * @param numTelefono (Numero de Telefono del comercial)
	 * @param NSS (Numero de Seguridad Social del comercial)
	 * @param numeroCuenta (Numero de Cuenta del comercial)
	 * @param sueldo (Sueldo del comercial)
	 * @param horas (Horas trabajadas del comercial)
	 * @param cochesVendidos (Cantidad de coches vendidos por comercial)
	 * @param importeObtenido (Importe optenido por el comercial)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo para insertar usuarios de tipo mecanicos en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param dni (DNI del mecanico)
	 * @param nickname (Nickname del mecanico)
	 * @param contrasenia (Contrasenia del mecanico)
	 * @param nombre (Nombre del mecanico)
	 * @param apellido (Apellido del mecanico)
	 * @param sexo (Sexo del mecanico)
	 * @param email (Email del mecanico)
	 * @param ciudad (Ciudad del mecanico)
	 * @param codigoPostal (Codigo postal del mecanico)
	 * @param dir (Direccion del mecanico)
	 * @param numTelefono (Numero de telefono del mecanico)
	 * @param NSS (Numero de Seguridad Social del mecanico)
	 * @param numeroCuenta (Numero de Cuenta del mecanico)
	 * @param sueldo (Sueldo del mecanico)
	 * @param horas (Horas trabajadas del mecanico)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean mecanicosInsert(Statement st, String dni, String nickname, String contrasenia, String nombre, String apellido, String sexo, String email, String ciudad, int codigoPostal, String dir, String numTelefono, String NSS, String numeroCuenta, int sueldo, int horas) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_MECANICO + " values ('" + secu(dni) + "', '" + nickname + "', '" + contrasenia + "', '" + nombre + "', '" + apellido + "', '" + sexo + "', '" + email + "', '"  + ciudad + "', " + codigoPostal + ",'" + dir + "', '" + numTelefono + "', '" + NSS + "', '" + numeroCuenta + "', " + sueldo + ", " + horas  +")";
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
	
	//Tabla DEPARTAMENTO_COMPRAS:
	/**
	 * Metodo para insertar usuario de tipo departamento de compra en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param dni (DNI del usuario de departamento de compras)
	 * @param nickname (Nickname del usuario de departamento de compras)
	 * @param contrasenia (Contrasenia del usuario de departamento de compras)
	 * @param nombre(Nombre del usuario de departamento de compras)
	 * @param apellido (Apellido del usuario de departamento de compras)
	 * @param sexo (Sexo del usuario de departamento de compras)
	 * @param email (Email del usuario de departamento de compras)
	 * @param ciudad (Ciudad del usuario de departamento de compras)
	 * @param codigoPostal (Codigo Postal del usuario de departamento de compras)
	 * @param dir (Direccion del usuario de departamento de compras)
	 * @param numTelefono (Numero de Telefono del usuario de departamento de compras)
	 * @param NSS (Numero de Seguridad Social del usuario de departamento de compras)
	 * @param numeroCuenta (Numero de cuenta del usuario de departamento de compras)
	 * @param sueldo (Sueldo del usuario de departamento de compras)
	 * @param pedidos (Pedidos del usuario de departamento de compras)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	
	/**
	 * Metodo para insertar las herramientas del taller en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo de la herramienta)
	 * @param nombre (Nombre de la herramienta)
	 * @param stock (Cantidad de la herramienta que se dispone en el stock )
	 * @param ubicacion (Ubicacion de la herramienta en el taller)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean herramientasTallerInsert(Statement st, String codigo, String nombre, int stock, String ubicacion) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_HERRAMIENTAS_TALLER + " values ('" + secu(codigo) + "', '" + nombre + "', " + stock + ", '" + ubicacion + "')";
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
	/**
	 * Metodo para insertar piezas en la base de datos. 
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo de la pieza)
	 * @param nombre (Nombre de la pieza)
	 * @param stock (Cantidad de la pieza que se dispone en stock)
	 * @param ubicacion (Ubicacion dela pieza)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo para insertar las piezas utilizadas en el taller en la base de datos.
	 * @param st (Sttement para la conexion)
	 * @param codigo (Codigo de la pieza utilizada)
	 * @param nombre (Nombre de la pieza utilizada)
	 * @param unidades (Unidades de las piezas utilizadas)
	 * @param ubicacion (Ubicacion de las piezas utilizadas)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
		/**
		 * Metodo para insercar coches en el concesionario.
		 * @param st (Statement para la conexion)
		 * @param modelo (Modelo del coche)
		 * @param marca (Marca del coche)
		 * @param color (Color del coche)
		 * @param CV (CV del coche)
		 * @param numPuertas (Numero de Puertas del coche)
		 * @param unidades (Unidades del coche)
		 * @param precio (Precio del coche)
		 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
		 */
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
	/**
	 * Metodo para insertar los coches matriculados en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param matricula (Matricula del coche matriculado)
	 * @param marca (Marca del coche matriculado)
	 * @param modelo (Modelo del coche matriculado)
	 * @param anyoMatriculacion (Anyo de Matriculacion del coche matriculado)
	 * @param revisiones (Cantidad de revisiones del coche matriculado)
	 * @param cv (CV del coche matriculado)
	 * @param nombrePropietario (Nombre del Propietario del coche matriculado)
	 * @param numPuertas (Numero de Puertas del coche matriculado)
	 * @param color (Color del coche matriculado)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo para insertar los coches ventido en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param fechaVenta (Fecha de venta del coche que se ha vendido)
	 * @param nombreVendedor (Nombre del vendedor que ha vendido el coche)
	 * @param nombreComprador (Nombre del comprador del coche)
	 * @param marca (Marca del coche vendido)
	 * @param modelo (Moldelo del coche vendido)
	 * @param matricula (Matricula del coche vendido)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo para insertar los coches que pasan por el taller en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param matriculaCoche (Matricula del coche que pasa por el taller)
	 * @param marca (Marca del coche que pasa por el taller)
	 * @param modelo (Modelo del coche que pasa por el taller)
	 * @param mecanico (Mecanico que se encarga del coche que pasa por el taller)
	 * @param dniCliente (DNI del Cliente que deja el coche en el taller)
	 * @param coste (Coste de las acciones que se han hecho en el taller con el coche)
	 * @param estado (Estado del coche que esta en taller)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
	/**
	 * Metodo par insertar los presupuestos del taller en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo del presupuesto)
	 * @param dniCliente (DNI del Cliente)
	 * @param mecanico (Mecanico)
	 * @param marca (Marca del coche)
	 * @param modelo (Modelo del coche)
	 * @param problema (Problema del coche del cliente)
	 * @param numPiezas (Numero de piezas que se necesitan para solucionar el problema)
	 * @param piezas (Piezas que se utilizan para solucionar el problema)
	 * @param observaciones (Observaciones)
	 * @param precio (Precio del presupuesto)
	 * @param fecha (Fecha del presupuesto)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
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
			/**
			 * Metodo para insertar las tarifas del taller en la base de datos.
			 * @param st (Statement para la conexion)
			 * @param idTarifa (Identificativo de la tarifa)
			 * @param nomTarifa (Nombre de la tarifa)
			 * @param precioAprox (Precio Aproximado de la tarifa)
			 * @param horas_manodeobra (Horas de Mano de Obra de la tarifa)
			 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
			 */
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
	/**
	 * Metodo para inserta las citas del comercial en la base de datos.		
	 * @param st (Statement para la conexion)
	 * @param nombre (Nombre del cliente que tiene la cita con el comercial)
	 * @param DNICliente (DNI del CLiente que tiene la cita con el comercial)
	 * @param fecha (Fecha de la cita)
	 * @param hora (Hora de la cita)
	 * @param comercial (Nombre del comercial que tiene la cita)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean CitaComercialInsert(Statement st, String nombre, String DNICliente, String fecha, String hora, String comercial ) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_CITAS_COMERCIAL + " values ('" + secu(nombre) + "', '" + secu(DNICliente) + "', '" + fecha + "', '"  + hora + "', '"  + comercial + "')";
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
	
	/**
	 * Metodo para insertar las citas de los mecanicos en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param nombre (Nombre del cliente que tiene la cita con el mecanico)
	 * @param DNICliente (DNI del Cliente que tiene la cita con el mecanico)
	 * @param fecha (Fecha de la cita)
	 * @param hora (Hora del cita)
	 * @param mecanico (Nomrbe del mecanico que tiene la cita)
	 * @param problema (Problema del cliente por el cual ha solicitado la cita)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean CitaTallerInsert(Statement st, String nombre, String DNICliente, String fecha, String hora, String mecanico, String problema ) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_CITAS_TALLER + " values ('" + secu(nombre) + "', '" + secu(DNICliente) + "', '" + fecha + "', '"  + hora + "', '"  + mecanico + "', '"  + problema + "')";
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
	
	/**
	 * Metodo para insertar las horas de los empleados en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del empleado)
	 * @param hora (Horas del empleado)
	 * @param minuto (Minutos del empleado)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean HorasEmpleadoInsert(Statement st, String nickname, int hora, int minuto) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_HORAS_EMPLEADO + " values ('" + secu(nickname) + "', " + hora + ", " + minuto + ")";
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
	
	/**
	 * Metodo para insertas las horas del empleado temporal en la base de datos.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del empleado)
	 * @param hora (Horas del empleado)
	 * @param minuto (Minutos del empleado)
	 * @return boolean (Si el insert es correcta, devuelve un True, si no, devuelve un False)
	 */
	public static boolean HorasEmpleadoTemporalInsert(Statement st, String nickname, int hora, int minuto) {
		String sentSQL = "";
		try {
			sentSQL = "insert into " + TABLA_HORAS_EMPLEADO_TEMPORAL + " values ('" + secu(nickname) + "', " + hora + ", " + minuto + ")";
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

//METODOS SELECT:
	
		/**
		 * Metodo para obetenr las herramientas de la base de datos.
		 * @param st (Statement para la conexion)
		 * @return rs (Objeto ResultSet)
		 */
		public static ResultSet herramientasSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_HERRAMIENTAS;
				rs = st.executeQuery(sentSQL);		
				
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}	
		
		/**
		 * Metodo para obtener los proveedores de herramientas de la base de datos.
		 * @param st (Statement para la conexion)
		 * @return rs (Objeto de ResultSet)
		 */
		public static ResultSet proveedoresHerramientasSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_PROVEEDORES_HERRAMIENTAS;
				rs = st.executeQuery(sentSQL);		
				
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}

	// Tabla USUARIOS:
	/**	
	 * Metodo apra obtener los usuarios de la base de datos.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del usuario que se desee obtener)
	 * @return user (Objeto de tipo Usuario)
	 */
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
		/**
		 * Metodo para obtener clientes de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param nickname (Nickname del cliente que se desee obtener)
		 * @return client (Objeto de tipo Cliente)
		 */
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
		/**
		 * Metodo para obtener los proveedores de la base de datos.
		 * @param st (Statement para la conexion)
		 * @return rs (Objeto de tipo ResultSet)
		 */
		public static ResultSet proveedoresSelect(Statement st) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				sentSQL = "select * from " + TABLA_PROVEEDORES;
				rs = st.executeQuery(sentSQL);		
				
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}	
	
	/**
	 * Metodo para obtener las piezas con los proveedores de la base de datos.	
	 * @param st (Statement para la conexion)
	 * @return rs (Objeto de tipo ResultSet)
	 */
	public static ResultSet piezasProveedoresSelect(Statement st) {
		String sentSQL = "";
		ResultSet rs = null;
		try {
			sentSQL = "select * from " + TABLA_PIEZAS_PROVEEDORES;
			rs = st.executeQuery(sentSQL);		
			
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Metodo obtener las solicitudes de compra segun el tipo de la base de datos.
	 * @param st (Statement para la conexion)
	 * @param tipo (Tipo de solicitud que se desee obtener)
	 * @return sc (Objeto de tipo SilicitudCompra)
	 */
	public static SolicitudCompra solicitudSelect(Statement st, String tipo) {
		String sentSQL = "";
		SolicitudCompra sc = null;
		try {
			sentSQL = "select * from " + TABLA_SOLICITUD_COMPRA + " where tipo= '" + tipo + "' ";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs.next()) {	
				
				String cod = rs.getString("codigo");
				String tipoSol = rs.getString("tipo");
				String nombre = rs.getString("nombre");
				int unidades = rs.getInt("unidades");
			
				sc = new SolicitudCompra(cod,tipoSol, nombre, unidades);
			}
			
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return sc;
	}
	
	/**
	 * Metodo para obtener todas las solicitudes de compra que se posee en la base de datos.
	 * @param st (Statement para la conexion)
	 * @return rs (Objeto de tipo ResultSet)
	 */
	public static ResultSet solicitudTodasSelect(Statement st) {
		String sentSQL = "";
		ResultSet rs = null;
		try {
			sentSQL = "select * from " + TABLA_SOLICITUD_COMPRA;
			rs = st.executeQuery(sentSQL);		
			
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return rs;
	}	
	
	/**
	 * Metodo para obtener los proveedores segun el codigo de la base de datos.
	 * @param st (Statement para la conexion)
	 * @param cod (Codigo del proveedor que se desee obtener)
	 * @return pr (Objeto de tipo Proveedor)
	 */
	public static Proveedor proveedorSelect(Statement st, String cod) {
		String sentSQL = "";
		Proveedor pr = null;
		try {
			sentSQL = "select * from " + TABLA_PROVEEDORES + " where idProveedor= '" + cod + "' ";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs.next()) {	
				String codigo = rs.getString("idProveedor");
				String pais = rs.getString("pais");
				String tipoPieza = rs.getString("tipo");
				String nombre = rs.getString("nombre");
				pr = new Proveedor(codigo, nombre, pais, tipoPieza);
			}
			
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return pr;
	}
	
		//Todas:
		/**
		 * Metodo para obtener todos los empleados que se dispone en la base de datos
		 * @param st (Statement para la conexion)
		 * @return rs (Objeto de tipo ResultSet)
		 */
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
		/**
		 * Metodo para obtener empleados segun el Nickname en la base de datos.
		 * @param st (Statement para la conexion)
		 * @param nickname (Nickname del empleado que se quiere obtener)
		 * @return empleado (Objeto de tipo Empleado)
		 */
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
				/**
				 * Metodo para obtener mecanicos segun el Nickname en la base de datos.
				 * @param st (Statement para la conexion)
				 * @param nickname (Nickaname del mecanico)
				 * @return mecanico (Objeto de tipo Mecanico)
				 */ 
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
		/**
		 * Metodo para obtener comerciales segun el Nickname en la base de datos.
		 * @param st (Statement para la conexion)
		 * @param nickname (Nickname del comercial)
		 * @return comercial (Objeto de tipo Mecanico)
		 */
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
		
		//Todas:
	/**
	 * Metodo para obtener todos los comerciales que se disponen en base de datos.
	 * @param st (Statement para la conexion)
	 * @return rs (Objeto de tipo ResultSet)
	 */
	public static ResultSet comercialesTodasSelect(Statement st) {
		String sentSQL = "";
		ResultSet rs = null;
		try {
			sentSQL = "select * from " + TABLA_COMERCIAL;
			rs = st.executeQuery(sentSQL);
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Metodo para obtener topos los mecanicos que se disponen en la base de datos.
	 * @param st (Statement para la conexion)
	 * @return rs (Objeto de tipo ResultSet)
	 */
	public static ResultSet mecanicosTodasSelect(Statement st) {
		String sentSQL = "";
		ResultSet rs = null;
		try {
			sentSQL = "select * from " + TABLA_MECANICO;
			rs = st.executeQuery(sentSQL);
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return rs;
	}
		
	//Tabla DEPARTAMENTO_COMPRAS:
	/**
	 * Metodo para obtener usuarios de tipo departamento de compra segune el Nickname de la base de datos.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del usuario de tipo departamento de compras)
	 * @return depar (Objeto de tipo DepartamentoCompras)
	 */
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
	
	/**
	 * Metodo para obtener todas las herramientas que se disponen en la base de datos.
	 * @param st (Statement para la conexion)
	 * @return rs (Objeto de tipo ResultSet)
	 */
	public static ResultSet herramientasTallerTodasSelect(Statement st) {
		String sentSQL = "";
		ResultSet rs = null;
		try {
			sentSQL = "select * from " + TABLA_HERRAMIENTAS_TALLER + " order by codigo";
			rs = st.executeQuery(sentSQL);
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return rs;
	}
	//Tabla PIEZAS:
		//Todas:
		/**
		 * Metodo para obtener todas las piezas que se disponen en la base de datos.
		 * @param st (Statement para la conexion)
		 * @return rs (Objeto de tipo ResultSet)
		 */
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
		
		/**
		 * Metodo para obtener las herramientas segun el filtro de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param tipo (Tipo numerico para saber si se trata un filtro de un codigo, un nombre o el stock)
		 * @param restriccion (Restriccion a filtrar)
		 * @return rs (Objeto de tipo ResultSet)
		 */
		public static ResultSet herramientasMecanicoFiltroSelect(Statement st, int tipo, String restriccion) {
			String sentSQL = "";
			ResultSet rs = null;
			try {
				switch (tipo) {
				case 0:
					sentSQL = "select * from " + TABLA_HERRAMIENTAS_TALLER + " where codigo= '" + restriccion + "'";
					break;
				case 1:
					sentSQL = "select * from " + TABLA_HERRAMIENTAS_TALLER + " where nombre= '" + restriccion + "'";
					break;
				case 2: 
					sentSQL = "select * from " + TABLA_HERRAMIENTAS_TALLER + " where stock= " + restriccion + "";
					break;
				}
				rs = st.executeQuery(sentSQL);
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return rs;
		}
		
		/**
		 * Metodo para obtener las piezas segun el filtro de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param tipo (Tipo numerico para saber si se trata de un filtro de tipo codigo, de nombre o stock)
		 * @param restriccion (Restriccion a filtrar)
		 * @return rs (Objeto de tipo ResueltSet)
		 */
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
		
		/**
		 * Metodo para obtener herramientas segun el codigo de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param codigo (Codigo de la herramienta que se desee objeter)
		 * @return herramienta (Objeto de tipo Herramienta)
		 */
		public static HerramientasTaller herramientaTallerSelect(Statement st, String codigo) {
			String sentSQL = "";
			HerramientasTaller herramienta = null;
			try {
				sentSQL = "select * from " + TABLA_HERRAMIENTAS_TALLER + " where codigo= '" + codigo + "' ";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String cod = rs.getString("codigo");
					String nombre = rs.getString("nombre");
					int unidades = rs.getInt("stock");
					String ubicacion = rs.getString("ubicacion");
					herramienta = new HerramientasTaller(cod, nombre, unidades, ubicacion);
				}
			} catch (Exception e) {
				lastError = e;
				e.printStackTrace();
			}
			return herramienta;
		}
		
		//Busqueda mediante CODIGO:
		/**
		 * Metodo para obtener las piezas segun el codigo de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param codigo (Codico de la pieza que se desee obtener)
		 * @return pieza (Objeto de tipo pieza)
		 */
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
			/**
			 * Metodo para obtener todas las piezas utilizadas de la base de datos.
			 * @param st (Statement para la conexion)
			 * @return rs (Objeto de tipo ResultSet)
			 */
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
			
			/**
			 * Metodo para obtener las piezas utilizadas segun el filtro de la base de datos.
			 * @param st (Statement para la conexion)
			 * @param tipo (Tipo numerico para sabar se si trata de un filtro de tipo de mas unidades que unidades, menos unidades que o de codigo)
			 * @param restriccion (Restriccion a filtrar)
			 * @return rs (Objeto de tipo ResultSet)
			 */
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
			/**
			 * Metodo para obtener las piezas utilizadas segun el codigo de la base de datos.
			 * @param st (Statement para la conexion)
			 * @param codigo (Codigo de la piezas utilizada que se desee obtener)
			 * @return pieza (Objeto de tipo Pieza)
			 */
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
	/**
	 * Metodo para obtener los coches del concesionario segun el modelo de la base de datos.
	 * @param st (Statement para la conexion)
	 * @param modelo (Modelo del coche que se quere obtener)
	 * @return coche (Objeto de tipo CocheConcesionario)
	 */
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
	/**
	 * Metodo para obterner todos los coches del concesionario de la base de datos.
	 * @param st (Statement para la conexion)
	 * @return rs (Objeto de tipo ResultSet)
	 */
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
		
		/**
		 * Metodo para obtener los coches del concesionario segun el filtro de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param restriccion (Resticcion a filtrar)
		 * @param tipo (Tipo numerido para saber si se trata de un filtro de tipo marca, de color, de CV o de precio menor que)
		 * @return rs (Objeto de tipo ResultSet)
		 */
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
		/**
		 * Metodo para obtener los coches del concesionario segun la marca de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param marca (Marca del coche del concesionario que se desee obtener)
		 * @return rs (Objeto de tipo ResultSet)
		 */
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
		/**
		 * Metodo para obtener los coches del concesionario segun el modelo de la base de datos.
		 * @param st (Statement para la conexion)
		 * @param modelo (Modelo del coche del concesionario que se desee obtener)
		 * @return rs (Objeto de tipo ResultSet)
		 */
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
		/**
		 * Metodo para obtener todos los coches matriculados que se dipone en la base de datos.
		 * @param st (Statement para la conexion)
		 * @return rs (Objeto de tipo ResultSet)
		 */
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
		
		/**	  
 		 * Metodo para seleccionar un Coche del Concesionario mediante la Matricula.
 		 * @param st (Statement para la conexion)
 		 * @param tipo (Tipo de Filtrado)
 		 * @param restriccion (Restriccion para el Filtrado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
		/**	  
 		 * Metodo para seleccionar los Coches del Taller.
 		 * @param st (Statement para la conexion)
 		 * @return rs (Objeto ResultSet)
 		 */
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
		/**	  
 		 * Metodo para seleccionar los Coches del Taller mediante el Mecanico.
 		 * @param st (Statement para la conexion)
 		 * @param mecanico (Nickname del Mecanico)
 		 * @return rs (Objeto ResultSet)
 		 */
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
		
		/**	  
 		 * Metodo para seleccionar los Coche del Taller mediante un filtrado.
 		 * @param st (Statement para la conexion)
 		 * @param tipo (Tipo de Filtrado)
 		 * @param restriccion (Restriccion para el Filtrado)
 		 * @return cocheTaller (Objeto CocheTaller)
 		 */
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
	
		/**	  
 		 * Metodo para seleccionar un Coche del Taller mediante la Matricula.
 		 * @param st (Statement para la conexion)
 		 * @param matricula (Matricula del Vehiculo)
 		 * @return cocheTaller (Objeto CocheTaller)
 		 */
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
	/**
	 * Metodo para seleccionar una Venta
	 * @param st (Statement para la conexion)
	 * @param matricula (Matricula del Vehiculo)
	 * @return venta (Objeto Venta)
	 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Ventas.
 		 * @param st (Statement para la conexion)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Ventas filtradas por la Marca.
 		 * @param st (Statement para la conexion)
 		 * @param marca (Marca del Vehiculo)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Ventas filtradas por el Modelo del Vehiculo.
 		 * @param st (Statement para la conexion)
 		 * @param modelo (Modelo del Vehiculo)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Ventas de un Comercial.
 		 * @param st (Statement para la conexion)
 		 * @param comercial (Nombre del Comercial Seleccionado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		/**	  
 		 * Metodo para seleccionar un Presupuesto filtrado por el DNI del Cliente.
 		 * @param st (Statement para la conexion)
 		 * @param dni (DNI del Cliente)
 		 * @return presupuesto (Objeto Presupuesto)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar un Presupuesto filtrado por el Codigo.
 		 * @param st (Statement para la conexion)
 		 * @param cod (Codigo Identificativo Seleccionado)
 		 * @return presupuesto (Objeto Presupuesto)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar los Presupuesto .
 		 * @param st (Statement para la conexion)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar los Presupuesto filtrado por el Codigo.
 		 * @param st (Statement para la conexion)
 		 * @param codigo (Codigo Identificativo Seleccionado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar los Presupuesto filtrado por el Cliente.
 		 * @param st (Statement para la conexion)
 		 * @param cliente (Cliente Seleccionado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar los Presupuesto filtrado por el Problema.
 		 * @param st (Statement para la conexion)
 		 * @param problema (Problema Seleccionado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		/**	  
 		 * Metodo para seleccionar las Tarifas filtrado por un Maximo de Precio.
 		 * @param st (Statement para la conexion)
 		 * @param precio (Precio Seleccionado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Tarifas filtrado por un Minimo de Precio.
 		 * @param st (Statement para la conexion)
 		 * @param precio (Precio Seleccionado)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Tarifas filtrado por un Maximo de Horas.
 		 * @param st (Statement para la conexion)
 		 * @param horas (Horas Seleccionadas)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar las Tarifas filtrado por un Minimo de Horas.
 		 * @param st (Statement para la conexion)
 		 * @param horas (Horas Seleccionadas)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar una Tarifa filtrando por el Codigo.
 		 * @param st (Statement para la conexion)
 		 * @param id_tarifa (Codigo Identificativo de la Tarifa)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 		/**	  
 		 * Metodo para seleccionar todas las Tarifas.
 		 * @param st (Statement para la conexion)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		/**	  
 		 * Metodo para seleccionar la fidelidad de los clientes.
 		 * @param st (Statement para la conexion)
 		 * @return rs (Objeto ResultSet)
 		 */
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
 		
 	/**	  
 	 * Metodo para seleccionar una Cita del Concesionario.
 	 * @param st (Statement para la conexion)
 	 * @param comercial (Nickname del Comercial)
 	 * @param fecha (Fecha Seleccionada)
 	 * @param hora (Hora Seleccionada)
 	 * @return rs (Objeto ResultSet)
 	 */
 	public static CitaComercial citaComercialSelect(Statement st, String fecha, String hora, String comercial) {
 		String sentSQL = "";
 		CitaComercial citaComercial = null;
 		try {
 			sentSQL = "select * from " + TABLA_CITAS_COMERCIAL + " where fecha= '" + fecha + "' and hora= '" + hora + "' and comercial= '" + comercial + "'";
 			ResultSet rs = st.executeQuery(sentSQL);
 			if (rs.next()) {
 				String nombre = rs.getString("nombre");
 				String dniCliente = rs.getString("dniCliente");
 				String f	= rs.getString("fecha");
 				String h = rs.getString("hora");
 				String c = rs.getString("comercial");
 				citaComercial = new CitaComercial(nombre, dniCliente, f, h, c);
			}
 			st.close();
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
 		return citaComercial;
	}
 	
 	/**	  
	 * Metodo para seleccionar una Cita del Taller.
	 * @param st (Statement para la conexion)
	 * @param mecanico (Nickname del Mecanico)
	 * @param fecha (Fecha Seleccionada)
	 * @param hora (Hora Seleccionada)
	 * @return rs (Objeto ResultSet)
	 */
 	public static CitaTaller citaTallerSelect(Statement st, String fecha, String hora, String mecanico) {
 		String sentSQL = "";
 		CitaTaller citaTaller = null;
 		try {
 			sentSQL = "select * from " + TABLA_CITAS_TALLER + " where fecha= '" + fecha + "' and hora= '" + hora + "' and mecanico= '" + mecanico + "'";
 			ResultSet rs = st.executeQuery(sentSQL);
 			if (rs.next()) {
 				String nombre = rs.getString("nombre");
 				String dniCliente = rs.getString("dniCliente");
 				String f	= rs.getString("fecha");
 				String h = rs.getString("hora");
 				String m = rs.getString("mecanico");
 				String problema = rs.getString("problema");
 				citaTaller = new CitaTaller(nombre, dniCliente, f, h, m, problema);
			}
 			st.close();
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
 		return citaTaller;
	}
 	
 	/**	  
	 * Metodo para seleccionar las Citas de un Comercial.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Comercial)
	 * @return rs (Objeto ResultSet)
	 */
 	public static ResultSet citasDelComercialSelect(Statement st, String nickname) {
 		String sentSQL2 = "";
 		String sentSQL = "";
 		ResultSet rsNombre = null;
 		ResultSet rsFinal = null;
 		String nombre = "";
 		
 		try {
			sentSQL2 = "select nombre from " + TABLA_COMERCIAL + " where nickname= '" + nickname + "'";
			rsNombre = st.executeQuery(sentSQL2);
			if (rsNombre.next()) {
				nombre = rsNombre.getString("nombre");
			}
			
		} catch(Exception e) {
 			lastError = e;
 			e.printStackTrace();		
 		}
 		try {
 			sentSQL = "select * from " + TABLA_CITAS_COMERCIAL + " where comercial= '" + nombre + "'";
 			rsFinal = st.executeQuery(sentSQL);
 		} catch(Exception e) {
 			lastError = e;
 			e.printStackTrace();
 		}
 		return rsFinal;
 	}
 	
 	/**	  
	 * Metodo para seleccionar las Citas de un Comercial por Fecha.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Comercial)
	 * @param fecha (Fecha Seleccionada)
	 * @return rs (Objeto ResultSet)
	 */
 	public static ResultSet filtrarCitasPorFecha(Statement st, String nickname, String fecha) {
 		String sentSQL = "";
 		String sentSQL2 = "";
 		ResultSet rsNombre = null;
 		ResultSet rsFinal = null;
 		String nombre = "";
 		try {
			sentSQL2 = "select nombre from " + TABLA_COMERCIAL + " where nickname= '" + nickname + "'";
			rsNombre = st.executeQuery(sentSQL2);
			if (rsNombre.next()) {
				nombre = rsNombre.getString("nombre");
			}
			
		} catch(Exception e) {
 			lastError = e;
 			e.printStackTrace();		
 		} 		
 		try { 
			sentSQL = "select * from " + TABLA_CITAS_COMERCIAL + " where comercial= '" + nombre + "' and fecha= '" + fecha + "'";
			rsFinal = st.executeQuery(sentSQL);
		} catch (Exception e) {
			lastError = e;
 			e.printStackTrace();		
		}
 		
 		return rsFinal;
 	}
 	
 	/**	  
	 * Metodo para seleccionar las Citas de un Mecanico.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Mecanico)
	 * @return rs (Objeto ResultSet)
	 */
 	public static ResultSet citasDelMecanicoSelect(Statement st, String nickname) {
 		String sentSQL2 = "";
 		String sentSQL = "";
 		ResultSet rsNombre = null;
 		ResultSet rsFinal = null;
 		String nombre = "";
 		
 		try {
			sentSQL2 = "select nombre from " + TABLA_MECANICO + " where nickname= '" + nickname + "'";
			rsNombre = st.executeQuery(sentSQL2);
			if (rsNombre.next()) {
				nombre = rsNombre.getString("nombre");
			}
			
		} catch(Exception e) {
 			lastError = e;
 			e.printStackTrace();		
 		}
 		try {
 			sentSQL = "select * from " + TABLA_CITAS_TALLER + " where mecanico= '" + nombre + "'";
 			rsFinal = st.executeQuery(sentSQL);
 		} catch(Exception e) {
 			lastError = e;
 			e.printStackTrace();
 		}
 		return rsFinal;
 	}
 	
 	/**	  
	 * Metodo para seleccionar las Citas de un Mecanico por Fecha.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Mecanico)
	 * @param fecha (Fecha Seleccionada)
	 * @return rs (Objeto ResultSet)
	 */
 	public static ResultSet filtrarCitasMecanicoPorFecha(Statement st, String nickname, String fecha) {
 		String sentSQL = "";
 		String sentSQL2 = "";
 		ResultSet rsNombre = null;
 		ResultSet rsFinal = null;
 		String nombre = "";
 		try {
			sentSQL2 = "select nombre from " + TABLA_MECANICO + " where nickname= '" + nickname + "'";
			rsNombre = st.executeQuery(sentSQL2);
			if (rsNombre.next()) {
				nombre = rsNombre.getString("nombre");
			}
			
		} catch(Exception e) {
 			lastError = e;
 			e.printStackTrace();		
 		} 		
 		try { 
			sentSQL = "select * from " + TABLA_CITAS_TALLER + " where mecanico= '" + nombre + "' and fecha= '" + fecha + "'";
			rsFinal = st.executeQuery(sentSQL);
		} catch (Exception e) {
			lastError = e;
 			e.printStackTrace();		
		}
 		
 		return rsFinal;
 	}
 	
 	/**	  
	 * Metodo para seleccionar las horas de un Empleado mediante un filtro.
	 * @param st (Statement para la conexion)
	 * @param tipo (Tipo de Filtrado)
	 * @return rs (Objeto ResultSet)
	 */
 	public static ResultSet empleadosHorasFiltroSelect(Statement st, int tipo) {
		String sentSQL = "";
		ResultSet rs = null;
		try {
			switch (tipo) {
			case 0:
				sentSQL = "select * from " + TABLA_COMERCIAL;
				break;
			case 1:
				sentSQL = "select * from " + TABLA_MECANICO;
				break;
			}
			
			rs = st.executeQuery(sentSQL);
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
		return rs;
	}
 	
 	/**	  
	 * Metodo para seleccionar las Horas Temporales de un Empleado.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Empleado)
	 * @return horasEmpleados (Objeto HorasEmpleados)
	 */
 	public static HorasEmpleados horaEmpleadoTemporalSelect(Statement st, String nickname) {
			String sentSQL = "";
			HorasEmpleados horasEmpleados = null;
			try {
				sentSQL = "select * from " + TABLA_HORAS_EMPLEADO_TEMPORAL + " where nickname= '" + nickname + "' ";
				ResultSet rs = st.executeQuery(sentSQL);
				if (rs.next()) {
					String nick = rs.getString("nickname");
					int horas	= rs.getInt("horas");
					int minutos = rs.getInt("minutos");
					horasEmpleados = new HorasEmpleados(horas, minutos, nick);
			}
				st.close();
		} catch (Exception e) {
			lastError = e;
			e.printStackTrace();
		}
			return horasEmpleados;
	}
 	
 	/**	  
	 * Metodo para seleccionar las horas de un Empleado.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Empleado)
	 * @return horasEmpleados (Objeto HorasEmpleados)
	 */
 	public static HorasEmpleados horaEmpleadoSelect(Statement st, String nickname) {
		String sentSQL = "";
		HorasEmpleados horasEmpleados = null;
		try {
			sentSQL = "select * from " + TABLA_HORAS_EMPLEADO + " where nickname= '" + nickname + "' ";
			ResultSet rs = st.executeQuery(sentSQL);
			if (rs.next()) {
				String nick = rs.getString("nickname");
				int horas	= rs.getInt("horas");
				int minutos = rs.getInt("minutos");
				horasEmpleados = new HorasEmpleados(horas, minutos, nick);
		}
			st.close();
	} catch (Exception e) {
		lastError = e;
		e.printStackTrace();
	}
		return horasEmpleados;
}
 	

//METODOS DELETE:

	// Tabla USUARIOS:
 	/**	  
	 * Metodo para eliminar un Usuario.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Usuario)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
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
	/**	  
	 * Metodo para eliminar un Cliente.
	 * @param st (Statement para la conexion)
	 * @param dni (Nickname del Cliente)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
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
	
	/**	  
	 * Metodo para eliminar ls Horas Temporales de un Empleado.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Comercial)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	public static boolean horasEmpleadoDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_HORAS_EMPLEADO + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	/**	  
	 * Metodo para eliminar las Horas de un Empleado.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Empleado)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	public static boolean horasEmpleadoTemporarlDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_HORAS_EMPLEADO_TEMPORAL + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla EMPLEADO:
	/**	  
	 * Metodo para eliminar un Empleado.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Empleado)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
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
	/**	  
	 * Metodo para eliminar un Mecanico.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Mecanico)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	public static boolean mecanicosDelete(Statement st, String nickname) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_MECANICO + " where nickname= '" + secu(nickname) + "'";
			int val = st.executeUpdate(sentSQL);
			st.close();
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}
	
	//Tabla COMERCIAL:
	/**	  
	 * Metodo para eliminar un Comercial.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Comercial)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
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
	/**	  
	 * Metodo para eliminar un Empleado del Departamento de Compras.
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Empleado del Departamento de Compras)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
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
	/**	  
	 * Metodo para eliminar una Pieza
	 * @param st (Statement para la conexion)
	 * @param codigo (Codigo Identificativo de la Pieza)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
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
		/**	  
		 * Metodo para eliminar una Pieza Utilizada
		 * @param st (Statement para la conexion)
		 * @param codigo (Codigo Identificativo de la Pieza Utilizada)
		 * @return boolean (Respuesta afirvativa si todo esta bien)
		 */
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
		/**	  
		 * Metodo para eliminar un Coche Concesionario
		 * @param st (Statement para la conexion)
		 * @param modelo (Modelo del CocheConcesionario)
		 * @return boolean (Respuesta afirvativa si todo esta bien)
		 */
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
	/**	  
	 * Metodo para eliminar un Coche del Taller
	 * @param st (Statement para la conexion)
	 * @param matricula (Matricula del Coche del Taller)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
		public static boolean cocheTallerDelete(Statement st, String matricula) {
			String sentSQL = "";
			try {
				sentSQL = "delete from " + TABLA_TALLER + " where matriculaCoche= '" + secu(matricula) + "'";
				int val = st.executeUpdate(sentSQL);
				return (val == 1);
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}	
		}
	
	//Tabla VENTAS:
	/**	  
	 * Metodo para eliminar una Venta
	 * @param st (Statement para la conexion)
	 * @param matricula (Matricula del Vehiculo de la Venta)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
		public static boolean ventasDelete(Statement st, String matricula) {
			String sentSQL = "";
			try {
				sentSQL = "delete from " + TABLA_VENTAS + " where matricula= '" + secu(matricula) + "'";
				int val = st.executeUpdate(sentSQL);
				return (val == 1);
			} catch (SQLException e) {
				lastError = e;
				e.printStackTrace();
				return false;
			}	
		}
	
	/**
	 * Metodo para eliminar una Compra
	 * @param st (Statement para la conexion)
	 * @param cod (Codigo Identificativo de la Compra)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	public static boolean solicitudCompraDelete(Statement st, String cod) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_SOLICITUD_COMPRA + " where codigo= '" + secu(cod) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 * Metodo para eliminar una Tarifa
	 * @param st (Statement para la conexion)
	 * @param idTarifa (Codigo Identificativo de la Tarifa)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	//TABLA TARIFA:
	public static boolean tarifasDelete(Statement st, String idTarifa) {
		String sentSQL = "";
		try {
			sentSQL = "delete from " + TABLA_TARIFAS + " where idTarifa= '" + secu(idTarifa) + "'";
			int val = st.executeUpdate(sentSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}	
	}
	
	//TABLA CITA COMERCIAL
	/**
	 * Metodo para eliminar las Citas antiguas de un Comercial
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Comercial)
	 * @param fecha (Fecha limite)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	public static boolean citasComercialDelete(Statement st, String nickname, String fecha) {
		String setSQL = "";
		String preSentSQL = "";
		String nombre1 = "";
		ResultSet nombre = null;
			
		try {
			preSentSQL = "select nombre from " + TABLA_COMERCIAL + " where nickname= '" + nickname + "'";
			nombre = st.executeQuery(preSentSQL);
			if (nombre.next()) {
				nombre1 = nombre.getString("nombre");
			}
				
		} catch(Exception e) {
	 		lastError = e;
	 		e.printStackTrace();		
	 	}
			
		try {
			setSQL = "delete from " + TABLA_CITAS_COMERCIAL + " where comercial= '" + secu(nombre1) + "' and fecha= '" + secu(fecha) + "'";
			int val =  st.executeUpdate(setSQL);
			return (val == 1);
		} catch (SQLException e) {
			lastError = e;
			e.printStackTrace();
			return false;
		}
	}

	//TABLA CITA COMERCIAL
	/**
	 * Metodo para eliminar las Citas antiguas de un Mecanico
	 * @param st (Statement para la conexion)
	 * @param nickname (Nickname del Mecanico)
	 * @param fecha (Fecha limite)
	 * @return boolean (Respuesta afirvativa si todo esta bien)
	 */
	public static boolean citasMecanicoDelete(Statement st, String nickname, String fecha) {
		String setSQL = "";
		String preSentSQL = "";
		String nombre1 = "";
		ResultSet nombre = null;
			
		try {
			preSentSQL = "select nombre from " + TABLA_MECANICO + " where nickname= '" + nickname + "'";
			nombre = st.executeQuery(preSentSQL);
			if (nombre.next()) {
				nombre1 = nombre.getString("nombre");
			}
				
		} catch(Exception e) {
	 		lastError = e;
	 		e.printStackTrace();		
	 	}
			
		try {
			setSQL = "delete from " + TABLA_CITAS_TALLER + " where mecanico= '" + secu(nombre1) + "' and fecha= '" + secu(fecha) + "'";
			int val =  st.executeUpdate(setSQL);
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
	/**
	 * Metodo para devolver el string "securizado" para volcarlo en SQL.
	 * @param string (Objeto String)
	 * @return string (Objeto String "securizado")
	 */
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
