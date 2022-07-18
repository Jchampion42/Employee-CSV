package com.sparta.AlphaTeam.core;

import com.sparta.AlphaTeam.Controller.Controller;

public class SQLProgram {

    public void start(){
        Controller controller = new Controller();
        controller.greeting();
        controller.collectFileToUse();
        controller.convertFileToEmployee();
        controller.compareFileReading();
        controller.filterRecords();
        boolean quitProgram=false;
        while(!quitProgram){
                quitProgram=controller.userChoice();
        }

    }

}
