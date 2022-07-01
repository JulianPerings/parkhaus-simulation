package de.hbrs.team13.parkhaus_team13.singleton;

import java.util.HashMap;
import java.util.Map;

public class VehicleTypesMultiton {
  private VehicleTypesMultiton() {
    throw new IllegalStateException("Utility class");
  }

  private static final Map<String, VehicleTypes> types = new HashMap<>();

  public static VehicleTypes getInstance(String s) {
    if (types.containsKey(s)) {
      return types.get(s);
    } else {
      switch (s) {
        case "PKW":
          types.put(s, VehicleTypesPkw.getInstance());
          break;
        case "SUV":
          types.put(s, VehicleTypesSUV.getInstance());
          break;
        case "MOTORBIKE":
          types.put(s, VehicleTypesMotorbike.getInstance());
          break;
        case "E_VEHICLE":
          types.put(s, VehicleTypesEVehicle.getInstance());
          break;
        default:
          System.out.println("MULTITON ERROR");
      }
    }
    return types.get(s);
  }
}
