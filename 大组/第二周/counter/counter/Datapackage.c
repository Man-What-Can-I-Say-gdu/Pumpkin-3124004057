#include "counter.h"

//ֱ����char�����װ������޷����г���0��1��2��....��9����������ļӼ���
//���ʹ��int�������޷�����С���ļ��㣬ʹ��double�ᵼ���ܱ�ʾ���������ޣ�
//��˲�����߾��ȼ���ͬ�ְ�װ��ʽ��������������ֺͷ��ŵķֱ��װ��Ҳ��
//����������ֵ��Χ


//��װ��ʽ�������ˣ���+-*/���������char������з�װ

//��ȡһ�����ַ���
char* GetFormula() {
	int InitSize = 50;
	int Increment = 0;
	//��ʼ��һ���ܴ��һ�����ַ���������
	char* Formula = (char*)malloc(sizeof(char)* 50);
	if (Formula == NULL) {
		printf("�����ڴ�ʧ�ܣ�");
		free(Formula);
		return NULL;
	}
	printf("������Ҫ�����ʽ��:\n");
	fgets(Formula, 50, stdin);
	//�ж����һ���ַ��Ƿ�Ϊ���з�
	while ('\n' != Formula[strlen(Formula)-1]) {
		//���������ȡһ��������ַ������д洢,�ַ������ȵ�����Ϊ��ʼֵ��һ�㣨25byte��
		Increment++;
		char* Temp = (char*)realloc(Formula, (InitSize + Increment * (InitSize / 2)));
		if (Temp == NULL) {
			printf("��ȡ�ڴ�ʧ��");
			free(Temp);
			return NULL;
		}
		//��ȡ�������ʽ���ַ�
		Formula = Temp;
		fgets(Formula + strlen(Formula), (InitSize / 2), stdin);
	} 

	Formula[strlen(Formula)] = '\0';
	//printf("%s", Formula);
	////�ȵ���ȡ����������ʽ���ٷ���
	////��ֹ����51���ַ��ļ���������˷��ڴ�
	//char* Temp = (char*)realloc(Formula, sizeof(char)*strlen(Formula));
	//if (Temp == NULL) {
	//	printf("��ȡ�ڴ�ʧ��");
	//	free(Temp);
	//	return NULL;
	//}
	//Formula = Temp;
	//Formula[strlen(Formula)] = '\0';
	//printf("%s", Formula);
	return Formula;
	//����realloc����ַ�������һ����λ����һ������ַ������
}



//�Լ���ʽ����Ԥ�������õ���ʽ�ӽ������·�װ���һ�����������Դ�����ݺ����������
Formula PackageFormula(char* formula) {
	int i = 0,LastIndex = 0,NumberPart = 0,SymbolPart = 0;
	//���ڴ�ż���ʽ�е����ֲ���
	char** FormulaNumbArr = (char**)malloc(sizeof(char*) * 2);
	char* FormulaSymbol = (char*)malloc(sizeof(char)*2);
	if (FormulaNumbArr == NULL || FormulaSymbol == NULL) {
		printf("�����ڴ�����쳣��");
		free(FormulaNumbArr);
		free(FormulaSymbol);
		return;
	}
	while (i < strlen(formula)) {
		//��formula������
		if ((formula[i] > '0' && formula[i] < '9')||(formula[i] == '.')) {
		}
		else if (formula[i] == '+' || formula[i] == '-' || formula[i] == '*' || formula[i] == '/' || formula[i] == '��') {
			Result* temp = MyStrtok(formula, i, LastIndex);
			//�����ݴ�ŵ�FormulaArr����,���Part��С��2��˵����ʱʽ�ӵ���ɲ��ֲ�ֹ3��Ӧ������
			if (NumberPart >= 2) {
				//��ŷ��ź����ֵ��������ӳ���
				char* TempFormulaSymbol = (char*)realloc(FormulaSymbol, sizeof(char) * (SymbolPart + 1));
				char** TempFormulaNumbArr = (char**)realloc(FormulaNumbArr, sizeof(char*) * (NumberPart + 1));
				if (TempFormulaSymbol == NULL || TempFormulaNumbArr == NULL) {
					printf("�����ڴ�����쳣��");
					free(TempFormulaSymbol);
					free(TempFormulaNumbArr);
					return;
				}
				FormulaNumbArr = TempFormulaNumbArr;
				FormulaSymbol = TempFormulaSymbol;
			}
			FormulaNumbArr[NumberPart] = temp->number;
			FormulaSymbol[SymbolPart] = *(temp->symbol);
			NumberPart++;
			SymbolPart++;
			//������һ���и������
			LastIndex = i;
		}
		//�������ұ߲�������֣�ֱ�ӷŽ������м���
		else if (formula[i] == '(' ) {
			if (SymbolPart > 1) {

			}
		}
		else {
			//�������Ƕ��޹��ַ��Ĵ�����Ҫ�����ⲿ�֣��ȸ�ֵΪͳһ��һ���ַ���_'
			formula[i] = '_';
		}
		i++;
	}
	FormulaSymbol[SymbolPart] = '\0';
	Formula PreparedFormula = {FormulaNumbArr,FormulaSymbol,NumberPart};
	return PreparedFormula;
}

//�����ݽ��д���ȥ�������еġ�_'�ַ�
void ProcessingFormula(Formula PreparedFormula) {
	//����ÿһ������
	for (int index = 0;index < PreparedFormula.PartNumb;index++) {
		//���¿���һ��������
		int lenth = strlen(PreparedFormula.FormulaNumb[index]);
		char* ProcessedFormula = (char*)malloc(sizeof(char) * lenth);
		if (ProcessedFormula == NULL) {
			printf("�ַ��������ַ����");
			free(ProcessedFormula);
			return;
		}
		int NumberIndex = 0;
		for (int i = 0;i < lenth;i++) {
			if (PreparedFormula.FormulaNumb[i] != '_') {
				ProcessedFormula[NumberIndex] = PreparedFormula.FormulaNumb[i];
				NumberIndex++;
			}
		}
		//���ͷ�ԭ�ȵ�����
		free(PreparedFormula.FormulaNumb[index]);
		//���µ��ַ�����ֵ��Ԥ�������ʽ
		char* Temp = (char*)realloc(ProcessedFormula, strlen(ProcessedFormula));
		PreparedFormula.FormulaNumb[index] = ProcessedFormula;
	}
}

//TokIndex����ȷ������Ҫ�и��λ�㣬LastPoint����ȷ����һ���и��λ��
Result* MyStrtok(char* String, int TokIndex, int LastIndex) {
	// ������֤
	if (String == NULL || TokIndex <= LastIndex || TokIndex > (int)strlen(String)) {
		return NULL;
	}

	// �������ṹ��
	Result* result = (Result*)malloc(sizeof(Result));
	if (result == NULL) {
		printf("�ڴ����ʧ��\n");
		return NULL;
	}

	// �������ֲ����ڴ� 
	result->number = (char*)malloc(TokIndex - LastIndex + 1);
	if (result->number == NULL) {
		printf("�ڴ����ʧ��\n");
		free(result);
		return NULL;
	}

	// ������Ų����ڴ�
	result->symbol = (char*)malloc(2);
	if (result->symbol == NULL) {
		printf("�ڴ����ʧ��\n");
		free(result->number);
		free(result);
		return NULL;
	}

	// �������ֲ���
	strncpy(result->number, String + LastIndex, TokIndex - LastIndex);
	result->number[TokIndex - LastIndex] = '\0';

	// ���������
	result->symbol[0] = String[TokIndex];
	result->symbol[1] = '\0';

	return result;
}


