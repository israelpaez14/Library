package library.demo.controllers;

import library.demo.services.UploadedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("uploaded_file")
public class FileInteractionController {

    @Autowired
    UploadedFileService uploadedFilesService;

    @ResponseBody
    @RequestMapping("/view/{filename}")
    public ResponseEntity<Resource> serveUploadedFile(@PathVariable String filename, HttpServletRequest request) throws IOException {
        Resource resource = uploadedFilesService.serveUploadedFile(filename);
        String contentType =
                request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if (resource.exists()) {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.ALLOW, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
