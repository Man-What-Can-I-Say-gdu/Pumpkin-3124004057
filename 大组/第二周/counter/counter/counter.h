#define _CRT_SECURE_NO_WARNINGS 1
#pragma once

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>


extern const char addsymble;
extern const char subsymble;
extern const char mulsymble;
extern const char divsymble;
extern const char leftbracket;
extern const char rightbracket;

//���嵥����
typedef struct Node {
    char* data;
    struct Node* next;
}Node;
//�������Ϊ
//��ʼ��
Node* InitLinkList(Node* Head);
//β���������
Node* AddNode(Node* Head, char* Data);
//ͷɾɾ������
Node* DeleteNode(Node* Head);
//��ȡ����
int LenthOfLink(Node* Head);
//�������
Node* ClearLink(Node* Head);
//�ж��Ƿ�Ϊ��
bool IsLinkEmpty(Node* Head);
//��������
bool DestoryLink(Node* Head);


//����ջ
typedef struct Stack {
    Node* top;
    int Size;
}Stack;
//��ʼ��ջ
Stack* InitStack(Stack* stack);
//�ж�ջ�Ƿ�Ϊ��
bool IsStackEmpty(Stack* stack);
//�õ�ջ��Ԫ��
Node* GetTopElement(Stack* stack);
//���ջ
Stack* ClearStack(Stack* stack);
//����ջ
bool DestoryStack(Stack* stack);
//���ջ�Ĵ�С
int LenthOfStack(Stack* stack);
//��ջ
char* Pop(Stack* stack);
//��ջ
Stack* Push(Stack* stack,Node* Element);

//��׺����ʽ��FormulaNumb��ʾ��ŵ����֣�FormulaSymbol��ʾ��ŵ��������PartNumb�������ʽ�������
//��������׺���ʽ������ǰ�󣨳��˿�ͷ���ܰ����ŷ��ţ����ÿ��һ�����ֵĳ��־���һ�����ų��֣�������ʱֻ��Ҫע�����ʹÿ��ѭ��������ͬʱ���ü���
//ΨһҪע��ľ������š��������������������ź�Ϊ���֣���������ǰΪ���ţ�������ǰΪ���֣���Ϊ����
typedef struct Formula {
    char** FormulaNumb;
    char* FormulaSymbol;
    int PartNumb;
    int PartSymbol;
}Formula;

//��ʱ������飬���ڽ�����ص�ʱ���޷�������ָ�븳ֵ�����¼�����Ŷ�ȡΪ����
typedef struct Result {
    char* number;
    char* symbol;
}Result;

//��ȡһ�����ַ���
char* GetFormula();
//���õ���ʽ�ӽ������·�װ���һ�����������Դ�����ݺ����������
Formula PackageFormula(char* formula);
//���ݴ�������λ�ý��ַ����и�������ַ�����һ���������֣�һ�����������
Result* MyStrtok(char* String, int TokIndex, int LastIndex);
//�����ݽ��д���ȥ�������еġ�_'�ַ�
void ProcessingFormula(Formula PreparedFormula);
//������С�������
//�߾��ȼӷ�����
char* AddCaculate(char* Augend, char* Addend);
//�߾��ȼ�������
char* SubCaculate(char* Minuend, char* Subtraihend);
//�߾��ȳ˷�����
char* MulCaculate(char* Multiplier, char* Multipland);
// �Ƚ������������Ĵ�С
int compareStrings(const char* num1, const char* num2);
// �Ƴ�ǰ����
void removeLeadingZeros(char* num);
// ����������һλ��
char* multiplyByDigit(const char* num, int digit);
//�߾��ȳ�������
char* DivCalculate(const char* dividend, const char* divisor);
//����׺���ʽת��Ϊ��׺���ʽ
char** TurnInfixToPostfix(Formula InfixFormula);
//ִ�м���
char* Count(char** Postfix);
