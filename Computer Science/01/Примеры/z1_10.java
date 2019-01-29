/*
ln (( 1 + x ) / (1 - x))
arguments:
    [0]   x, (-1,1)
    [1]   k, k > 1
*/
public class z1_10 {
    public static void main(String[] args) {
        if ( args.length != 2 ) {
            System.err.println("Invalid number of arguments");
            System.exit(1);
        }
        double x = Double.parseDouble( args[0] );
        if ( Math.abs( x ) >= 1 ) {
            System.err.println("Invalid argument: " + x );
            System.exit(1);
        }
        int k = Integer.parseInt( args[1] );
        if ( k <= 1 ) {
            System.err.println("Invalid argument: " + k );
            System.exit(1);
        }
        double step = 2 * x;
        double result = step;
        int i = 1;
        double Eps = 1 / Math.pow( 10, k + 1 );
        while( Math.abs( step ) >= Eps ) {
            step = step * i * x * x /( i + 2 );
            result += step;
            i += 2;
        }
        String fmt = "%10." + k + "f\n";
        System.out.format( fmt, result );
        System.out.format( fmt, Math.log(( 1 + x )/( 1 - x )));
        System.exit(0);
    }
}
