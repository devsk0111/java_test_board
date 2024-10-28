import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // url로 &단위로 분석하게 된다.


        String queryString = "a=1&b=2&b=3";
        String[] bits = queryString.split("&");
        System.out.println(Arrays.toString(bits)); // 파라미터

        for (String bit : bits) {
            System.out.println(bit);
        }

        System.out.print("\n");

        Arrays.stream(bits).forEach(System.out::println);

        System.out.print("\n");

        for(String bit : bits) {
            String[] bitBits = bit.split("=");
            String paramName = bitBits[0];
            String paramValue = bitBits[1];

            System.out.printf("%s : %s\n", paramName, paramValue);
        }

    }
}
