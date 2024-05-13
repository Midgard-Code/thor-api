package midgard.code.thor.api.utils;

import java.util.HashMap;

public interface HttpClientManager {
    <T> T post(String endpoint, Object body);

    <T> T post(String endpoint, HashMap<String, String> queryParams, Object body);

    <T> T get(String endpoint);

    <T> T get(String endpoint, HashMap<String, String> queryParameters);
}
