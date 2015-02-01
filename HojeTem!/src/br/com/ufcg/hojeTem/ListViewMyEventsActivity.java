package br.com.ufcg.hojeTem;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.ufcg.hojeTem.model.Event;
import br.com.ufcg.hojeTem.service.EventFacade;

public class ListViewMyEventsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	List<Event> eventos = EventFacade.getInstance().getEventosDoUsuario();

	String[] values = new String[eventos.size()];
	int i = 0;
	for (Event evento : eventos) {
	    values[i++] = evento.getName();
	}

	this.setListAdapter(new ArrayAdapter<String>(this,
		R.layout.activity_list, R.id.label, values));

	ListView lv = getListView();

	// listening to single list item on click
	lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {

		// selected item
		String product = ((TextView) view).getText().toString();
		List<Event> eventos = EventFacade.getInstance()
			.getEventosDoUsuario();

		for (Event evento : eventos) {
		    if (evento.getName().equals(product)) {
			EventFacade.getInstance().getEvent(evento.getId());
			try {
			    new Thread().sleep(1000);
			} catch (InterruptedException e) {
			    // TODO
			    e.printStackTrace();
			}
			break;
		    }
		}

		// Launching new Activity on selecting single List Item
		Intent i = new Intent(getApplicationContext(),
			EventViewActivity.class);
		// sending data to new activity
		i.putExtra("product", product);

		startActivity(i);

	    }
	});
    }

}
