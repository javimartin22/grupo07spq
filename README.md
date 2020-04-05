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

### Ejecución 🔧

_A continuación se detallan los pasos necesarios para poder ejecutar el proyecto_

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

_A continuacion se detallan las diferentes variantes de iniciar sesión como distintos usuarios_

_Entrar como gerente_

```
Iniciar sesión con los siguientes datos:
-Nickname: admin
-Password: admin

Una vez iniciado sesión, un gerente puede acceder al registro de nuevos empleados, ver los empleados de la aplicación y gestionarlos.

```

_Entrar como mecanico_

```
El inicio de sesión como mecánico se puede realizar con todos los usuarios de tipo Mecánico que haya creado el gerente. De partida, se dispone de un mecánico ya creado con los siguientes datos:

-Nickname: Jose
-Password: 1234

Una vez iniciado sesión, un mecánico puede acceder a la visualización de las piezas, acceder al registro de vehículos en el taller, ver los vehículos en el taller y el historial de los vehículos.

```

_Entrar como comercial_

```
El inicio de sesión como comercial se puede realizar con todos los usuarios de tipo Comercial que haya creado el gerente. De partida, se dispone de un comercial ya creado con los siguientes datos:

-Nickname: Arturo
-Password: 1234

Una vez iniciado sesión, un comercial puede acceder al registro de nuevos vehículos en el concesionario, visualización de las ventas donde se podrá registrar una nueva venta, ver vehículos del concesionario donde se podrá ver la información detallada de estos y la opción de salir.

```

_Entrar como Departamento de Compras_

```
El inicio de sesión como Departamento de Compras se puede realizar con todos los usuarios de tipo Departamento de Compras que haya creado el gerente. De partida, se dispone de un Departamento de Compras ya creado con los siguientes datos:

-Nickname: Jorge
-Password: 1234

Una vez iniciado sesión, un Departamento Compras puede acceder a la visualización de piezas distinguiendo de las piezas que han sido utilizadas de las piezas que aún se encuentran dispnibles.

```

_Entrar como Cliente_

```
El registro como Cliente se puede realizar rellenando un username y una contraseña deseada, si el cliente no está registrado se visualizará un panel para completar el proceso de registro. Para iniciar sesión, se accede con el username y password ya realizado en el registro. Cliente por defecto:

-Nickname: Pablo
-Password: 1234

Una vez iniciado sesión, un cliente dispone de la posibilidad de cambiar la contraseña y el nickname.

```

## Construido con 🛠️

* [Jersey](http://www.dropwizard.io/1.0.2/docs/) - Framwework de aplicacion REST-Ful Web Services
* [Maven](https://maven.apache.org/) - Manejador de dependencias

## Autores ✒️

* **Pablo Gaviria Lengaran** 
* **Javier Martin Aizpuru** 
* **Mikel Romero Bermejo**
* **Yeray Bellanco Casares**

También puedes mirar la lista de todos los [contribuyentes](https://github.com/javimartin22/grupo07spq/contributors) que han participado en este proyecto. 

