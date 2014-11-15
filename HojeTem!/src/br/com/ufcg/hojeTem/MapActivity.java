package br.com.ufcg.hojeTem;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Projeto: Hoje Tem!
 * 
 * Activity principal para prover recursos e fornecer visualização do mapa de
 * eventos.
 * 
 */
public class MapActivity extends FragmentActivity {

   /** Nosso mapa do Google Maps */
   private GoogleMap eventsMap;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (isGooglePlayAvailable()) {
         setContentView(R.layout.activity_map);
         setAtualLocation();
      }
   }

   /**
    * Método para direcionar o mapa para o local atual.
    */
   private void setAtualLocation() {
      eventsMap = ((SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map)).getMap();

      eventsMap.setMyLocationEnabled(true);

      LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

      String provider = LocationManager.GPS_PROVIDER;

      Location location = locationManager
            .getLastKnownLocation(provider);

      // caso o GPS tenha falhado, pega da rede
      if (location == null) {
         provider = LocationManager.NETWORK_PROVIDER;
         location = locationManager
               .getLastKnownLocation(provider);
      }

      if (location != null) {
         onLocationChanged(location);
      }

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      this.getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      if (item.getItemId() == R.id.action_logout) {
         this.finish();
      }
      return super.onOptionsItemSelected(item);
   }

   /**
    * Método que acessa os serviços da Google Play e verifica se este serviço
    * está disponível.
    * 
    * @return se o serviço da Google Play está disponível.
    */
   private boolean isGooglePlayAvailable() {
      int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

      boolean available = status == ConnectionResult.SUCCESS;
      if (!available) {
         Toast.makeText(this, "Google Play not available", Toast.LENGTH_SHORT)
               .show();
      }
      return available;
   }

   /**
    * Cidade alterada, direcionando para o local.
    * 
    * @param location
    *           {@code Location} contendo dados da localização.
    */
   private void onLocationChanged(Location location) {
      double latitude = location.getLatitude();
      double longitude = location.getLongitude();

      LatLng latLng = new LatLng(latitude, longitude);

      eventsMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
      eventsMap.animateCamera(CameraUpdateFactory.zoomTo(15));
   }

}