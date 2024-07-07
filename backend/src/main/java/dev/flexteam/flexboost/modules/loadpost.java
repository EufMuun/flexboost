package dev.flexteam.flexboost.modules;

import dev.flexteam.flexboost.ConnectToDB;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class loadpost {
    private ConnectToDB connect = new ConnectToDB();
    private Connection connection;
    private MinioClient minioClient;
    public void APILogin() throws SQLException {
        connection = connect.getConnection();
        this.minioClient = minioClient;
    }

    public Map<String, String> loadpost(MultipartFile file ){
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint("http://127.0.0.1:9000")
                        .credentials("flexboost", "flexboost")
                        .build();

        Map<String, String> response = new HashMap<>();
        try {
            // Получаем имя файла
            String fileName = "postImg_" + file.getOriginalFilename();

            // Загружаем файл в MinIO
            InputStream inputStream = file.getInputStream();

            minioClient.putObject(
                    PutObjectArgs.builder().bucket("content").object("my-objectname").stream(
                                    inputStream, file.getSize(), -1)
                            .contentType("video/mp4")
                            .build());

            // Возвращаем успешный ответ
            response.put("message", "File uploaded successfully: " + fileName);
        } catch (IOException | MinioException e) {
            e.printStackTrace();
            response.put("message", "Error uploading file: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}

