#define _CRT_SECURE_NO_WARNINGS 1

#pragma once
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//����ʵ��ջ�ṹ
//��Ҫ����������
typedef struct Node {
	struct Node* last;
	char* Data;
	struct Node* next;
}Node;

//ջ�ṹ��
typedef struct Stack {
	//�������,��Ҫһ���ɱ��С�����������ݣ������ȿ��Զ�̬��չʵ�ִ���㹻������ݣ��ֿ��Դ�ŷ��ţ��ﵽ��Լ�ڴ�
	Node* TopElement;
	//�����һ�����ݵĵ�ַ
	int Size;
}Stack;







