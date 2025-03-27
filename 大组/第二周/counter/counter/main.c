#include "counter.h"

const char addsymble = '+';
const char subsymble = '-';
const char mulsymble = '*';
const char divsymble = '/';
const char leftbracket = '(';
const char rightbracket = ')';


int main() {
	printf("===========================");
	printf("1.�������");
	printf("2.�˳�");
	printf("���������ѡ��\n");
	int choose = 0;
	switch (choose)
	{
	case 1:
	{
		char* NeedFormula = GetFormula();
		Formula formula = PackageFormula(NeedFormula);
		ProcessingFormula(formula);
		char** Postfix = TurnInfixToPostfix(formula);
		char* result = Count(Postfix);
		printf("%s", result);
	}
	case 2: 
	{
		exit(0);
	}
	default:
		break;
	}
	return 0;
}