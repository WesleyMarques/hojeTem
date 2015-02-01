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
import br.com.ufcg.hojeTem.model.EventInfo;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventFacade implements IEventFacade {

    private GoogleMap map;

    private List<Event> eventos;
    private List<Event> eventosDoUsuario;
    private EventInfo eventInfo;

    private static EventFacade instance;

    // singleton pattern
    private EventFacade() {
    }

    @Override
    public void markEventsByCity(String city, GoogleMap map) {
	this.map = map;
	String URI = UriService.getURI(city);
	new HttpAsyncEventTask().execute(URI);
    }

    @Override
    public void getEvent(Long id) {
	new HttpAsyncEventInfoTask().execute(UriService.getURI(id));
    }

    @Override
    public void markEventCurrentLocation(GoogleMap map, double latitude,
	    double longitude) {
	this.map = map;
	String URI = UriService.getURI(latitude, longitude);
	new HttpAsyncTask().execute(URI);
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
		setEventos(eventsRequest);
		for (Event event : eventsRequest) {
		    map.addMarker(eventToMark(event));
		}

	    } catch (JSONException e) {
		e.printStackTrace();
	    }
	}
    }

    private class HttpAsyncEventInfoTask extends
	    AsyncTask<String, Void, String> {
	@Override
	protected String doInBackground(String... urls) {
	    return GET(urls[0]);
	}

	protected void onPostExecute(String result) {
	    try {
		// TODO Solucionar
		JSONObject json;
		json = new JSONObject(result);
		setEventInfo(getEvent(json));
	    } catch (JSONException e) {
		e.printStackTrace();
	    }
	}

	private EventInfo getEvent(JSONObject json) {
	    try {
		Long id = json.getLong("id");
		String name = json.getString("name");
		JSONObject venue = json.getJSONObject("venue");
		Double latitude = venue.getDouble("latitude");
		Double longitude = venue.getDouble("longitude");
		String location = json.getString("location");

		String description = json.getString("description");

		String privacy = json.getString("privacy");

		EventInfo event = new EventInfo(id, name, latitude, longitude,
			description, location, privacy);
		return event;
	    } catch (JSONException e) {
		// TODO Solucionar
	    }
	    return null;
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
		    JSONArray mTypes = address.getJSONObject(i).getJSONArray(
			    "types");
		    String type = mTypes.getString(0);
		    if (type.equalsIgnoreCase("locality")) {
			String cidade = address.getJSONObject(i).getString(
				"long_name");
			new HttpAsyncEventTask().execute(UriService
				.getURI(cidade));
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
	BufferedReader bufferedReader = new BufferedReader(
		new InputStreamReader(inputStream));
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
			object.getString("name"), latitude, longitude);
		events.add(event);
	    }
	}
	return events;
    }

    private List<Event> getEventsDoUsuario(JSONObject json)
	    throws JSONException {
	JSONArray array = json.getJSONArray("data");
	List<Event> events = new ArrayList<Event>();

	for (int i = 0; i < array.length(); i++) {
	    JSONObject object = array.getJSONObject(i);
	    Event event = new Event(object.getLong("id"),
		    object.getString("name"), 0, 0);
	    events.add(event);
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

    public List<Event> getEventos() {
	if (eventos == null) {
	    eventos = new ArrayList<Event>();
	}
	return eventos;
    }

    public void setEventos(List<Event> eventos) {
	this.eventos = eventos;
    }

    public EventInfo getEventInfo() {
	return this.eventInfo;
    }

    public void setEventInfo(EventInfo eventInfo) {
	this.eventInfo = eventInfo;
    }

    @Override
    public void getUserEvents() {
	String URI = UriService.getUserURI();
	new HttpAsyncTaskGetUser().execute(URI);
    }

    public List<Event> getEventosDoUsuario() {
	if (eventosDoUsuario == null) {
	    eventosDoUsuario = new ArrayList<Event>();
	}
	return eventosDoUsuario;
    }

    public void setEventosDoUsuario(List<Event> eventosDoUsuario) {
	this.eventosDoUsuario = eventosDoUsuario;
    }

    private class HttpAsyncTaskGetUser extends AsyncTask<String, Void, String> {
	@Override
	protected String doInBackground(String... urls) {
	    return GET(urls[0]);
	}

	protected void onPostExecute(String result) {
	    try {
		JSONObject json = new JSONObject(result);
		Long userID = json.getLong("id");
		String URI = UriService.getUserEventsURI(userID);
		new HttpAsyncTaskGetUserEvents().execute(URI);
	    } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    private class HttpAsyncTaskGetUserEvents extends
	    AsyncTask<String, Void, String> {
	@Override
	protected String doInBackground(String... urls) {
	    return GET(urls[0]);
	}

	protected void onPostExecute(String result) {
	    try {
		JSONObject json = new JSONObject(result);
		List<Event> eventos = getEventsDoUsuario(json);
		setEventosDoUsuario(eventos);
	    } catch (JSONException e) {
		e.printStackTrace();
	    }
	}
    }

}
