package com.example.ahmed.webservice_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        try {
                            URL url = new URL("http://localhost/firstproject/webservice_1.php");
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            final String msg = bufferedReader.readLine();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txt.setText(msg);
                                }
                            });
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                };

                Thread thread=new Thread(runnable);
                thread.start();

            }
        });

    }

    public void update(){}
}
