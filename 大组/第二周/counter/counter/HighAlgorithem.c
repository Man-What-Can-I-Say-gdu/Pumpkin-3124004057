#include "counter.h"
//实现高精度算法

////Augend表示被加数，Addend表示加数
//char* AddCaculate(char* Augend,char Addend) {
//	//先对传进来的数进行遍历找到小数点的索引，如果没有小数点则小数点的索引是数字的长度，传进来的数字的存放方式是顺序存放
//	int AugendDecimalPointIndex = Find_DecimalPoint(Augend);
//	int AddendDecimalPointIndex = Find_DecimalPoint(Addend);
//	//获得新的数字
//	if (AugendDecimalPointIndex == strlen(Augend)) Augend = RepackageNumber(AugendDecimalPointIndex, AugendDecimalPointIndex);
//	if (AddendDecimalPointIndex == strlen(Addend)) Addend = RepackageNumber(AddendDecimalPointIndex,AddendDecimalPointIndex);
//	//根据两式的小数点位置进行对齐，先比较哪一个数的小数点后的数字更多,得到小数位数的差值
//	int DifferenceInPointDigits = (strlen(Augend) - AugendDecimalPointIndex) - (strlen(Addend) - AddendDecimalPointIndex);
//	//得到自然数位数的差距，这两个差距之和等于创建结果字符串的长度-1
//	int DifferenceInIntegerDigits = AugendDecimalPointIndex - AddendDecimalPointIndex;
//	//获得结果数组的长度
//	
//	//加2其中一个是小数点，一个是‘\0’
//	char* AddResult = (char*)malloc(sizeof(char) * (DifferenceInIntegerDigits + DifferenceInPointDigits)+2);
//	
//
//}
////找到小数点，如果没有就返回数组长度
//int Find_DecimalPoint(char* Number) {
//	int Index = 0;
//	while (Index == strlen(Number)) {
//		if (Number[Index] == '.') {
//			//遍历找到小数点
//			break;
//		}
//	}
//	//判断是否没有小数点
//	if (strlen(Number) == Index-1) {
//		Index++;
//	}
//	return Index;
//}
////把没有小数点的数字进行包装，使小数点出现再字符串末尾
//char* RepackageNumber(char* Number,int NumberLenth) {
//	char* NewNumber = (char*)malloc(NumberLenth + 2);
//	NewNumber = strcpy(NewNumber, Number);
//	NewNumber[NumberLenth] = '.';
//	NewNumber[NumberLenth + 1] = '\0';
//	free(Number);
//	return NewNumber;
//}
////用于得到小数部分的结果,DecimalPlace表示小数位数
////DifferenceInPointDigits:
//char* GetAddDecimalResult(char* AddResult, char* Augend, char* Addend, int DifferenceInPointDigits, int DecimalPlace) {
//	
//}
////

//不考虑小数的情况
char* AddCaculate(char* Augend, char* Addend) {
	int AugendLenth = strlen(Augend);
	int AddendLenth = strlen(Addend);
	//创造存放加法结果的数组
	int resultLenth= (AugendLenth > AddendLenth ? AugendLenth : AddendLenth);
	char* Addresult = (char*)malloc(sizeof(char) * (resultLenth+1));
	//进行计算
	int Index = 0;
	while (Index<resultLenth) {
		//减掉一个字符‘0’获得的字符为数字字符加数字1或数字0：为了和后面的继续计算保持一致
		Addresult[resultLenth - Index] += (Index >= AugendLenth ? '0': Augend[AugendLenth - Index] + Index >= AddendLenth ? '0':Addend[AddendLenth - Index]-  '0');
		if (Addresult[resultLenth - Index] > 10) {
			Addresult[resultLenth - Index - 1] = 1;
		}
		Index++;
	}
	Addresult[resultLenth] = '\0';
	free(Augend);
	free(Addend);
	return Addresult;
}


//进行减法的高精度
char* SubCaculate(char* Minuend, char* Subtraihend) {
	int MinuendLenth = strlen(Minuend);
	int SubtraihendLenth = strlen(Subtraihend);
	//创造存放加法结果的数组
	int resultLenth = (MinuendLenth > SubtraihendLenth ? MinuendLenth : SubtraihendLenth);
	char* Subresult = (char*)malloc(sizeof(char) * (resultLenth + 1));
	//进行计算
	int Index = 0;
	while (Index < resultLenth) {

		Subresult[resultLenth - Index] = Index >= MinuendLenth ? '0' : Minuend[MinuendLenth - Index] + Index >= SubtraihendLenth ? '0' : Subtraihend[SubtraihendLenth - Index];
		
		if (Subresult[resultLenth - Index]<'0') {
			Subresult[resultLenth - Index - 1] = -1;
		}
		Index++;
	}
	Subresult[resultLenth] = '\0';
	free(Minuend);
	free(Subtraihend);
	return Subresult;
}


//乘法的高精度：可以转化为多个数的每一位与另一个数相乘再相加
char* MulCaculate(char* Multiplier, char* Multipland) {
	int MultiplierLenth = strlen(Multiplier);
	int MultiplandLenth = strlen(Multipland);
	char* MulResult = (char*)malloc(sizeof(char) * (MultiplandLenth + MultiplierLenth));
	int Index = 0;
	while (Index < MultiplandLenth) {
		//获取MultiplandLenth再对应索引的字符并包装成字符串
		//获取对应的整形数据
		int x = Multipland[Index]-'0';
		//相乘时作为Multiplier的索引
		int TempIndex = 0;
		while (TempIndex<MultiplierLenth) {
			//将Multiplier每一位与Multipland在Index处的整形数字相乘
			MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] += (Multiplier[TempIndex] - '0') * x;
			if (MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] >= 10) {
				//Index位的下一位（因为没有翻转字符串因此时对应索引的前一位）
				MulResult[(MultiplandLenth + MultiplierLenth) - Index - 2] = (MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] / 10)+'0';
				MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] %= 10;
			}
			MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] += '0';
			TempIndex++;
		}
	}
	free(Multiplier);
	free(Multipland);
	//返回最终结果
}



//除法的高精度:只进行整形之间的除法
// 比较两个大整数的大小
int compareStrings(const char* num1, const char* num2) {
    int len1 = strlen(num1);
    int len2 = strlen(num2);

    if (len1 > len2) return 1;
    if (len1 < len2) return -1;

    for (int i = 0; i < len1; i++) {
        if (num1[i] > num2[i]) return 1;
        if (num1[i] < num2[i]) return -1;
    }
    return 0;
}

// 移除前导零
void removeLeadingZeros(char* num) {
    int len = strlen(num);
    int i = 0;
    while (i < len - 1 && num[i] == '0') {
        i++;
    }
    if (i > 0) {
        memmove(num, num + i, len - i + 1);
    }
}



// 大整数乘以一位数
char* multiplyByDigit(const char* num, int digit) {
    if (digit == 0) {
        char* result = (char*)malloc(2);
        strcpy(result, "0");
        return result;
    }

    int len = strlen(num);
    char* result = (char*)malloc(len + 2);
    result[len + 1] = '\0';

    int carry = 0;
    for (int i = len - 1; i >= 0; i--) {
        int product = (num[i] - '0') * digit + carry;
        result[i + 1] = (product % 10) + '0';
        carry = product / 10;
    }

    if (carry > 0) {
        result[0] = carry + '0';
        return result;
    }
    else {
        char* finalResult = (char*)malloc(len + 1);
        strcpy(finalResult, result + 1);
        free(result);
        return finalResult;
    }
}

// 高精度整数除法
char* DivCalculate(const char* dividend, const char* divisor) {
    // 检查除数是否为0
    if (strcmp(divisor, "0") == 0) {
        printf("Error: Division by zero\n");
        return NULL;
    }

    // 特殊情况处理
    int cmp = compareStrings(dividend, divisor);
    if (cmp < 0) {
        char* result = (char*)malloc(2);
        strcpy(result, "0");
        return result;
    }
    if (cmp == 0) {
        char* result = (char*)malloc(2);
        strcpy(result, "1");
        return result;
    }

    int dividendLen = strlen(dividend);
    int divisorLen = strlen(divisor);

    // 分配结果空间
    char* quotient = (char*)malloc(dividendLen + 1);
    memset(quotient, '0', dividendLen);
    quotient[dividendLen] = '\0';

    char* currentDividend = (char*)malloc(dividendLen + 1);
    strcpy(currentDividend, dividend);

    int pos = 0;

    while (pos <= dividendLen - divisorLen) {
        // 获取当前部分被除数
        int partialLen = divisorLen;
        if (compareStrings(currentDividend + pos, divisor) < 0 && pos + divisorLen < dividendLen) {
            partialLen++;
        }

        char* partialDividend = (char*)malloc(partialLen + 1);
        strncpy(partialDividend, currentDividend + pos, partialLen);
        partialDividend[partialLen] = '\0';

        // 计算当前位的商
        int currentQuotient = 0;
        char* temp = (char*)malloc(strlen(divisor) + 1);
        strcpy(temp, divisor);

        while (compareStrings(partialDividend, temp) >= 0) {
            currentQuotient++;
            char* newTemp = multiplyByDigit(divisor, currentQuotient);
            free(temp);
            temp = newTemp;
        }

        quotient[pos] = currentQuotient + '0';

        // 计算余数
        char* product = multiplyByDigit(divisor, currentQuotient - 1);
        char* remainder = SubCaculate(partialDividend, product);

        // 更新当前被除数
        int remainderLen = strlen(remainder);
        memmove(currentDividend + pos, remainder, remainderLen);
        if (remainderLen < partialLen) {
            memset(currentDividend + pos + remainderLen, '0', partialLen - remainderLen);
        }

        free(partialDividend);
        free(temp);
        free(product);
        free(remainder);

        pos++;
    }

    removeLeadingZeros(quotient);
    free(currentDividend);

    return quotient;
}
