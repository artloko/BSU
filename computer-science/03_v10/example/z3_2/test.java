package z3_2;

/*���������� ����� Matrix ����������� (n x n). �������� ������ �� m ��������.
�������� ������, ����������� ������ � ������ ����� �������. ����������, �����
�� ������ ����� ���������� ������ � ������ �����*/

import java.util.Random;

public class test {
    static final int NUM = 10;

    public static void main(String[] args) {
        Matrix [] array = new Matrix[NUM];
        //Random rand = new Random();
        for ( int i = 0; i < NUM; i++ ) {
            System.out.println( i + 1 );
            array[i] = new Matrix( /*rand.nextInt(5)+1*/ 2 );
            array[i].Print();
        }
        int min = 999, k = NUM + 1;
        for ( int i = 0; i < NUM; i++ )	{
            int norm = array[i].Norm_1() + array[i].Norm_2();
            if ( norm < min ) {
                min = norm;
                k = i + 1;
            }
        }
        System.out.println(
            "matrix #" + k +
            " has minimal norm-1 (" + array[k - 1].Norm_1() +
            ") and norm-2 (" + array[k - 1].Norm_2()+")");
        for ( int i = 0; i < NUM; i++ ) {
            int norm = array[i].Norm_1();
            if ( norm < min ) {
                min = norm;
                k = i + 1;
            }
        }
        System.out.println(
            "matrix #" + k +
            " has minimal norm-1 (" + min + ")" );
        min=999;
        for ( int i = 0; i < NUM; i++) {
            int norm = array[i].Norm_2();
            if ( norm < min ) {
                min = norm;
                k = i + 1;
            }
        }
        System.out.println(
            "matrix #" + k +
            " has minimal norm-2 (" + min + ")" );

        System.out.println("---test assert---");
        Matrix m = new Matrix();
        m.Print();
    }
}
