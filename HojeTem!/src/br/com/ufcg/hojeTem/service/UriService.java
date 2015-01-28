package br.com.ufcg.hojeTem.service;

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
}
