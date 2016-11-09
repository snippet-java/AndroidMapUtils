package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 */

public class FormActivity extends Activity {
  private static final String DELETE_URL =
      "http://nodecloudantformgeojsonexample.mybluemix.net/cloudant/destroy?id=";
  private static final String ADD_UPDATE_URL =
      "http://nodecloudantformgeojsonexample.mybluemix.net/cloudant/update";
  EditText idEditView;
  EditText nameEditView;
  EditText descriptionEditView;
  EditText latLngEditView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form);
    idEditView = (EditText) findViewById(R.id.idEditView);
    nameEditView = (EditText) findViewById(R.id.nameEditView);
    descriptionEditView = (EditText) findViewById(R.id.descriptionEditView);
    latLngEditView = (EditText) findViewById(R.id.latLngEditView);
    findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!TextUtils.isEmpty(idEditView.getText())) {
          new AsyncDeleteItem().execute(DELETE_URL + idEditView.getText().toString());
        }
      }
    });
    findViewById(R.id.addUpdateButton).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!TextUtils.isEmpty(idEditView.getText()) &&
            !TextUtils.isEmpty(nameEditView.getText()) &&
            !TextUtils.isEmpty(descriptionEditView.getText()) &&
            !TextUtils.isEmpty(latLngEditView.getText())) {
          try {
            new AsyncAddUpdateItem().execute(ADD_UPDATE_URL +
                "?id=" + URLEncoder.encode(idEditView.getText().toString(), "UTF-8") + "&name=" +
                URLEncoder.encode(nameEditView.getText().toString(), "UTF-8") +
                "&description=" + URLEncoder.encode(descriptionEditView.getText().toString(),
                "UTF-8") +
                "&point=" +
                URLEncoder.encode(latLngEditView.getText().toString(), "UTF-8"));
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  private class AsyncDeleteItem extends AsyncTask<String, Void, Boolean> {

    @Override protected Boolean doInBackground(String... params) {
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
        JSONObject jsonObject = new JSONObject(result.toString()).optJSONObject("data");
        boolean isDeleted = false;
        if (jsonObject != null) isDeleted = jsonObject.optBoolean("ok");
        return isDeleted;
      } catch (IOException e) {
        Log.e("LIST ACTIVITY", "GeoJSON file could not be read");
      } catch (JSONException e) {
      }
      return false;
    }

    @Override protected void onPostExecute(Boolean bool) {
      if (bool) Toast.makeText(FormActivity.this, "item deleted!", Toast.LENGTH_SHORT).show();
    }
  }

  private class AsyncAddUpdateItem extends AsyncTask<String, Void, Boolean> {

    @Override protected Boolean doInBackground(String... params) {
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
        JSONObject jsonObject = new JSONObject(result.toString()).optJSONObject("data");
        boolean isDeleted = false;
        if (jsonObject != null) isDeleted = jsonObject.optBoolean("ok");
        return isDeleted;
      } catch (IOException e) {
        Log.e("LIST ACTIVITY", "GeoJSON file could not be read");
      } catch (JSONException e) {
      }
      return false;
    }

    @Override protected void onPostExecute(Boolean bool) {
      if (bool) Toast.makeText(FormActivity.this, "item added/updated!", Toast.LENGTH_SHORT).show();
    }
  }
}
