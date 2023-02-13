package nyongnyong.pangparty.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FirebaseConfig {

    @Value("${firebase.configuration.file}")
    private String firebaseConfigPath;
    @Value("${firebase.database.url}")
    private String firebaseDatabaseUrl;

    @PostConstruct
    public void initialize() throws IOException {

        InputStream resource = getClass().getResourceAsStream(firebaseConfigPath);

        FirebaseApp firebaseApp = null;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        if(firebaseApps != null && !firebaseApps.isEmpty()) {
            for(FirebaseApp app : firebaseApps) {
                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                    firebaseApp = app;
            }
        } else {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(resource))
                    .setDatabaseUrl(firebaseDatabaseUrl)
                    .build();
            FirebaseApp.initializeApp(options);
        }
    }
}