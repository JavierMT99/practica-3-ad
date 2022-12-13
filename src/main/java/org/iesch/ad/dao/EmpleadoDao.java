package org.iesch.ad.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.iesch.ad.models.Empleado;

import java.util.List;

public class EmpleadoDao {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    public EmpleadoDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("personal");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public List<Empleado> buscaTodosLosEmpleadosConIdDep(Empleado emp) {
        Query queryEmpleado = entityManager.createQuery("SELECT e FROM Empleado e WHERE id_dep = :id").setParameter("id", emp.getId_dep());
        return queryEmpleado.getResultList();
    }

    public void buscaEmpleadoYSuDepartamento(int id) {
        Empleado empleado = entityManager.find(Empleado.class, id);
        System.out.println(empleado.toStringEmpleadoConDepartamento());
    }

    public void insert(Empleado empleado) {
        try {
            transaction.begin();
            entityManager.persist(empleado);
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

    public void delete(Empleado empleado) {
        try {
            transaction.begin();
            Empleado delete = entityManager.find(Empleado.class, empleado.getId_emp());
            entityManager.remove(delete);
            transaction.commit();
            System.out.println("\tSe ha borrado el empleado con id_emp: " + empleado.getId_emp());
        } catch (Exception e) {
            System.out.println("\tNo se ha podido borrar el empleado con id_emp: " + empleado.getId_emp());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public List<Empleado> obtenerListadoEmpleadosSalarioMayorQue(Float salario) {
        Query queryEmpleado = entityManager.createQuery("SELECT e FROM Empleado e WHERE salario > :salario").setParameter("salario", salario);
        return queryEmpleado.getResultList();
    }

    public List<Empleado> obtenerListadoEmpleadosDadosDeAltaAnio(int year) {
        Query queryEmpleado = entityManager.createQuery("SELECT e FROM Empleado e WHERE YEAR(fecha_alta) = :year").setParameter("year", year);
        return queryEmpleado.getResultList();
    }
}
