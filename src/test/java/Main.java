import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<String, String> params = Util.getParamsFromUrl("/usr/article/list?id=3&memberId=13&boardId=2&hit=73&idName=박승근");

        System.out.println(params);

    }

    static class Util {
        static Map<String, String> getParamsFromUrl(String queryString) {
            Map<String, String> params = new LinkedHashMap<>();

            // Split the URL into path and query parts
            String[] split = queryString.split("\\?", 2); // Limit to 2 splits
            if (split.length < 2) {
                // No query string present, return empty map
                return params;
            }

            String query = split[1];
            String[] pairs = query.split("&");

            for (String pair : pairs) {
                String[] keyValue = pair.split("=", 2); // Limit to 2 splits to handle missing values
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                } else if (keyValue.length == 1) {
                    params.put(keyValue[0], "");
                }
            }

            return params;
        }
    }
}
