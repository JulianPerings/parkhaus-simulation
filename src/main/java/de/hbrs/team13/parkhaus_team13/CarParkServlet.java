package de.hbrs.team13.parkhaus_team13;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/** common superclass for all servlets groups all auxiliary common methods used in all servlets */
public abstract class CarParkServlet extends HttpServlet {

  /* abstract methods, to be defined in subclasses */
  abstract String name(); // each ParkhausServlet should have a name, e.g. "Level1"

  abstract int max(); // maximum number of parking slots of a single parking level

  abstract String config(); // configuration of a single parking level

  static Statistics stats = new Statistics(); // Handles all our statistics
  static PriceCalc priceCalc = new PriceCalc(); // Handles price calculations
  static ParkingGarage garage =
      new ParkingGarage(20); // Stores vehicles currently parking in our garage
  static List<Consumer<UndoCommand>> undoList =
      new ArrayList<>(); // Undo-list to undo actions regarding entering and leaving
  static UndoCommand uComm = new UndoCommand(); // Handles undo-requests
  /** HTTP GET */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    String cmd = request.getParameter("cmd");
    System.out.println(cmd + " requested: " + request.getQueryString());
    String result = ServletGetLogic.response(cmd);
    if (result != null) {
      out.println(result);
    } else {
      System.out.println("Invalid Command: " + request.getQueryString());
    }
  }

  /** HTTP POST */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    String body = getBody(request);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    System.out.println(body);
      String[] params = body.split(",");
      String event = params[0];

      if(event.contains("licencePlate")){
        params = event.split("=");
        event = params[0];
      }
      String[] restParams = Arrays.copyOfRange(params, 1, params.length);
      if (event.equals("tomcat")) {
        out.println(
                getServletConfig().getServletContext().getServerInfo()
                        + getServletConfig().getServletContext().getMajorVersion()
                        + getServletConfig().getServletContext().getMinorVersion());
      }
    String result = ServletPostLogic.response(event, restParams);
    if (result != null) {
      out.println(result);
    } else {
      System.out.println(body);
    }
  }

  // auxiliary methods used in HTTP request processing

  /**
   * @param request the HTTP POST request
   * @return the body of the request
   */
  String getBody(HttpServletRequest request) throws IOException {

    StringBuilder stringBuilder = new StringBuilder();

    try (InputStream inputStream = request.getInputStream()) {
      if (inputStream != null) {
        try (BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(inputStream))) {
          char[] charBuffer = new char[128];
          int bytesRead;
          while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
            stringBuilder.append(charBuffer, 0, bytesRead);
          }
        }
      }
    }
    return stringBuilder.toString();
  }

  @Override
  public void destroy() {
    System.out.println("Servlet destroyed.");
  }
}
