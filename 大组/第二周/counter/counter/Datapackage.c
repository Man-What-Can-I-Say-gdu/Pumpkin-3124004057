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
	//�ȵ���ȡ����������ʽ���ٷ���
	//��ֹ����51���ַ��ļ���������˷��ڴ�
	char* Temp = (char*)realloc(Formula, strlen(Formula));
	if (Temp == NULL) {
		printf("��ȡ�ڴ�ʧ��");
		free(Temp);
		return NULL;
	}
	Formula = Temp;
	Formula[strlen(Formula)-1] = '\0';
	return Formula;
}



//�����ֽ���Ԥ�������õ���ʽ�ӽ������·�װ���һ�����������Դ�����ݺ����������
Formula PackageFormula(char* formula) {
	int i = 0,LastIndex = 0,NumberPart = 0,SymbolPart = 0;
	//���ڴ�ż���ʽ�е����ֲ���
	char** FormulaNumbArr = (char**)malloc(sizeof(char*) * 2);
	char* FormulaSymbol = (char*)malloc(sizeof(char));
	if (FormulaNumbArr == NULL || FormulaSymbol == NULL) {
		printf("�����ڴ�����쳣��");
		free(FormulaNumbArr);
		free(FormulaSymbol);
		return;
	}
	while (i < strlen(formula)) {
		//��formula������
		if ((formula[i] > '0' && formula[i] < '9')||(formula[i] == '.')) {
			continue;
		}
		else if (formula[i] == '+' || formula[i] == '-' || formula[i] == '*' || formula[i] == '/' || formula[i] == '��') {
			char** temp = MyStrtok(formula, i, LastIndex);
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
			FormulaNumbArr[NumberPart] = temp[0];
			FormulaSymbol[SymbolPart] = temp[1];
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

	}
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
char** MyStrtok(char* String, int TokIndex,int LastIndex) {
	//��ȡ���ڴ�����ֵ�����
	char* Number = (char*)malloc(TokIndex - LastIndex + 1);
	//���ڴ�ŷ��ŵ�����
	char* Operator = (char*)malloc(sizeof(char) * 2);
	char* PartOfFormula[2] = { Number,Operator };
	if (Number == NULL || Operator == NULL) {
		printf("�����ڴ�ʧ��");
		free(Number);
		free(Operator);
		return NULL;
	}
	if (*(strncpy(Number, String + LastIndex, TokIndex - LastIndex)) == NULL) {
		printf("�и�ʧ�ܣ�");
		free(Number);
		free(Operator);
		return NULL;
	}
	//�����ַ��������һλΪ��\0����ֹ�ڴ�й¶
	Number[TokIndex - LastIndex] = '\0';
	//��ȡ�����
	Operator[0] = String[TokIndex];
	//ͬ97������
	Operator[1] = '\0';
	return PartOfFormula;
}


