package code;

public class FirstProgram {

    public static void main(String[] args) {
        task7_1();
        task7_2();
        task8_1();
        task8_2();
    }

    private static void task7_1() {
        final double PI = 3.14;
        System.out.println("Значение числа π равно " + PI);
    }

    private static void task7_2() {
        int x = 5;
        System.out.println("Искомое число равно " + x);
    }

    private static void task8_1() {
        short i = 7778;
        float f = 876.351f;
        String n = "Игорь";
        String s = "‘ny’";
        boolean b = true;
        System.out.println("Значение целочисленной переменной - " + i + "\n" +
                "Значение числа с плавающей точкой - " + f + "\n" +
                "Значение строковой переменной - " + n + "\n" +
                "Значение строковой переменной - " + s + "\n" +
                "Булево значение - " + b);
    }

    private static void task8_2() {
        byte a = 1;
        short b = 2;
        long c = a + b;
        System.out.println(c);
        int d = 1;
        int e = 2;
        String f = Integer.toString(d) + e;
        System.out.println(f);
    }

}
