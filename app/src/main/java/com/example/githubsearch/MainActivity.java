package com.example.githubsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.githubsearch.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    TextView textview;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
//        textview=findViewById(R.id.output);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchterm=binding.search.getText().toString();
                if(TextUtils.isEmpty(searchterm))
                    return;
                URL searchUrl=NetworkUtil.build(searchterm);
                new GithubqueryTask().execute(searchUrl);

            }
        });



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
                parseandDisplayrepos(s);

        }
        private void parseandDisplayrepos(String json){
            List<GithubRepository> repositories=NetworkUtil.parsegithubrepos(json);
            StringBuilder str=new StringBuilder();
            for(GithubRepository repository:repositories){
                str.append("id: ").append(repository.getId()).append("\n").append("Name: ").append(repository.getName())
                .append("\n\n");
            }
           binding.output.setText(str.toString());
        }
    }

}
