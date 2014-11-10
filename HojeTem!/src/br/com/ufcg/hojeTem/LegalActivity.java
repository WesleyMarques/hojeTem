package br.com.ufcg.hojeTem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LegalActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_legal);

      StringBuilder legalNotices = new StringBuilder().append("Dev Team:\n\n")
            .append("Erick de Azevedo Alcântara\n")
            .append("Fellype Cavalcante de Albuquerque\n")
            .append("Isabelly Santos Cavalcante\n")
            .append("Júlio Leitão de Melo Júnior\n")
            .append("Gustavo Nobuhiro Santos Yamaguchi\n")
            .append("Wesley Nunes Marques Torres\n\n\n")
            .append("Agradecimentos para:\n\n")
            .append("João Pedro Ferreira de Melo Leôncio");

      ((TextView) findViewById(R.id.legalTextId)).setText(legalNotices
            .toString());

   }
}
