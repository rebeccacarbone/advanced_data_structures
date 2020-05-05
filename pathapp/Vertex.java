/*Project #3
 *Source code file: Vertex.java
 *Programmer: Rebecca Carbone
 *Due: 11/26/2019
 *Description: Vertex class contains char label for the vertex and whether it is
 *  in the tree or not
*/
package pathapp;
import java.io.Serializable;

class Vertex implements Serializable
   {
   public char label;        // label (e.g. 'A')
   public boolean isInTree;
// -------------------------------------------------------------
   public Vertex(char lab)   // constructor
      {
      label = lab;
      isInTree = false;
      }
// -------------------------------------------------------------
   }
