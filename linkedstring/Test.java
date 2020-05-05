/*Project #2
 *Source code file: Test.java
 *Programmer: Rebecca Carbone
 *Due: 3/18/2019
 *Description: Test class for the LinkedString
*/
package linkedstring;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        testString(); //test linkedstring with string constructor/also test the different methods
        testChar(); //test linkedstring char constructor
    }

    public static void testString() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the String you would like to test with");
        String temp = input.nextLine();

        LinkedString str = new LinkedString(temp); //set up LinkedString wtih String constructor
        str.print(); //print out LinkedString

        //----------------------------- test charAt ----------------------------
        System.out.println("Enter an index for charAt");
        int index = input.nextInt();
        System.out.println(str.charAt(index));

        //----------------------------- test concat ----------------------------
        System.out.println("Enter another string to concatenate");
        String temp2 = input.next();
        LinkedString str2 = new LinkedString(temp2);
        str.concat(str2);
        str.print(); //print out new concatenated LinkedString

        //----------------------------- test substring -------------------------
        System.out.println("Enter 2 numbers for the index of the substring");
        int beginIndex = input.nextInt();
        int endIndex = input.nextInt();
        LinkedString str3 = str.substring(beginIndex, endIndex); //set up LinkedString from substring
        str3.print(); //print new LinkedString

        System.out.println("The LinkedString is empty: " + str.isEmpty()); //test isEmpty method
        System.out.println("The length of the LinkedString is: " + str.length()); //test length method

    }
    
    public static void testChar() {
        Scanner input = new Scanner(System.in);
        System.out.println("How many characters will be in your linkedstring");
        int number = input.nextInt();
        
        char[] value = new char[number];
        
        for(int i = 0; i < number; i++) {
            System.out.println("Enter a character: ");
            String temp = input.next();
            value[i] = temp.charAt(0);
        }
        
        LinkedString str = new LinkedString(value);
        str.print();

    }
}
