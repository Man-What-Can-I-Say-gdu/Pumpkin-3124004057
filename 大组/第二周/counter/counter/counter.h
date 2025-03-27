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
Stack* Pop(Stack* stack);
//入栈
Stack* Push(Stack* stack,Node* Element);

//计算式，FormulaNumb表示存放的数字，FormulaSymbol表示存放的运算符，PartNumb代表计算式的组成数
typedef struct Formula {
    char** FormulaNumb;
    char* FormulaSymbol;
    int PartNumb;
}Formula;

//临时存放数组，用于解决返回的
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
void ProcessFormula(Formula PreparedFormula);

