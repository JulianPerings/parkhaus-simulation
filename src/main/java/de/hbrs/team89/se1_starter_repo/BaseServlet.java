package de.hbrs.team89.se1_starter_repo;

import javax.servlet.annotation.WebServlet;

@WebServlet(name ="Super Duper Parkhaus" ,value= "/BaseServlet")
public class BaseServlet extends CarParkServlet {
    @Override
    String NAME() {
        return "Super Duper Parkhaus";
    }
    @Override
    int MAX() {
        return 0;
    }

    @Override
    String config() {
        return null;
    }
}
