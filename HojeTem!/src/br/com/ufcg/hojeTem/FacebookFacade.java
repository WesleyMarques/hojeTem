package br.com.ufcg.hojeTem;

import android.util.Log;

import com.facebook.Response;
import com.facebook.Session;

public class FacebookFacade {

   FacebookQuery facebookQuery;
   private Session session;

   public FacebookFacade(Session session) {
      this.session = session;
      facebookQuery = new FacebookQuery(myHandler, session);
   }

   public void getAllEvents() {
      facebookQuery
            .getRequest("https://graph.facebook.com/search?type=location&center=37.76,-122.427&distance=1000");
      // search?q=campina grande&type=event&fields=id&limit=5
   }

   private final Handler myHandler = new Handler() {

      @Override
      public void handler(Response response) {
         // TODO Joga para o googleMaps o resultado como Map
         Log.i("TOKEN", session.getAccessToken());
         Log.i("Facebook response", "####" + response.getRawResponse());
         Log.i("Facebook response", "####"
               + response.getError().getErrorMessage());
      }

   };

   public abstract class Handler {

      public abstract void handler(final Response response);
   }

}
