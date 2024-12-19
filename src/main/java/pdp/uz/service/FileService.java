package pdp.uz.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.dao.AttachmentDao;
import pdp.uz.entity.AttachmentContentEntity;
import pdp.uz.entity.AttachmentEntity;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final AttachmentDao attachmentDao;

    public AttachmentEntity saveAttachment(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("File name cannot be empty");
        }
        byte[] bytes = file.getBytes();

        AttachmentContentEntity attachmentContentEntity = new AttachmentContentEntity();
        attachmentContentEntity.setContent(bytes);
        attachmentContentEntity.setId(UUID.randomUUID());

        AttachmentEntity attachmentEntity = new AttachmentEntity(
                UUID.randomUUID(),
                originalFilename,
                file.getContentType(),
                file.getSize()
        );
        return attachmentDao.saveAttachment(attachmentEntity, attachmentContentEntity);
    }

    public AttachmentContentEntity getAttachmentById(UUID attachmentId) {
        return attachmentDao.getAttachmentById(attachmentId);
    }
}
