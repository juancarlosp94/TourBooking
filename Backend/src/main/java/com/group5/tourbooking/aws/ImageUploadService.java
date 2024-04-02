package com.group5.tourbooking.aws;

import com.group5.tourbooking.exception.InvalidFormatException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

@Service
public class ImageUploadService {

    private S3Client s3;
    private static final String BUCKET_NAME = "c2-g5-tourbooking-2";

    @Value("${aws_access_key_id}")
    private String accessKeyId;

    @Value("${aws_secret_access_key}")
    private String secretAccessKey;

    @PostConstruct
    public void init() {
        s3 = S3Client.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if(!isValidImage(file)) {
            throw new InvalidFormatException();
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;



            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(uniqueFileName)
                    .build();

            s3.putObject(
                    objectRequest,
                    RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes()))
            );

            return "https://" + BUCKET_NAME + ".s3.amazonaws.com/" + uniqueFileName;

    }

    private boolean isValidImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename().toLowerCase();
        String contentType = file.getContentType();

        // Validar por extensión
        boolean isValidExtension = originalFilename.endsWith(".jpg") || originalFilename.endsWith(".jpeg") || originalFilename.endsWith(".png") || originalFilename.endsWith(".svg");

        // Validar por tipo MIME
        boolean isValidMimeType = "image/jpeg".equals(contentType) || "image/png".equals(contentType) || "image/svg+xml".equals(contentType);

        // Añade logs de depuración
        System.out.println("Original Filename: " + originalFilename);
        System.out.println("Content Type: " + contentType);
        System.out.println("isValidExtension: " + isValidExtension);
        System.out.println("isValidMimeType: " + isValidMimeType);

        return isValidExtension && isValidMimeType;
    }

    public void deleteImage(String filename) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(filename)
                .build();

        s3.deleteObject(deleteObjectRequest);
    }

    public String updateImage(String oldFilename, MultipartFile newFile) throws IOException {
        // Borramos la imagen antigua
        deleteImage(oldFilename);

        // Subimos la nueva imagen
        return uploadImage(newFile);
    }

    public String extractFilenameFromUrl(String url) {
        if (url != null && !url.isEmpty()) {
            String[] parts = url.split("/");
            return parts[parts.length - 1]; // Devuelve el último segmento de la URL
        }
        return null;
    }
}
