#include "counter.h"
//为了防止输入的自字符串长度超过分配的值，这里采用动态分配的方式进行分配内存
char* GetSuitableString() {
	//先创建20个字符长度字符数组
	char* FormulaString = (char*)malloc(sizeof(char) * 20);
	fscanf(stdin, FormulaString, 20);
	int TempIndex = 0;
	//fscanf循环获取知道缓存区为空
	while(!FormulaString[(TempIndex = strlen(FormulaString))] == '\n') {
		char* TempStirng = (char*)malloc(sizeof(char) * 10);
		fscanf(stdin, TempIndex, 10);
		FormulaString = strcat(FormulaString, TempStirng);
		free(TempStirng);
	}
		FormulaString[TempIndex] = '\0';
		return FormulaString;
}