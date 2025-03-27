#include "counter.h"

//直接用char数组封装会出现无法进行除了0，1，2，....，9的数字以外的加减法
//如果使用int数组则无法进行小数的计算，使用double会导致能表示的数很有限，
//因此采用与高精度计算同种包装方式，既能满足对数字和符号的分别包装，也能
//扩大计算的数值范围


//封装方式：将除了（）+-*/外的数字用char数组进行封装

//获取一整个字符串
char* GetFormula() {
	int InitSize = 50;
	int Increment = 0;
	//初始化一个能存放一定量字符串的数组
	char* Formula = (char*)malloc(sizeof(char)* 50);
	if (Formula == NULL) {
		printf("开辟内存失败！");
		free(Formula);
		return NULL;
	}
	printf("请输入要计算的式子:\n");
	fgets(Formula, 50, stdin);
	//判断最后一个字符是否为换行符
	while ('\n' != Formula[strlen(Formula)-1]) {
		//不等于则获取一个更大的字符串进行存储,字符串长度的增量为初始值的一般（25byte）
		Increment++;
		char* Temp = (char*)realloc(Formula, (InitSize + Increment * (InitSize / 2)));
		if (Temp == NULL) {
			printf("获取内存失败");
			free(Temp);
			return NULL;
		}
		//获取更多计算式的字符
		Formula = Temp;
		fgets(Formula + strlen(Formula), (InitSize / 2), stdin);
	} 

	Formula[strlen(Formula)] = '\0';
	//printf("%s", Formula);
	////等到读取完整个计算式后再返回
	////防止出现51个字符的极端情况而浪费内存
	//char* Temp = (char*)realloc(Formula, sizeof(char)*strlen(Formula));
	//if (Temp == NULL) {
	//	printf("获取内存失败");
	//	free(Temp);
	//	return NULL;
	//}
	//Formula = Temp;
	//Formula[strlen(Formula)] = '\0';
	//printf("%s", Formula);
	return Formula;
	//出现realloc后的字符串包含一个换位符和一个随机字符的情况
}



//对计算式进行预处理，将得到的式子进行重新封装获得一个二级数组以存放数据和算数运算符
Formula PackageFormula(char* formula) {
	int i = 0,LastIndex = 0,NumberPart = 0,SymbolPart = 0;
	//用于存放计算式中的数字部分
	char** FormulaNumbArr = (char**)malloc(sizeof(char*) * 2);
	char* FormulaSymbol = (char*)malloc(sizeof(char)*2);
	if (FormulaNumbArr == NULL || FormulaSymbol == NULL) {
		printf("分配内存出现异常！");
		free(FormulaNumbArr);
		free(FormulaSymbol);
		return;
	}
	while (i < strlen(formula)) {
		//将formula数组拆分
		if ((formula[i] > '0' && formula[i] < '9')||(formula[i] == '.')) {
		}
		else if (formula[i] == '+' || formula[i] == '-' || formula[i] == '*' || formula[i] == '/' || formula[i] == '）') {
			Result* temp = MyStrtok(formula, i, LastIndex);
			//将数据存放到FormulaArr当中,如果Part不小于2，说明此时式子的组成部分不止3，应该增加
			if (NumberPart >= 2) {
				//存放符号和数字的数组增加长度
				char* TempFormulaSymbol = (char*)realloc(FormulaSymbol, sizeof(char) * (SymbolPart + 1));
				char** TempFormulaNumbArr = (char**)realloc(FormulaNumbArr, sizeof(char*) * (NumberPart + 1));
				if (TempFormulaSymbol == NULL || TempFormulaNumbArr == NULL) {
					printf("分配内存出现异常！");
					free(TempFormulaSymbol);
					free(TempFormulaNumbArr);
					return;
				}
				FormulaNumbArr = TempFormulaNumbArr;
				FormulaSymbol = TempFormulaSymbol;
			}
			FormulaNumbArr[NumberPart] = temp->number;
			FormulaSymbol[SymbolPart] = *(temp->symbol);
			NumberPart++;
			SymbolPart++;
			//更新上一次切割到的索引
			LastIndex = i;
		}
		//左括号右边不会跟数字，直接放进数组中即可
		else if (formula[i] == '(' ) {
			if (SymbolPart > 1) {

			}
		}
		else {
			//接下来是对无关字符的处理，需要舍弃这部分，先赋值为统一的一个字符’_'
			formula[i] = '_';
		}
		i++;
	}
	FormulaSymbol[SymbolPart] = '\0';
	Formula PreparedFormula = {FormulaNumbArr,FormulaSymbol,NumberPart};
	return PreparedFormula;
}

//对数据进行处理，去除数字中的‘_'字符
void ProcessingFormula(Formula PreparedFormula) {
	//遍历每一个数字
	for (int index = 0;index < PreparedFormula.PartNumb;index++) {
		//重新开辟一个数组存放
		int lenth = strlen(PreparedFormula.FormulaNumb[index]);
		char* ProcessedFormula = (char*)malloc(sizeof(char) * lenth);
		if (ProcessedFormula == NULL) {
			printf("字符串分配地址错误");
			free(ProcessedFormula);
			return;
		}
		int NumberIndex = 0;
		for (int i = 0;i < lenth;i++) {
			if (PreparedFormula.FormulaNumb[i] != '_') {
				ProcessedFormula[NumberIndex] = PreparedFormula.FormulaNumb[i];
				NumberIndex++;
			}
		}
		//先释放原先的数字
		free(PreparedFormula.FormulaNumb[index]);
		//将新的字符串赋值给预处理计算式
		char* Temp = (char*)realloc(ProcessedFormula, strlen(ProcessedFormula));
		PreparedFormula.FormulaNumb[index] = ProcessedFormula;
	}
}

//TokIndex用于确认现在要切割的位点，LastPoint用于确定上一个切割的位点
Result* MyStrtok(char* String, int TokIndex, int LastIndex) {
	// 输入验证
	if (String == NULL || TokIndex <= LastIndex || TokIndex > (int)strlen(String)) {
		return NULL;
	}

	// 分配结果结构体
	Result* result = (Result*)malloc(sizeof(Result));
	if (result == NULL) {
		printf("内存分配失败\n");
		return NULL;
	}

	// 分配数字部分内存 
	result->number = (char*)malloc(TokIndex - LastIndex + 1);
	if (result->number == NULL) {
		printf("内存分配失败\n");
		free(result);
		return NULL;
	}

	// 分配符号部分内存
	result->symbol = (char*)malloc(2);
	if (result->symbol == NULL) {
		printf("内存分配失败\n");
		free(result->number);
		free(result);
		return NULL;
	}

	// 复制数字部分
	strncpy(result->number, String + LastIndex, TokIndex - LastIndex);
	result->number[TokIndex - LastIndex] = '\0';

	// 复制运算符
	result->symbol[0] = String[TokIndex];
	result->symbol[1] = '\0';

	return result;
}


