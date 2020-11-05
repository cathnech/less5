import java.util.Arrays;


public class Main {
	static final int size = 10000000;
	static final int h = size / 2;

	public static void main(String[] args) {
		methodOne();                                                                                //Заполняют этот массив единицами
		methodTwo();
	}

	public static void methodOne(){
		float[] arr = new float[size];
		Arrays.fill(arr, 1);
		long a = System.currentTimeMillis();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)(i / 5)) * Math.cos(0.2f + (float)(i / 5)) * Math.cos(0.4f + (float)(i / 2)));    //Проходят по всему массиву и для каждой ячейки считают новое значение
		}
		System.out.printf("Время первого метода: %d \n",System.currentTimeMillis() - a);
	}

	public static void methodTwo(){
		float[] arr = new float[size];
		float[] a1 = new float[h];
		float[] a2 = new float[h];
		Arrays.fill(arr, 1);

		long a = System.currentTimeMillis();

		System.arraycopy(arr, 0, a1, 0, h);
		System.arraycopy(arr, h, a2, 0, h);

		Thread first = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < a1.length; i++) {
					a1[i] = (float) (a1[i] * Math.sin(0.2f + (float) (i / 5)) * Math.cos(0.2f + (float) (i / 5)) * Math.cos(0.4f + (float) (i / 2)));
				}
			}
		});
		first.start();

		Thread second = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < a2.length; i++) {
					a1[i] = (float) (a2[i] * Math.sin(0.2f + (float)((i+h) / 5)) * Math.cos(0.2f + (float)((i+h) / 5)) * Math.cos(0.4f + (float)((i+h) / 2)));
				}
			}

		});
		second.start();

		System.arraycopy(a1, 0, arr, 0, h);
		System.arraycopy(a2, 0, arr, h, h);
		System.out.printf("Время второго метода: %d \n",System.currentTimeMillis() - a);
	}
}
