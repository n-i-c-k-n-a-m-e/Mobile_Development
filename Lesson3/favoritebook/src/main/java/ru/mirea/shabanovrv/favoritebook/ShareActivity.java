package ru.mirea.shabanovrv.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    static final String QUOTES_KEY = "quotes_name";
    private TextView textView;
    private EditText editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.shared_activity);

        textView = findViewById(R.id.textTitle);
        EditText Book_input = findViewById(R.id.editTitle);
        EditText Quote_input = findViewById(R.id.editQuote);
        Button send_button = findViewById(R.id.button3);

        Bundle data = getIntent().getExtras();
        if(data != null){
            String book = data.getString(MainActivity.BOOK_NAME_KEY);
            String quote = data.getString(MainActivity.QUOTES_KEY);
            textView.setText(String.format("Моя любимая книга: %s и цитата %s", book, quote));
        }
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = Book_input.getText().toString();
                String quotes = Quote_input.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(MainActivity.USER_MESSAGE, title);
                intent.putExtra(ShareActivity.QUOTES_KEY,quotes);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}