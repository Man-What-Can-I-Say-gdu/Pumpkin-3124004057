#include "counter.h"


//�����������:��ɾ�������ȡ����


//��������
Node* CreateLinkList() {
	return  (Node*)malloc(sizeof(Node));
}

//��ʼ������
Node* InitLinkList(Node* Head) {
	Head->Data = NULL;
	Head->next = NULL;
	Head->last = Head->next;
	return Head;
}

//ͨ����������ڵ�,�������Ľڵ�
Node* AddNode(Node* Head,Node* NewNode) {
	//��β�ڵ����һ���ڵ�ָ���½ڵ㣬�����½ڵ����һ���ڵ�ָ��β�ڵ�
	Head->last->next = NewNode;
	NewNode->last = Head->last;
	//��ͷ�ڵ����һ���ڵ�ָ���½ڵ㣬�����½ڵ����һ���ڵ�ָ��ͷ�ڵ�
	Head->last = NewNode;
	NewNode->next = Head;
	return Head;
}

//ɾ���ڵ㣬Ϊ����Ӧջ����Ҫ������βɾ
Node* DeleteNode(Node* Head) {
	//������Ϊ0��ֱ�ӷ���
	if (Head->next != Head->last) {
		//�ҵ���β�Ľڵ�
		Node* Temp = Head->last;
		//�Խڵ����ɾ��
		Temp->next->last = Temp->last;
		Temp->last->next = Head;
		Temp->last = NULL;
		Temp->next = NULL;
		free(Temp);
	}
	return Head;
}

//��ѯ�ڵ�
Node* SelectNode(Node* Head,int Index) {
	Node* Temp = Head;
	for (int i = 0;i < Index;i++) {
		if ((Temp =Temp->next) == Head)
			return NULL;
	}
	return Temp;
}



//��ȡ�ڵ����
int LinkListSize(Node* Head) {
	Node* Temp = Head;
	int Size = 0;
	while ((Temp = Temp->next) != Head)
		Size++;
	return Size;
}