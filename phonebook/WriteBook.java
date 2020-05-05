/*Project #5
 *Source code file: WriteBook.java
 *Programmer: Rebecca Carbone
 *Due: 5/29/2019
 *Description: Class that is used at the end of the menu to write the phonebook
  to a file
*/
package phonebook;

import java.io.*;

public class WriteBook {
    FileWriter file;
    
    public WriteBook() {
        try {
            file = new FileWriter("phonebook.txt");
        }
        
        catch (IOException e) {

        }
    }
    
    public void write(String content) {
       try {
           file.write(content);
           file.write("\r\n");
       
       }
       
       catch (IOException e) {
       }
    }
    
    public void close() {
        try {
            file.close();
        }
        
        catch (IOException e) {
            
        }
        
    }
        
    
}
