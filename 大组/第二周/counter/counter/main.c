#include "counter.h"

const char addsymble = '+';
const char subsymble = '-';
const char mulsymble = '*';
const char divsymble = '/';
const char leftbracket = '(';
const char rightbracket = ')';

int main() {
	char test01[5] = "abcd";
	char** test02 = MyStrtok(test01, 2, 0);
	printf("%s,%s", test02[0], test02[1]);
	return 0;
}