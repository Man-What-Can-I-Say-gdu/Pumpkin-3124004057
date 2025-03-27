#include "counter.h"

//Formula�ṹ���Ǹ�����׺���ʽ��Ƶ�
//����׺���ʽת��Ϊ��׺���ʽ
char** TurnInfixToPostfix(Formula InfixFormula) {
	Stack* stack = (Stack*)malloc(sizeof(Stack));
	Node* Head = (Node*)malloc(sizeof(Node));
	InitLinkList(Head);
	InitStack(stack,Head);
	int NumberIndex = 0;
	int SymbolIndex = 0;
	int PostfixIndex = 0;
	char** Postfix = (char**)malloc(sizeof(char*) * (InfixFormula.PartNumb + InfixFormula.PartSymbol + 1));
	while (NumberIndex < InfixFormula.PartNumb || SymbolIndex < InfixFormula.PartSymbol) {
		if (InfixFormula.FormulaSymbol[SymbolIndex] == '(') {
			//������ֱ�ӽ�ջ
			Node* Element = (Node*)malloc(sizeof(Node));
			char* Symbol = (char*)malloc(sizeof(char) * 2);
			Symbol[0] = InfixFormula.FormulaSymbol[SymbolIndex];
			Symbol[1] = '\0';
			Element->data = Symbol;
			free(InfixFormula.FormulaSymbol[SymbolIndex]);
			Push(stack, Element);
			SymbolIndex++;
		}
		if (NumberIndex < InfixFormula.PartNumb) {
			//����ֱ�����
			Postfix[PostfixIndex] = InfixFormula.FormulaNumb[NumberIndex];
			PostfixIndex++;
		}
		if (SymbolIndex < InfixFormula.PartSymbol) {
			//�Է��Ž����ж�
			if (InfixFormula.FormulaSymbol[SymbolIndex] == '+' || InfixFormula.FormulaSymbol[SymbolIndex] == '-') {
				while ((GetTopElement(stack)->data) != '+' || (GetTopElement(stack)->data) != '-' || (GetTopElement(stack)->data) != '(') {
					Postfix[PostfixIndex] = Pop(stack);
					PostfixIndex++;
				}
				Node* Element = (Node*)malloc(sizeof(Node));
				char* Symbol = (char*)malloc(sizeof(char) * 2);
				Symbol[0] = InfixFormula.FormulaSymbol[SymbolIndex];
				Symbol[1] = '\0';
				Element->data = Symbol;
				free(InfixFormula.FormulaSymbol[SymbolIndex]);
				Push(stack, Element);
				SymbolIndex++;
			}
			else if (InfixFormula.FormulaSymbol[SymbolIndex] == '*' || InfixFormula.FormulaSymbol[SymbolIndex] == '/') {
				Node* Element = (Node*)malloc(sizeof(Node));
				char* Symbol = (char*)malloc(sizeof(char) * 2);
				Symbol[0] = InfixFormula.FormulaSymbol[SymbolIndex];
				Symbol[1] = '\0';
				Element->data = Symbol;
				free(InfixFormula.FormulaSymbol[SymbolIndex]);
				Push(stack, Element);
				SymbolIndex++;
			}
			else {
				//������
				while (stack->top->data == '(') {
					Postfix[PostfixIndex] = Pop(stack);
					PostfixIndex++;
				}
			}
			SymbolIndex++;
		}
		
	}
	//����ʵʱ��ջʣ�����ݳ�ջ����Ϊֹ
	while (stack->top == Head) {
		Postfix[PostfixIndex] = Pop(stack);
		PostfixIndex++;
	}
	Postfix[PostfixIndex] = '\0';
	return Postfix;
}


//ִ�м���
char* Count(char** Postfix) {
	Stack* stack = (Stack*)malloc(sizeof(Stack));
	Node* Head = (Node*)malloc(sizeof(Node));
	InitLinkList(Head);
	InitStack(stack, Head);
	int Index = 0;
	char* result;
	//�����û���ַ���������Ϊ���ַ����Ļ�
	while (*(Postfix[Index]) != '\0') {
		switch (*(Postfix[Index]))
		{
			//���Ž��м���
		case '+': {
			char* FirstCountNumb = Pop(stack);
			char* SecondCountNumb = Pop(stack);
			result = AddCaculate(FirstCountNumb, SecondCountNumb);
		};
		case '-': {
			char* FirstCountNumb = Pop(stack);
			char* SecondCountNumb = Pop(stack);
			result = SubCaculate(FirstCountNumb, SecondCountNumb);
		};
		case '*': {
			char* FirstCountNumb = Pop(stack);
			char* SecondCountNumb = Pop(stack);
			result = MulCaculate(FirstCountNumb, SecondCountNumb);
		};
		case '/': {
			char* FirstCountNumb = Pop(stack);
			char* SecondCountNumb = Pop(stack);
			result = DivCaculate(FirstCountNumb, SecondCountNumb);
		};
		default: {
			Node* Temp = (Node*)malloc(sizeof(Node));
			Temp->data = Postfix[Index];
			Push(stack,Temp);
		}
			break;
		}
	}
	free(stack);
	free(Head);
	free(Postfix);
	return result;
}