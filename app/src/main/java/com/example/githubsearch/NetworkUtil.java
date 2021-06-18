package com.example.githubsearch;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    //networking ka logic, url build, response
    private static final String GITHUB_BASE_URL="https://api.github.com";
    private static final String GITHUB_USER="users";
    private static final String GITHUB_REPOSITORY="repositories";
    private static final String GITHUB_SEARCH="search";
    private static final String PARAM_QUERY="q";

    private NetworkUtil(){

    }
    //base url build to query github
    public static URL build(String query){// url type ka return hoga
        Uri buildUri=Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendPath(GITHUB_SEARCH).appendPath(GITHUB_REPOSITORY)
                .appendQueryParameter(PARAM_QUERY,query).build() ;
        URL url=null;
        try {
            url=new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getresponse(URL url)throws IOException{
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        try {
            InputStream input = urlConnection.getInputStream();
            Scanner sc = new Scanner(input);
            sc.useDelimiter("\\A");
            if(sc.hasNext())
                return sc.next();
            else
                return null;
        }finally{
            urlConnection.disconnect();
        }
    }
}
