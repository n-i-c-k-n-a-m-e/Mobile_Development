package ru.mirea.shabanovrv.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String FILE_NAME = "data.txt";

    private EditText text;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.data_edit);
        save = findViewById(R.id.save_button);

        save.setOnClickListener(v -> {
            String data = text.getText().toString();
            try (FileOutputStream outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
                outputStream.write(data.getBytes());
                Toast.makeText(MainActivity.this, "Данные сохранены в файл", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Ошибка при записи файла", e);
                Toast.makeText(MainActivity.this, "Ошибка при сохранении данных", Toast.LENGTH_LONG).show();
            }
        });
    }
}