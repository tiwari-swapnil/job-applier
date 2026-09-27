package swapnil.job_applier.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swapnil.job_applier.service.FileUploadService;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(
            FileUploadService fileUploadService) {

        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file) {

        try {

            String filePath =
                    fileUploadService.saveFile(file);

            return ResponseEntity.ok(filePath);

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Upload failed: " + e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
}
