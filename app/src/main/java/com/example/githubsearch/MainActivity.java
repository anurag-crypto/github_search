package com.example.githubsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=findViewById(R.id.output);

        URL searchUrl=NetworkUtil.build("android");
        new GithubqueryTask().execute(searchUrl);

    }
    public class GithubqueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl=params[0];
            String githubsearchresult=null;
            try{
                githubsearchresult=NetworkUtil.getresponse(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubsearchresult;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null && !s.equals("") )
                textview.setText(s);

        }
    }

}
