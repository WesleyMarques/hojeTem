package br.com.ufcg.hojeTem.service;

import com.facebook.Session;

/**
 * Monta as URI's das requisições para se obter os dados da aplicação.
 * Construindo URI's para acesso das API's.
 * 
 */
public class UriService {

    private static final String FACEBOOK_API = "https://graph.facebook.com/v2.2/";
    private static final String TOKEN = "access_token=";

    /**
     * Build URI to take events from a city
     * 
     * @param city
     *            city name
     * @return URI. Format:
     *         https://graph.facebook.com/v2.2/search?access_token={user_token}
     *         &q={city}&type=event&fields=name,id,venue&limit=20
     */
    public static String getURI(String city) {
	StringBuilder uriRequest = new StringBuilder();
	uriRequest.append(FACEBOOK_API + "search?");
	uriRequest.append(getTokenValue());
	uriRequest.append(consultaEventosDaCidade(city));
	return uriRequest.toString();
    }

    private static String consultaEventosDaCidade(String cidade) {
	return String.format("&q=%s&type=event&fields=name,id,venue&limit=20",
		cidade);
    }

    /**
     * Build URI to get a specific event
     * 
     * @param id
     *            event id
     * @return URI to take the event.
     */
    public static String getURI(Long id) {
	StringBuilder uriRequest = new StringBuilder();
	String event = String.format("%d?", id);
	uriRequest.append(FACEBOOK_API + event);
	uriRequest.append(getTokenValue());
	return uriRequest.toString();
    }

    /**
     * Obtém URI de Geolocation do usuário.
     * 
     * @param latitude
     *            latitude do usuário
     * @param longitude
     *            longitude do usuário
     * @return URI com dados da posição do usuário
     */
    public static String getURI(double latitude, double longitude) {

	return String
		.format("http://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&sensor=true",
			latitude, longitude);
    }

    public static String getUserURI() {
	StringBuilder builder = new StringBuilder();
	builder.append(FACEBOOK_API);
	builder.append("me?fields=id&");
	builder.append(getTokenValue());
	return builder.toString();
    }

    public static String getUserEventsURI(Long userID) {
	StringBuilder builder = new StringBuilder();
	builder.append(FACEBOOK_API);
	builder.append(userID + "/events/attending");
	return builder.toString();
    }

    private static String getTokenValue() {
	StringBuilder builder = new StringBuilder();
	builder.append(TOKEN);
	builder.append(Session.getActiveSession().getAccessToken());
	return builder.toString();
    }

}
