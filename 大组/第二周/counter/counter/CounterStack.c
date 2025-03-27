#include "counter.h"

//Formula结构体是根据中缀表达式设计的
//将中缀表达式转化为后缀表达式
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
			//左括号直接进栈
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
			//数字直接输出
			Postfix[PostfixIndex] = InfixFormula.FormulaNumb[NumberIndex];
			PostfixIndex++;
		}
		if (SymbolIndex < InfixFormula.PartSymbol) {
			//对符号进行判定
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
				//右括号
				while (stack->top->data == '(') {
					Postfix[PostfixIndex] = Pop(stack);
					PostfixIndex++;
				}
			}
			SymbolIndex++;
		}
		
	}
	//结束实时把栈剩余数据出栈到空为止
	while (stack->top == Head) {
		Postfix[PostfixIndex] = Pop(stack);
		PostfixIndex++;
	}
	Postfix[PostfixIndex] = '\0';
	return Postfix;
}


//执行计算
char* Count(char** Postfix) {
	Stack* stack = (Stack*)malloc(sizeof(Stack));
	Node* Head = (Node*)malloc(sizeof(Node));
	InitLinkList(Head);
	InitStack(stack, Head);
	int Index = 0;
	char* result;
	//解引用获得字符串，若不为空字符串的话
	while (*(Postfix[Index]) != '\0') {
		switch (*(Postfix[Index]))
		{
			//符号进行计算
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