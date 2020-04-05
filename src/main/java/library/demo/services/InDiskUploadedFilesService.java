package library.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class InDiskUploadedFilesService implements UploadedFileService{

    @Autowired
    FileNamingService fileNamingService;


    @Override
    public Resource serveUploadedFile(String filename) throws IOException {
            String realPath = "/tmp/uploads/";
            File transferFile = new File(realPath + "/" + filename);
            Resource resource = new UrlResource(transferFile.toURI());
            return resource;
    }


    @Override
    public String  uploadFile(MultipartFile file) throws IOException {
        String realPath = "/tmp/uploads/";
        String savedFileName =
                fileNamingService.getValidName("." + file.getContentType().split("/")[1]);
        File transferFile = new File(realPath + "/" + savedFileName);
        file.transferTo(transferFile);
        return savedFileName;

    }


}
