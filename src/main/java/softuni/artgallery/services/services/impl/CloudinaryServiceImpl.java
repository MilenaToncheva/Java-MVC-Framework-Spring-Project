package softuni.artgallery.services.services.impl;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import softuni.artgallery.services.services.CloudinaryService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file=File.createTempFile("temp-file",multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return cloudinary.uploader().upload(file,new HashMap()).get("url").toString();
    }
}
