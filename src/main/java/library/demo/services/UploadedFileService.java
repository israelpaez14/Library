package library.demo.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadedFileService {
    Resource serveUploadedFile(String filename) throws IOException;
    String uploadFile(MultipartFile file) throws IOException;

}
