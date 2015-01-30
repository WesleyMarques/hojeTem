package br.com.ufcg.hojeTem.model;

public class EventInfo extends Event {

   private String description;
   private String location;
   private String privacy;

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

}
