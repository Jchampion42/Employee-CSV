package com.sparta.AlphaTeam.UserInterface;

import com.sparta.AlphaTeam.DataManagement.Employee;
import com.sparta.AlphaTeam.UserInterface.Reader.Reader;
import com.sparta.AlphaTeam.core.FileEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Scanner;



public class UserManager {
    private static final Logger LOG = LogManager.getLogger(UserManager.class);

    public void intro(){
        prompt("Welcome to the file management and storage application!\n\nFirst, you will need to select a file");
    }
    public int userChoicePanel(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        prompt("your operation was successful!");

        return ChooseTools.actionChoice();
    }

    public void displayRecords(List<Employee> listToRead){
        for (Employee e : listToRead){
            String test = String.format("%-7s%-6s%-14s%-5s%-15s%-3s%-35s%-35s%-35s %-10s", e.getId(), e.getPrefix(), e.getfName(), e.getmName(), e.getlName(),e.getGender(),e.getEmail(),e.getDateOfBirth(),e.getJoinDate(),e.getSalary());
            System.out.println(test);
        }
    }
    public List<String> readFile(String filePath){
        return Reader.readFile(filePath);
    }
    public List<String> streamReadFile(String filePath){
        return Reader.streamReadFile(filePath);
    }

    public void prompt(String string){
        System.out.println(string);
    }
    public int getUserThread(){
        prompt("How many threads would you like to use?");
        return ChooseTools.getUserArrayLength();
    }

    public FileEnum getFileENumber(){
        FileEnum fileEnum;
        prompt("Please select a file to use by typing its number:");
        for (FileEnum s : FileEnum.values()){ // prints a list to the user of all enums in the list representing the different sort methods
            System.out.println(s.getEnumKey()+ ". " +s.getFileName());
        }

        fileEnum= ChooseTools.getUserEnum(); // selection module to return an enum for storage related to the list available
        return  fileEnum;
    }
    private static class ChooseTools{
        public static int actionChoice(){
            boolean userChoosing=true;
            Scanner scanner = new Scanner(System.in);
            int userValue=0;
            while (userChoosing) {
                System.out.println("You may choose a new operation by typing its' number: \n" +
                        "RECORDS______________\n"+
                        "1. View clean records \n" +
                        "2. View unsorted records\n" +
                        "3. View all dirty records\n" +
                        "4. View records with invalid date\n" +
                        "5. View records with missing fields\n" +
                        "6. View duplicate records\n" +
                        "DATABASE___________________\n" +
                        "7. Push clean records to the database\n" +
                        "8. Retrieve records from database\n" +
                        "9. View records received from database\n" +
                        "SELECTION / UTILITY_______________\n" +
                        "10. Choose a new file\n" +
                        "11. Choose thread count\n" +
                        "12. Compare the efficiency of lambdas and streams to a scanner\n" +
                        "13. Quit");
                userValue= StringConverter.stringToInt(scanner.next()); // uses converter to only return a positive int, and loop refuses a null array.
                if (userValue>=1 && userValue<=13){
                    userChoosing=false;
                }
            }
            return  userValue;
        }
        public static int getUserArrayLength(){
            boolean userChoosing=true;
            Scanner scanner = new Scanner(System.in);
            int userValue=1;
            while (userChoosing) {
                System.out.println("Please input an integer of value 1 or greater");
                userValue= StringConverter.stringToInt(scanner.next()); // uses converter to only return a positive int, and loop refuses a null array.
                if (userValue != 0){
                    userChoosing=false;
                }
            }
            return  userValue;
        }
        public static FileEnum getUserEnum(){
            Scanner scanner = new Scanner(System.in);
            FileEnum fileEnum=FileEnum.EMPLOYEERECORDS1;
            boolean userChoosing = true;
            String userInput;
            int userInputAsInt;

            while (userChoosing) {
                userInput = scanner.next();
                userInputAsInt= StringConverter.stringToInt(userInput); // personalised string-to int converter, reads for any ints and always returns positive
                for (FileEnum s : FileEnum.values()){ // checks through the list of enums to see if the input key matches user input key then returns relevant enum
                    if (s.getEnumKey()==userInputAsInt){
                        userChoosing=false;
                        fileEnum=s;
                    }
                }
                if (userChoosing){
                    System.out.println("please check you entered a valid number\n \nPlease enter a number corresponding to a file");
                }
            }
            return fileEnum;
        }

    }
    public static class StringConverter {
        public static int stringToInt(String inputString){
            StringBuilder stringBuilder= new StringBuilder(inputString.length());
            stringBuilder.append(0);
            char [] inputAsCharArray = inputString.toCharArray();
            // char value 48-57 are numerics
            for (char c : inputAsCharArray){
                if (c>= 48 && c<=57){
                    stringBuilder.append(c);
                }
            }
            // System.out.println(stringBuilder);
            return Integer.parseInt(stringBuilder.toString());
        }
    }
}

