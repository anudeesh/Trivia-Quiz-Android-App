package com.example.anudeesh.hw04;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    private TextView qnum,qtext,timeleft;
    private LinearLayout choicesCont;
    ImageView qimg;
    Button buttonnext;
    ArrayList<Question> qlist = new ArrayList<Question>();
    final static String GRADE_KEY ="GRADE";
    int qno=0, grade=0,checked=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        qnum = (TextView) findViewById(R.id.textViewQuestionNumber);
        qtext = (TextView) findViewById(R.id.textViewQuestion);
        qimg = (ImageView) findViewById(R.id.imageViewQuestion);
        buttonnext = (Button) findViewById(R.id.buttonNext);
        timeleft = (TextView)findViewById(R.id.textViewTimeLeft);
        choicesCont = (LinearLayout) findViewById(R.id.optionsCont);

        qlist = (ArrayList<Question>) getIntent().getExtras().getSerializable(MainActivity.QUESTIONS_KEY);
        populateView(qno);

        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checked == Integer.parseInt(qlist.get(qno).getAnswer().toString())) {
                    grade++;
                }
                qno++;
                if(qno<qlist.size()) {
                    populateView(qno);
                }
                else {
                    Intent intent = new Intent(TriviaActivity.this,StatsActivity.class);
                    intent.putExtra(GRADE_KEY,grade);
                    intent.putExtra(MainActivity.QUESTIONS_KEY,qlist);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.buttonQuit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TriviaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeleft.setText("Time Left: "+ millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                timeleft.setText("done!");
                Intent intent = new Intent(TriviaActivity.this,StatsActivity.class);
                intent.putExtra(GRADE_KEY,grade);
                intent.putExtra(MainActivity.QUESTIONS_KEY,qlist);
                startActivity(intent);
            }
        }.start();

    }

    private void populateView(final int qI)
    {
        ArrayList choicesList = null;
        buttonnext.setEnabled(false);
        choicesCont.removeAllViews();
        choicesList = (ArrayList) qlist.get(qI).getOptions();
        qnum.setText("Q"+(qI+1));

        qtext.setText(qlist.get(qI).getQuestionText());
        new GetImageAsyncTask(this).execute(qlist.get(qI).getQuestionURL());

        final RadioButton[] rb = new RadioButton[choicesList.size()];
        RadioGroup rg = new RadioGroup(this);
        rg.removeAllViews();
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int j = 0; j < choicesList.size(); j++) {
            rb[j] = new RadioButton(this);
            rb[j].setId(j+1);
            rg.addView(rb[j]);
            rb[j].setText(choicesList.get(j).toString());
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checked = checkedId;
            }
        });

        choicesCont.addView(rg);
    }
}
