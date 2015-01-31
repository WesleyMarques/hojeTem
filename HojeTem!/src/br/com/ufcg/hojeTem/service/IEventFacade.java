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

   /**
    * 
    * @param city
    * @param map
    */
   public void markEventsByCity(String city, GoogleMap map);

   /**
    * 
    * @param map
    * @param latitude
    * @param longitude
    */
   public void markEventCurrentLocation(GoogleMap map, double latitude,
         double longitude);

   /**
    * 
    * @param id
    */
   public void getEvent(Long id);

   public void getUserEvents();

}
