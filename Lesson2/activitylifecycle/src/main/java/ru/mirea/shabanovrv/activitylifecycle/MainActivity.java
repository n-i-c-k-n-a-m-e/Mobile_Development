package ru.mirea.shabanovrv.activitylifecycle;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("TAG", "onStart()");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("TAG", "onRestart()");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("TAG", "onPause()");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i("TAG", "onSaveInstanceState()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("TAG", "onStop()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("TAG", "onResume()");
    }


}