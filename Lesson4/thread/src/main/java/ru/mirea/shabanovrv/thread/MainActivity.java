package ru.mirea.shabanovrv.thread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.AsyncTask;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.shabanovrv.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private	int	counter	=	0;
    private TextView infoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Меняем имя потоку
        infoTextView = findViewById(R.id.textView2);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 03, НОМЕР ПО СПИСКУ: 22, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Голодные игры");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));
        binding.buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pairsString = binding.editTextDays.getText().toString();
                String daysString = binding.editTextPairs.getText().toString();


                int Pairs = Integer.parseInt(pairsString);
                int Days = Integer.parseInt(daysString);
                new AveragePairsPerDay().execute(Pairs, Days);
                //Ломаем приложение
               // long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
              //  while 	(System.currentTimeMillis()	<	endTime)	{
                 //   synchronized	(this)	{
                     //   try	{
                      //      wait(endTime	-	System.currentTimeMillis());
                     //   }	catch	(Exception	e)	{
                       //     throw	new	RuntimeException(e);
                      //  }
                        //  }}
                //Переносим функционал
                //new	Thread(new	Runnable()	{
                  //  public	void	run()	{
                  //      int	numberThread = counter++;
                      //  Log.d("ThreadProject",	String.format("Запущен	поток №	%d студентом группы	№ %s номер	по списку №	%d ",	numberThread, "БИСО-03-20",	22));
                       // long endTime = System.currentTimeMillis()	+	20	*	1000;
                      //  while (System.currentTimeMillis()	<	endTime)	{
                       //     synchronized (this)	{
                         //       try	{
                         //           wait(endTime	-	System.currentTimeMillis());
                          //          Log.d(MainActivity.class.getSimpleName(),	"Endtime:	"	+	endTime);
                          //      }	catch	(Exception	e)	{
                        //            throw	new	RuntimeException(e);
                           //     }
                         //   }
                       //     Log.d("ThreadProject",	"Выполнен	поток	№	"	+	numberThread);
                      //  }
                   // }
              //  }).start();
            }
        });

        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public class AveragePairsPerDay extends AsyncTask<Integer, Void, Double> {

        @Override
        protected Double doInBackground(Integer... arrays) {
            int days = arrays[0];
            int pairs = arrays[1];

            return (double) pairs / days; // Средний показатель
        }

        @Override
        protected void onPostExecute(Double result) {
            binding.textViewResult.setText("Среднее число пар в день: " + result); // вывод результата
        }
    }

}