package ru.mirea.shabanovrv.favoritebook;

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

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);

        EditText Book_input = findViewById(R.id.editTextBookTitle);
        EditText Quote_input = findViewById(R.id.editTextQuote);
        Button buttonsub = findViewById(R.id.buttonsub);

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = Book_input.getText().toString();
                String Quote = Quote_input.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("BOOK",Title);
                intent.putExtra("QUOTE",Quote);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
