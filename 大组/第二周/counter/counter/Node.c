#include "Counter.h"

//初始化
Node* InitLinkList(Node* Head) {
    Head->next = NULL;
    //头指针不需要数据
    Head->data = NULL;
}
//头插添加数据
Node* AddNode(Node* Head, char* Data) {
    Node* Temp = Head->next;
    
    Node* AddElement = (Node*)malloc(sizeof(Node));
    if (AddElement == NULL) {
        printf("添加失败！");
    }
    //赋值并连接节点
    AddElement->next = Temp;
    AddElement->data = Data;
    Head->next = AddElement;
    return Head;
}
//头删删除数据
Node* DeleteNode(Node* Head) {
    if (Head->next == NULL) {
        printf("链表已经很干净了");
        return Head;
    }
    Node* Temp = Head->next;
    Head->next = Temp->next;
    Temp->next = NULL;
    free(Temp);
    Temp = NULL;
    return Head;
}
//获取长度
int LenthOfLink(Node* Head) {
    Node* Temp = Head;
    int Lenth = 0;
    while ((Temp = Temp->next) != NULL)
        Lenth++;
    return Lenth;
}
//清空链表
Node* ClearLink(Node* Head) {
    //头指针处不需要释放内存，因此第一次删除时不需要释放内存
    while (Head->next != NULL)
        DeleteNode(Head);
    return Head;
}
//判断是否为空
bool IsLinkEmpty(Node* Head) {
    return Head->data == NULL;
}

//销毁链表
bool DestoryLink(Node* Head) {
    //销毁Head后的数据
    DeleteNode(Head);
    //销毁head
    free(Head);
    Head = NULL;
    return true;
}
