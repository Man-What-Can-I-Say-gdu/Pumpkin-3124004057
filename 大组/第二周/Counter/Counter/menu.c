#include "counter.h"

//打印菜单
void MenuPrintf(char** MenuText, int TextRow) {
	//循环遍历打印菜单
	for (int i = 0;i < TextRow;i++) {
		printf("%s\n", MenuText[i]);
	}
}

//实现菜单打印与选择
int MenuWithChoose(char** MenuText, int TextRow) {
	MenuPrintf(MenuText, TextRow);
	printf("请输入要执行的功能：");
	int choose = 0;
	while (true) {
		scanf("%d", &choose);
		if (choose < TextRow && choose > 0)
			break;
		else
			printf("输入错误，请重新输入：");
	}
	return choose;
}








