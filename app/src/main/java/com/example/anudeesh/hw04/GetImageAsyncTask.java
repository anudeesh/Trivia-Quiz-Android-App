package com.example.anudeesh.hw04;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Anudeesh on 9/23/2016.
 */
public class GetImageAsyncTask extends AsyncTask<String,Void, Bitmap> {
    TriviaActivity activity;
    ProgressDialog progressDialog;

    public GetImageAsyncTask(TriviaActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection)url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.setRequestMethod("GET");

            Bitmap image = BitmapFactory.decodeStream(httpURLConnection.getInputStream());

            return image;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading Image");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.qimg.setImageResource(0);
        if(bitmap!=null)
        {
            activity.qimg.setImageBitmap(bitmap);
        }
        activity.buttonnext.setEnabled(true);
        progressDialog.dismiss();
    }
}
