package ru.mirea.shabanovrv.securesharedpreferences;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView name;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        photo = findViewById(R.id.photo);

        try {
            // Мастер-ключ для шифрования
            String mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            // Создаём SharedPreferences
            SharedPreferences secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );


            String actorName = secureSharedPreferences.getString("secure", "Михаил Булгаков");
            name.setText(actorName);


            SharedPreferences.Editor editor = secureSharedPreferences.edit();
            editor.putString("secure","Михаил Булгаков");
            editor.putString("bulgakov", "bulgakov");
            editor.apply();


            String photoName = secureSharedPreferences.getString("bulgakov", "bulgakov");
            int imageResId = getResources().getIdentifier(photoName, "raw", getPackageName());

            Resources res = getResources();
            InputStream inputStream = res.openRawResource(imageResId);
            Drawable drawable = Drawable.createFromStream(inputStream, photoName);
            photo.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
            name.setText("Ошибка загрузки данных");
        }
    }
}