#include "counter.h"
//ʵ�ָ߾����㷨

////Augend��ʾ��������Addend��ʾ����
//char* AddCaculate(char* Augend,char Addend) {
//	//�ȶԴ������������б����ҵ�С��������������û��С������С��������������ֵĳ��ȣ������������ֵĴ�ŷ�ʽ��˳����
//	int AugendDecimalPointIndex = Find_DecimalPoint(Augend);
//	int AddendDecimalPointIndex = Find_DecimalPoint(Addend);
//	//����µ�����
//	if (AugendDecimalPointIndex == strlen(Augend)) Augend = RepackageNumber(AugendDecimalPointIndex, AugendDecimalPointIndex);
//	if (AddendDecimalPointIndex == strlen(Addend)) Addend = RepackageNumber(AddendDecimalPointIndex,AddendDecimalPointIndex);
//	//������ʽ��С����λ�ý��ж��룬�ȱȽ���һ������С���������ָ���,�õ�С��λ���Ĳ�ֵ
//	int DifferenceInPointDigits = (strlen(Augend) - AugendDecimalPointIndex) - (strlen(Addend) - AddendDecimalPointIndex);
//	//�õ���Ȼ��λ���Ĳ�࣬���������֮�͵��ڴ�������ַ����ĳ���-1
//	int DifferenceInIntegerDigits = AugendDecimalPointIndex - AddendDecimalPointIndex;
//	//��ý������ĳ���
//	
//	//��2����һ����С���㣬һ���ǡ�\0��
//	char* AddResult = (char*)malloc(sizeof(char) * (DifferenceInIntegerDigits + DifferenceInPointDigits)+2);
//	
//
//}
////�ҵ�С���㣬���û�оͷ������鳤��
//int Find_DecimalPoint(char* Number) {
//	int Index = 0;
//	while (Index == strlen(Number)) {
//		if (Number[Index] == '.') {
//			//�����ҵ�С����
//			break;
//		}
//	}
//	//�ж��Ƿ�û��С����
//	if (strlen(Number) == Index-1) {
//		Index++;
//	}
//	return Index;
//}
////��û��С��������ֽ��а�װ��ʹС����������ַ���ĩβ
//char* RepackageNumber(char* Number,int NumberLenth) {
//	char* NewNumber = (char*)malloc(NumberLenth + 2);
//	NewNumber = strcpy(NewNumber, Number);
//	NewNumber[NumberLenth] = '.';
//	NewNumber[NumberLenth + 1] = '\0';
//	free(Number);
//	return NewNumber;
//}
////���ڵõ�С�����ֵĽ��,DecimalPlace��ʾС��λ��
////DifferenceInPointDigits:
//char* GetAddDecimalResult(char* AddResult, char* Augend, char* Addend, int DifferenceInPointDigits, int DecimalPlace) {
//	
//}
////

//������С�������
char* AddCaculate(char* Augend, char* Addend) {
	int AugendLenth = strlen(Augend);
	int AddendLenth = strlen(Addend);
	//�����żӷ����������
	int resultLenth= (AugendLenth > AddendLenth ? AugendLenth : AddendLenth);
	char* Addresult = (char*)malloc(sizeof(char) * (resultLenth+1));
	//���м���
	int Index = 0;
	while (Index<resultLenth) {
		//����һ���ַ���0����õ��ַ�Ϊ�����ַ�������1������0��Ϊ�˺ͺ���ļ������㱣��һ��
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


//���м����ĸ߾���
char* SubCaculate(char* Minuend, char* Subtraihend) {
	int MinuendLenth = strlen(Minuend);
	int SubtraihendLenth = strlen(Subtraihend);
	//�����żӷ����������
	int resultLenth = (MinuendLenth > SubtraihendLenth ? MinuendLenth : SubtraihendLenth);
	char* Subresult = (char*)malloc(sizeof(char) * (resultLenth + 1));
	//���м���
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


//�˷��ĸ߾��ȣ�����ת��Ϊ�������ÿһλ����һ������������
char* MulCaculate(char* Multiplier, char* Multipland) {
	int MultiplierLenth = strlen(Multiplier);
	int MultiplandLenth = strlen(Multipland);
	char* MulResult = (char*)malloc(sizeof(char) * (MultiplandLenth + MultiplierLenth));
	int Index = 0;
	while (Index < MultiplandLenth) {
		//��ȡMultiplandLenth�ٶ�Ӧ�������ַ�����װ���ַ���
		//��ȡ��Ӧ����������
		int x = Multipland[Index]-'0';
		//���ʱ��ΪMultiplier������
		int TempIndex = 0;
		while (TempIndex<MultiplierLenth) {
			//��Multiplierÿһλ��Multipland��Index���������������
			MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] += (Multiplier[TempIndex] - '0') * x;
			if (MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] >= 10) {
				//Indexλ����һλ����Ϊû�з�ת�ַ������ʱ��Ӧ������ǰһλ��
				MulResult[(MultiplandLenth + MultiplierLenth) - Index - 2] = (MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] / 10)+'0';
				MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] %= 10;
			}
			MulResult[(MultiplandLenth + MultiplierLenth) - Index - 1] += '0';
			TempIndex++;
		}
	}
	free(Multiplier);
	free(Multipland);
	//�������ս��
}



//�����ĸ߾���:ֻ��������֮��ĳ���
// �Ƚ������������Ĵ�С
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

// �Ƴ�ǰ����
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



// ����������һλ��
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

// �߾�����������
char* DivCalculate(const char* dividend, const char* divisor) {
    // �������Ƿ�Ϊ0
    if (strcmp(divisor, "0") == 0) {
        printf("Error: Division by zero\n");
        return NULL;
    }

    // �����������
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

    // �������ռ�
    char* quotient = (char*)malloc(dividendLen + 1);
    memset(quotient, '0', dividendLen);
    quotient[dividendLen] = '\0';

    char* currentDividend = (char*)malloc(dividendLen + 1);
    strcpy(currentDividend, dividend);

    int pos = 0;

    while (pos <= dividendLen - divisorLen) {
        // ��ȡ��ǰ���ֱ�����
        int partialLen = divisorLen;
        if (compareStrings(currentDividend + pos, divisor) < 0 && pos + divisorLen < dividendLen) {
            partialLen++;
        }

        char* partialDividend = (char*)malloc(partialLen + 1);
        strncpy(partialDividend, currentDividend + pos, partialLen);
        partialDividend[partialLen] = '\0';

        // ���㵱ǰλ����
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

        // ��������
        char* product = multiplyByDigit(divisor, currentQuotient - 1);
        char* remainder = SubCaculate(partialDividend, product);

        // ���µ�ǰ������
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
