package com.example.rogerio.campominado.leaderboard;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rogerio.campominado.adapters.LeaderboardAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by r176976 on 25/10/17.
 */

public class SelectPlayer extends AsyncTask <Void,Void,String> {

    private Context context;
    private String[] fields;
    private String[] values;
    private static final String HOST = "http://es.ft.unicamp.br/ulisses/si700/select_data.php";

    ListView listView;

    public SelectPlayer(Context context, ListView listView) {
        this.context = context;
        this.listView =listView;
        this.fields = fields;
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

    @Override
    protected void onPostExecute(String result) {

        ArrayList<Player_item> players = new ArrayList<>();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        try{
            JSONArray jsonArray = new JSONArray(result);


            for(int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String time = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(Long.parseLong(jsonObject.getString("time"))),
                        TimeUnit.MILLISECONDS.toSeconds(Long.parseLong(jsonObject.getString("time")))
                );

                Player_item player = new Player_item(jsonObject.getString("nickname"),time);
                players.add(player);
                // players.add(jsonObject.getString("nickname")+" "+jsonObject.getString("time"));

            }

            LeaderboardAdapter adapter = new LeaderboardAdapter(context,players);
            listView.setAdapter(adapter);





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
