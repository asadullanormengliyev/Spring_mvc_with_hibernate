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
import pdp.uz.service.FileService;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final FileService fileService;

    @GetMapping
    public String base(
            Model model
    ) {
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        model.addAttribute("attachment", attachmentEntity);
        return "file/attachment.html";
    }

    @PostMapping("/upload")
    public String upload(
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        AttachmentEntity attachmentEntity = fileService.saveAttachment(file);
        model.addAttribute("attachment", attachmentEntity);
        return "file/attachment.html";
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
}