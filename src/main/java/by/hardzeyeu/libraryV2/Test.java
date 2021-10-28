package by.hardzeyeu.libraryV2;

public class Test {

    public static void main(String[] args) {
        System.out.println(f(3));


    }

    static int f(int n) {
        if (n == 0) {
            return 1;
        }

        return n = n * f(n - 1);

    }


}
