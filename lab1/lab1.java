public class lab1{


	/*
	Разобьем наше решение на действия
	Таким образом мы облегчим читабельность кода
	И сложнее сделать ошибку в действиях.
	Сделаем это переприсваиванием переменной результата 
	каждого математического действия
	*/

	//функция для случаев с=5
	public static double func1(double x){
		double result;

		result = Math.cos(x);
		result = Math.pow(result,Math.pow(Math.E, x));
		result = Math.pow(result, 0.5);
		result++;
		result = Math.pow(result, 3);

		return result;
	}

	//функция для случаев когда с = 8 и тд (2 условие)
	public static double func2(double x){
		double result;
		double degree; //создадим переменную для подсчета отдельно степени

		result = Math.pow(x, (double)1/3);
		result = Math.cos(result);
		degree = Math.pow(2 - x, 2) / 2;
		degree = Math.pow(degree, 3) / 2;
		result = Math.pow(result, degree);

		return result;
	}

	//функция для всех остальных случаев
	public static double func3(double x){
		double result;

		result = Math.pow(x, x);
		result = Math.pow(x, 0.5) + 1;
		result = Math.sin(x) * result;
		result = Math.pow(result, 2);
		result = Math.pow(result, (double)1/3);
		result = result * 0.75;
		result = Math.pow(result, 3);

		return result;
	}


	public static void main(String[] args){
		final int firstC = 4; //первое элемент массива с по условию
		final int lastC = 19; //последний элемент массива с по условию
		final int firstX = -2; //первый элемент массива х по условию
		final int lastX = 4; //последний элемент массива х по условию
		final int countElementsInX = 10; //количество элементов в массиве х по условию
		final int firstSizeFinalArray = 16; //размер конечного массива по условию
		final int secondSizeFinalArray = 10; //второй размер конечного массива по условию

		short[] c = new short[lastC - firstC + 1]; 
		double[] x = new double[countElementsInX];
		double[][] u = new double[firstSizeFinalArray][secondSizeFinalArray];//double тк нецелые числа

		int step = 0; //шаг для заполнения массивов

		//заполнение массива с числами от 4 до 19
		for (short i=4; i<=19;i++){
			c[step] = i;
			step++;
		}

		//заполнение массива х рандомными числами от -2 до 4
		for (int i=0; i<10; i++){
			x[i] = Math.random()*(lastX - firstX) + firstX;
		}

		//заполнение последнего массива
		for(int i=0; i<firstSizeFinalArray; i++){
			for(int j=0; j<secondSizeFinalArray; j++){	


				if(c[i]==5){
					u[i][j] = func1(x[j]);
				}

				else if(c[i]==8 || c[i]==9 || c[i]==10 || c[i]==11 || c[i]==12 || c[i]==14 || c[i]==15 || c[i]==19){
					u[i][j] = func2(x[j]);
				}

				else{
					u[i][j] = func3(x[j]);
				}


				System.out.printf("%.2f", u[i][j]);//вывод с двумя знаками после запятой
				System.out.print(" ");

			}
			System.out.println();
		}
		
	}
}