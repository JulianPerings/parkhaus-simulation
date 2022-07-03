package de.hbrs.team13.parkhaus_team13;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Super Duper Parkhaus", value = "/BaseServlet")
public class BaseServlet extends CarParkServlet {
  @Override
  String name() {
    return "Super Duper Parkhaus";
  }

  @Override
  int max() {
    return 30;
  }

  int openingFrom(){
    return 0;
  }

  int openTo(){
    return 24;
  }

  int delay(){
    return 100;
  }

  int simulationSpeed(){
    return 5;
  }

  @Override
  String config() {
    return max()+","+openingFrom()+","+openTo()+","+delay()+","+simulationSpeed();
  }
}
