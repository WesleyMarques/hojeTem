package br.com.ufcg.hojeTem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * @author Júlio
 * 
 */
public class LoginActivity extends FragmentActivity {

   private MainFragment mainFragment;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      if (savedInstanceState == null) {
         // Add the fragment on initial activity setup
         mainFragment = new MainFragment();
         getSupportFragmentManager()
               .beginTransaction()
               .add(android.R.id.content, mainFragment)
               .commit();
      } else {
         // Or set the fragment from restored state info
         mainFragment = (MainFragment) getSupportFragmentManager()
               .findFragmentById(android.R.id.content);
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      if (item.getItemId() == R.id.action_search) {
         // TODO @author Júlio L. Remover isto, pois o mapa deve ser acessível
         // ao logar (direcionar direto)
         startActivity(new Intent(this, MapActivity.class));
      }
      return super.onOptionsItemSelected(item);
   }

}
