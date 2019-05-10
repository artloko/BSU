import java.util.*;

public class Compex {
      
       public static void main(String[] args) {	
             Comp[] ex = {
                 new Comp("Stive Global", 121),
                 new Comp("Stive Global", 221),        
                 new Comp("Nancy Summer", 3213),
                 new Comp("Aaron Eagle", 3123),
                 new Comp("Barbara Smith", 88786)
             };
             Arrays.sort( ex );             
             for(Comp e : ex) {
                    System.out.print("Str: ");
                    for(Object obj : e )
                       System.out.print( obj + " " );
                    System.out.println();
             }
       }

}
