package de.hbrs.team89.se1_starter_repo;

import javax.servlet.annotation.WebServlet;

@WebServlet(name ="Super Duper Parkhaus" ,value= "/BaseServlet")
public class BaseServlet extends ParkhausServlet {
    @Override
    String NAME() {
        return "Super Duper Parkhaus";
    }
    double sum = 0;
    double carCounter = 0;
    long sumDoration = 0;

    @Override
    int MAX() {
        return 0;
    }

    @Override
    String config() {
        return null;
    }
}
