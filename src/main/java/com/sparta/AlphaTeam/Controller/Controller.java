package com.sparta.AlphaTeam.Controller;

import com.sparta.AlphaTeam.DataManagement.DataManager;
import com.sparta.AlphaTeam.UserInterface.UserManager;
import com.sparta.AlphaTeam.core.FileEnum;
import com.sparta.AlphaTeam.core.Timer;

public class Controller {
    UserManager userManager=new UserManager();
    DataManager dataManager=new DataManager();

    public void greeting(){
        userManager.intro();
    }
    public boolean userChoice(){
        boolean quitProgram=false;
        switch (userManager.userChoicePanel()){
            case 1: if (dataManager.getCleanRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("sorry, there are no clean records right now.");
            } else  displayCleanRecords();
            break;

            case 2: if (dataManager.getUnsortedRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("There are no unsorted records right now.");
            } else  displayUnsortedRecords();
            break;

            case 3: if (dataManager.getAllDirtyRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("There are no dirty records right now.");
            } else  displayAllDirtyRecords();
            break;

            case 4: if (dataManager.getInvalidDateRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("There are no records with invalid dates right now.");
            } else  displayInvalidDateRecords();
            break;

            case 5: if (dataManager.getMissingValueRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("There are no records with missing values right now.");
            } else  displayMissingValueRecords();
            break;

            case 6: if (dataManager.getDuplicatedRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("There are no duplicate records right now.");
            } else  displayDuplicateRecords();
            break;

            case 7: if (dataManager.getCleanRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("sorry, there are no clean records to push right now.");
            } else  addToDatabase();
            break;

            case 8: getRecords();
            break;

            case 9: if (dataManager.getFetchedRecords().size()==0 || dataManager.getCleanRecords()==null){
                System.out.println("no fetched records available, ensure that records have first been retrieved from the database");
            } else  displayFetchedRecords();
            break;

            case 10: collectFileToUse();
                convertFileToEmployee();
                filterRecords();
                break;

            case 11: getNumberOfThreads();
            generateThreads();
            break;

            case 12: compareFileReading();
            break;

            case 13: quitProgram=true;
            break;
        }
        return quitProgram;
    }

    public void collectFileToUse(){
        FileEnum temp =  userManager.getFileENumber();
        String filePath = temp.getFilePath();
        dataManager.setChosenFile(filePath);
    }
    public void getNumberOfThreads(){
        int threads = userManager.getUserThread();
        dataManager.setThreadCount(threads);
    }
    public void generateThreads(){
        dataManager.createThreads();
    }
    public void addToDatabase(){
        dataManager.addAllToDatabase();
    }

    public void convertFileToEmployee(){
        dataManager.convertStringListToEmployee(userManager.readFile(dataManager.getChosenFile().getPath()));
    }

    public void compareFileReading(){
        Timer timer = new Timer();
        timer.start();
        dataManager.convertStringListToEmployee(userManager.streamReadFile(dataManager.getChosenFile().getPath()));
        double timeOne = timer.stop()/1E6;
        timer.start();
        dataManager.convertStringListToEmployee(userManager.readFile(dataManager.getChosenFile().getPath()));
        double timeTwo = timer.stop()/1E6;
        System.out.println("\nUsing the scanner class, reading the file took: "+ Math.floor(timeOne * 1000) / 1000 + " milliseconds");
        System.out.println("Using lambdas and streams, reading the file took: " + Math.floor(timeTwo * 1000) / 1000 + " milliseconds");
        double difference = 0;
        if(timeOne<timeTwo){
            difference = timeTwo-timeOne;
        }else{
            difference = timeOne-timeTwo;
        }
        System.out.println("There was a "+ Math.floor(difference * 1000) / 1000 + " milliseconds difference between the 2 ways to read files\n");
    }

    public void filterRecords(){
        dataManager.sortUnsortedRecords();
    }
    public void getRecords(){
        dataManager.getEmployeeFromDatabase();
    }

    public void displayUnsortedRecords(){
        System.out.println("unsorted records____________");
        userManager.displayRecords(dataManager.getUnsortedRecords());
    }
    public void displayFetchedRecords(){
        System.out.println("records from database___________");
        userManager.displayRecords(dataManager.getFetchedRecords());
    }
    public void displayCleanRecords(){
        System.out.println("clean records___________");
        userManager.displayRecords(dataManager.getCleanRecords());
    }
    public void displayAllDirtyRecords(){
        System.out.println("dirty records___________");
        userManager.displayRecords(dataManager.getAllDirtyRecords());
    }
    public void displayInvalidDateRecords(){
        System.out.println("invalid data___________");
        userManager.displayRecords(dataManager.getInvalidDateRecords());
    }
    public void displayDuplicateRecords(){
        System.out.println("duplicate records___________");
        userManager.displayRecords(dataManager.getDuplicatedRecords());
    }
    public void displayMissingValueRecords(){
        System.out.println("records with missing values___________");
        userManager.displayRecords(dataManager.getCleanRecords());
    }


}
