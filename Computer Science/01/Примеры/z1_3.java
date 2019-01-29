/*
log (1 - x)
arguments:
    [0]   x, [-1,1)
    [1]   k, k > 1
*/
public class z1_3 {
    public static void main(String[] args) {
        if ( args.length != 2 ) {
            System.err.println("Invalid number of arguments");
            System.exit(1);
        }
        double x = Double.parseDouble( args[0] );
        if ( x >= 1 || x < -1 ) {
            System.err.println("Invalid argument: " + x );
            System.exit(1);
        }
        int k = Integer.parseInt( args[1] );
        if ( k <= 1 ) {
            System.err.println("Invalid argument: " + k );
            System.exit(1);
        }
        double Eps = 1 / Math.pow( 10, k + 1 );
        double result = 0;
        double step = x;
        int n = 1;
        while( Math.abs( step ) >= Eps ) {
            result += step;
            step = (( step * n ) * x )/( n + 1 );
            n++;
	}
        result = (-1) * result;
        String fmt = "%." + args[1] + "f\n";
        System.out.printf( fmt, result );
        System.out.printf( fmt, Math.log( 1 - x ));
        System.exit(0);
    }
}
