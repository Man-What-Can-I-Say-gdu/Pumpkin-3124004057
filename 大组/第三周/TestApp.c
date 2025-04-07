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


//输出上述排序函数在不同的大数据量下的用时，有三个层次（10000、50000、200000）
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

//获得测试用的数据并存放到文件中
//创建两个文件，一个用于存放测试次数，时间，配置，一个用于存放数据
void SubmitTestArr(int lenth,int max,int min) {
	//获得用于存放测试数据的文件
	FILE* fp = fopen("BefTest.txt", 'w');
	if (fp == NULL) {
		printf("获取文件错误");
		return;
	}
	//增加记录
	int Times = SetTestTimes();
	//进行写入操作
	for (int i = 0;i < lenth;i++) {
		fprintf(fp, "%d ", rand() % (max - min + 1) + min);
		if (i % 100 == 0) {
			//每100个数就换行一次
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
		//获取上次传输的数据
		//未到文件末尾则一直读
		while (EOF != fscanf(fp, "第%d次: %d,%d,%d %d:%d:%d\n", &Times, &(ThisTime->tm_year), &(ThisTime->tm_mon), &(ThisTime->tm_mday), &(ThisTime->tm_hour), &(ThisTime->tm_min), &(ThisTime->tm_sec)));
	}
	//在条件外进行关闭则一定会关闭文件
	fclose(fp);
	time_t timer;
	time(&timer);
	ThisTime = localtime(&timer);
	fopen("TestTimes.txt", 'a');
	//进行数据写入
	fprintf(fp, "第%d次: %d,%d,%d %d:%d:%d\n", Times + 1, ThisTime->tm_year, ThisTime->tm_mon, ThisTime->tm_mday, ThisTime->tm_hour, ThisTime->tm_min, ThisTime->tm_sec);
	fclose(fp);
	return Times;
}

//实现获取数据并进行排序
//Max指传入数组的最大值，只有在SortModel为3时需要，其他时候可以传入0
void SortedArr( int lenth,int TestTimes,int SortModel,int Max) {
	FILE* fp = fopen("BefTest", 'r');
	if (fp == NULL) {
		printf("无需要排序的数组");
		return;
	}
	int* Arraylist = (int*)malloc(sizeof(int) * lenth);
	//读取对应数量的数字
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
		printf("未成功创建文件");
		return;
	}
	for (int i = 0;i < lenth;i++) {
		fprintf(fp, "%d ", Arraylist[i]);
	}
	fclose(fp);
}



//现给定一个长度为N的数组，里面存有值0,1或2，请编写一个函数，只能使用一个单层for循环，将其从小到大进行排序

void Test5_1(int N) {
	int* Arraylist = (int*)malloc(sizeof(int) * N);
	srand((unsigned int)time(NULL));
	//随机获得N个数
	printf("排序前的数组：");
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
	printf("排序后的数组");
	for (int i = 0;i < N;i++) {
		printf("%d ", Arraylist[i]);
	}
	free(Arraylist);
}
void Test5_2(int N,int K) {
	int* Arraylist = (int*)malloc(sizeof(int) * N);
	srand((unsigned int)time(NULL));
	//随机获得N个数
	printf("排序前的数组：");
	for (int i = 0;i < N;i++) {
		Arraylist[i] = rand();
		printf("%d", Arraylist[i]);
	}
	QuickSort(Arraylist, 0, N - 1);
	printf("第K小的数为：%d", Arraylist[K]);
	free(Arraylist);
}


