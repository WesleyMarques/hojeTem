package br.com.ufcg.hojeTem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends FragmentActivity {

   private GoogleMap googleMap;
   private Intent legalActivity;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      if (isGooglePlayAvailable()) {
         setContentView(R.layout.activity_main);
         setupMap();
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      this.getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      if (item.getItemId() == R.id.action_legalNotices) {
         startActivity(this.getLegalActivity());
      } else if (item.getItemId() == R.id.action_logout) {
         this.finish();
      }

      return super.onOptionsItemSelected(item);
   }

   private Intent getLegalActivity() {
      if (legalActivity == null) {
         legalActivity = new Intent(this, LegalActivity.class);
      }
      return legalActivity;
   }

   private boolean isGooglePlayAvailable() {
      int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

      boolean available = status == ConnectionResult.SUCCESS;
      if (!available) {
         Toast.makeText(this, "Google Play not available", Toast.LENGTH_SHORT)
               .show();
      }

      return available;
   }

   private void setupMap() {
      if (googleMap == null) {
         googleMap = ((MapFragment) getFragmentManager().findFragmentById(
               R.id.map)).getMap();
      }
   }

}
