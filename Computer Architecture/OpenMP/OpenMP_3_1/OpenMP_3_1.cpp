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
		omp_set_num_threads(2);
		double a = 0;
		double b = 100;
		int n = 1000000;
		cout << findminmax(a, b, n, f, g) << endl;
	}

private:
	static double f(double x)
	{
		return (x * x + 3 * x + 2);
	}

	static double g(double x)
	{
		return (10 * x * x + 20 * x + 1);
	}

	static int findminmax(double a, double b, int n, double f(double x), double g(double x))
	{
		double step = (b - a) / n;
		double min = (f(a + step) - f(a)) / step;
		double max = INT_MIN;
		auto startTime = omp_get_wtime();
		#pragma omp parallel sections
		{
			#pragma omp section
			{
				#pragma omp parallel for private(min)
				for (int i = 0; i <= n; i++)
				{
					double buff = (f(a + i * step + step) - f(a + i * step)) / step;
					#pragma omp critical
					{
						if (min > buff)
							min = buff;
					}
				}
			}
			#pragma omp section
			{
				#pragma omp parallel for private(max)
				for (int i = 0; i <= n; i++)
				{
					double buff = (g(a + i * step + step) - g(a + i * step)) / step;
					#pragma omp critical
					{
						if (max < buff)
							max = buff;
					}
				}
			}
		}
		return (int)GetEllapsedMilliseconds(startTime);
	}

	static double GetEllapsedMilliseconds(double startTime)
	{
		return (omp_get_wtime() - startTime) * 1000;
	}
};

int main()
{
	Program::Main();
	system("pause");
}