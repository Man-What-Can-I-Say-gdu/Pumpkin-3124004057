#include "Counter.h"

//��ʼ��
Node* InitLinkList(Node* Head) {
    Head->next = NULL;
    //ͷָ�벻��Ҫ����
    Head->data = NULL;
}
//ͷ���������
Node* AddNode(Node* Head, char* Data) {
    Node* Temp = Head->next;
    
    Node* AddElement = (Node*)malloc(sizeof(Node));
    if (AddElement == NULL) {
        printf("���ʧ�ܣ�");
    }
    //��ֵ�����ӽڵ�
    AddElement->next = Temp;
    AddElement->data = Data;
    Head->next = AddElement;
    return Head;
}
//ͷɾɾ������
Node* DeleteNode(Node* Head) {
    if (Head->next == NULL) {
        printf("�����Ѿ��ܸɾ���");
        return Head;
    }
    Node* Temp = Head->next;
    Head->next = Temp->next;
    Temp->next = NULL;
    free(Temp);
    Temp = NULL;
    return Head;
}
//��ȡ����
int LenthOfLink(Node* Head) {
    Node* Temp = Head;
    int Lenth = 0;
    while ((Temp = Temp->next) != NULL)
        Lenth++;
    return Lenth;
}
//�������
Node* ClearLink(Node* Head) {
    //ͷָ�봦����Ҫ�ͷ��ڴ棬��˵�һ��ɾ��ʱ����Ҫ�ͷ��ڴ�
    while (Head->next != NULL)
        DeleteNode(Head);
    return Head;
}
//�ж��Ƿ�Ϊ��
bool IsLinkEmpty(Node* Head) {
    return Head->data == NULL;
}

//��������
bool DestoryLink(Node* Head) {
    //����Head�������
    DeleteNode(Head);
    //����head
    free(Head);
    Head = NULL;
    return true;
}
