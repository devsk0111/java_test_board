import java.util.*;

public class Main {
    public static void main(String[] args) {

        // url로 &단위로 분석하게 된다.

        String queryString1 = "id=meme&age=32&occupation=developer&pay=400&stress=500000";
        Map<String, String> params1 = Util.getParams(queryString1);

        String queryString2 = "id=koko&age=25&occupation=translator&pay=200&stress=230000";
        Map<String, String> params2 = Util.getParams(queryString2);

        System.out.println(params1);
        System.out.println(params2);


    }

    class Util {
        static Map<String, String> getParams(String queryString) {
            String[] bits = queryString.split("&");
            Map<String, String> params = new LinkedHashMap<>();

            for(String bit : bits) {
                String[] bitBits = bit.split("=");
                params.put(bitBits[0], bitBits[1]);
            }

            return params;
        }
    }
}
