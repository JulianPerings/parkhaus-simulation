package de.hbrs.team13.parkhaus_team13;

import java.util.Arrays;
import java.util.Scanner;

public class InputAdapter implements InputAdapterIF {
  String[] params;
  public InputAdapter(String[] params) {
    this.params = params;
  }
  @Override
  public int getNr() {
    int nr = -1;
    try (Scanner scan = new Scanner(params[0].replace("\n", ""))) {
      nr = scan.useDelimiter("\\D+").nextInt();
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (nr == -1) {
      System.out.println("scanner can't scan nr from " + Arrays.toString(this.params));
    }
    return nr;
  }
  @Override
  public long getBegin() {

    long begin = -1;
    try (Scanner scan = new Scanner(params[1].replace("\n", ""))) {
      begin = scan.useDelimiter("\\D+").nextLong();
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (begin == -1) {
      System.out.println("scanner can't scan begin from " + Arrays.toString(this.params));
    }
    return begin;
  }
  @Override
  public int getDuration() {

    int duration = -1;
    try (Scanner scan = new Scanner(params[2].replace("\n", ""))) {
      duration = scan.useDelimiter("\\D+").nextInt();
      duration /= 1000;
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (duration == -1) {
      System.out.println("scanner can't scan duration from " + Arrays.toString(this.params));
    }
    return duration;
  }
  @Override
  public int getPrice() {

    int price = -1;
    try (Scanner scan = new Scanner(params[3].replace("\n", ""))) {
      price = scan.useDelimiter("\\D+").nextInt();
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (price == -1) {
      System.out.println("scanner can't scan price from " + Arrays.toString(this.params));
    }
    return price;
  }
  @Override
  public String getTicket() {

    String ticket = null;
    try (Scanner scan = new Scanner(params[4].replace("\n", ""))) {
      ticket = scan.findInLine("[\\da-f]{32}");
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (ticket == null) {
      System.out.println("scanner can't scan ticket from " + Arrays.toString(this.params));
    }
    return ticket;
  }
  @Override
  public String getColor() {

    String color = null;
    try (Scanner scan = new Scanner(params[5].replace("\n", ""))) {
      color = scan.useDelimiter("\\D+").findInLine("#[\\da-f]{6}");
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (color == null) {
      System.out.println("scanner can't scan price from " + Arrays.toString(this.params));
    }
    return color;
  }
  @Override
  public int getSpace() {
    int space = -1;
    try (Scanner scan = new Scanner(params[6].replace("\n", ""))) {
      space = scan.useDelimiter("\\D+").nextInt();
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (space == -1) {
      System.out.println("scanner can't scan space from " + Arrays.toString(this.params));
    }
    return space;
  }
  @Override
  public String getClientCategory() {

    String clientCategory = null;
    try (Scanner scan = new Scanner(params[7].replace("\n", ""))) {
      clientCategory = scan.useDelimiter("\\D+").findInLine("(FAMILY|WOMEN|ANY|HANDICAPPED)");
    } catch (Exception ingonred) {
      // Exception ignored on purpose
    }
    if (clientCategory == null) {
      System.out.println("scanner can't scan clientCategory from " + Arrays.toString(this.params));
    }
    return clientCategory;
  }
  @Override
  public String getVehicleType() {
    String vehicleType = null;
    try (Scanner scan = new Scanner(params[8].replace("\n", ""))) {
      vehicleType = scan.useDelimiter("\\D+").findInLine("(PKW|SUV|MOTORBIKE|E_VEHICLE)");
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (vehicleType == null) {
      System.out.println("scanner can't scan vehicleType from " + Arrays.toString(this.params));
    }
    return vehicleType;
  }
  @Override
  public String getLicense() {
    String license = null;
    try (Scanner scan = new Scanner(params[9].replace("\n", ""))) {
      license = scan.useDelimiter("\\D+").findInLine("SU-[A-Z] [\\d]{1,3}");
    } catch (Exception ignored) {
      // Exception ignored on purpose
    }
    if (license == null) {
      System.out.println("scanner can't scan license from " + Arrays.toString(this.params));
    }
    return license;
  }
  @Override
  public boolean isCorrect() {
    return (this.getNr() >= 0
        && this.getBegin() >= 0
        && this.getDuration() >= 0
        && this.getPrice() >= 0
        && this.getTicket() != null
        && this.getColor() != null
        && this.getClientCategory() != null
        && this.getVehicleType() != null
        && this.getLicense() != null);
  }
}
