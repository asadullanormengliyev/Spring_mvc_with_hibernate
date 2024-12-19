package pdp.uz.dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.UserEntity;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserDao implements BaseDao<UserEntity,UserEntity> {
    private final SessionFactory sessionFactory;
    @Override
    public UserEntity save(UserEntity userEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return null;
    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public UserEntity get(Integer id) {
        return null;
    }
}
