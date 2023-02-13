package com.example.pmd_asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonEjecutar = (Button) findViewById(R.id.buttonExe);
    Button botonCancelar = (Button) findViewById(R.id.buttonCancell);
    ProgressBar miProgress = (ProgressBar) findViewById(R.id.miBarra);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonEjecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TareaAsincrona tareaAsincrona = new TareaAsincrona();
                tareaAsincrona.execute();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public class TareaAsincrona extends AsyncTask<Void, Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i*10);
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            miProgress.setMax(100);
            miProgress.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result)
                Toast.makeText(MainActivity.this,"Tarea Finalizada!",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progreso = values[0].intValue();
            miProgress.setProgress(progreso);
        }


    }

}