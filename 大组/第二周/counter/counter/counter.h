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

//定义单链表
typedef struct Node {
    char* data;
    struct Node* next;
}Node;
//链表的行为
//初始化
Node* InitLinkList(Node* Head);
//尾增添加数据
Node* AddNode(Node* Head, char* Data);
//头删删除数据
Node* DeleteNode(Node* Head);
//获取长度
int LenthOfLink(Node* Head);
//清空链表
Node* ClearLink(Node* Head);
//判断是否为空
bool IsLinkEmpty(Node* Head);
//销毁链表
bool DestoryLink(Node* Head);


//定义栈
typedef struct Stack {
    Node* top;
    int Size;
}Stack;
//初始化栈
Stack* InitStack(Stack* stack);
//判断栈是否为空
bool IsStackEmpty(Stack* stack);
//得到栈顶元素
Node* GetTopElement(Stack* stack);
//清空栈
Stack* ClearStack(Stack* stack);
//销毁栈
bool DestoryStack(Stack* stack);
//检测栈的大小
int LenthOfStack(Stack* stack);
//出栈
char* Pop(Stack* stack);
//入栈
Stack* Push(Stack* stack,Node* Element);

//中缀计算式，FormulaNumb表示存放的数字，FormulaSymbol表示存放的运算符，PartNumb代表计算式的组成数
//由于在中缀表达式中数字前后（除了开头）总伴随着符号，因此每有一个数字的出现就有一个符号出现，在引用时只需要注意这点使每次循环中两者同时调用即可
//唯一要注意的就是括号‘（’‘）’，在左括号后为数字，但左括号前为符号，右括号前为数字，后为符号
typedef struct Formula {
    char** FormulaNumb;
    char* FormulaSymbol;
    int PartNumb;
    int PartSymbol;
}Formula;

//临时存放数组，用于解决返回的时候无法给二级指针赋值而导致计算符号读取为乱码
typedef struct Result {
    char* number;
    char* symbol;
}Result;

//获取一整个字符串
char* GetFormula();
//将得到的式子进行重新封装获得一个二级数组以存放数据和算数运算符
Formula PackageFormula(char* formula);
//根据传进来的位置将字符串切割成两个字符串，一个保存数字，一个保存运算符
Result* MyStrtok(char* String, int TokIndex, int LastIndex);
//对数据进行处理，去除数字中的‘_'字符
void ProcessingFormula(Formula PreparedFormula);
//不考虑小数的情况
//高精度加法运算
char* AddCaculate(char* Augend, char* Addend);
//高精度减法运算
char* SubCaculate(char* Minuend, char* Subtraihend);
//高精度乘法运算
char* MulCaculate(char* Multiplier, char* Multipland);
// 比较两个大整数的大小
int compareStrings(const char* num1, const char* num2);
// 移除前导零
void removeLeadingZeros(char* num);
// 大整数乘以一位数
char* multiplyByDigit(const char* num, int digit);
//高精度除法运算
char* DivCalculate(const char* dividend, const char* divisor);
//将中缀表达式转化为后缀表达式
char** TurnInfixToPostfix(Formula InfixFormula);
//执行计算
char* Count(char** Postfix);
