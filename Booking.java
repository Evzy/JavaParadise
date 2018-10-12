import java.io.*;
import java.time.*;

public class Booking implements Serializable, Comparable<Booking> {
  
  private int bookingId;
  private int custId;
  private int roomNo;
  private String strStartDate;
  private LocalDate startDate;
  private LocalDate endDate;
  private int duration;
  private double cost;
  private boolean breakfastBoolean;
  private double breakfast = 34;
  private boolean isPaid;
  static int nextID = 10;
  public Booking () {
   bookingId = nextID++;
  }
  
  public Booking (int custId, int roomNo, String strStartDate, int duration, boolean breakfastBoolean) {
    this.custId = custId;
    this.roomNo = roomNo;
    startDate = LocalDate.parse(strStartDate);
    endDate = startDate.plusDays(duration);
    this.duration = duration;
    this.breakfastBoolean = breakfastBoolean;
    bookingId = nextID++;
  }
   public Booking (int custId, LocalDate startDate, int duration) {
    this.custId = custId;
    this.startDate = startDate;
    this.duration = duration;
    bookingId= nextID++;
  }
   
    public Booking (int custId,  int duration) {
    this.custId = custId;
    this.duration = duration;
    bookingId = nextID++;
  }
  public int getBookingId () {
    return bookingId;
  }
  
  public int getCustId () {
    return custId;
  }
  
  public LocalDate getDate () {
    return startDate;
  }
  
  public LocalDate getEndDate(){
    return endDate;
  }
  
  public int getDuration () {
    return duration;
  }
   
  public int getRoomNo () {
    return roomNo;
  }
  
  public boolean getBreakfastBoolean(){
    return breakfastBoolean;
  }
  
  public void setBreakfatBoolean(boolean breakfastBoolean){
    this.breakfastBoolean = breakfastBoolean;
  }
  
  public double getBreakfast(){
    return breakfast;
  } 
  
  public void setTotalCost(double cost){
    this.cost = cost;
  }
  public boolean getIsPaid(){
    return isPaid;
  }
  public void setDate (String dateStr) {
    startDate = LocalDate.parse(dateStr);
  }
  
  public void setBreakfast(double breakfast){
    this.breakfast = breakfast;
  }
  public void setDuration (int duration){ 
        this.duration = duration;
  }
  
 public void setRoomNo (int roomNo) {
    this.roomNo = roomNo;
  }
 public void setIsPaid(boolean isPaid){
   this.isPaid = isPaid;
 }
 public double getTotalCost(){
   return cost;
 }
  public String toString () {
    return "<html>" + "Booking ID: " + bookingId+ ", customer ID: " + custId + ", start date: " + startDate + ", end date: " + endDate + "<br /> \nRoom Number: " + roomNo + ", Duration: " + duration +
            ", \nBreakfast included: " +breakfastBoolean + ". Has Paid: " +isPaid + "</html>";
  }
  
  @Override
  public int compareTo(Booking b) {
    return getEndDate().compareTo(b.getEndDate());
  }
  
}