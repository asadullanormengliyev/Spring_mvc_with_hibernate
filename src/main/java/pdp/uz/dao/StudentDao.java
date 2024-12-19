package pdp.uz.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.AttachmentEntity;
import pdp.uz.entity.StudentEntity;

import java.util.List;

@Repository
@Transactional
public class StudentDao {
    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(StudentEntity studentEntity) {
//        Session session = sessionFactory.openSession(); // Bu yo;lda sessionni o'zimiz boshqaramiz
        Session currentSession = sessionFactory.getCurrentSession(); // Bu yerda sessionni Spring o'zi boshqaryabdi
        currentSession.persist(studentEntity);
    }

    public void delete(StudentEntity studentEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(studentEntity);
    }

    public List<StudentEntity> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        List<StudentEntity> studentEntities = currentSession.createQuery("from StudentEntity").list();
        return studentEntities;
    }

    public StudentEntity updateStudentImage(int id, AttachmentEntity attachmentEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        StudentEntity studentEntity = currentSession.get(StudentEntity.class, id);
        studentEntity.setAttachment(attachmentEntity);
        currentSession.update(studentEntity);
        return studentEntity;
    }

    public StudentEntity findById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        StudentEntity studentEntity = currentSession.get(StudentEntity.class, id);
        return studentEntity;
    }


}
