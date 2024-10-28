import java.util.*;

public class Main {
    public static void main(String[] args) {

        // url로 &단위로 분석하게 된다.

        String queryString = "id=meme&age=32&occupation=developer&pay=400&stress=500000";
        String[] bits = queryString.split("&");

        Map<String, String> params = new LinkedHashMap<>(); //HashMap vs LinkedHashMap : 순서 보장, but 크게 상관 없다

        for(String bit : bits) {
            String[] bitBits = bit.split("=");
            params.put(bitBits[0], bitBits[1]);
        }


        System.out.println("==원하는 것을 이렇게 가져 오겠습니다 ==");
        System.out.printf("나이 : %d\n", Integer.parseInt(params.get("age")));
        System.out.printf("ID : %s\n", params.get("id"));

        System.out.println("");

        System.out.println("== 반복문으로 ==");

        for(String paramName : params.keySet()) {
            String paramValue = params.get(paramName);

            System.out.printf("%s : %s\n", paramName, paramValue);
        }

    }
}
