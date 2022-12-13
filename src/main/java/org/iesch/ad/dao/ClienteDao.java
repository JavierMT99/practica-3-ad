package org.iesch.ad.dao;

import jakarta.persistence.*;
import org.iesch.ad.models.Cliente;
import org.iesch.ad.models.Venta;

import java.util.List;

public class ClienteDao {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    public ClienteDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ventas");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void insert(Cliente cliente) {
        try {
            transaction.begin();
            entityManager.persist(cliente);
            transaction.commit();
            System.out.println("\tSe ha insertado correctamente");
        } catch (Exception e) {
            System.out.println("\tNo se ha insertado correctamente");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void borrarClienteYSusVentas(Cliente cliente) {
        try {
            transaction.begin();
            Cliente delete = entityManager.find(Cliente.class, cliente.getId());
            Query ventasDelete = entityManager.createQuery("select v from Venta v where id_cliente = :borrarCliente")
                    .setParameter("borrarCliente", delete);

            List<Venta> lstVentas = ventasDelete.getResultList();
            for (Venta v :
                    lstVentas) {
                entityManager.remove(v);
            }
            entityManager.remove(delete);
            transaction.commit();
            System.out.println("Se ha borrado el cliente con id: " + cliente.getId() + " y sus ventas");
        } catch (Exception e) {
            System.out.println("No se ha borrado el cliente con id: " + cliente.getId() + " y sus ventas");
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void borrarVentasDeCliente(Cliente cliente) {
        try {
            transaction.begin();
            Cliente delete = entityManager.find(Cliente.class, cliente.getId());
            Query ventasDelete = entityManager.createQuery("select v from Venta v where id_cliente = :borrarCliente")
                    .setParameter("borrarCliente", delete);

            List<Venta> lstVentas = ventasDelete.getResultList();
            for (Venta v :
                    lstVentas) {
                entityManager.remove(v);
            }
            transaction.commit();
            System.out.println("Se han borrado las ventas del cliente con id: " + cliente.getId());
        } catch (Exception e) {
            System.out.println("No se han borrado las ventas del cliente con id: " + cliente.getId());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Cliente> buscarClientesCiudad(String ciudad) {
        Query queryCliente = entityManager.createQuery("select c from Cliente c where c.poblacion = :ciudad").setParameter("ciudad", ciudad);
        return queryCliente.getResultList();
    }

    public List<Cliente> buscarClienteInicial(String inicial) {
        Query queryCliente = entityManager.createQuery("select c from Cliente c where nombre like concat(:nombre,'%')").setParameter("nombre", inicial);
        return queryCliente.getResultList();
    }
}
