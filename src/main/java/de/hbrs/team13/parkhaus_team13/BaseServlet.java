package de.hbrs.team13.parkhaus_team13;

import javax.servlet.annotation.WebServlet;

@WebServlet(name ="Super Duper Parkhaus" ,value= "/BaseServlet")
public class BaseServlet extends CarParkServlet {
    @Override
    String NAME() {
        return "Super Duper Parkhaus";
    }
    @Override
    int MAX() {
        return 10;
    }

    @Override
    String config() {
        return null;
    }
}
