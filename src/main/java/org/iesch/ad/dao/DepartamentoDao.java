package org.iesch.ad.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.iesch.ad.models.Departamento;

import java.util.List;

public class DepartamentoDao {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction transaction;

    public DepartamentoDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("personal");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public List<Departamento> buscaTodosLosDepartamentos() {
        Query queryDepartamento = entityManager.createQuery("SELECT d FROM Departamento d");
        return queryDepartamento.getResultList();
    }

    public List<Departamento> buscaUnDepartamento(int id) {
        Query queryDepartamento = entityManager.createQuery("SELECT d FROM Departamento d where id_dep = :id").setParameter("id", id);
        return queryDepartamento.getResultList();
    }

    public void updateNombreYLocalidad(int id, String nombre, String localidad) {
        System.out.println("\tActualizamos el departamento con id_dep: " + id + "\n" + "\t" + buscaUnDepartamento(id));
        Departamento departamentoUpdate = entityManager.find(Departamento.class, id);
        departamentoUpdate.setNombre(nombre);
        departamentoUpdate.setLocalidad(localidad);
        transaction.begin();
        entityManager.merge(departamentoUpdate);
        transaction.commit();
        System.out.println("\tResultados del Update: " + "\n" + "\t" + buscaUnDepartamento(id));
    }

    public void delete(Departamento departamentoDelete) {
        try {
            transaction.begin();
            Departamento delete = entityManager.find(Departamento.class, departamentoDelete.getId_dep());
            entityManager.remove(delete);
            transaction.commit();
            System.out.println("\tSe ha borrado el departamento con id_dep: " + departamentoDelete.getId_dep());
        } catch (Exception e) {
            System.out.println("\tNo se ha podido borrar el departamento con id_dep: " + departamentoDelete.getId_dep());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
