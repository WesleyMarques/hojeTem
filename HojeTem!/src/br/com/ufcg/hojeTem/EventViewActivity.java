package br.com.ufcg.hojeTem;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import br.com.ufcg.hojeTem.model.Event;
import br.com.ufcg.hojeTem.model.EventInfo;
import br.com.ufcg.hojeTem.service.EventFacade;

public class EventViewActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_event_view);

      Intent i = getIntent();
      // getting attached intent data
      String product = i.getStringExtra("product");

      List<Event> eventos = EventFacade.getInstance().getEventos();

      String[] values = new String[eventos.size()];
      Long id = 0L;
      for (Event evento : eventos) {
         if (evento.getName().equals(product)) {
            id = evento.getId();
            EventFacade.getInstance().getEvent(id);
            break;
         }
      }

      while (EventFacade.getInstance().getEventInfo() == null) {
         findViewById(R.id.loadingPanel).setVisibility(View.GONE);
      }
      findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
      TextView txtNomeEvento = (TextView) findViewById(R.id.nome_evento);
      TextView txtDescricao = (TextView) findViewById(R.id.descricao_evento);
      TextView txtPrivacidade = (TextView) findViewById(R.id.privacidade_evento);
      TextView txtLocation = (TextView) findViewById(R.id.location_evento);
      TextView txtLatitude = (TextView) findViewById(R.id.latitude_evento);
      TextView txtLongitude = (TextView) findViewById(R.id.longitude_evento);

      EventInfo eventInfo = EventFacade.getInstance().getEventInfo();
      txtNomeEvento.setText(eventInfo.getName());
      txtDescricao.setText(eventInfo.getDescription());
      txtPrivacidade.setText(eventInfo.getPrivacy());
      txtLocation.setText(eventInfo.getLocation());
      txtLatitude.setText(String.valueOf(eventInfo.getLatitude()));
      txtLongitude.setText(String.valueOf(eventInfo.getLongitude()));

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
