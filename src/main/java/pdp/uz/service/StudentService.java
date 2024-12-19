package pdp.uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.dao.StudentDao;
import pdp.uz.entity.AttachmentEntity;
import pdp.uz.entity.StudentEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;

    public StudentEntity addStudent(StudentEntity studentEntity) {
        studentDao.save(studentEntity);
        return studentEntity;
    }

    public StudentEntity updateStudentImage(int id, AttachmentEntity attachmentEntity) {
        return studentDao.updateStudentImage(id, attachmentEntity);
    }

    public void deleteStudent(StudentEntity studentEntity) {
        studentDao.delete(studentEntity);
    }

    public List<StudentEntity> findAllStudents() {
        return studentDao.findAll();
    }

    public StudentEntity findStudentById(int id) {
        return studentDao.findById(id);
    }
}
