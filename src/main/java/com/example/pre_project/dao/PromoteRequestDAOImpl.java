package com.example.pre_project.dao;

import com.example.pre_project.model.PromoteRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PromoteRequestDAOImpl implements PromoteRequestDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PromoteRequest> getAllPromoteRequest() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PromoteRequest> cq = cb.createQuery(PromoteRequest.class);
        Root<PromoteRequest> rootEntry = cq.from(PromoteRequest.class);
        CriteriaQuery<PromoteRequest> all = cq.select(rootEntry);
        TypedQuery<PromoteRequest> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void add(PromoteRequest promoteRequest) {
        if(entityManager.find(PromoteRequest.class,promoteRequest.getUserID())!= null){
            update(promoteRequest);
        }else
        entityManager.persist(promoteRequest);
    }

    @Override
    public void delete(PromoteRequest promoteRequest) {
        entityManager.remove(entityManager.contains(promoteRequest) ? promoteRequest : entityManager.merge(promoteRequest));

    }

    @Override
    public void update(PromoteRequest promoteRequest) {
        entityManager.merge(promoteRequest);
    }

    @Override
    public PromoteRequest getByUserID(Long id) {
        return entityManager.find(PromoteRequest.class,id);
    }

}
