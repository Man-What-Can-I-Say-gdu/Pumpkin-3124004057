#include "counter.h"



//counter��Ҫ���еĲ����ǽ������봦��
char* ScanOperation() {
	//��ȡ��Ҫ�ļ���ʽ������ʽ���������
	//�ڶ��ж�̬����OperationFormula������
	char TempChar = '\0';
	int CharIndex = 0;
	printf("������Ҫ����ļ���ʽ��");
	//��ü���ʽ��������Ҫ�Լ���ʽ���д����ȱ�ɺ�׺���ڷ���ջ�н�������
	char* OperationFormula = GetSuitableString();
	//�������飬�������е�ascll��ֵ���м���
	for (int i = 0;i < strlen(OperationFormula);i++) {
		if (OperationFormula[i] >= '0' && OperationFormula[i] <= '9') {
			//�ж�Ϊ���֣������ֵĽ��м��㣬��ֹ�����ַ���ļ�����ִ���ascll��ֵ����һһ��Ӧ�ģ�
			OperationFormula[i] -= '0';
		}else if (OperationFormula[i] == 42 || OperationFormula[i] == 43 || OperationFormula[i] == 45 || OperationFormula[i] == 47 || OperationFormula[i] == 40 || OperationFormula[i] == 41) {
			//���������ź����Ų��䣬������������ж��������
			continue;
		}else {
			//44,46Ϊ,��.����Ҫ���������������������ж�,42,43,45,47

			//��Ч�ַ��ÿո���棬��ת��Ϊ��׺���ʽʱ������
			OperationFormula[i] = ' ';
		}
	}
	//������õ��ַ��𴫳�
	return OperationFormula;
}

//