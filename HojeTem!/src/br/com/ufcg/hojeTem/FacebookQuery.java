package br.com.ufcg.hojeTem;

import br.com.ufcg.hojeTem.FacebookFacade.Handler;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Response;
import com.facebook.Session;

public class FacebookQuery {

   private Request request;
   private Callback callback;
   private Session session;

   public FacebookQuery(final Handler handler, Session session) {
      this.session = session;
      callback = new Request.Callback() {

         @Override
         public void onCompleted(Response response) {
            handler.handler(response);
         }
      };

   }

   public void getRequest(String query) {
      request = new Request(session, query, null, HttpMethod.GET, callback);
      request.executeAsync();
   }

}
