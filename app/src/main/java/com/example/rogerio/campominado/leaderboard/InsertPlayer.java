package com.example.rogerio.campominado.leaderboard;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by r176976 on 25/10/17.
 */

public class InsertPlayer extends AsyncTask <Void,Void,String> {

    private String[] fields;
    private String[] values;
    private static final String HOST = "http://es.ft.unicamp.br/ulisses/si700/insert_data.php";

    public InsertPlayer(String[] fields,String[] values) {
        this.fields = fields;
        this.values = values;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection httpURLConnection = null;

        try {
            String data =
            URLEncoder.encode("database","UTF-8")+"="+
            URLEncoder.encode("ra176976","UTF-8")+"&"+

            URLEncoder.encode("table","UTF-8")+"="+
            URLEncoder.encode("leaderboard","UTF-8");

            for(int i = 0;i < fields.length; i++){
                data += "&"+URLEncoder.encode(fields[i],"UTF-8")+"="+
                        URLEncoder.encode(values[i],"UTF-8");
            }

            URL url = new URL(HOST);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(data);
            wr.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                sb.append(line);
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
