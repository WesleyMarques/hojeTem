package br.com.ufcg.hojeTem.model;

/**
 * Evento com dados específicos para visualização
 */
public class EventInfo extends Event {

   private String description;
   private String location;
   private String privacy;

   /**
    * 
    * @param id
    *           identificador do evento
    * @param name
    *           nome do evento
    * @param latitude
    *           latitude do evento
    * @param longitude
    *           longitude do evento
    * @param description
    *           descrição do evento
    * @param location
    *           informação sobre a localização do evento
    * @param privacy
    *           privacidade do evento
    */
   public EventInfo(long id, String name, double latitude, double longitude,
         String description, String location, String privacy) {
      super(id, name, latitude, longitude);
      this.description = description;
      this.location = location;
      this.privacy = privacy;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public String getPrivacy() {
      return this.privacy;
   }

   public void setPrivacy(String privacy) {
      this.privacy = privacy;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result
            + ((description == null) ? 0 : description.hashCode());
      result = prime * result + ((location == null) ? 0 : location.hashCode());
      result = prime * result + ((privacy == null) ? 0 : privacy.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      EventInfo other = (EventInfo) obj;
      if (description == null) {
         if (other.description != null)
            return false;
      } else if (!description.equals(other.description))
         return false;
      if (location == null) {
         if (other.location != null)
            return false;
      } else if (!location.equals(other.location))
         return false;
      if (privacy == null) {
         if (other.privacy != null)
            return false;
      } else if (!privacy.equals(other.privacy))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return "EventInfo [description=" + description + ", location=" + location
            + ", privacy=" + privacy + "]";
   }

}
