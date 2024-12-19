package pdp.uz.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pdp.uz.entity.BookEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookDao implements BaseDao<BookEntity, BookEntity> {
    private final SessionFactory sessionFactory;

    @Override
    public BookEntity save(BookEntity bookEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(bookEntity);
        return bookEntity;
    }

    @Override
    public BookEntity update(BookEntity bookEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(bookEntity);
        return bookEntity;
    }

    @Override
    public void delete(BookEntity bookEntity) {
        sessionFactory.getCurrentSession().delete(bookEntity);
    }


    @Override
    public BookEntity get(Integer id) {
        return sessionFactory.getCurrentSession()
                .get(BookEntity.class, id);
    }

    public List<BookEntity> findAll(Integer authorId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM BookEntity b WHERE b.author.id = :authorId", BookEntity.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }

    public List<BookEntity> bookEntityList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM BookEntity").list();
    }

    public List<BookEntity> bookEntityListByAuthorId(Integer authorId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM BookEntity b WHERE b.author.id = :authorId");
        query.setParameter("authorId", authorId);
        return query.list();
    }


}
