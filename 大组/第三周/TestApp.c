#include "main.h"

int* BuildRandArr(int Lenth, int Min, int Max) {
	int* Arraylist = (int*)calloc(Lenth,sizeof(int));
	if (Arraylist == NULL) {
		printf("NullPointerError");
		return NULL;
	}
	srand((unsigned int)time(NULL));
	for (int i = 0;i < Lenth;i++) {
		Arraylist[i] = rand()%(Max-Min+1)+Min;
	}
	return Arraylist;
}


void InsertSpendTime(int ArraylistLenth) {
	clock_t start, end;
	int* Arr = BuildRandArr(ArraylistLenth, 0, 100);
	start = clock();
	InsertSort(Arr, ArraylistLenth);
	end = clock();
	double SpendTime = ((double)(end - start)) / CLOCKS_PER_SEC;
	printf("%f\n", SpendTime);
	free(Arr);
}
void QuickSpendTime(int ArraylistLenth) {
	clock_t start, end;
	int* Arr = BuildRandArr(ArraylistLenth, 0, 100);
	start = clock();
	QuickSort(Arr,0, ArraylistLenth -1);
	end = clock();
	double SpendTime = ((double)(end - start)) / CLOCKS_PER_SEC;
	printf("%f\n", SpendTime);
	free(Arr);
}
void CountSpendTime(int ArraylistLenth) {
	clock_t start, end;
	int* Arr = BuildRandArr(ArraylistLenth, 0, 100);
	start = clock();
	CountSort(Arr,100, ArraylistLenth);
	end = clock();
	double SpendTime = ((double)(end - start)) / CLOCKS_PER_SEC;
	printf("%f\n", SpendTime);
	free(Arr);
}
void RadixCountSpendTime(int ArraylistLenth) {
	clock_t start, end;
	int* Arr = BuildRandArr(ArraylistLenth, 0, 100);
	start = clock();
	RadixCountSort(Arr, ArraylistLenth);
	end = clock();
	double SpendTime = ((double)(end - start)) / CLOCKS_PER_SEC;
	printf("%f\n", SpendTime);
	free(Arr);
}


//��������������ڲ�ͬ�Ĵ��������µ���ʱ����������Σ�10000��50000��200000��
void Test2(int lenth) {
	InsertSpendTime(lenth);
	QuickSpendTime(lenth);
	CountSpendTime(lenth);
	RadixCountSpendTime(lenth);
}

void Test3(int lenth) {
	clock_t start, end;
	double SpendTime = 0;
	for (int i = 0;i < 100000;i++) {
		int* Arr = BuildRandArr(lenth, 0, 100);
		start = clock();
		InsertSort(Arr, lenth);
		end = clock();
		SpendTime += ((double)(end - start)) / CLOCKS_PER_SEC;
		free(Arr);
	}
	printf("%f\n",SpendTime);
	SpendTime = 0;
	for (int i = 0;i < 100000;i++) {
		int* Arr = BuildRandArr(lenth, 0, 100);
		start = clock();
		QuickSort(Arr,0, lenth-1);
		end = clock();
		SpendTime += ((double)(end - start)) / CLOCKS_PER_SEC;
		free(Arr);
	}
	printf("%f\n", SpendTime);
	SpendTime = 0;
	for (int i = 0;i < 100000;i++) {
		int* Arr = BuildRandArr(lenth, 0, 100);
		start = clock();
		CountSort(Arr,100, lenth);
		end = clock();
		SpendTime += ((double)(end - start)) / CLOCKS_PER_SEC;
		free(Arr);
	}
	printf("%f\n", SpendTime);
	SpendTime = 0;
	for (int i = 0;i < 100000;i++) {
		int* Arr = BuildRandArr(lenth, 0, 100);
		start = clock();
		RadixCountSort(Arr, lenth);
		end = clock();
		SpendTime += ((double)(end - start)) / CLOCKS_PER_SEC;
		free(Arr);
	}
	printf("%f\n", SpendTime);
}

//��ò����õ����ݲ���ŵ��ļ���
//���������ļ���һ�����ڴ�Ų��Դ�����ʱ�䣬���ã�һ�����ڴ������
void SubmitTestArr(int lenth,int max,int min) {
	//������ڴ�Ų������ݵ��ļ�
	FILE* fp = fopen("BefTest.txt", 'w');
	if (fp == NULL) {
		printf("��ȡ�ļ�����");
		return;
	}
	//���Ӽ�¼
	int Times = SetTestTimes();
	//����д�����
	for (int i = 0;i < lenth;i++) {
		fprintf(fp, "%d ", rand() % (max - min + 1) + min);
		if (i % 100 == 0) {
			//ÿ100�����ͻ���һ��
			fprintf(fp, "\n");
		}
	}
	fclose(fp);
}
int SetTestTimes() {
	FILE* fp = fopen("TestTimes.txt", 'r');
	int Times = 0;
	struct tm* ThisTime = 0;
	if (fp != NULL) {
		//��ȡ�ϴδ��������
		//δ���ļ�ĩβ��һֱ��
		while (EOF != fscanf(fp, "��%d��: %d,%d,%d %d:%d:%d\n", &Times, &(ThisTime->tm_year), &(ThisTime->tm_mon), &(ThisTime->tm_mday), &(ThisTime->tm_hour), &(ThisTime->tm_min), &(ThisTime->tm_sec)));
	}
	//����������йر���һ����ر��ļ�
	fclose(fp);
	time_t timer;
	time(&timer);
	ThisTime = localtime(&timer);
	fopen("TestTimes.txt", 'a');
	//��������д��
	fprintf(fp, "��%d��: %d,%d,%d %d:%d:%d\n", Times + 1, ThisTime->tm_year, ThisTime->tm_mon, ThisTime->tm_mday, ThisTime->tm_hour, ThisTime->tm_min, ThisTime->tm_sec);
	fclose(fp);
	return Times;
}

//ʵ�ֻ�ȡ���ݲ���������
//Maxָ������������ֵ��ֻ����SortModelΪ3ʱ��Ҫ������ʱ����Դ���0
void SortedArr( int lenth,int TestTimes,int SortModel,int Max) {
	FILE* fp = fopen("BefTest", 'r');
	if (fp == NULL) {
		printf("����Ҫ���������");
		return;
	}
	int* Arraylist = (int*)malloc(sizeof(int) * lenth);
	//��ȡ��Ӧ����������
	for (int i = 0;i < lenth;i++) {
		fscanf(fp, "%d ", Arraylist[i]);
	}
	fclose(fp);
	switch (SortModel) {
	case 1:
		InsertSort(Arraylist, lenth);
		break;
	case 2:
		QuickSort(Arraylist, 0, lenth - 1);
		break;
	case 3:
		CountSort(Arraylist, Max, lenth);
		break;
	case 4:
		RadixCountSort(Arraylist, lenth);
		break;
	}
	fp = fopen("AftTest", 'w');
	if (fp == NULL) {
		printf("δ�ɹ������ļ�");
		return;
	}
	for (int i = 0;i < lenth;i++) {
		fprintf(fp, "%d ", Arraylist[i]);
	}
	fclose(fp);
}



//�ָ���һ������ΪN�����飬�������ֵ0,1��2�����дһ��������ֻ��ʹ��һ������forѭ���������С�����������

void Test5_1(int N) {
	int* Arraylist = (int*)malloc(sizeof(int) * N);
	srand((unsigned int)time(NULL));
	//������N����
	printf("����ǰ�����飺");
	for (int i = 0;i < N;i++) {
		Arraylist[i] = rand();
		printf("%d", Arraylist[i]);
	}

	int p0 = 0;
	int p2 = N - 1;
	for (int tmp = 0;tmp < N;tmp++) {
		if (Arraylist[tmp] == 0) {
			swap(Arraylist[p0], Arraylist[tmp]);
			p0++;
		}
		else if (Arraylist[tmp] == 2) {
			swap(Arraylist[p2], Arraylist[tmp]);
			p2--;
		}
		else {
			continue;
		}
	}
	printf("����������");
	for (int i = 0;i < N;i++) {
		printf("%d ", Arraylist[i]);
	}
	free(Arraylist);
}
void Test5_2(int N,int K) {
	int* Arraylist = (int*)malloc(sizeof(int) * N);
	srand((unsigned int)time(NULL));
	//������N����
	printf("����ǰ�����飺");
	for (int i = 0;i < N;i++) {
		Arraylist[i] = rand();
		printf("%d", Arraylist[i]);
	}
	QuickSort(Arraylist, 0, N - 1);
	printf("��KС����Ϊ��%d", Arraylist[K]);
	free(Arraylist);
}


