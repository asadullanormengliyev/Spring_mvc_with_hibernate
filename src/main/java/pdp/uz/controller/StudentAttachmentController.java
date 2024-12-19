package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdp.uz.entity.AttachmentContentEntity;
import pdp.uz.entity.AttachmentEntity;
import pdp.uz.entity.StudentEntity;
import pdp.uz.service.FileService;
import pdp.uz.service.StudentService;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentAttachmentController {
    private final StudentService studentService;
    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") int id,
            Model model
    ) throws IOException {
        AttachmentEntity attachmentEntity = fileService.saveAttachment(file);
        StudentEntity studentEntity = studentService.updateStudentImage(id, attachmentEntity);
        model.addAttribute("student", studentEntity);
        return "student/show";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") UUID id) {
        AttachmentContentEntity attachmentContentEntity = fileService.getAttachmentById(id);
        if (attachmentContentEntity != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(attachmentContentEntity.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/show/{id}")
    public String showStudent(@PathVariable("id") int id, Model model) {
        StudentEntity student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "student/show";
    }
}
