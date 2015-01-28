package br.com.ufcg.hojeTem;

import com.facebook.Response;
import com.facebook.Session;

public class FacebookFacade {

   FacebookQuery facebookQuery;
   private Session session;

   public FacebookFacade(Session session) {
      this.session = session;
      facebookQuery = new FacebookQuery(myHandler, session);
   }

   private final Handler myHandler = new Handler() {

      @Override
      public void handler(Response response) {
      }

   };

   public abstract class Handler {

      public abstract void handler(final Response response);
   }

}
