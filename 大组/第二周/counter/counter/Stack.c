#include "counter.h"
//初始化栈
Stack* InitStack(Stack* stack) {
	//栈顶元素置空，栈的大小设为0
	stack->top = NULL;
	stack->Size = 0;
	return stack;
}
//判断栈是否为空
bool IsStackEmpty(Stack* stack) {
	//栈的长度是否为0
	return 0 == stack->Size;
}
//得到栈顶元素
Node* GetTopElement(Stack* stack) {
	//实际上返回的是头节点
	return stack->top->next;
}
//清空栈
Stack* ClearStack(Stack* stack) {
	ClearLink(stack->top);
	stack->Size = 0;
	return stack;
}
//销毁栈
bool DestoryStack(Stack* stack) {
	DestoryLink(stack->top);
	stack->top = NULL;
	stack->Size = 0;
	free(stack);
	return true;
}
//检测栈的大小
//好像有点多此一举
int LenthOfStack(Stack* stack) {
	return stack->Size;
}
//出栈
Stack* Pop(Stack* stack) {
	//只弹出不销毁
	stack->top->next = stack->top->next->next;
	stack->Size--;
	return stack;
}
//入栈
Stack* Push(Stack* stack,Node* Element) {
	//头插法将新元素插入
	AddNode(stack->top, Element);
	//进行栈的自增
	stack->Size++;
	return stack;
}