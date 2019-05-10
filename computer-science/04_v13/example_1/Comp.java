import java.util.*;

class Comp implements Comparable<Comp>, Iterable<Object>, Iterator<Object> {
    
      
       String str;
       int number;
      
       Comp(String str, int number) {
             this.str = str;
             this.number = number;
       }
      
       public int compareTo(Comp entry) {
            
             int result = str.compareTo(entry.str);
             if(result != 0) {
                    return result;
             }
            
             result = number - entry.number;
             if(result != 0) {
                    return (int) result / Math.abs( result );
             }
             return 0;
       }
       
       public Iterator<Object> iterator() { 
             return this;
       }
       
       private int iterator_idx = 0;
       public void reset() {
           iterator_idx = 0;
       }
       public boolean hasNext() {            
             return iterator_idx > 1 ? false: true;
       }      
       public void remove() {
             // Не реализуем так как это разрушит структуру данных
       }
       public Object next() {             
             if ( iterator_idx == 0 ) {
                  iterator_idx++;
                  return str;
             }
             else if ( iterator_idx == 1 ) {
                  iterator_idx++;
                  return new Integer( number );
             }
             else {
                  reset();
                  return null;
             }
       }
}
