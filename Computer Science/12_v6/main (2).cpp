#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <cmath>

using namespace std;

const double d1pow10[DBL_DIG - 2 + 1] = {
	0.001,											// 1/(10^3)
	0.0001,											// 1/(10^4)
	0.00001,										// 1/(10^5)
	0.000001,										// 1/(10^6)
	0.0000001,										// 1/(10^7)
	0.00000001,										// 1/(10^8)
	0.000000001,									// 1/(10^9)
	0.0000000001,									// 1/(10^10)
	0.00000000001,									// 1/(10^11)
	0.000000000001,									// 1/(10^12)
	0.0000000000001,								// 1/(10^13)
	0.00000000000001,								// 1/(10^14)
	0.000000000000001,								// 1/(10^15)
	0.0000000000000001,								// 1/(10^16)
};

double fpucos(double x)
{
													// вычисление косинуса при помощи трансцендентных команд
	__asm
	{
		fld x; 
		fcos;
	}
}

double taylorcos(double x, int n) {
	double two = 2.0;
	n += 3;
													// Asm version by Taylor
													//    st(3)     result
													//    st(2)     x
													//    st(1)     step
													//    st(0)     eps
	__asm {
		fld 1;										// result = 1
		fld x;										// load x - st = x
		fmul st, st;								// st = x * x
		fchs;										// st = -x * x
		fld st;										// step = -x * x
		fld two;									// положить двойку в стек
		fdiv st(1), st;								// step = (-x * x) / 2
		fstp st;									// достать двойку из стека
		
		mov ebx, n
		dec ebx
		dec ebx
		fld qword ptr d1pow10[ebx * 8];				// eps = 1 / 10^(n + 1)
			
		mov ebx, 1;									// n = 1, ebx == n
		mov n, ebx
			
		fld st(1);									// st= step, st1= eps, st2= step, st3= (-x * x) / 2, st4= result
	next_:											// while( fabs( step ) >= eps ) {
		fabs;										// st = abs(step)
		fcomip st, st(1);
		jna finish;
		
		fld st(1);									// st= step, st1= eps, st2= step, st3= (-x * x) / 2, st4= result
		fadd st(4), st;								// result += step;
			
													// step = (step * (-x * x) / 2) / ((2 * n + 1) * (2 * n + 2))
		fmul st, st(3);								// st= (( step * (-x * x) / 2 ), ...
		mov eax, ebx
			shl eax, 1
			inc eax
			mov n, eax
			fidiv n
			inc eax
			mov n, eax
			fidiv n
			inc ebx;								// n++
		mov n, ebx;
		fst st(2);									// step = st
		jmp next_;
			finish :
		fstp    st;									// clear stack
		fstp    st;									// clear stack
		fstp    st;									// clear stack
	}
}

int main(int argc, char** argv)
{
	if (argc != 3)
		cout << "usage: [x] [k]" << endl;
	else
	{
		double x = strtod(argv[1], NULL);
		int k = strtol(argv[2], NULL, 10);

		cout << "cmath: " << setprecision(k) << cos(x) << endl;
		cout << "fpu: " << setprecision(k) << fpucos(x) << endl;
		cout << "taylor cos: " << setprecision(k) << taylorcos(x, k) << endl;
	}

	return 0;
}