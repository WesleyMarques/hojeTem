package br.com.ufcg.hojeTem.service;

import android.util.Log;

import com.facebook.Session;

/**
 * Monta as URL's das requisições para se obter os eventos.
 * 
 * @author Júlio
 * 
 */
public class UriService {

   private static final String URL = "https://graph.facebook.com/v2.2/search?";

   private static final String TOKEN = "access_token=";

   public static String getURI(String city) {
      StringBuilder uriRequest = new StringBuilder();
      uriRequest.append(URL);
      uriRequest.append(TOKEN);
      uriRequest.append(Session.getActiveSession().getAccessToken());

      uriRequest.append(String.format(
            "&q=%s&type=event&fields=name,id,venue&limit=20", city));
      return uriRequest.toString();
   }

   public static String getURI(Long id) {
      StringBuilder uriRequest = new StringBuilder();
      uriRequest.append("https://graph.facebook.com/v2.2/");

      uriRequest.append(String.format(
            "%d", id));
      uriRequest.append("?" + TOKEN);
      uriRequest.append(Session.getActiveSession().getAccessToken());
      Log.e("URL", uriRequest.toString());
      return uriRequest.toString();
   }
}
