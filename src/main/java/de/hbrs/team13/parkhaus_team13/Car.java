package de.hbrs.team13.parkhaus_team13;

import java.security.SecureRandom;
import java.util.*;

/** Car has values String[] params and String[] priority */
public class Car implements CarIF {
  /**
   * storage medium for car number, time when the car entered the parking garage, duration car
   * parked, price paid by the customer , ticket as hash value, color of the car, value how much
   * space the vehicle needs, the clients' category, the vehicle type and the license
   */
  private String[] params;
  /**
   * array with parking lot priority details. Defines the order in which he will park on the
   * remaining parking lots
   */
  String[] priority;

  public Car(String[] params) {
    this.params = params;
    if (this.params != null) {
      sortOutPriority();
    }
  }
  /**
   * random generator for car constructor with start time and duration based on current time and
   * random nr between 0 and 1000
   */
  public Car() {
    this(getSecureRandomNumber(1000));
  }

  /**
   * random generator for car constructor with start time and duration based on current time
   *
   * @param nr sets the first param value
   */
  public Car(int nr) {
    long startzeit =
        System.currentTimeMillis() - ((long) getSecureRandomNumber(1000) * 60 * 60 * 24);
    long dauer = (getSecureRandomNumber(System.currentTimeMillis() - startzeit)) * 1000;
    StringBuilder hash = new StringBuilder();
    for (int i = 0; i < 32; i++) {
      hash.append(Integer.toHexString((getSecureRandomNumber(16))));
    }
    String[] randParams = new String[10];
    randParams[0] = "\"nr\": " + nr;
    randParams[1] = "\"timer\": " + startzeit;
    randParams[2] = "\"duration\": " + dauer;
    randParams[3] = "\"price\": " + (dauer / 10000L);
    randParams[4] = "\"hash\": \"" + hash + "\"";
    randParams[5] = "\"color\": \"#" + hash.substring(0, 6) + "\"";
    randParams[6] = "\"space\": " + getSecureRandomNumber(25);
    randParams[7] =
        "\"client_category\": \""
            + (new String[] {"FAMILY", "WOMEN", "ANY", "HANDICAPPED"}[getSecureRandomNumber(4)])
            + "\"";
    randParams[8] =
        "\"vehicle_type\": \""
            + (new String[] {"PKW", "SUV", "MOTORBIKE", "E_VEHICLE"}[getSecureRandomNumber(4)])
            + "\"";
    randParams[9] = "\"license\": \"SU-X " + (getSecureRandomNumber(100)) + "\"";
    this.params = randParams;
    sortOutPriority();
  }

  @Override
  public int nr() {
    int nr = 0;
    try (Scanner scan = new Scanner(params[0])) {
      nr = scan.useDelimiter("\\D+").nextInt();
    } catch (Exception e) {
      System.out.println("scanner can't scan nr from " + this);
    }
    return nr;
  }

  @Override
  public long begin() {
    long begin = 0;
    try (Scanner scan = new Scanner(params[1])) {
      begin = scan.useDelimiter("\\D+").nextLong();
    } catch (Exception e) {
      System.out.println("scanner can't scan begin from " + this);
    }
    return begin;
  }

  @Override
  public long end() {
    return this.begin() + this.getDuration();
  }

  /**
   * Duration how long the car parked in the parking garage in <big>seconds</big>
   *
   * @return int time in <big>seconds</big>
   */
  @Override
  public int getDuration() {

    int duration = 0;
    try (Scanner scan = new Scanner(params[2]); ) {
      duration = scan.useDelimiter("\\D+").nextInt();
      duration /= 1000;
    } catch (Exception e) {
      System.out.println("scanner can't scan duration from " + this);
    }
    return duration;
  }

  @Override
  public double getPrice() {

    double price = 0;
    try (Scanner scan = new Scanner(params[3])) {
      price = scan.useDelimiter("\\D+").nextInt();
      price /= 10000;
    } catch (Exception e) {
      System.out.println("scanner can't scan price from " + this);
    }
    return price;
  }

  @Override
  public String getTicket() {
    if (params != null && params[4] != null) {
      String s = params[4].split(":")[1];
      return s.substring(2, s.length() - 1);
    }
    return "";
  }

  @Override
  public String getColor() {
    if (params != null && params[5] != null) {
      String s = params[5].split(":")[1];
      return s.substring(2, s.length() - 1);
    }
    return "";
  }

  /**
   * generates a random number between 0 and maxnumber via the secure random byte generation method
   *
   * @param maxnumber maximum of the random number
   * @return random int between 0 and maxnumber
   */
  static int getSecureRandomNumber(int maxnumber) {
    if (maxnumber == 0) {
      return 0;
    }
    SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
    byte[] randbytes = new byte[2];
    random.nextBytes(randbytes);
    return Math.abs(randbytes[0] + randbytes[1]) % maxnumber;
  }
  /**
   * generates a random number between 0 and maxnumber via the secure random byte generation method
   *
   * @param maxnumber maximum of the random number
   * @return random int between 0 and maxnumber
   */
  static long getSecureRandomNumber(long maxnumber) {
    if (maxnumber == 0) {
      return 0;
    }
    SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
    byte[] bytes = new byte[6];
    random.nextBytes(bytes);
    return Math.abs(bytes[0] + bytes[1] + bytes[2] + bytes[3] + bytes[4] + bytes[5]) % maxnumber;
  }

  @Override
  public int getSpace() {

    int space = 0;
    try (Scanner scan = new Scanner(params[6]); ) {
      space = scan.useDelimiter("\\D+").nextInt();
    } catch (Exception e) {
      System.out.println("scanner can't scan space from " + this);
    }
    return space;
  }

  @Override
  public String getClientCategory() {
    if (params != null && params[7] != null) {
      String s = params[7].split(":")[1];
      return s.substring(2, s.length() - 1);
    }
    return "";
  }

  @Override
  public String getVehicleType() {
    if (params != null && params[8] != null) {
      String s = params[8].split(":")[1];
      return s.substring(2, s.length() - 1);
    }
    return "PKW";
  }

  @Override
  public String getLicense() {
    if (params != null && params[9] != null) {
      String s = params[9].split(":")[1];
      return s.substring(2, s.length() - 1);
    }
    return "";
  }
  /**
   * returns all car values saved in params[] as one string split with /
   *
   * @return string of all params[] values split with /
   */
  @Override
  public String export() {
    // Format: Nr, timer begin, duration, price, Ticket, color, space, client category, vehicle
    // type, license
    return ""
        + nr()
        + "/"
        + begin()
        + "/"
        + getDuration()*1000
        + "/"
        + getPrice()*100
        + "/"
        + getTicket()
        + "/"
        + getColor()
        + "/"
        + getClientCategory()
        + "/"
        + getVehicleType()
        + "/"
        + getLicense();
  }

  @Override
  public String toString() {
    return Arrays.toString(params);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car other = (Car) o;
    return this.getTicket().equals(other.getTicket());
  }
    //"vehicle_types":["PKW","SUV","MOTORBIKE","E_VEHICLE"],
    // "client_categories":["FAMILY","WOMEN","ANY","HANDICAPPED"]

    /**
     * calculates the array priority for "this." Car from the standard arrangment:
     * {1_"HANDICAPPED", 2_"MOTORBIKE", 3_"E_VEHICLE", 4_"WOMEN", 5_"FAMILY", 6_"SUV", 7_"PKW", 8_"ANY"}
     */
    @Override
    public void sortOutPriority(){
        if(params != null && params.length >= 8) {
            ArrayList<String> sortOrder = new ArrayList<>();
            // priority
            String[] s = new String[]{"HANDICAPPED", "MOTORBIKE", "E_VEHICLE", "WOMEN", "FAMILY", "SUV", "PKW", "ANY"};
            Collections.addAll(sortOrder, s);

            ArrayList<String> vSa = new ArrayList<>(); //vehicle sort Array
            String vHS = getVehicleType(); //vehicle help string
            addMissing(vSa, vHS, new String[]{"SUV", "PKW"}); //base case if nothing is there it can park on spots for SUV and PKW
            ArrayList<String> cSa = new ArrayList<>(); //client sort Array
            String cHS = getClientCategory();   //client help string
            addMissing(cSa, cHS, new String[]{"ANY"}); //base case if nothing is there it can park on spots for ANY
            priority = new String[vSa.size() + cSa.size()];
            int counterV = 0;
            int counterC = 0;
            for (int i = 0; i < priority.length; i++) { //fill the priority array
                if (counterV == vSa.size()) { //if all vehicle priorities are through
                    addRest(i, counterC, cSa);
                    break;
                }
                //compares the index of the next to be inserted parts on client and vehicle lists with each other
                //and inserts the lower one (basically the merge part of merge sort
                if (sortOrder.indexOf(vSa.get(counterV)) < sortOrder.indexOf(cSa.get(counterC))) {
                    priority[i] = vSa.get(counterV++);
                } else {
                    priority[i] = cSa.get(counterC++);
                }
            }
        }
    }

  @Override
  public String[] getPriority() {
    return priority;
  }

    /**
     * Adds the type of Client or vehicle and its base cases to an ArrayList
     * @param a helper Array that all the types are added to
     * @param type the specific type as the car params specify
     * @param missing the missing base cases
     */
    private void addMissing(ArrayList<String> a, String type, String[] missing){
        a.add(type);
        for(String s : missing) {
            if (!type.equals(s)) {
                a.add(s);
            }
        }
    }
    /**
     * Fills the rest of the priority Array with the missing priorites
     * @param i index of priority array empty spot
     * @param counter index for missing arrayList
     */
    private void addRest(int i,int counter, ArrayList<String> missing){
        while(i < priority.length){
            priority[i++] = missing.get(counter++);
        }
    }
    /**
     * @return params String[]
     */
    public String[] getParams() {
      return params;
    }

}
