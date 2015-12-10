/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Processo;

/**
 *
 * @author alunos
 */
public class ProcessoJpaController implements Serializable {

    public ProcessoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Processo processo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(processo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesso(processo.getPid()) != null) {
                throw new PreexistingEntityException("Processo " + processo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Processo processo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            processo = em.merge(processo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = processo.getPid();
                if (findProcesso(id) == null) {
                    throw new NonexistentEntityException("The processo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Processo processo;
            try {
                processo = em.getReference(Processo.class, id);
                processo.getPid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The processo with id " + id + " no longer exists.", enfe);
            }
            em.remove(processo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Processo> findProcessoEntities() {
        return findProcessoEntities(true, -1, -1);
    }

    public List<Processo> findProcessoEntities(int maxResults, int firstResult) {
        return findProcessoEntities(false, maxResults, firstResult);
    }

    private List<Processo> findProcessoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Processo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Processo findProcesso(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Processo.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcessoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Processo> rt = cq.from(Processo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
