package br.com.ufcg.hojeTem.service;

import com.google.android.gms.maps.GoogleMap;

/**
 * Interface contendo métodos para adicionar eventos de acordo com a filtragem.
 * Adiciona um conjunto de {@code Event} no mapa {@code GoogleMap}.
 * 
 * @author Júlio
 * 
 */
public interface IEventFacade {

   public void markEventsByCity(String city, GoogleMap map);

   public void markEventCurrentLocation(GoogleMap map, double latitude,
         double longitude);

   public void getEvent(Long id);

}
