#include "counter.h"
//Ϊ�˷�ֹ��������ַ������ȳ��������ֵ��������ö�̬����ķ�ʽ���з����ڴ�
char* GetSuitableString() {
	//�ȴ���20���ַ������ַ�����
	char* FormulaString = (char*)malloc(sizeof(char) * 20);
	fscanf(stdin, FormulaString, 20);
	int TempIndex = 0;
	//fscanfѭ����ȡ֪��������Ϊ��
	while(!FormulaString[(TempIndex = strlen(FormulaString))] == '\n') {
		char* TempStirng = (char*)malloc(sizeof(char) * 10);
		fscanf(stdin, TempIndex, 10);
		FormulaString = strcat(FormulaString, TempStirng);
		free(TempStirng);
	}
		FormulaString[TempIndex] = '\0';
		return FormulaString;
}