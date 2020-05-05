/*Project #5
 *Source code file: BinaryTree.java
 *Programmer: Rebecca Carbone
 *Due: 5/29/2019
 *Description: Binary Search Tree java class
*/
package phonebook;

public class BinaryTree {
    private Node root;
    
    public BinaryTree() {
        
    }
    
    public BinaryTree(Node newRoot) {
        root = newRoot;
    }
    
    public Node getRoot() {
        return root;
    }
    
    public void add(String name, String number) {
        if (root == null) 
            root = new Node(name, number);
        
        else 
            addRec(root, name, number);
    }
    
    private Node addRec(Node curr, String name, String number) {
    
        if (name.compareTo((String) curr.getName()) < 0) {
            
            if (curr.left == null) {
                curr.left = new Node(name, number);
            }
            else {
                addRec(curr.left, name, number);
            }
        }
        
        else if (name.compareTo((String) curr.getName()) > 0) {
            
            if (curr.right == null) {
                curr.right = new Node(name, number);
            }
            else {
                addRec(curr.right, name, number);
            }
        }
        
        return new Node(name, number);
        
    }
    
    public void delete(String name) {
        root = deleteRec(root, name);
    }
    
    private Node deleteRec(Node curr, String name) {
        if (curr == null) {
            return curr;
        }
        
        if (name.compareTo((String) curr.getName()) == 0) {
            
            if (curr.left == null && curr.right == null) {
                return null;
            }
            
            else if (curr.right == null) {
                return curr.left;
            }
            
            else if (curr.left == null) {
                return curr.right;
            }
            
            else {
                String leastName = findLeast(curr);
                curr.name = leastName;
                curr.right = deleteRec(curr.right, leastName);
                return curr;
            }
            
        }
        
        if (name.compareTo((String) curr.getName()) < 0) {
            curr.left = deleteRec(curr.left, name);
            return curr;
        }
        
        if (name.compareTo((String) curr.getName()) > 0) {
            curr.right = deleteRec(curr.right, name);
            return curr;
        }
    
        return curr;
    }
    
    private String findLeast(Node node) {
        if (node.left != null) {
           node.name = findLeast(node.left);
        }
        return (String) node.getName();
    }
    
    public boolean change(Node curr, String name, String newNumber) {
        if (curr == null) {
            return false;
        }
        
        if (name.compareTo((String) curr.getName()) == 0) {
            curr.number = newNumber;
        }
        
        else if (name.compareTo((String) curr.getName()) < 0) {
            change(curr.left, name, newNumber);
        }
        
        else if (name.compareTo((String) curr.getName()) > 0) {
            change(curr.right, name, newNumber);
        }
        return true;
    }
    
    public String find(Node curr, String name) {
        
        String number = "";
        if (curr == null) {
            return null;
        }
        
        if (name.compareTo((String) curr.getName()) == 0) {
            number = (String) curr.getNumber();
        }
        
        else if (name.compareTo((String) curr.getName()) < 0) {
            number = find(curr.left, name);
        }
        
        else if (name.compareTo((String) curr.getName()) > 0) {
            number = find(curr.right, name);
        }
        return number;
    }
    
    
}
