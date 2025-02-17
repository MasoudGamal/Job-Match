package org.springdemo.serviceproviders.firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() {
        try {
            // تحدد مسار ملف التهيئة
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccountKey.json");

            // إعداد خيارات Firebase
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // تهيئة Firebase
            FirebaseApp.initializeApp(options);

            System.out.println("Firebase Initialized Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to initialize Firebase.");
        }
    }
}
