import java.io.*;
import java.util.*;
import java.time.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;


public class JavaParadiseGUI extends JFrame implements ActionListener, ListSelectionListener{
  private FileInputStream fis;
  private ObjectInputStream ois;
  private FileOutputStream fos;
  private ObjectOutputStream oos;
  
  private ArrayList<Room> rooms = new ArrayList<Room>();
  Room[] roomsArray = {new Room("Single", 130, false), new Room("Double",240, false), new Room("Single",120, false),new Room("Double", 260, true), new Room("Double",240, true), new Room("Deluxe",320, true), new Room("Single",170, true), new Room("Single",110, true), new Room("Double",265, true), new Room("Deluxe",380, true)};
  DefaultListModel<Room> roomsModel = new DefaultListModel<Room>();
  JList <Room> roomsList = new JList<Room>(roomsModel);
  
  Customer[] customersArray = {new Customer("Natasha"), new Customer("Jeff"), new Customer("Sam")};
  private ArrayList<Customer> customers = new ArrayList<Customer>();
  DefaultListModel<Customer> customersModel = new DefaultListModel<Customer>();
  JList <Customer> customersList = new JList<Customer>(customersModel);   
  
  Booking[] bookingsArray = {new Booking(100,1, "2016-10-16" ,3, true), new Booking(101,2,"2017-08-16",6, false), new Booking(102,3,"2018-09-15",2, true)};
  private ArrayList<Booking> bookings = new ArrayList<Booking>() ;
  DefaultListModel<Booking> bookingsModel = new DefaultListModel<Booking>();
  JList <Booking> bookingsList = new JList<Booking>(bookingsModel); 
  
  JTabbedPane tabs = new JTabbedPane();
  //tabs
  JPanel roomsTab = new JPanel();
  JPanel customersTab = new JPanel();
  JPanel bookingsTab = new JPanel();
  //contaiiinersss
  JPanel inputRPanel = new JPanel(), inputCPanel = new JPanel(), inputBPanel = new JPanel();
  JPanel displayCPanel = new JPanel(), displayBPanel = new JPanel();
  JPanel buttonRPanel = new JPanel(), buttonCPanel = new JPanel(),buttonBPanel = new JPanel();
  JPanel textRPanel = new JPanel(), textCPanel = new JPanel(), textBPanel = new JPanel();
  //labels
  
  JLabel icon = new JLabel();
  JLabel cNameLbl = new JLabel("Name:");
  JLabel cSearchLbl = new JLabel("Search by customer ID:");
  JLabel cDetailsLbl = new JLabel("First, please select a customer");
  JLabel cCheckoutDetailsLbl = new JLabel("");
  
  JLabel bStartDateLbl = new JLabel("Start Date: (Format YYYY-MM-DD)");
  JLabel bDurationLbl = new JLabel("Duration");
  JLabel bBreakfastLbl = new JLabel("Breakfast Included?");
  JLabel bStatusLbl = new JLabel("Booking Status: ");
  JLabel bSuccessLbl = new JLabel("Add a booking first");
  
  
  //text fields
  
  JTextField cNameTextField = new JTextField(20);
  JTextField cSearchTextField = new JTextField(20);
  
  JTextField bStartDateTextField = new JTextField(10);
  JTextField bDurationTextField = new JTextField(10);
  
  //buttons
  JButton roomSingleButton = new JButton("Single");
  JButton roomDoubleButton = new JButton("Double");
  JButton roomDeluxeButton = new JButton("Deluxe");
  JButton roomClearSelectionButton = new JButton("Clear Selection");
  
  JButton nameAddButton = new JButton("Add");
  JButton searchButton = new JButton("Search");
  JButton checkoutButton = new JButton("Checkout");
  JButton customerClearSelectionButton = new JButton("Clear Selection");
  
  JButton bSortButton = new JButton ("Sort");
  JButton bSaveButton = new JButton ("Save");
  JButton bReadButton = new JButton ("Read");
  JButton bAddButton = new JButton("Add");
  
  //check box
  
  JCheckBox breakfastCheckBox = new JCheckBox("Yes");
   
  public JavaParadiseGUI() {
    //tabbed pane added
    add(tabs);
    //tabs are created 
    tabs.addTab("Rooms", roomsTab);
    tabs.addTab("Customers", customersTab);
    tabs.addTab("Bookings", bookingsTab);
    
    // room panel setup//
    inputRPanel.setLayout(new GridLayout(1,3,15,15));
    inputRPanel.setBorder(new TitledBorder("Show first avaliable room of type:"));
    inputRPanel.add(roomSingleButton);
    inputRPanel.add(roomDoubleButton);
    inputRPanel.add(roomDeluxeButton);
    textRPanel.add(icon);
    buttonRPanel.add(roomClearSelectionButton);
    
    JScrollPane scrollRooms = new JScrollPane(roomsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollRooms.setBorder(new TitledBorder("List of rooms"));
    
    //customer panel setup
    
    inputCPanel.setLayout(new GridLayout(2,3,10,10));
    EmptyBorder ebC = new EmptyBorder(20,50,20,50);
    inputCPanel.setBorder(ebC);
    inputCPanel.add(cNameLbl);
    inputCPanel.add(cNameTextField);
    inputCPanel.add(nameAddButton);
    inputCPanel.add(cSearchLbl);
    inputCPanel.add(cSearchTextField);
    inputCPanel.add(searchButton);
    textCPanel.setLayout(new GridLayout(2,1,10,10));
    textCPanel.setBorder(new TitledBorder("Customer Booking Details"));
    textCPanel.add(cDetailsLbl);
    textCPanel.add(cCheckoutDetailsLbl);
    buttonCPanel.setLayout(new GridLayout(1,2,50,50));
    buttonCPanel.add(customerClearSelectionButton);
    buttonCPanel.add(checkoutButton);
    JScrollPane scrollCustomers = new JScrollPane(customersList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollCustomers.setBorder(new TitledBorder("List of customers"));
    
    //booking panel setup
    
    inputBPanel.setLayout(new GridLayout(4,2,10,10));
    EmptyBorder ebB = new EmptyBorder(20,20,20,325);
    inputBPanel.setBorder(ebB);
    inputBPanel.add(bStartDateLbl);
    inputBPanel.add(bStartDateTextField);
    inputBPanel.add(bDurationLbl);
    inputBPanel.add(bDurationTextField);    
    inputBPanel.add(bBreakfastLbl);  
    inputBPanel.add(breakfastCheckBox);
    inputBPanel.add(bStatusLbl);
    inputBPanel.add(bSuccessLbl);
    JScrollPane scrollBookings = new JScrollPane(bookingsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollBookings.setBorder(new TitledBorder("List of bookings"));
    buttonBPanel.setLayout(new GridLayout(1,4,10,10));
    buttonBPanel.add(bAddButton);
    buttonBPanel.add(bSortButton);
    buttonBPanel.add(bSaveButton);
    buttonBPanel.add(bReadButton);
    
    
    //add panels
    roomsTab.setLayout(new BorderLayout());
    roomsTab.add(inputRPanel, BorderLayout.NORTH);
    roomsTab.add(scrollRooms, BorderLayout.CENTER);
    roomsTab.add(textRPanel, BorderLayout.EAST);
    roomsTab.add(buttonRPanel, BorderLayout.SOUTH);
    
    customersTab.setLayout(new BorderLayout());
    customersTab.add(inputCPanel, BorderLayout.NORTH);
    customersTab.add(scrollCustomers, BorderLayout.CENTER);
    customersTab.add(textCPanel, BorderLayout.EAST);
    customersTab.add(buttonCPanel, BorderLayout.SOUTH);
    
    bookingsTab.setLayout(new BorderLayout());
    bookingsTab.add(inputBPanel, BorderLayout.NORTH);
    bookingsTab.add(scrollBookings, BorderLayout.CENTER);
    bookingsTab.add(buttonBPanel, BorderLayout.SOUTH);
    
    //list selection listener
    roomsList.addListSelectionListener(this);
    customersList.addListSelectionListener(this);
    
    //button action listeners
    
    roomSingleButton.addActionListener(this);
    roomDoubleButton.addActionListener(this);
    roomDeluxeButton.addActionListener(this);
    roomClearSelectionButton.addActionListener(this);
    
    nameAddButton.addActionListener(this);
    searchButton.addActionListener(this);
    checkoutButton.addActionListener(this);
    customerClearSelectionButton.addActionListener(this);
    
    bAddButton.addActionListener(this); 
    bSaveButton.addActionListener(this);
    bReadButton.addActionListener(this);
    bSortButton.addActionListener(this);
  }


  public static void main (String[] args) {
    JavaParadiseGUI GUI = new JavaParadiseGUI();
    GUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
    GUI.setSize(750,600);
    GUI.setTitle("Java Paradise");
    GUI.setLocationRelativeTo(null);
    GUI.setVisible(true);
    GUI.populateLists();    
  }
  
  public void populateLists(){
   for(int i = 0; i < roomsArray.length; i++){
     rooms.add(roomsArray[i]);
     roomsModel.addElement(roomsArray[i]);
   }
    for(int k = 0; k < customersArray.length; k++){
     customers.add(customersArray[k]);
     customersModel.addElement(customersArray[k]);
  }
    for(int l = 0; l < bookingsArray.length; l++){
     bookings.add(bookingsArray[l]);
     bookingsModel.addElement(bookingsArray[l]);
  }
 }
  
  public void actionPerformed(ActionEvent e){
    
    if(e.getSource() == roomSingleButton){
     roomsList.clearSelection();
     displaySingleRooms();
    }
    
    if(e.getSource() == roomDoubleButton){
     roomsList.clearSelection();
     displayDoubleRooms();
    }
    
    if(e.getSource() == roomDeluxeButton){
     roomsList.clearSelection();
     displayDeluxeRooms();
    }
    
    if(e.getSource() == roomClearSelectionButton){
     roomsList.clearSelection();
    }
    
    if(e.getSource() == customerClearSelectionButton){
     customersList.clearSelection();
     cDetailsLbl.setText("First, please select a customer");
     cCheckoutDetailsLbl.setText("");
    }
    
    if(e.getSource() == nameAddButton){
     addCustomer();
    }
    if(e.getSource() == searchButton){
     searchCustomerById();
    }
    
    if(e.getSource() == bAddButton){
     addBooking();
    }
    
    if(e.getSource() == bSaveButton){
     saveBookings();
    }
    
    if(e.getSource() == bReadButton){
     readBookings();
      
    }
    
    if(e.getSource() == bSortButton){
     sortBookings();      
    }
    
    if(e.getSource() == checkoutButton){
     int index = customersList.getSelectedIndex();
      if(index >=0){
        
        Customer c = customers.get(index);
        Booking b = searchBookingsByCustId(c.getCustId());
        if(b!=null){
          int bIndex = bookings.indexOf(b);
          checkOut(bIndex);
          int roomNo = b.getRoomNo();
        
          for (Room ro: rooms) {
            if (ro.getRoomNo() == roomNo){
              ro.setAvailability(true);
            }
          } 
         }
       }
    }
   }
  
  public void valueChanged(ListSelectionEvent e){
    
    if(e.getSource() == roomsList){
    int index = roomsList.getSelectedIndex();
    icon.setIcon(new ImageIcon("images/"+Integer.toString(index)+".jpg"));
    icon.setText(null);
    }
    
    if(e.getSource() == customersList){
      int index = customersList.getSelectedIndex();
       if(index >=0){
        Customer c = customers.get(index);
        int custId = c.getCustId();
        Booking b = searchBookingsByCustId(custId);
        if (b!=null){
        
        int duration = b.getDuration();
        int roomNo = b.getRoomNo();
        Room r = searchRoomsByRoomNo(roomNo);
        double totalCost = duration * r.getPricePerDay();
        if(b.getBreakfastBoolean()==true){
           totalCost +=b.getBreakfast()*duration;
        }
        b.setTotalCost(totalCost);
        cDetailsLbl.setText(""+b);
        cCheckoutDetailsLbl.setText("Total Cost for this booking: " +b.getTotalCost() +" Dollars.");          
        }
        else
        {cDetailsLbl.setText("Customer has no unpaid bookings");
         cCheckoutDetailsLbl.setText("");
        }
    }
   }
 }
  
   public void displaySingleRooms() {
      for (Room r:rooms) {
        if (r.getType() == "Single" & r.getAvailability() == true){
         int index = rooms.indexOf(r);
         roomsList.setSelectedIndex(index);
         break;
        }
        else icon.setText("There is no room of this type currently available");
    }
  }
   
   public void displayDoubleRooms() {
      for (Room r:rooms) {
        if (r.getType() == "Double" & r.getAvailability() == true){
         int index = rooms.indexOf(r);
         roomsList.setSelectedIndex(index);
         break;
        }
        else icon.setText("There is no room of this type currently available");
    }
  }
   
   public void displayDeluxeRooms() {
      for (Room r:rooms) {
        if (r.getType() == "Deluxe" & r.getAvailability() == true){
         int index = rooms.indexOf(r);
         roomsList.setSelectedIndex(index);
         break;
        }
        else icon.setText("There is no room of this type currently available");
    }
  }
   
  public void addCustomer() {
    String name = cNameTextField.getText();
    System.out.println(""+name);
    Customer c = new Customer(name);
    customers.add(c);
    customersModel.addElement(c);
  }
  
  public void searchCustomerById() {
    String strCustId = cSearchTextField.getText();
    int custId = Integer.parseInt(strCustId);
    
    for (Customer c:customers) {
      if (c.getCustId() == custId){
      int index = customers.indexOf(c);
      customersList.setSelectedIndex(index);
      }      
    }
  }
  public void addBooking() {
    int rIndex = roomsList.getSelectedIndex();
    Room r = rooms.get(rIndex);
    int roomNo = r.getRoomNo();
    int cIndex = customersList.getSelectedIndex();
    Customer c = customers.get(cIndex);
    int custId = c.getCustId();
    
    String startDate = bStartDateTextField.getText();    
    String strDur = bDurationTextField.getText();
    int dur = Integer.parseInt(strDur);
    
    boolean breakfastBoolean = breakfastCheckBox.isSelected();
    
    if (r.getAvailability()){
      Booking booking = new Booking(custId, roomNo, startDate, dur, breakfastBoolean);
      bookings.add(booking);
      bookingsModel.addElement(booking);
      r.setAvailability(false);
      roomsModel.setElementAt(r,rIndex);
      
    }
    else {
      bSuccessLbl.setText("Room is unavaliable");      
    }
   } 
  
  public void sortBookings(){
    
    Collections.sort(bookings);
    bookingsModel.removeAllElements();
    for (Booking b: bookings) {
      bookingsModel.addElement(b);
     }
 
  }
  
  public Booking searchBookingsByCustId(int custId) {
      for (Booking b: bookings) {
      if (b.getCustId() == custId & b.getIsPaid()== false)
       return b;
     }
    return null;
  }
  public Room searchRoomsByRoomNo(int roomNo){
    for(Room r: rooms){
      if(r.getRoomNo() == roomNo)
        return r;
    }
    return null;
   }
  
  public void checkOut(int index){
    Booking b = bookings.get(index);
    b.setIsPaid(true);
    cDetailsLbl.setText(""+b);    
    bookingsModel.removeAllElements();
    for (Booking bo: bookings) {
      bookingsModel.addElement(bo);
     }
    
  }
  
    
  public void saveBookings() {
    try {
      fos = new FileOutputStream("bookings.dat");
      oos = new ObjectOutputStream(fos);
      for (Booking b:bookings) {
        oos.writeObject(b);
      }
      fos.close();
      oos.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  public void readBookings() {
   bookings.clear();
   bookingsModel.removeAllElements();
    try {
      fis = new FileInputStream("bookings.dat");
      ois = new ObjectInputStream(fis);
      
      while (true) {
        try {
          Object object = ois.readObject();
          Booking b = (Booking)object;
          //update room status
          int roomNo = b.getRoomNo();
          Room r = searchRoomsByRoomNo(roomNo);
          r.setAvailability(false);
          //add to array list
          bookings.add(b);
          bookingsModel.addElement(b);
        } catch (EOFException eof) {
          fis.close();
          ois.close();
          break;
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }
  
}