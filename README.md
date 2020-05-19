[![Build Status](https://travis-ci.org/javimartin22/grupo07spq.svg?branch=master)](https://travis-ci.org/javimartin22/grupo07spq)

# Concesionario-Taller



Proyecto cliente-servidor que simula la gestión de un concesionario e implementa funcionalidad tanto para los clientes como para los distintos tipos de empleados.

## Funcionalidad del Proyecto 🚀

* Gerente: Realiza la gestión de usuarios 
* Departamento Compras: Registro de piezas
* Mecánico: Registro de coches en el taller
* Comercial: Registro de coches en el concesionario y getionar las ventas
* Cliente: Iniciar sesion

Mira **Ejecución** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

Será necesario descargar Maven para poder ejecutar el proyecto.
```
* [Maven](https://maven.apache.org/) - Manejador de dependencias
```
Esta aplicació utiliza una Base de Datos SQLite la cual no es necesario crear ni configurarla, ya que en caso de que esta no exista la propia aplicación la desarrolla. 

### Ejecución 🔧

_A continuación se detallan los pasos necesarios para poder ejecutar el proyecto_

_En caso de que se realice por primera vez la ejecucion del proyecto, se debe realizar los siguientes pasos:_

```
Hay que acceder desde la linea de comandos hasta el fichero raiz del proyecto (concesionario) en git.
Ejecutar el siguiente comando:
-mvn install
```


_Poner en marcha el servidor_

```
Hay que acceder desde la linea de comandos hasta el fichero raiz del proyecto (concesionario) en git.
Ejecutar el siguiente comando:
-mvn exec:java
```

_Ejecutar el cliente_

```
Hay que acceder desde la linea de comandos hasta el fichero raiz del proyecto (concesionario) en git.
Ejecutar el siguiente comando, que hace referencia al profiles del Cliente en el pom:
-mvn exec:java -Pclient
```

_A continuacion se detallan los ejemplos para las diferentes variantes de iniciar sesión como distintos usuarios que se 
encuentran en nuestra BD._

_Entrar como gerente_

```
Iniciar sesión con los siguientes datos:
-Nickname: admin
-Password: admin

```

_Entrar como mecanico_

```
Iniciar sesión con los siguientes datos:
-Nickname: Javiertxu
-Password: 1234

```

_Entrar como comercial_

```
Iniciar sesión con los siguientes datos:
-Nickname: Jorgico
-Password: 1234

```

_Entrar como Departamento de Compras_

```
Iniciar sesión con los siguientes datos:
-Nickname: Arturito
-Password: 1234

```

_Entrar como Cliente_

```
Iniciar sesión con los siguientes datos:ado en el registro. Cliente por defecto:
-Nickname: Pablito
-Password: 1234

En caso de que el usuario no se encuentre registrado en la Base de Datos se dispondra de la posibilidad de registrarse.
```

## Construido con 🛠️

* [Jersey](http://www.dropwizard.io/1.0.2/docs/) - Framwework de aplicacion REST-Ful Web Services.
* [Maven](https://maven.apache.org/) - Manejador de dependencias.
* [SQLite](https://www.sqlite.org/index.html) - Sistema de gestión de Base de Datos.
* [PDFBox](https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox/2.0.1) - Creación de Documentos PDF.
* [PDFBox Tutorial](https://www.tutorialspoint.com/pdfbox/index.htm) - Tutorial de PDFBox.
* [JFreeChart](https://mvnrepository.com/artifact/org.jfree/jfreechart/1.0.18) - Generación de Graficos en Java.
* [JCalendar](https://mvnrepository.com/artifact/com.toedter/jcalendar/1.4) - Seleccionador de Fechas gráficamente.
* [JFreeChart Tutorial](https://www.tutorialspoint.com/jfreechart/index.htm) - Tutorial de JFreeCbart.
* [Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core/2.18.0) - Mockito for Test Unitarios.
* [PowerMock](https://mvnrepository.com/artifact/org.powermock/powermock-module-junit4/2.0.2) - PowerMock para testear métodos. estáticos.
* [Contiperf](https://mvnrepository.com/artifact/org.databene/contiperf/2.3.4) - Contiperf para Test Rendimiento.
* [Jacoco](https://mvnrepository.com/artifact/org.jacoco/jacoco-maven-plugin/0.8.3) - Jacoco para Generar informe de cobertura.
* [Logger](https://mvnrepository.com/artifact/log4j/log4j/1.2.17) - Logger para realizar documentos Logs.
* [JavaDoc](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-javadoc-plugin/3.2.0) - Plugin para la generación de Javadocs.


## Autores ✒️

**BSPQ20-S11:**
* **Pablo Gaviria Lengaran** 
* **Javier Martin Aizpuru** 
* **Mikel Romero Bermejo**
* **Yeray Bellanco Casares**

También puedes mirar la lista de todos los [contribuyentes](https://github.com/javimartin22/grupo07spq/contributors) que han participado en este proyecto. 

