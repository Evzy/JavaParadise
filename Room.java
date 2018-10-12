
public class Room {
  
  private int roomNo;
  private String type;
  private double pricePerDay;
  private boolean availability = true;
  static int nextId = 1;
  
  public Room() {}
  
  public Room (String type, double pricePerDay, boolean availability) {
    this.type = type;
    this.pricePerDay = pricePerDay;
    this.availability = availability;
    roomNo = nextId++; 
  }
  public int getRoomNo () {
    return roomNo;
  }
  
  public String getType () {
    return type;
  }
  
  public double getPricePerDay () {
    return pricePerDay;
  }
  
  public boolean getAvailability () {
    return availability;
  }
   
  public void setType (String type) {
    this.type = type;
  }
  
  public void setPricePerDay (double pricePerDay) {
    this.pricePerDay = pricePerDay;
  }
  
  public void setAvailability (boolean availability) {
    this.availability = availability;
  }
  
  public String toString () {
    return "Room #: " + roomNo + ", type: " + type + ", price per day: " + pricePerDay + ", available?  " + availability;
  }
  
}