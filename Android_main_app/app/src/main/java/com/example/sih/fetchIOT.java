package com.example.sih;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchIOT extends AsyncTask<Void,Void,Void> {
    String data="";
    String dp = "";
    String sp="";


    @Override
    protected Void doInBackground(Void... voids) {

       /* try {
            URL url = new URL("https://api.thingspeak.com/channels/1110396/feeds.json?results=1");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line!=null){
                line = bufferedReader.readLine();
                data=data+line;
            }
            JSONArray ja = new JSONArray(data);
            for (int i=0;i<ja.length();i++){
                JSONObject jo = (JSONObject)ja.get(i);
                sp = jo.get("field1")+"\n";
                dp = dp+sp;
            }
            JSONObject obj = new JSONObject(data);
            JSONArray ja = obj.getJSONArray("field1");
            for (int i=1;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                sp = jo.getString("field1")+"\n";
                dp = dp+sp;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        HttpHandler sh = new HttpHandler();
        String url = "https://api.thingspeak.com/channels/1110396/feeds.json?results=1";
        String jsonStr = sh.makeServiceCall(url);
        if(jsonStr!=null){
            try{
                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray feeds = jsonObj.getJSONArray("feeds");
                for (int i=0;i<feeds.length();i++){
                    JSONObject c = feeds.getJSONObject(i);
                    String r = c.getString("field1");
                    sp=sp+r;
                    dp=dp+sp;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        HomeActivity.data.setText(this.dp);
    }

}
