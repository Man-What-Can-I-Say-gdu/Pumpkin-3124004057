#include "counter.h"

//��ӡ�˵�
void MenuPrintf(char** MenuText, int TextRow) {
	//ѭ��������ӡ�˵�
	for (int i = 0;i < TextRow;i++) {
		printf("%s\n", MenuText[i]);
	}
}

//ʵ�ֲ˵���ӡ��ѡ��
int MenuWithChoose(char** MenuText, int TextRow) {
	MenuPrintf(MenuText, TextRow);
	printf("������Ҫִ�еĹ��ܣ�");
	int choose = 0;
	while (true) {
		scanf("%d", &choose);
		if (choose < TextRow && choose > 0)
			break;
		else
			printf("����������������룺");
	}
	return choose;
}








