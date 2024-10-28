import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // url로 &단위로 분석하게 된다.

        String queryString = "a=1&b=2&c=3&d=400&e=500000";
        String[] bits = queryString.split("&");

        List<String> paramNames = new ArrayList<>();
        List<Integer> paramValues = new ArrayList<>();

        for(String bit : bits) {
            String[] bitBits = bit.split("=");
            String paramName = bitBits[0];
            String paramValue = bitBits[1];

            paramNames.add(paramName);
            paramValues.add(Integer.parseInt(paramValue));

        }

        for(int i = 0; i < paramNames.size(); i++) {
            String paramName = paramNames.get(i);
            int paramValue = paramValues.get(i);

            System.out.printf("%s : %d\n",  paramName, paramValue);
        }

    }
}
