package com.yash.httpmimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Context ct = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetNotifications().execute();
    }

    private class GetNotifications extends AsyncTask<String, String, String> {

        android.app.ProgressDialog pDialog;
        String response = "";
        //Bitmap bitmap;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            try {


                HttpRequest http = new HttpRequest(ct);
             //   JSONObject sign_up_obj = new JSONObject();
                //  String customer_id = AppSession.getValue(ct, Constant.USER_ID);

                // sign_up_obj.put("user_id", customer_id);
                response = http.AnimsDate();
                System.out.println(response);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        protected void onPostExecute(String response) {

            try {
                if (response != null)
                {
                    
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
