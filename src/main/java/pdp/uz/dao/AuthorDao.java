package pdp.uz.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pdp.uz.entity.AuthorEntity;
import pdp.uz.entity.StudentEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class AuthorDao implements BaseDao<AuthorEntity, AuthorEntity> {

    private final SessionFactory sessionFactory;

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(authorEntity);
        return authorEntity;
    }

    @Override
    public AuthorEntity update(AuthorEntity authorEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(authorEntity);
        return authorEntity;
    }

    @Override
    public void delete(AuthorEntity authorEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(authorEntity);
        currentSession.createQuery("delete from BookEntity b where b.author.id = :id")
                .setParameter("id", authorEntity.getId())
                .executeUpdate();
    }


    @Override
    public AuthorEntity get(Integer id) {
        return sessionFactory.getCurrentSession()
                .get(AuthorEntity.class, id);
    }

    public List<AuthorEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AuthorEntity").list();
    }

    public AuthorEntity findById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(AuthorEntity.class, id);
    }



}