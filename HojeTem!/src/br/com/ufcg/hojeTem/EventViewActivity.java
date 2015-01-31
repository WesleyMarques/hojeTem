package br.com.ufcg.hojeTem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.ufcg.hojeTem.model.EventInfo;
import br.com.ufcg.hojeTem.service.EventFacade;

public class EventViewActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_event_view);

      Intent i = getIntent();
      // getting attached intent data
      TextView txtNomeEvento = (TextView) findViewById(R.id.nome_evento);
      TextView txtDescricao = (TextView) findViewById(R.id.descricao_evento);
      TextView txtPrivacidade = (TextView) findViewById(R.id.privacidade_evento);
      TextView txtLocation = (TextView) findViewById(R.id.location_evento);
      TextView txtCoordenada = (TextView) findViewById(R.id.coordenadas);

      EventInfo eventInfo = EventFacade.getInstance().getEventInfo();

      txtNomeEvento.setText("Nome do evento: " + eventInfo.getName());
      txtDescricao.setText("Descrição:" + eventInfo.getDescription());
      txtPrivacidade.setText("Nível de privacidade: " + eventInfo.getPrivacy());
      txtLocation.setText("Informação de localização: "
            + eventInfo.getLocation());

      String coordenadas = String.format("Latitude: %.2f Longitude: %.2f",
            eventInfo.getLatitude(), eventInfo.getLongitude());
      txtCoordenada.setText(coordenadas);
      // displaying selected product name
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      if (id == R.id.action_settings) {
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
}
