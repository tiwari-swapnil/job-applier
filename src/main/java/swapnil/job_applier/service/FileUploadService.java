package swapnil.job_applier.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    private final Path uploadDirectory =
            Paths.get("uploads");

    public FileUploadService() throws IOException {

        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
    }

    public String saveFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String originalName = file.getOriginalFilename();

        if (originalName == null) {
            throw new RuntimeException("Invalid file name");
        }

        // Only allow PDF
        if (!originalName.toLowerCase().endsWith(".pdf")) {
            throw new RuntimeException(
                    "Only PDF files are allowed"
            );
        }

        // Generate unique filename
        String fileName =
                UUID.randomUUID() + "_" + originalName;

        Path filePath =
                uploadDirectory.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath
        );

        return filePath.toString();
    }
}