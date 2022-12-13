package org.iesch.ad;

import org.iesch.ad.dao.*;
import org.iesch.ad.models.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            int option;

            System.out.println("Introduce una opción del 1 al 6");
            System.out.println("1.- Ejercicio 1" + "\n" + "2.- Ejercicio 2" + "\n" + "3.- Ejercicio 3" + "\n" + "4.- Ejercicio 4" + "\n" + "5.- Ejercicio 5" + "\n" + "6.- Cerrar programa");

            if (!sc.hasNextInt()) {
                System.out.println("Error, Introduce una opción del 1 al 6\n");
                sc.nextLine();
            } else {
                option = sc.nextInt();
                if (option < 1 || option > 6) {
                    System.out.println("Error, Introduce una opción del 1 al 6\n");
                } else {
                    switch (option) {
                        case 1:
                            ejercicio1();
                            break;
                        case 2:
                            ejercicio2();
                            break;
                        case 3:
                            ejercicio3();
                            break;
                        case 4:
                            ejercicio4();
                            break;
                        case 5:
                            ejercicio5();
                            break;
                        case 6:
                            System.out.println("Cerrando programa...");
                            exit = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private static void ejercicio5() {
        // DAOs a utilizar
        ProductoDao productoDao = new ProductoDao();
        ClienteDao clienteDao = new ClienteDao();
        VentaDao ventaDao = new VentaDao();

        System.out.println("Ejercicio 5:");

        // Apartado A)
        System.out.println("\tA) Modificar el precio de todos los productos de forma que incluyan un IVA del 21%");
        productoDao.updateImpuestos(21F);
        System.out.println();
        System.out.println();

        // Apartado B)
        System.out.println("\tB) Eliminar las ventas realizadas por el cliente 20");
        Cliente clienteDelete = Cliente.builder().id(20).build();
        clienteDao.borrarVentasDeCliente(clienteDelete);
        System.out.println();
        System.out.println();
    }

    private static void ejercicio4() {
        // DAOs a utilizar
        ProductoDao productoDao = new ProductoDao();
        ClienteDao clienteDao = new ClienteDao();
        VentaDao ventaDao = new VentaDao();

        System.out.println("Ejercicio 4:");

        // Apartado A)
        System.out.println("\tA) Obtener un listado de los productos que tienen un precio inferior a 100");
        List<Producto> lstA = productoDao.buscarProductosPrecioInferior(100F);
        System.out.println(lstA);
        System.out.println();

        // Apartado B)
        System.out.println("\tB) Obtener un listado de los productos cuyo stock actual sea igual a su stock mínimo");
        List<Producto> lstB = productoDao.buscarProductoStockactualIgualStockminimo();
        System.out.println(lstB);
        System.out.println();

        // Apartado C)
        System.out.println("\tC) Obtener un listado de los clientes que viven en Zaragoza");
        List<Cliente> lstC = clienteDao.buscarClientesCiudad("Zaragoza");
        System.out.println(lstC);
        System.out.println();

        // Apartado D)
        System.out.println("\tD) Obtener un listado de los clientes cuyo nombre empieza por la letra R");
        List<Cliente> lstD = clienteDao.buscarClienteInicial("R");
        System.out.println(lstD);
        System.out.println();

        // Apartado E)
        System.out.println("\tE) Obtener un listado de las ventas realizadas en los últimos 30 días");
        List<Venta> lstE = ventaDao.VentasRealizadasUltimosDias(30);
        for (Venta v : lstE
        ) {
            System.out.println(v.toStringExtra());
        }
        System.out.println();

        // Apartado F)
        System.out.println("\tF) Obtener un listado, ordenado por id de cliente, de las ventas realizadas de un producto determinado");
        List<Venta> lstF = ventaDao.VentasOrdenadasPorIdClienteDeUnProducto(9);
        for (Venta v : lstF
        ) {
            System.out.println(v.toStringExtra());
        }
        System.out.println();
        System.out.println();
    }

    private static void ejercicio3() {
        // DAOs a utilizar
        EmpleadoDao empleadoDao = new EmpleadoDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();

        System.out.println("Ejercicio 3:");

        // Apartado A)
        System.out.println("\tA) Obtener un listado de los empleados que tienen un salario superior a 1000");
        Float salario = 1000.00F;
        List<Empleado> ej3ApartadoA = empleadoDao.obtenerListadoEmpleadosSalarioMayorQue(salario);
        for (Empleado emp : ej3ApartadoA
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();
        System.out.println();

        // Apartado B)
        System.out.println("\tB) Obtener un listado de los empleados que pertenezcan a los departamentos 10, 20, 30,\n" +
                "40, 50, 60 y 70. Utiliza una lista de parámetros en la consulta");

        Empleado id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(10).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado10 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado10
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();

        id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(20).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado20 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado20
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();

        id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(30).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado30 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado30
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();

        id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(40).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado40 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado40
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();

        id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(50).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado50 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado50
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();

        id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(60).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado60 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado60
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();

        id_dep = Empleado.builder().id_dep(Departamento.builder().id_dep(70).build()).build();
        System.out.println("\tDepartamento con id: " + id_dep.getId_dep().getId_dep());
        List<Empleado> lstEmpleado70 = empleadoDao.buscaTodosLosEmpleadosConIdDep(id_dep);
        for (Empleado emp : lstEmpleado70
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();
        System.out.println();

        // Apartado C)
        System.out.println("\tC) Obtener un listado de los empleados nuevos del año 2022");
        int anio = 2022;
        List<Empleado> lstEmpleadoAnio = empleadoDao.obtenerListadoEmpleadosDadosDeAltaAnio(anio);
        for (Empleado emp : lstEmpleadoAnio
        ) {
            System.out.println(emp.toString());
        }
        System.out.println();
        System.out.println();
    }

    private static void ejercicio2() {
        // DAOs a utilizar
        ClienteDao clienteDao = new ClienteDao();
        ProductoDao productoDao = new ProductoDao();
        VentaDao ventaDao = new VentaDao();

        System.out.println("Ejercicio 2:");

        // Apartado A)
        System.out.println("\tA) Insertar un cliente");
        Cliente clienteInsert = Cliente.builder().id(26).nombre("Marquitos").direccion("Renfe").poblacion("Teruel").telef("684394265").nif("18463458A").build();
        clienteDao.insert(clienteInsert);
        System.out.println();
        System.out.println();

        // Apartado B)
        System.out.println("\tB) Insertar un producto");
        Producto productoInsert = Producto.builder().id(26).descripcion("Bolsa de patatas").stockactual(200).stockminimo(15).precio(1.50F).build();
        productoDao.insert(productoInsert);
        System.out.println();
        System.out.println();

        // Apartado C)
        System.out.println("\tC) Insertar una venta");
        int idCliente = 26;
        Cliente ventaInsertIdCliente = Cliente.builder().id(idCliente).build();

        int idProducto = 26;
        Producto ventaInsertIdProducto = Producto.builder().id(idProducto).build();

        Venta ventaInsert = Venta.builder().id_venta(27).fecha(new Date(Date.parse("2022/11/5"))).cantidad(25).id_cliente(ventaInsertIdCliente).id_producto(ventaInsertIdProducto).build();
        ventaDao.insert(ventaInsert);
        System.out.println();
        System.out.println();

        // Apartado D)
        System.out.println("\tD) Leer una venta, y además, su cliente y su producto correspondientes");
        int idVenta = 26;
        ventaDao.buscaVentaYSuClienteYProducto(idVenta);
        System.out.println();
        System.out.println();

        // Apartado E)
        System.out.println("\tE) Eliminar un producto. Previamente, se debe eliminar todas las ventas realizadas de dicho producto. Utiliza una transacción");
        int idProductoDelete = 2;
        Producto productoDelete = Producto.builder().id(idProductoDelete).build();
        productoDao.borrarProductoYSusVentas(productoDelete);
        System.out.println();
        System.out.println();

        // Apartado F)
        System.out.println("\tF) Eliminar un cliente. Previamente, se debe eliminar todas las ventas realizadas por dicho cliente. Utiliza una transacción.");
        int idClienteDelete = 6;
        Cliente clienteDelete = Cliente.builder().id(idClienteDelete).build();
        clienteDao.borrarClienteYSusVentas(clienteDelete);
        System.out.println();
        System.out.println();
    }

    private static void ejercicio1() {
        // DAOs a utilizar
        EmpleadoDao empleadoDao = new EmpleadoDao();
        DepartamentoDao departamentoDao = new DepartamentoDao();

        System.out.println("Ejercicio 1:");

        // Apartado A)
        System.out.println("\tA) Modificar un departamento");
        departamentoDao.updateNombreYLocalidad(1, "Training", "Barcelona");
        System.out.println();
        System.out.println();

        // Apartado B)
        System.out.println("\tB) Insertar un empleado");
        Empleado empleadoInsert = Empleado.builder().id_emp(26).apellido("Perez").oficio("Trainer").fecha_alta(new Date(2022 / 11 / 2)).id_dep(Departamento.builder().id_dep(25).build()).salario(500.0F).comision(0F).build();
        empleadoDao.insert(empleadoInsert);
        System.out.println();
        System.out.println();

        // Apartado C)
        System.out.println("\tC) Leer un empleado, y además, su departamento correspondiente");
        empleadoDao.buscaEmpleadoYSuDepartamento(2);
        System.out.println();
        System.out.println();

        // Apartado D)
        System.out.println("\tD) Eliminar un empleado");
        Empleado empleadoDelete = Empleado.builder().id_emp(1).build();
        empleadoDao.delete(empleadoDelete);
        System.out.println();
        System.out.println();

        // Apartado E)
        System.out.println("\tE) Eliminar un departamento");
        int id_dep = 25;
        Departamento departamentoDelete = Departamento.builder().id_dep(id_dep).build();
        Empleado empdlete = Empleado.builder().id_dep(Departamento.builder().id_dep(id_dep).build()).build();
        List<Empleado> lstEmpleado = empleadoDao.buscaTodosLosEmpleadosConIdDep(empdlete);
        for (Empleado emp : lstEmpleado
        ) {
            empleadoDao.delete(emp);
        }
        departamentoDao.delete(departamentoDelete);
        System.out.println();
        System.out.println();
    }
}
