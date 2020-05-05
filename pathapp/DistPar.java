/*Project #3
 *Source code file: DistPar.java
 *Programmer: Rebecca Carbone
 *Due: 11/26/2019
 *Description: DistPar contains distance from the start to the vertex and the 
 *  parent of that vertex
*/
package pathapp;
import java.io.Serializable;

class DistPar implements Serializable            // distance and parent
   {                      // items stored in sPath array
   public int distance;   // distance from start to this vertex
   public int parentVert; // current parent of this vertex
// -------------------------------------------------------------
   public DistPar(int pv, int d)  // constructor
      {
      distance = d;
      parentVert = pv;
      }
// -------------------------------------------------------------
   }  
