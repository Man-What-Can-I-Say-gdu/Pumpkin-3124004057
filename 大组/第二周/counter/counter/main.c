#include "counter.h"

const char addsymble = '+';
const char subsymble = '-';
const char mulsymble = '*';
const char divsymble = '/';
const char leftbracket = '(';
const char rightbracket = ')';


int main() {

	Formula x =PackageFormula(GetFormula());
	printf("%s", x.FormulaNumb[0]);
	printf("%s", x.FormulaSymbol);
	printf("%d", x.PartNumb);
	return 0;
}