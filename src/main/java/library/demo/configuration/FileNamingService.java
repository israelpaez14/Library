package library.demo.configuration;

import org.springframework.stereotype.Service;

@Service
public class FileNamingService {

    public String getValidName(String fileExtension){
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        String generatedName = "";
        for(int i = 0 ; i<25;i++){
            int randomIndex = (int)(Math.random()*chars.length());
            generatedName+=chars.charAt(randomIndex);
        }
        return generatedName+fileExtension;

    }
}
