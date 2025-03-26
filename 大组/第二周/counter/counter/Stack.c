#include "counter.h"
//��ʼ��ջ
Stack* InitStack(Stack* stack) {
	//ջ��Ԫ���ÿգ�ջ�Ĵ�С��Ϊ0
	stack->top = NULL;
	stack->Size = 0;
	return stack;
}
//�ж�ջ�Ƿ�Ϊ��
bool IsStackEmpty(Stack* stack) {
	//ջ�ĳ����Ƿ�Ϊ0
	return 0 == stack->Size;
}
//�õ�ջ��Ԫ��
Node* GetTopElement(Stack* stack) {
	//ʵ���Ϸ��ص���ͷ�ڵ�
	return stack->top->next;
}
//���ջ
Stack* ClearStack(Stack* stack) {
	ClearLink(stack->top);
	stack->Size = 0;
	return stack;
}
//����ջ
bool DestoryStack(Stack* stack) {
	DestoryLink(stack->top);
	stack->top = NULL;
	stack->Size = 0;
	free(stack);
	return true;
}
//���ջ�Ĵ�С
//�����е���һ��
int LenthOfStack(Stack* stack) {
	return stack->Size;
}
//��ջ
Stack* Pop(Stack* stack) {
	//ֻ����������
	stack->top->next = stack->top->next->next;
	stack->Size--;
	return stack;
}
//��ջ
Stack* Push(Stack* stack,Node* Element) {
	//ͷ�巨����Ԫ�ز���
	AddNode(stack->top, Element);
	//����ջ������
	stack->Size++;
	return stack;
}