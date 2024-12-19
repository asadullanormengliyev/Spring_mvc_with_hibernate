package pdp.uz.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pdp.uz.entity.RoleEntity;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional
public class RoleDao implements BaseDao<RoleEntity,RoleEntity> {
    private final SessionFactory sessionFactory;
    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(roleEntity);
        return roleEntity;
    }

    @Override
    public RoleEntity update(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public void delete(RoleEntity roleEntity) {

    }

    @Override
    public RoleEntity get(Integer id) {
        return null;
    }

    public List<RoleEntity> findAll() {
       return sessionFactory.getCurrentSession().createQuery("from RoleEntity").list();
    }
}
