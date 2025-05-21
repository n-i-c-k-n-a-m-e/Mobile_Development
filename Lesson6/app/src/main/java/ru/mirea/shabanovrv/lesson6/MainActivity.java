package ru.mirea.shabanovrv.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText ed_group;
    private EditText ed_number;
    private EditText ed_serial;
    private Button save_button;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_GROUP = "group_number";
    private static final String KEY_LIST_NUMBER = "list_number";
    private static final String KEY_CINEMA = "favorite_cinema";
    private static final String KEY_LAUNCH_COUNT = "launch_count";

    private SharedPreferences sharedPref;

    private int launchCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_group = findViewById(R.id.ed_group);
        ed_number = findViewById(R.id.ed_number);
        ed_serial = findViewById(R.id.ed_serial);
        save_button = findViewById(R.id.save_button);

        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // счётчик запусков
        launchCount = sharedPref.getInt(KEY_LAUNCH_COUNT, 0);
        launchCount++;
        sharedPref.edit().putInt(KEY_LAUNCH_COUNT, launchCount).apply();

        // Если это не первый запуск - загружаем сохранённые данные
        if (launchCount > 1) {
            loadPreferences();
        }

        save_button.setOnClickListener(v -> {
            savePreferences();
            Toast.makeText(MainActivity.this, "Данные сохранены", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // При полном уничтожении приложения обнуляем счётчик запусков
        sharedPref.edit().putInt(KEY_LAUNCH_COUNT, 0).apply();
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY_GROUP, ed_group.getText().toString());
        editor.putString(KEY_LIST_NUMBER, ed_number.getText().toString());
        editor.putString(KEY_CINEMA, ed_serial.getText().toString());
        editor.apply();
    }

    private void loadPreferences() {
        ed_group.setText(sharedPref.getString(KEY_GROUP, ""));
        ed_number.setText(sharedPref.getString(KEY_LIST_NUMBER, ""));
        ed_serial.setText(sharedPref.getString(KEY_CINEMA, ""));
    }
}