package pdp.uz.dao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pdp.uz.entity.AttachmentContentEntity;
import pdp.uz.entity.AttachmentEntity;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional
public class AttachmentDao {

    private final SessionFactory sessionFactory;

    public AttachmentEntity saveAttachment(AttachmentEntity attachmentEntity, AttachmentContentEntity attachmentContentEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(attachmentContentEntity);
        attachmentEntity.setContentEntity(attachmentContentEntity);
        currentSession.persist(attachmentEntity);
        return attachmentEntity;
    }

    public AttachmentContentEntity getAttachmentById(UUID attachmentId) {
        Session currentSession = sessionFactory.getCurrentSession();
        String hql = "SELECT c FROM AttachmentEntity a JOIN a.contentEntity c WHERE a.id = :id";
        AttachmentContentEntity contentEntity = currentSession.createQuery(hql, AttachmentContentEntity.class)
                .setParameter("id", attachmentId)
                .uniqueResult();
        return contentEntity;
    }
}
