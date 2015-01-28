package br.com.ufcg.hojeTem.model;

/**
 * Encapsula dados dos eventos do Facebook
 */
public class Event {

   private Long id;
   private String name;
   private double latitude;
   private double longitude;

   public Event(long id, String name, double latitude, double longitude) {
      this.id = id;
      this.name = name;
      this.latitude = latitude;
      this.longitude = longitude;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public double getLatitude() {
      return latitude;
   }

   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }

   public double getLongitude() {
      return longitude;
   }

   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }

   @Override
   public String toString() {
      return "Event [id=" + id + ", latitude=" + latitude + ", longitude="
            + longitude + "]";
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      long temp;
      temp = Double.doubleToLongBits(latitude);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(longitude);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Event other = (Event) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      if (Double.doubleToLongBits(latitude) != Double
            .doubleToLongBits(other.latitude))
         return false;
      if (Double.doubleToLongBits(longitude) != Double
            .doubleToLongBits(other.longitude))
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      return true;
   }

}
