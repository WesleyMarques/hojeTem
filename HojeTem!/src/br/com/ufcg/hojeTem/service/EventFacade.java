package br.com.ufcg.hojeTem.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import br.com.ufcg.hojeTem.model.Event;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventFacade implements IEventFacade {

   private GoogleMap map;

   private static EventFacade instance;

   // manter singleton
   private EventFacade() {
   }

   @Override
   public void markEventsByCity(String city, GoogleMap map) {
      this.map = map;
      new HttpAsyncEventTask().execute(UriService.getURI(city));
   }

   @Override
   public void markEventCurrentLocation(GoogleMap map, double latitude,
         double longitude) {
      this.map = map;

      new HttpAsyncTask()
            .execute("http://maps.googleapis.com/maps/api/geocode/json?latlng="
                  + latitude + "," + longitude + "&sensor=true");
   }

   private class HttpAsyncEventTask extends AsyncTask<String, Void, String> {
      @Override
      protected String doInBackground(String... urls) {
         return GET(urls[0]);
      }

      protected void onPostExecute(String result) {
         try {
            JSONObject json = new JSONObject(result);
            List<Event> eventsRequest = getEvents(json);

            for (Event event : eventsRequest) {
               map.addMarker(eventToMark(event));
            }

         } catch (JSONException e) {
            e.printStackTrace();
         }
      }
   }

   private class HttpAsyncTask extends AsyncTask<String, Void, String> {
      @Override
      protected String doInBackground(String... urls) {
         return GET(urls[0]);
      }

      protected void onPostExecute(String result) {
         try {
            JSONObject json = new JSONObject(result);
            JSONArray results = json.getJSONArray("results");
            JSONArray address = results.getJSONObject(0).getJSONArray(
                  "address_components");

            for (int i = 0; i < address.length(); i++) {
               JSONArray mTypes = address.getJSONObject(i)
                     .getJSONArray("types");
               String type = mTypes.getString(0);
               if (type.equalsIgnoreCase("locality")) {
                  String cidade = address.getJSONObject(i).getString(
                        "long_name");
                  new HttpAsyncEventTask().execute(UriService.getURI(cidade));
                  break;
               }

            }
         } catch (JSONException e) {
            e.printStackTrace();
         }
      }
   }

   public static String GET(String url) {
      InputStream inputStream = null;
      String result = "";
      try {

         // create HttpClient
         HttpClient httpclient = new DefaultHttpClient();

         // make GET request to the given URL
         HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

         // receive response as inputStream
         inputStream = httpResponse.getEntity().getContent();

         // convert inputstream to string
         if (inputStream != null)
            result = convertInputStreamToString(inputStream);
         else
            result = "Did not work!";

      } catch (Exception e) {
         Log.d("InputStream", e.getLocalizedMessage());
      }
      return result;
   }

   private static String convertInputStreamToString(InputStream inputStream)
         throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
            inputStream));
      String line = "";
      String result = "";
      while ((line = bufferedReader.readLine()) != null)
         result += line;

      inputStream.close();
      return result;
   }

   private List<Event> getEvents(JSONObject json) throws JSONException {
      JSONArray array = json.getJSONArray("data");

      List<Event> events = new ArrayList<Event>();

      for (int i = 0; i < array.length(); i++) {
         JSONObject object = array.getJSONObject(i);
         if (object.has("venue")
               && object.getJSONObject("venue").has("latitude")) {
            Double latitude = ((JSONObject) object.get("venue"))
                  .getDouble(("latitude"));

            Double longitude = ((JSONObject) object.get("venue"))
                  .getDouble(("longitude"));

            Event event = new Event(object.getLong("id"),
                  object.getString("name"),
                  latitude, longitude);
            events.add(event);
         }
      }
      return events;
   }

   public static EventFacade getInstance() {
      if (instance == null) {
         instance = new EventFacade();
      }
      return instance;
   }

   public MarkerOptions eventToMark(Event event) {
      return new MarkerOptions().position(
            new LatLng(event.getLatitude(), event.getLongitude())).title(
            event.getName());
   }

}
