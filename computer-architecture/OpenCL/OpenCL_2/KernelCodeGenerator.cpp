#include "KernelCodeGenerator.hpp"

namespace KernelCodeGenerator
{
	const std::string codeTemplateStart = ""
		"__kernel void KernelMain(__global int* m1, __global int* m2, __global int* m3, int n, int m, int l)\n"
		"{\n"
		"\tint i = get_global_id(0);\n"
		"\tint j = get_global_id(1);\n"
		"\n";
	const std::string codeTemplateMiddle =
		"\tint m1Base = i * m;\n"
		"\tint m2Base = j * m;\n"
		"\tint bound = m / $N;\n"
		"\n"
		"\tfor (int k = 0; k < bound; ++k)\n"
		"\t{\n";

	const std::string codeTemplateEnd = ""
		"\t\tsum += v1 * v2;\n"
		"\n"
		"        m1Base += $N;\n"
		"        m2Base += $N;\n"
		"\t}\n"
		"\n"
		"    if (bound * $N < m)\n"
		"    {\n";

	std::string ReplaceString(std::string subject, const std::string &search,
		const std::string &replace)
	{
		size_t pos = 0;
		while ((pos = subject.find(search, pos)) != std::string::npos)
		{
			subject.replace(pos, search.length(), replace);
			pos += replace.length();
		}
		return subject;
	}

	std::string Generate(int vectorSize)
	{
		std::string result = ReplaceString(codeTemplateStart, "$N", std::to_string(vectorSize));
		result += "int" + std::to_string(vectorSize) + " sum = (int" + std::to_string(vectorSize) + ")(";

		for (unsigned int index = 0; index < vectorSize; ++index)
		{
			result += "0";
			if (index + 1 < vectorSize)
			{
				result += ", ";
			}
		}

		result += ");\n";
		result += ReplaceString(codeTemplateMiddle, "$N", std::to_string(vectorSize));
		result += "int" + std::to_string(vectorSize) + " v1 = (int" + std::to_string(vectorSize) + ")(";

		for (unsigned int index = 0; index < vectorSize; ++index)
		{
			result += "m1[m1Base + " + std::to_string(index) + "]";
			if (index + 1 < vectorSize)
			{
				result += ", ";
			}
		}

		result += ");\n";
		result += "int" + std::to_string(vectorSize) + " v2 = (int" + std::to_string(vectorSize) + ")(";

		for (unsigned int index = 0; index < vectorSize; ++index)
		{
			result += "m2[m2Base + " + std::to_string(index) + "]";
			if (index + 1 < vectorSize)
			{
				result += ", ";
			}
		}

		result += ");\n";
		result += ReplaceString(codeTemplateEnd, "$N", std::to_string(vectorSize));
		result += "int" + std::to_string(vectorSize) + " v1 = (int" + std::to_string(vectorSize) + ")(";

		for (unsigned int index = 0; index < vectorSize; ++index)
		{
			result += "bound * " + std::to_string(vectorSize) + " + " + std::to_string(index) +
				" < m ? m1[m1Base + " + std::to_string(index) + "] : 0";
			if (index + 1 < vectorSize)
			{
				result += ",\n";
			}
		}

		result += ");\n";
		result += "int" + std::to_string(vectorSize) + " v2 = (int" + std::to_string(vectorSize) + ")(";

		for (unsigned int index = 0; index < vectorSize; ++index)
		{
			result += "bound * " + std::to_string(vectorSize) + " + " + std::to_string(index) +
				" < m ? m2[m2Base + " + std::to_string(index) + "] : 0";
			if (index + 1 < vectorSize)
			{
				result += ",\n";
			}
		}

		result += ");\n\t\tsum += v1 * v2;\n    }\n\tm3[i * l + j] = \n";
		for (unsigned int index = 0; index < vectorSize; ++index)
		{
			std::string indexStr;
			if (index < 10)
			{
				indexStr = std::to_string(index);
			}
			else
			{
				indexStr = ('A' + (index - 10));
			}

			result += "sum.s" + indexStr;
			if (index + 1 < vectorSize)
			{
				result += " + ";
			}
		}

		result += ";\n}";
		return result;
	}
}
