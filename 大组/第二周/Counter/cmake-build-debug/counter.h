#ifndef 第二周_COUNTER_H
#define 第二周_COUNTER_H

#endif //第二周_COUNTER_H

#include "stdio.h"
#include "stdlib.h"
#include "stdbool.h"

typedef struct Node{
    char* Data;
    struct Node* next;
    struct Node* last;
}Node;

typedef struct Stack{
    Node* Top;
    int Size;
    Node* Desk;
}Stack;

//创建链表
Node* CreateLink();
//初始化链表
Node* InitLink(Node* Head);
//添加元素
Node* AddNode(Node* Head,char* Data);
//删除节点:尾删
Node* DeleteNode(Node* Head);
//获取链表长度
int LinkLenth(Node* Head);
//清空链表
Node* ClearLink(Node* Head);
//判断是否为空
bool IsLinkEmpty(Node* Head);



//初始化栈
Stack* InitStack(Stack* Stack,int Size);
//判断是否为空
bool IsStackEmpty(Stack* stack);
//得到栈顶元素

//清空栈

//销毁栈

//入栈

//出栈

//检测栈的大小

