#include <omp.h>
#include <iostream>
#include <sstream>
#include <ctime>
#include <cmath>

using namespace std;

class Program
{
public:
	static void Main()
	{
		omp_set_num_threads(64);
		cout << "The given integral equals: " << integral(h, 0, 15, 1000000) << endl;
	}

private:
	static double h(double x)
	{
		return (x * x + 3 * x + 2);
	} 

	static double integral(double func(double x), double A, double B, long long N)
	{
		double summ = 0;
		double step = (B - A) / N;
		#pragma omp parallel for reduction(+:summ)
		for (int i = 0; i < N; ++i)
		{
			double a = A + i * step;
			double b = a + step;
			double a_b = (a + b) / 2;
			#pragma omp critical
			{
				summ += (b - a) * (func(a) + 4 * func(a_b) + func(b)) / 6;
			}
		}
		return summ;
	}
};

int main()
{
	Program::Main();
	system("pause");
}