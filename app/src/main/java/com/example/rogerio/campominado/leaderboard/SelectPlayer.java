package com.example.rogerio.campominado.leaderboard;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by r176976 on 25/10/17.
 */

public class SelectPlayer extends AsyncTask<Void, Integer, String> {

    private Context context;
    private String[] fields;
    private String[] values;
    private static final String HOST = "http://es.ft.unicamp.br/ulisses/si700/select_data.php";

    ListView listView;
    ProgressBar progress;

    static final int TOP = 10;

    public SelectPlayer(Context context, ListView listView, ProgressBar progress) {
        this.context = context;
        this.listView = listView;
        this.progress = progress;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection httpURLConnection = null;

        try {
            String data =
                    URLEncoder.encode("database", "UTF-8") + "=" +
                            URLEncoder.encode("ra176976", "UTF-8") + "&" +
                            URLEncoder.encode("table", "UTF-8") + "=" +
                            URLEncoder.encode("leaderboard", "UTF-8");

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

            while ((line = reader.readLine()) != null) {
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
        try {
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                long timeSpent = Long.parseLong(jsonObject.getString("time"));
                Player_item player = new Player_item(jsonObject.getString("nickname"), formatTimeSpent(timeSpent));
                players.add(player);
            }

            Collections.sort(players, new Comparator<Player_item>() {
                @Override
                public int compare(Player_item p1, Player_item p2) {
                    return p1.getTime().compareTo(p2.getTime());
                }
            });

            ArrayList<Player_item> topPlayers = filterTopPlayers(players);
            LeaderboardAdapter adapter = new LeaderboardAdapter(context, topPlayers);
            listView.setAdapter(adapter);
            progress.setVisibility(View.GONE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progress.setProgress(values[0]);
    }

    /**
     * Format timeSpent to 00:00 (minutes-seconds) format
     * @param timeSpent Time spent by player
     * @return
     */
    public String formatTimeSpent(long timeSpent) {
        String time = String.format(Locale.getDefault(),
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeSpent),
                TimeUnit.MILLISECONDS.toSeconds(timeSpent) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeSpent))
        );
        return time;
    }

    /**
     * Filter players by displaying a certain amount of players defined by variable TOP
     * @param players All players
     * @return Filtered ArrayList of players
     */
    public ArrayList<Player_item> filterTopPlayers(ArrayList<Player_item> players){
        // Filter top players
        if (players.size() > TOP)
            return new ArrayList<>(players.subList(0, TOP));

        return players;
    }


}
