package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.maps.android.utils.demo.model.ListJson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 */

public class ListActivity extends Activity {
  ListView listView;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    listView = (ListView) findViewById(R.id.listView);
    new DownloadGeoJsonFile().execute(
        "http://nodecloudantformgeojsonexample.mybluemix.net/cloudant/list");
  }

  private class DownloadGeoJsonFile extends AsyncTask<String, Void, ListJson> {

    @Override protected ListJson doInBackground(String... params) {
      try {
        // Open a stream from the URL
        InputStream stream = new URL(params[0]).openStream();
        String line;
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        while ((line = reader.readLine()) != null) {
          // Read and save each line of the stream
          result.append(line);
        }
        // Close the stream
        reader.close();
        stream.close();
        return new Gson().fromJson(result.toString(), ListJson.class);
      } catch (IOException e) {
        Log.e("LIST ACTIVITY", "GeoJSON file could not be read");
      }
      return null;
    }

    @Override protected void onPostExecute(ListJson listJson) {
      if (listJson != null) {
        listView.setAdapter(new MySimpleArrayAdapter(ListActivity.this,listJson.getData().getRows()));

      }
    }
  }
}
