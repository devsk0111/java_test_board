import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // url로 &단위로 분석하게 된다.


        String queryString = "a=1&b=2&b=3";
        String[] bits = queryString.split("&");
        System.out.println(Arrays.toString(bits));

        for (String bit : bits) {
            System.out.println(bit);
        }

        System.out.print("\n");

        Arrays.stream(bits).forEach(System.out::println);

    }
}
