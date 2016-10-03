package com.example.anudeesh.hw04;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anudeesh on 9/23/2016.
 */
public class GetQuestionsAsyncTask extends AsyncTask<String,Void,ArrayList<Question>>{

    MainActivity activity;
    ProgressDialog progressDialog;

    public GetQuestionsAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<Question> doInBackground(String... params) {
        URL url = null;
        BufferedReader reader = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line= "";
            while ((line=reader.readLine())!=null){
                sb.append(line+"\n");
            }
            return QuestionUtil.QuestionJSONparser.parseQuestions(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading Trivia");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        super.onPostExecute(questions);
        activity.quesList = questions;
        progressDialog.dismiss();
        activity.imgtrivia.setVisibility(View.VISIBLE);
        activity.buttonstart.setEnabled(true);
        activity.stat.setText("Trivia Ready");
    }
}
