package com.example.anudeesh.hw04;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Question> quesList = new ArrayList<Question>();
    final static String QUESTIONS_KEY ="QUESTIONS";
    ImageView imgtrivia;
    Button buttonstart;
    TextView stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgtrivia = (ImageView) findViewById(R.id.imageTrivia);
        buttonstart = (Button) findViewById(R.id.buttonStart);
        stat = (TextView) findViewById(R.id.textViewStatus);
        imgtrivia.setVisibility(View.INVISIBLE);
        buttonstart.setEnabled(false);

        if(isConnectedOnline()) {
            new GetQuestionsAsyncTask(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        }
        else {
            Toast.makeText(MainActivity.this,"Cannot access internet",Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TriviaActivity.class);
                intent.putExtra(QUESTIONS_KEY,quesList);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        else {
            return false;
        }
    }
}
