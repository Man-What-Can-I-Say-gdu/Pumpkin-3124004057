#include "counter.h"


//��ʼ��ջ����ʱջ����ջ��Ϊͬһ��Stack�����ֻ��Ҫ����ջ����ָ�뼴��
Stack* IninStack() {
	//����ջ��ָ��
	Stack* Desk = (Stack*)malloc(sizeof(Stack));
	//ջ��ָ���ǰһ��ΪNULL
	Desk->TopElement = NULL;
	//��ʼ������Ϊ15
	Desk->Size = 15;
	return Desk;
}

//��ջ����������ջ����ָ��
Stack* Push(Stack* Top, Node* PushInElement) {
	//�Ȼ�ȡNodeֵ��ΪData
	Top->TopElement = PushInElement;
	//��ʱ���ص�ֵPopInElement����ջ��
	return PushInElement;
}

//��ջ���������ص�����Ԫ��
Node* Pop(Stack* Top) {
	Node* PopElement = Top->TopElement;
	//��Topָ�����һλ
	Top->TopElement = Top->TopElement->last;
	return PopElement;
}


//�ж��Ƿ�Ϊ�գ����ص���Bool����,��Ҫ����һ��ջ��ջ��ָ��,��������ʵ�ֵ�ջջ����ͷ�ڵ㣬��ͷ�ڵ㲻������ݣ�ֻ��Ϊ���
bool IsEmpty(Stack* TopPointer){
	return TopPointer->TopElement->next == TopPointer->TopElement;
}


//��ȡջ�Ĵ�С,����ջ��ͨ����������ȡ���ֵ
int GetStackSize(Stack* Top) {
	return LinkListSize(Top->TopElement);
}


//�鿴ջ��
Node* CheckTop(Stack* Stack) {
	return Stack->TopElement;
}


//����ջ���������ֵ
int SetMaxSize() {
	printf("������ջ�����ֵ��");
	int MaxSizeOfStack = 0;
	scanf("%d",MaxSizeOfStack);
	return MaxSizeOfStack;
}



//����Ӧջ���ȣ�������Ҫ�䳤ջ����


