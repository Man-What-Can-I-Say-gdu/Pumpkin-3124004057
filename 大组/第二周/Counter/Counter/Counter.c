#include "counter.h"



//counter需要进行的操作是进行输入处理
char* ScanOperation() {
	//获取需要的计算式将计算式打包并返回
	//在堆中动态分配OperationFormula的内容
	char TempChar = '\0';
	int CharIndex = 0;
	printf("请输入要计算的计算式：");
	//获得计算式，接下来要对计算式进行处理，先变成后缀，在放入栈中进行运算
	char* OperationFormula = GetSuitableString();
	//遍历数组，将数组中的ascll码值进行计算
	for (int i = 0;i < strlen(OperationFormula);i++) {
		if (OperationFormula[i] >= '0' && OperationFormula[i] <= '9') {
			//判断为数字，对数字的进行计算，防止后续字符间的计算出现错误（ascll码值不是一一对应的）
			OperationFormula[i] -= '0';
		}else if (OperationFormula[i] == 42 || OperationFormula[i] == 43 || OperationFormula[i] == 45 || OperationFormula[i] == 47 || OperationFormula[i] == 40 || OperationFormula[i] == 41) {
			//四则计算符号和括号不变，方便后续进行判断随后运算
			continue;
		}else {
			//44,46为,和.，需要对这两个及其他数进行判断,42,43,45,47

			//无效字符用空格代替，在转化为后缀表达式时会舍弃
			OperationFormula[i] = ' ';
		}
	}
	//将处理好的字符拆传出
	return OperationFormula;
}

//