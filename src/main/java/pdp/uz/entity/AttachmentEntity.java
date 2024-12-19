package pdp.uz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AttachmentEntity {
    @Id
    private UUID id;
    private String fileName;
    private String contentType;
    private long size;

    public AttachmentEntity(UUID id, String fileName, String contentType, long size) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
    }

    @OneToOne(fetch = FetchType.EAGER)
    private AttachmentContentEntity contentEntity;
}
