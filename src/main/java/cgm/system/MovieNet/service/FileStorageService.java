package cgm.system.MovieNet.service;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {

    public String storeFile(MultipartFile file);
    public Path loadFileAsResource(String fileName);
}






