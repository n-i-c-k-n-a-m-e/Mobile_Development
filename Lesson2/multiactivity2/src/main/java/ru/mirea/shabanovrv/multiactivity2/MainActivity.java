package ru.mirea.shabanovrv.multiactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}
public class MainActivity extends AppCompatActivity {
    private EditText Text;
    private Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = findViewById(R.id.editTextText);
        butt = findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {;
            String value = Text.getText().toString();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("key", value);
            startActivity(intent);
            }
        });
    }
//    public void onClick(View view) {
//
//        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//        intent.putExtra("key", "MIREA - ШАБАНОВ РАГИМ ВАИДОВИЧ");
//        startActivity(intent);
//        //String text = (String) getIntent().getSerializableExtra("key");
//    }
}