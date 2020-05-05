/*Project #2
 *Source code file: Record.java
 *Programmer: Rebecca Carbone
 *Due: 10/28/2019
 *Description: Class to represent a record object
*/
package project2;

public class Record {

    //------GLOBAL VARIABLE------//
    private String recordInfo;

    /**
     * Record constructor
     * @param newInfo 
     */
    public Record(String newInfo) {
        setRecordInfo(newInfo);
    }

    /**
     * Sets recordInfo global variable
     * @param newInfo 
     */
    private void setRecordInfo(String newInfo) {
        recordInfo = newInfo;
    }

    /**
     * Adds left child data field to existing Record
     * @param left 
     */
    public void setLeft(int left) {
        recordInfo += ", " + left;
    }

    /**
     * Adds right child data field to existing Record w/ left child
     * @param right 
     */
    public void setRight(int right) {
        recordInfo += ", " + right;
    }

    /**
     * Returns left child data field
     * @return 
     */
    private int getLeftRecord() {
        String temp = (cut(recordInfo, 5));
        return Integer.valueOf(getSection(temp));
    }

    /**
     * Returns right child data field
     * @return 
     */
    private int getRightRecord() {
        return Integer.valueOf(cut(recordInfo, 6));
    }

    /**
     * Returns first name data field
     * @return 
     */
    public String getFirstName() {
        return getSection(recordInfo);
    }

    /**
     * Returns last name data field
     * @return 
     */
    public String getLastName() {
        String temp = cut(recordInfo, 0);
        return getSection(temp);
    }
    
    /**
     * Returns the email data field
     * @return 
     */
    public String getEmail() {
        String temp = cut(recordInfo, 1);
        return getSection(temp);
    }

    /**
     * Returns ID number data field
     * @return 
     */
    public String getID() {
        String temp = cut(recordInfo, 2);
        return getSection(temp);
    }

    /**
     * Returns the color data field
     * @return 
     */
    public String getColor() {
        String temp = cut(recordInfo, 3);
        return getSection(temp);
    }

    /**
     * Returns the balance data field
     * @return 
     */
    public String getBalance() {
        return cut(recordInfo, 4);
    }

    /**
     * Returns the record info global variable
     * @return 
     */
    public String getRecordInfo() {
        return recordInfo;
    }

    /**
     * Returns the length of the record
     * @return 
     */
    public int getLength() {
        return recordInfo.length();
    }

    /**
     * toString method for the Record Class
     * @return 
     */
    public String toString() {
        return recordInfo;
    }

    /**
     * Creates a fixed length Record 
     * @param length
     * @return 
     */
    public Record getFixedLengthRecord(int length) {

        length -= 2;
        String fixedLengthRecord = String.format("%-" + length + "." + length + "s", getRecordInfo());
        fixedLengthRecord += "*\n";

        return new Record(fixedLengthRecord);
    }

    /**
     * Recursive method that cuts off each section up to desired data field
     * @param original
     * @param numberOfCuts
     * @return 
     */
    private String cut(String original, int numberOfCuts) {

        original = original.substring(original.indexOf(',') + 1);

        //removes any leading spaces
        while (original.charAt(0) == ' ') {
            original = original.substring(1);
        }

        if (numberOfCuts > 0) {
            return cut(original, numberOfCuts - 1);
        }

        return original;
    }

    /**
     * Returns a single section from recordInfo
     * @param temp
     * @return 
     */
    private String getSection(String temp) {
        return temp.substring(0, temp.indexOf(','));
    }

}
