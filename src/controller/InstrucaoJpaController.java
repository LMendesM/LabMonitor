package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Estacao;
import model.Instrucao;

public class InstrucaoJpaController implements Serializable {

    public InstrucaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Instrucao instrucao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(instrucao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Instrucao instrucao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            instrucao = em.merge(instrucao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = instrucao.getId();
                if (findInstrucao(id) == null) {
                    throw new NonexistentEntityException("The instrucao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Instrucao instrucao;
            try {
                instrucao = em.getReference(Instrucao.class, id);
                instrucao.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The instrucao with id " + id + " no longer exists.", enfe);
            }
            em.remove(instrucao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Instrucao> findInstrucaoEntities() {
        return findInstrucaoEntities(true, -1, -1);
    }

    public List<Instrucao> findInstrucaoEntities(int maxResults, int firstResult) {
        return findInstrucaoEntities(false, maxResults, firstResult);
    }

    private List<Instrucao> findInstrucaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Instrucao.class));
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

    public Instrucao findInstrucao(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Instrucao.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstrucaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Instrucao> rt = cq.from(Instrucao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Instrucao> findInstrucaoEntitiesByEstacao(Estacao estacao){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Instrucao> query = cb.createQuery(Instrucao.class);
        Root<Instrucao> from = query.from(Instrucao.class);
        TypedQuery<Instrucao> typedQuery = em.createQuery(
                query.select(from)
                .where(
                        cb.equal(from.join("instrucao").get("id"), estacao.getNome())
                )
        );

        List<Instrucao> results = typedQuery.getResultList();
        return results;
    }
    
}
