/*Project #2
 *Source code file: LinkedString.java
 *Programmer: Rebecca Carbone
 *Due: 3/18/2019
 *Description: The LinkedString ADT class
*/
package linkedstring;

import java.util.Scanner;

public class LinkedString {

    private Node head;
    private int numItems;

    public LinkedString() {
        head = null;
        numItems = 0;
    } //default constructor

    public LinkedString(char[] value) {
        Node curr;
        Node node;

        if (value.length != 0) { //check that the array is not empty

            node = new Node(value[0]);
            head = node; //assign head to first char in the array
            numItems++;
            curr = head;

            for (int i = 1; i < value.length; i++) {
                node = new Node(value[i]); //set up next node in the list
                curr.next = node; //assign previous node to point to current node
                curr = node; //assign current node to correct value
                numItems++;
            }
        }

    }

    public LinkedString(String original) {
        Node curr;
        Node node;

        if (original.length() != 0) { //check that String isn't empty

            node = new Node(original.charAt(0));
            head = node; //assign head to first char in the String
            numItems++;
            curr = head;

            for (int i = 1; i < original.length(); i++) {
                node = new Node(original.charAt(i)); //set up next node in the list
                curr.next = node; //assign previous node to point to current node
                curr = node; //assign current node to correct value
                numItems++;
            }
        }
    }

    public char charAt(int index) {

        if (index <= numItems && index >= 0) { //check that index is valid
            Node curr = find(index - 1);
            char ch = (char) curr.item; //access the item of that node
            return ch;
        } 
        else {
            System.out.println("Invalid index");
            return 0; //return 0 if index is invalid
        }

    }

    private Node find(int index) {
        Node curr = head;

        for (int i = 0; i < index; i++) { //search the list for the desired node, starting with head and terminating at index
            curr = curr.next;
        }
        return curr;
    }

    public void concat(LinkedString str) {
        int index = this.numItems;
        Node node = find(index-1); //find final node in the list, subtracting 1 from index because the list begins at 0
        Node curr = node;

        for (int i = 1; i <= str.numItems; i++) { //set up loop to traverse the linkedstring parameter
            node = new Node(str.charAt(i)); //set current node to current node in the linkedstring paramater
            curr.next = node; //assign previous node to point to current node
            curr=node; //assign previous node to current node
            this.numItems++;
        }
        
        
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public int length() {
        return numItems;
    }

    public LinkedString substring(int beginIndex, int endIndex) {
        LinkedString str; //initialize linkedstring

        if (beginIndex < endIndex && beginIndex >= 0 && endIndex < numItems) { //check that indexes are valid 
            String temp = new String(""); //set up empty string which will be used to collect the characters of the substring

            for (int i = beginIndex+1; i < endIndex+1; i++) {
                temp += charAt(i); //add each character to the string, starting from beginIndex and terminating at endIndex like the String object substring method
            }

            str = new LinkedString(temp); //set up new linkedstring
            return str;
        } 
        
        else {
            str = new LinkedString();
            return str; //return empty linkedlist if indexes are invalid
        }

    }

    public void print() {

        Node curr = head;
        for (int i = 0; i < numItems; i++) {
            System.out.print(curr.item); //print out current node
            curr = curr.next; //assign current node to next node
        }
        System.out.println(); //print a new line
    }
}
