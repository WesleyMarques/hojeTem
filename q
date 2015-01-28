[1mdiff --git a/HojeTem!/AndroidManifest.xml b/HojeTem!/AndroidManifest.xml[m
[1mindex e510ab5..d896a2a 100644[m
[1m--- a/HojeTem!/AndroidManifest.xml[m
[1m+++ b/HojeTem!/AndroidManifest.xml[m
[36m@@ -60,6 +60,11 @@[m
         <meta-data[m
             android:name="com.facebook.sdk.ApplicationId"[m
             android:value="@string/app_id" />[m
[32m+[m[41m[m
[32m+[m[32m        <activity[m[41m[m
[32m+[m[32m            android:name=".NavegationActivity"[m[41m[m
[32m+[m[32m            android:label="@string/title_activity_navegation" >[m[41m[m
[32m+[m[32m        </activity>[m[41m[m
     </application>[m
 [m
 </manifest>[m
\ No newline at end of file[m
[1mdiff --git a/HojeTem!/project.properties b/HojeTem!/project.properties[m
[1mindex 0f618e5..53e6d6c 100644[m
[1m--- a/HojeTem!/project.properties[m
[1m+++ b/HojeTem!/project.properties[m
[36m@@ -11,7 +11,8 @@[m
 #proguard.config=${sdk.dir}/tools/proguard/proguard-android.txt:proguard-project.txt[m
 [m
 # Project target.[m
[31m-target=android-20[m
[32m+[m[32mtarget=android-14[m
 android.library=false[m
[31m-android.library.reference.1=../../workspace/google-play-services_lib[m
[31m-android.library.reference.2=../../workspace/FacebookSDK[m
[32m+[m[32mandroid.library.reference.1=..\\..\\workspace\\google-play-services_lib[m
[32m+[m[32mandroid.library.reference.2=..\\..\\workspace\\FacebookSDK[m
[32m+[m[32mandroid.library.reference.3=../../workspace/appcompat_v7[m
[1mdiff --git a/HojeTem!/res/menu/main.xml b/HojeTem!/res/menu/main.xml[m
[1mindex 08620e3..0fb9c38 100644[m
[1m--- a/HojeTem!/res/menu/main.xml[m
[1m+++ b/HojeTem!/res/menu/main.xml[m
[36m@@ -4,10 +4,6 @@[m
     tools:context="br.com.ufcg.hojeTem.MapActivity" >[m
 [m
     <item[m
[31m-        android:id="@+id/action_search"[m
[31m-        android:icon="@drawable/ic_action_search"[m
[31m-        android:title="@string/action_search"/>[m
[31m-    <item[m
         android:id="@+id/action_logout"[m
         android:orderInCategory="100"[m
         android:title="@string/logout"/>[m
[1mdiff --git a/HojeTem!/res/values/strings.xml b/HojeTem!/res/values/strings.xml[m
[1mindex 765fca8..af3a650 100644[m
[1m--- a/HojeTem!/res/values/strings.xml[m
[1m+++ b/HojeTem!/res/values/strings.xml[m
[36m@@ -9,5 +9,4 @@[m
     <string name="hello_world">Hello world!</string>[m
     <string name="app_id">1500633920220188</string>[m
     <string name="title_activity_login">Hoje Tem!</string>[m
[31m-[m
 </resources>[m
[1mdiff --git a/HojeTem!/src/br/com/ufcg/hojeTem/Event.java b/HojeTem!/src/br/com/ufcg/hojeTem/Event.java[m
[1mdeleted file mode 100644[m
[1mindex 988280e..0000000[m
[1m--- a/HojeTem!/src/br/com/ufcg/hojeTem/Event.java[m
[1m+++ /dev/null[m
[36m@@ -1,58 +0,0 @@[m
[31m-package br.com.ufcg.hojeTem;[m
[31m-[m
[31m-/**[m
[31m- * Encapsula dados dos eventos do Facebook[m
[31m- */[m
[31m-public class Event {[m
[31m-[m
[31m-   private Long id;[m
[31m-   private String name;[m
[31m-   private double latitude;[m
[31m-   private double longitude;[m
[31m-[m
[31m-   public Event(long id, String name, double latitude, double longitude) {[m
[31m-      this.id = id;[m
[31m-      this.name = name;[m
[31m-      this.latitude = latitude;[m
[31m-      this.longitude = longitude;[m
[31m-   }[m
[31m-[m
[31m-   public Long getId() {[m
[31m-      return id;[m
[31m-   }[m
[31m-[m
[31m-   public void setId(Long id) {[m
[31m-      this.id = id;[m
[31m-   }[m
[31m-[m
[31m-   public String getName() {[m
[31m-      return name;[m
[31m-   }[m
[31m-[m
[31m-   public void setName(String name) {[m
[31m-      this.name = name;[m
[31m-   }[m
[31m-[m
[31m-   public double getLatitude() {[m
[31m-      return latitude;[m
[31m-   }[m
[31m-[m
[31m-   public void setLatitude(double latitude) {[m
[31m-      this.latitude = latitude;[m
[31m-   }[m
[31m-[m
[31m-   public double getLongitude() {[m
[31m-      return longitude;[m
[31m-   }[m
[31m-[m
[31m-   public void setLongitude(double longitude) {[m
[31m-      this.longitude = longitude;[m
[31m-   }[m
[31m-[m
[31m-   @Override[m
[31m-   public String toString() {[m
[31m-      return "Event [id=" + id + ", latitude=" + latitude + ", longitude="[m
[31m-            + longitude + "]";[m
[31m-   }[m
[31m-[m
[31m-}[m
[1mdiff --git a/HojeTem!/src/br/com/ufcg/hojeTem/FacebookFacade.java b/HojeTem!/src/br/com/ufcg/hojeTem/FacebookFacade.java[m
[1mindex b76635c..f001c51 100644[m
[1m--- a/HojeTem!/src/br/com/ufcg/hojeTem/FacebookFacade.java[m
[1m+++ b/HojeTem!/src/br/com/ufcg/hojeTem/FacebookFacade.java[m
[36m@@ -1,7 +1,5 @@[m
 package br.com.ufcg.hojeTem;[m
 [m
[31m-import android.util.Log;[m
[31m-[m
 import com.facebook.Response;[m
 import com.facebook.Session;[m
 [m
[36m@@ -15,21 +13,10 @@[m [mpublic class FacebookFacade {[m
       facebookQuery = new FacebookQuery(myHandler, session);[m
    }[m
 [m
[31m-   public void getAllEvents() {[m
[31m-      facebookQuery[m
[31m-            .getRequest("https://graph.facebook.com/search?type=location&center=37.76,-122.427&distance=1000");[m
[31m-      // search?q=campina grande&type=event&fields=id&limit=5[m
[31m-   }[m
[31m-[m
    private final Handler myHandler = new Handler() {[m
 [m
       @Override[m
       public void handler(Response response) {[m
[31m-         // TODO Joga para o googleMaps o resultado como Map[m
[31m-         Log.i("TOKEN", session.getAccessToken());[m
[31m-         Log.i("Facebook response", "####" + response.getRawResponse());[m
[31m-         Log.i("Facebook response", "####"[m
[31m-               + response.getError().getErrorMessage());[m
       }[m
 [m
    };[m
[1mdiff --git a/HojeTem!/src/br/com/ufcg/hojeTem/FacebookQuery.java b/HojeTem!/src/br/com/ufcg/hojeTem/FacebookQuery.java[m
[1mindex 5c59a00..b7b6b50 100644[m
[1m--- a/HojeTem!/src/br/com/ufcg/hojeTem/FacebookQuery.java[m
[1m+++ b/HojeTem!/src/br/com/ufcg/hojeTem/FacebookQuery.java[m
[36m@@ -16,6 +16,7 @@[m [mpublic class FacebookQuery {[m
 [m
    public FacebookQuery(final Handler handler, Session session) {[m
       this.session = session;[m
[32m+[m
       callback = new Request.Callback() {[m
 [m
          @Override[m
[1mdiff --git a/HojeTem!/src/br/com/ufcg/hojeTem/LoginActivity.java b/HojeTem!/src/br/com/ufcg/hojeTem/LoginActivity.java[m
[1mindex c4a137b..9e8769b 100644[m
[1m--- a/HojeTem!/src/br/com/ufcg/hojeTem/LoginActivity.java[m
[1m+++ b/HojeTem!/src/br/com/ufcg/hojeTem/LoginActivity.java[m
[36m@@ -1,11 +1,12 @@[m
 package br.com.ufcg.hojeTem;[m
 [m
[31m-import android.content.Intent;[m
 import android.os.Bundle;[m
 import android.support.v4.app.FragmentActivity;[m
 import android.view.Menu;[m
 import android.view.MenuItem;[m
 [m
[32m+[m[32mimport com.facebook.Session;[m
[32m+[m
 public class LoginActivity extends FragmentActivity {[m
 [m
    private MainFragment mainFragment;[m
[36m@@ -40,10 +41,8 @@[m [mpublic class LoginActivity extends FragmentActivity {[m
       // Handle action bar item clicks here. The action bar will[m
       // automatically handle clicks on the Home/Up button, so long[m
       // as you specify a parent activity in AndroidManifest.xml.[m
[31m-      if (item.getItemId() == R.id.action_search) {[m
[31m-         // TODO @author Júlio L. Remover isto, pois o mapa deve ser acessível[m
[31m-         // ao logar (direcionar direto)[m
[31m-         startActivity(new Intent(this, MapActivity.class));[m
[32m+[m[32m      if (item.getItemId() == R.id.action_logout) {[m
[32m+[m[32m         Session.getActiveSession().closeAndClearTokenInformation();[m
       }[m
       return super.onOptionsItemSelected(item);[m
    }[m
[1mdiff --git a/HojeTem!/src/br/com/ufcg/hojeTem/MainFragment.java b/HojeTem!/src/br/com/ufcg/hojeTem/MainFragment.java[m
[1mindex f8c6621..7f71153 100644[m
[1m--- a/HojeTem!/src/br/com/ufcg/hojeTem/MainFragment.java[m
[1m+++ b/HojeTem!/src/br/com/ufcg/hojeTem/MainFragment.java[m
[36m@@ -53,9 +53,9 @@[m [mpublic class MainFragment extends Fragment {[m
    private void onSessionStateChange(Session session, SessionState state,[m
          Exception exception) {[m
       if (state.isOpened()) {[m
[31m-         facebookFacade = new FacebookFacade(session);[m
[31m-         Log.i(TAG, "Logged in..." + (facebookFacade == null));[m
[31m-         facebookFacade.getAllEvents();[m
[32m+[m[32m         Intent intent = new Intent(this.getView().getContext(),[m
[32m+[m[32m               MapActivity.class);[m
[32m+[m[32m         startActivityForResult(intent, 0);[m
       } else if (state.isClosed()) {[m
          Log.i(TAG, "Logged out...");[m
       }[m
[1mdiff --git a/HojeTem!/src/br/com/ufcg/hojeTem/MapActivity.java b/HojeTem!/src/br/com/ufcg/hojeTem/MapActivity.java[m
[1mindex 7f867f3..de9c23b 100644[m
[1m--- a/HojeTem!/src/br/com/ufcg/hojeTem/MapActivity.java[m
[1m+++ b/HojeTem!/src/br/com/ufcg/hojeTem/MapActivity.java[m
[36m@@ -1,16 +1,13 @@[m
 package br.com.ufcg.hojeTem;[m
 [m
[31m-import java.util.ArrayList;[m
[31m-import java.util.List;[m
[31m-[m
 import android.location.Location;[m
 import android.location.LocationManager;[m
 import android.os.Bundle;[m
 import android.support.v4.app.FragmentActivity;[m
[31m-import android.util.Log;[m
 import android.view.Menu;[m
 import android.view.MenuItem;[m
 import android.widget.Toast;[m
[32m+[m[32mimport br.com.ufcg.hojeTem.service.EventFacade;[m
 [m
 import com.google.android.gms.common.ConnectionResult;[m
 import com.google.android.gms.common.GooglePlayServicesUtil;[m
[36m@@ -18,7 +15,6 @@[m [mimport com.google.android.gms.maps.CameraUpdateFactory;[m
 import com.google.android.gms.maps.GoogleMap;[m
 import com.google.android.gms.maps.SupportMapFragment;[m
 import com.google.android.gms.maps.model.LatLng;[m
[31m-import com.google.android.gms.maps.model.MarkerOptions;[m
 [m
 /**[m
  * Projeto: Hoje Tem![m
[36m@@ -35,6 +31,7 @@[m [mpublic class MapActivity extends FragmentActivity {[m
    @Override[m
    protected void onCreate(Bundle savedInstanceState) {[m
       super.onCreate(savedInstanceState);[m
[32m+[m
       if (isGooglePlayAvailable()) {[m
          setContentView(R.layout.activity_map);[m
          setAtualLocation();[m
[36m@@ -116,38 +113,8 @@[m [mpublic class MapActivity extends FragmentActivity {[m
       eventsMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));[m
       eventsMap.animateCamera(CameraUpdateFactory.zoomTo(15));[m
 [m
[31m-      for (Event event : getEvents()) {[m
[31m-         Log.i("HOJETem", event.toString());[m
[31m-         eventsMap.addMarker(eventToMark(event));[m
[31m-      }[m
[31m-[m
[31m-      Log.i("HOJETem", latitude + " " + longitude);[m
[31m-   }[m
[31m-[m
[31m-   public MarkerOptions eventToMark(Event event) {[m
[31m-      return new MarkerOptions().position([m
[31m-            new LatLng(event.getLatitude(), event.getLongitude())).title([m
[31m-            event.getName());[m
[32m+[m[32m      EventFacade.getInstance().markEventsByCity("campina grande",[m
[32m+[m[32m            this.eventsMap);[m
    }[m
 [m
[31m-   public List<Event> getEvents() {[m
[31m-      List<Event> eventosDefault = new ArrayList<Event>();[m
[31m-      eventosDefault.add(new Event(1l, "La Suissa", -7.2213647781047,[m
[31m-            -35.884464535062));[m
[31m-[m
[31m-      eventosDefault.add(new Event(663452713697240l,[m
[31m-            "Centro de Campina  Grande", -7.2209607361535, -35.886139637639));[m
[31m-[m
[31m-      eventosDefault.add(new Event(194881487223654l, "Mega Burguer",[m
[31m-            -7.21890247904, -35.8844112298));[m
[31m-      eventosDefault.add(new Event(133399323508231l, "Dental Líder",[m
[31m-            -7.22176378956, -35.884591916));[m
[31m-      eventosDefault.add(new Event(126193357458967l, "Comunidade Pio X",[m
[31m-            -7.22156657706, -35.8849709618));[m
[31m-[m
[31m-      eventosDefault.add(new Event(499563313415436l, "Degust Restaurante",[m
[31m-            -7.2204072118943, -35.884185004524));[m
[31m-[m
[31m-      return eventosDefault;[m
[31m-   }[m
 }[m
warning: LF will be replaced by CRLF in HojeTem!/project.properties.
The file will have its original line endings in your working directory.
