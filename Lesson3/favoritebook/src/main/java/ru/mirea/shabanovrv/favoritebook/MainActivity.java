package ru.mirea.shabanovrv.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    static final String QUOTES_KEY = "quotes_name";
    static final String BOOK_NAME_KEY = "book_name";
    static final String USER_MESSAGE="MESSAGE";
    private TextView textViewUserBook;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textViewUserBook = findViewById(R.id.textView);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            assert intent != null;
                            String Name = intent.getStringExtra(MainActivity.USER_MESSAGE);
                            String Quote = intent.getStringExtra(ShareActivity.QUOTES_KEY);
                            textViewUserBook.setText("Название Вашей любимой книги: " + Name + ", цитата: " + Quote);
                        }
                    }
                }
        );
        Button butt = findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShareActivity.class);
                activityResultLauncher.launch(intent);
            }
        });




//       textView = findViewById(R.id.textView);
//        Button butt = findViewById(R.id.button);
//        butt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivityForResult(intent,1);
//            }
//        });
//
//
//    }
//    @Override
//    protected void onActivityResult(int request_code, int result_code, Intent intent){
//        super.onActivityResult(request_code,result_code,intent);
//        if (request_code == 1 && result_code == RESULT_OK && intent != null){
//            String Name = intent.getStringExtra("BOOK");
//            String Quote = intent.getStringExtra("QUOTE");
//            textView.setText("Название Вашей любимой книги: " + Name + ", цитата: " + Quote);

    }
}