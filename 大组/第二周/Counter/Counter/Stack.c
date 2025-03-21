#include "counter.h"


//初始化栈，此时栈底与栈顶为同一个Stack，因此只需要返回栈顶的指针即可
Stack* IninStack() {
	//创建栈底指针
	Stack* Desk = (Stack*)malloc(sizeof(Stack));
	//栈底指向的前一个为NULL
	Desk->TopElement = NULL;
	//初始化容量为15
	Desk->Size = 15;
	return Desk;
}

//入栈操作，返回栈顶的指针
Stack* Push(Stack* Top, Node* PushInElement) {
	//先获取Node值作为Data
	Top->TopElement = PushInElement;
	//此时返回的值PopInElement就是栈顶
	return PushInElement;
}

//出栈操作，返回弹出的元素
Node* Pop(Stack* Top) {
	Node* PopElement = Top->TopElement;
	//将Top指针回退一位
	Top->TopElement = Top->TopElement->last;
	return PopElement;
}


//判断是否为空，返回的是Bool类型,需要传入一个栈的栈顶指针,基于链表实现的栈栈底是头节点，而头节点不存放数据，只作为标记
bool IsEmpty(Stack* TopPointer){
	return TopPointer->TopElement->next == TopPointer->TopElement;
}


//获取栈的大小,遍历栈并通过计数器获取最大值
int GetStackSize(Stack* Top) {
	return LinkListSize(Top->TopElement);
}


//查看栈顶
Node* CheckTop(Stack* Stack) {
	return Stack->TopElement;
}


//设置栈的容量最大值
int SetMaxSize() {
	printf("请输入栈的最大值：");
	int MaxSizeOfStack = 0;
	scanf("%d",MaxSizeOfStack);
	return MaxSizeOfStack;
}



//自适应栈长度，根据需要变长栈长度


