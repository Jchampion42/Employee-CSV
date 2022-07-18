package com.sparta.AlphaTeam.core;

public enum FileEnum {
    // Update new methods here
    EMPLOYEERECORDS1 (1, "EmployeeRecords1.csv", "src/main/resources/EmployeeRecords1.csv"),
    EMPLOYEERECORDS2 (2 , "EmployeeRecords2.csv", "src/main/resources/EmployeeRecords2.csv"),
    EMPLOYEERECORDSLARGE ( 3, "EmployeeRecordsLarge.csv", "src/main/resources/EmployeeRecordsLarge.csv");

    private final int enumKey;
    private final String fileName;
    private final String filePath;

    FileEnum(int keyValue, String fileName , String filePath){
        this.enumKey=keyValue;
        this.fileName=fileName;
        this.filePath=filePath;
    }
    //----------GETTERS----------------

    public int getEnumKey() {
        return enumKey;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }
}
