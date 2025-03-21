#define _CRT_SECURE_NO_WARNINGS 1

#pragma once
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//链表实现栈结构
//需要的链表数据
typedef struct Node {
	struct Node* last;
	char* Data;
	struct Node* next;
}Node;

//栈结构体
typedef struct Stack {
	//存放数据,需要一个可变大小的数组存放数据，这样既可以动态扩展实现存放足够大的数据，又可以存放符号，达到节约内存
	Node* TopElement;
	//存放下一个数据的地址
	int Size;
}Stack;







