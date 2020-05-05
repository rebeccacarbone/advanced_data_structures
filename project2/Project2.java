/*Project #2
 *Source code file: Project2.java
 *Programmer: Rebecca Carbone
 *Due: 10/28/2019
 *Description: .java file to display a menu that allows the user to create a 
                    binary tree and/or binary tree table file
*/
package project2;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Project2 {

    //--------Configuration Variables--------//
    private static String fileName = "data.txt";
    private static String outputFileName = "output.txt";
    private static String binTreeFile = "output.bt1";
    private static String binTreeTable = "output.bt2";
    private static int arraySize = 101;

    //-----------Global Variables-----------//
    private static int recordSize;
    private static UserInput userInput = new UserInput();

    /**
     * Main method calls menu method
     * @param args 
     */
    public static void main(String[] args) {

        try {

            File file = new File(fileName);
            Scanner input = new Scanner(file);
            int numberOfLines = 0;

            while (input.hasNextLine()) {
                numberOfLines++;
                input.nextLine();
            }

            Record[] records = new Record[numberOfLines];
            readFile(file, records);

            menu(records, numberOfLines);

        } catch (Exception e) {
            System.out.println("Error in main");
        }

    }

    /**
     * Menu for user input
     * @param records
     * @param numberOfLines 
     */
    public static void menu(Record[] records, int numberOfLines) {
        int userChoice = -1;

        while (userChoice != 0) {

            System.out.println("Menu: ");
            System.out.println("Enter 1 to Print a Record");
            System.out.println("Enter 2 to Print a Specific Data Field");
            System.out.println("Enter 3 to Create a Binary Tree");
            System.out.println("Enter 4 to Create a Binary Tree Table");
            System.out.println();
            System.out.println("Enter 0 to Exit");
            userChoice = userInput.getInt();
            int inputNumber;

            switch (userChoice) {
                case 1:
                    System.out.println("Enter a record number 1 - "
                            + numberOfLines);
                    inputNumber = userInput.getInt();

                    if (userInput.isValid(inputNumber, 1, numberOfLines)) {
                        System.out.println(records[inputNumber - 1].toString());
                    }
                    break;

                case 2:
                    System.out.println("Enter a record number 1 - "
                            + numberOfLines);
                    inputNumber = userInput.getInt();

                    if (userInput.isValid(inputNumber, 1, numberOfLines)) {
                        System.out.println("Enter a data field. Options are: "
                                + "First Name, Last Name, Email, "
                                + "ID Number, Color, Balance");
                        userInput.getDataField(records, inputNumber);
                    }
                    break;

                case 3:
                    getRecordSize();
                    Record[] binRecords = records.clone();
                    writeBinTree(binRecords);
                    break;

                case 4:
                    getRecordSize();
                    Record[] tableRecords = records.clone();
                    int[][] indexes = getIndexArray(tableRecords);
                    convertRecords(tableRecords, indexes);
                    writeTableFile(tableRecords);

            }
        }
    }

    /**
     * Ensures user inputs proper record size
     */
    public static void getRecordSize() {
        System.out.println("Enter a fixed length between 80-256");
        recordSize = userInput.getInt();

        if (!userInput.isValid(recordSize, 80, 256)) {
            recordSize = userInput.validEntry(recordSize);
        }
    }

    /**
     * Reads a file of records into an array
     * @param file
     * @param records 
     */
    public static void readFile(File file, Record[] records) {

        try {

            Scanner input = new Scanner(file);
            String recordInfo;

            for (int i = 0; i < records.length; i++) {
                recordInfo = input.nextLine();
                
                //removes any extra spaces at the beginning of a record
                while (recordInfo.charAt(0) == ' ') {
                    recordInfo = recordInfo.substring(1);
                }
                records[i] = new Record(recordInfo);

            }

        } catch (Exception e) {
            System.out.println("File reading error");
        }
    }

    /**
     * Creates array of fixed length records
     * @param records
     * @return 
     */
    public static void getFixedLengthArray(Record[] records) {
        for (int i = 0; i < records.length; i++) {
            records[i] = records[i].getFixedLengthRecord(recordSize);
        }
    }

    /**
     * Writes a file of empty records
     * @param file 
     */
    public static void writeEmptyFile(RandomAccessFile file) {

        try {
            Record emptyRecord = new Record("");
            emptyRecord = emptyRecord.getFixedLengthRecord(recordSize);
            file.seek(0);

            for (int i = 0; i < arraySize; i++) {
                file.writeBytes(emptyRecord.toString());
            }

        } catch (Exception e) {
            System.out.println("Error writing array to file");
        }
    }

    /**
     * Creates .bt1 file
     * @param records 
     */
    public static void writeBinTree(Record[] records) {
        try {
            RandomAccessFile file = new RandomAccessFile(binTreeFile, "rw");
            writeEmptyFile(file);
            getFixedLengthArray(records);
            insertRecords(file, records);
            file.close();

        } catch (Exception e) {
            System.out.println("Error writing binary tree file");
        }
    }

    /**
     * Loops through array of records and inserts each record into .bt1 file
     * @param file
     * @param records 
     */
    public static void insertRecords(RandomAccessFile file, Record[] records) {
        try {

            file.seek(0);
            file.write(records[0].toString().getBytes());
            for (int i = 1; i < records.length; i++) {
                int index = findIndex(file, records[i].getLastName());
                file.seek(index * recordSize);
                file.writeBytes(records[i].toString());

            }

        } catch (Exception e) {
            System.out.println("Error inserting records");
        }

    }

    /**
     * Finds proper index to insert record with given key into .bt1 file
     * @param file
     * @param key
     * @return 
     */
    public static int findIndex(RandomAccessFile file, String key) {
        try {
            int index = 0;
            file.seek(0);
            String line = file.readLine();
            Record temp = new Record(line);
            String lastName = temp.getLastName();
            boolean isEmpty = line.charAt(0) == ' ';

            while (!isEmpty) {
                if (key.compareTo(lastName) < 0) {
                    index = 2 * index + 1;
                } else {
                    index = 2 * index + 2;
                }

                file.seek(index * recordSize);
                line = file.readLine();
                isEmpty = line.charAt(0) == ' ';

                //check that the line isnt empty before attempting to make record
                if (!isEmpty) {
                    temp = new Record(line);
                    lastName = temp.getLastName();
                }

            }

            return index;

        } catch (Exception e) {
            System.out.println("Error finding index");
        }
        return 0;
    }

    /**
     * Creates 2D array of left and right child indexes for .bt2 file
     * @param records
     * @return 
     */
    public static int[][] getIndexArray(Record[] records) {
        int key, index, ID;
        int[][] indexes = new int[records.length][2];
        boolean found;

        //fill 2D array of indexes with default -1 values
        for (int i = 0; i < indexes.length; i++) {
            for (int j = 0; j < indexes[i].length; j++) {
                indexes[i][j] = -1;
            }
        }
        
        //loop through the array of records to find the children for each one
        for (int i = 1; i < records.length; i++) {
            index = 0;
            key = Integer.valueOf(records[i].getID());
            ID = Integer.valueOf(records[index].getID());
            found = false;

            while (!found) {
                if (key <= ID) {
                    //if <, check if a left child exists. if not the proper index was found
                    if (indexes[index][0] != -1) {
                        index = indexes[index][0];
                    } else {
                        indexes[index][0] = i;
                        found = true;
                    }
                } else {
                    //same as above but with the right child
                    if (indexes[index][1] != -1) {
                        index = indexes[index][1];
                    } else {
                        indexes[index][1] = i;
                        found = true;
                    }
                }
                if(!found)
                    ID = Integer.valueOf(records[index].getID());
            }
        }
        return indexes;
    }

    /**
     * Converts array of records to array of table format records
     * @param records
     * @param indexes
     * @return 
     */
    public static void convertRecords(Record[] records, int[][] indexes) {
        for (int i = 0; i < records.length; i++) {
            records[i].setLeft(indexes[i][0]);
            records[i].setRight(indexes[i][1]);
            records[i] = records[i].getFixedLengthRecord(recordSize);
        }
    }

    /**
     * Writes .bt2 table file
     * @param records 
     */
    public static void writeTableFile(Record[] records) {
        try {
            RandomAccessFile file = new RandomAccessFile(binTreeTable, "rw");
            writeEmptyFile(file);
            file.seek(0);
            for (int i = 0; i < records.length; i++) {
                file.writeBytes(records[i].getRecordInfo());
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error writing binary tree table file");
        }
    }
    

}