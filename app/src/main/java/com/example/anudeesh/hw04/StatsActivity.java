package com.example.anudeesh.hw04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        final ArrayList<Question> questionList = (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.QUESTIONS_KEY);
        int grade = getIntent().getExtras().getInt(TriviaActivity.GRADE_KEY);
        double temp = (double)grade/16;
        double percentage = temp*100;

        ((TextView)findViewById(R.id.textViewGrade)).setText(percentage+"%");
        ((ProgressBar)findViewById(R.id.progressBar)).setProgress((int)percentage);

        findViewById(R.id.buttonQuitStats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonTryAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StatsActivity.this, TriviaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(MainActivity.QUESTIONS_KEY,questionList);
                startActivity(intent);
            }
        });
    }
}
