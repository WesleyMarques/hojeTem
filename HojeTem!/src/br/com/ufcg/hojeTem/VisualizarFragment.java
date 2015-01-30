package br.com.ufcg.hojeTem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VisualizarFragment extends Fragment {

   public VisualizarFragment() {
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {

      View rootView = inflater
            .inflate(R.layout.fragment_visualizar, container, false);

      return rootView;
   }
}