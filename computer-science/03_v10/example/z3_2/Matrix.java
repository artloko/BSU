package z3_2;

/*ќпределить класс Matrix размерности (n x n). ќбъ€вить массив из m объектов.
Ќаписать методы, вычисл€ющие первую и вторую нормы матрицы. ќпределить, кака€
из матриц имеет наименьшую первую и вторую нормы*/

import java.util.Random;
import java.util.Date;

class Matrix {
    int N = 0;
    int [][] mas = null;

    public Matrix() {
        N = 0;
        mas = null;
    }

    public Matrix( int n ) {
        assert( n > 0 );
        Init( n, 10 );
    }

    public Matrix( int n, int max_val )	{
        assert( n > 0 );
        assert( max_val > 0 );
        Init( n, max_val );
    }

    public int Norm_1() {
        assert( N > 0 );
        int norm = 0;
        for( int i = 0; i < N; i++ ) {
            int temp = 0;
            for( int j = 0; j < N; j++ ) {
                temp += Math.abs( mas[i][j] );
            }
            if( temp > norm ) {
                norm = temp;
            }
	}
	return norm;
    }

    public int Norm_2() {
        assert( N > 0 );
        int norm = 0;
        for( int j = 0; j < N; j++ ) {
            int temp = 0;
            for( int i = 0; i < N; i++ ) {
                temp += Math.abs( mas[i][j] );
            }
            if ( temp > norm ) {
                norm = temp;
            }
        }
        return norm;
    }

    public void Print() {
        assert( N > 0 ): "Assertion failed: N > 0";
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                System.out.print( " " + mas[i][j] );
            }
            System.out.println();
        }
	System.out.println();
    }

    private void Init( int n, int max_val ) {
        assert( n > 0 );
        assert( max_val > 0 );
        mas = new int [n][n];
        N = n;
        Random rand = new Random( (new Date()).getTime() );
        for( int i = 0; i < N; i++ ) {
            for( int j = 0; j < N; j++ ) {
                mas[i][j] = rand.nextInt( max_val );
            }
        }
    }
}
