package library.demo.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("uploaded_file")
public class UploadFilesService {


    @ResponseBody
    @RequestMapping("/view/{filename}")
    public ResponseEntity<Resource> serveUploadedFile(@PathVariable String filename,
                                                      HttpServletRequest request) throws IOException {
        String realPath = "/tmp/uploads/";
        File transferFile = new File(realPath + "/" + filename);
        Resource resource = new UrlResource(transferFile.toURI());
        String contentType =
                request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.ALLOW, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
