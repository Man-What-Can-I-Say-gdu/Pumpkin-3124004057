#include "counter.h"


//链表基本操作:增删查遍历获取个数


//创造链表
Node* CreateLinkList() {
	return  (Node*)malloc(sizeof(Node));
}

//初始化链表
Node* InitLinkList(Node* Head) {
	Head->Data = NULL;
	Head->next = NULL;
	Head->last = Head->next;
	return Head;
}

//通过增加链表节点,返回最后的节点
Node* AddNode(Node* Head,Node* NewNode) {
	//将尾节点的下一个节点指向新节点，并将新节点的上一个节点指向尾节点
	Head->last->next = NewNode;
	NewNode->last = Head->last;
	//将头节点的上一个节点指向新节点，并将新节点的下一个节点指向头节点
	Head->last = NewNode;
	NewNode->next = Head;
	return Head;
}

//删除节点，为了适应栈的需要，采用尾删
Node* DeleteNode(Node* Head) {
	//链表长度为0则直接返回
	if (Head->next != Head->last) {
		//找到最尾的节点
		Node* Temp = Head->last;
		//对节点进行删除
		Temp->next->last = Temp->last;
		Temp->last->next = Head;
		Temp->last = NULL;
		Temp->next = NULL;
		free(Temp);
	}
	return Head;
}

//查询节点
Node* SelectNode(Node* Head,int Index) {
	Node* Temp = Head;
	for (int i = 0;i < Index;i++) {
		if ((Temp =Temp->next) == Head)
			return NULL;
	}
	return Temp;
}



//获取节点个数
int LinkListSize(Node* Head) {
	Node* Temp = Head;
	int Size = 0;
	while ((Temp = Temp->next) != Head)
		Size++;
	return Size;
}