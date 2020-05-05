/*Project #5
 *Source code file: PhoneBook.java
 *Programmer: Rebecca Carbone
 *Due: 5/29/2019
 *Description: PhoneBook class that writes and edits a phonebook
*/
package phonebook;

public class PhoneBook {
    
    BinaryTree book;
    WriteBook write;
    
    public PhoneBook(Node newRoot) {
        book = new BinaryTree(newRoot);
        write = new WriteBook();
    }
    
    public void add(String name, String number) {
        book.add(name, number);
    }
    
    public void delete(String name) {
       book.delete(name);
    }
    
    public void printBook() {
        System.out.println("PhoneBook: ");
        inOrderPrint(book.getRoot());
    }
    
    private int inOrderPrint(Node root) {
        if (root == null) {
            return 0;
        }
        
        inOrderPrint(root.left);
        System.out.println(root.getName() + " - " + root.getNumber());
        inOrderPrint(root.right);
        
        return 0;
    }
    
    
    public void change(String name, String newNumber) {
        book.change(book.getRoot(), name, newNumber);
    }
    
    public String find(String name) {
       return book.find(book.getRoot(), name);
    }
    
    public void print() {
       printToFile(book.getRoot());
       write.close();
    }
    
    private void printToFile(Node curr) {
        
        if (curr.left == null && curr.right == null) {
            write.write(curr.getName() + " - " + curr.getNumber());
        }
        
        else if (curr.left == null) {
            printToFile(curr.right);
            write.write(curr.getName() + " - " + curr.getNumber());
        }
        
        else if (curr.right == null) {
            printToFile(curr.left);
            write.write(curr.getName() + " - " + curr.getNumber());
        }
        
        else {
            printToFile(curr.left);
            write.write(curr.getName() + " - " + curr.getNumber());
            printToFile(curr.right);
        }
    }
}
