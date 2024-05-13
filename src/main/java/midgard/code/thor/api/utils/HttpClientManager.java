package midgard.code.thor.api.utils;

import java.util.HashMap;

public interface HttpClientManager {
    <T> T post(String endpoint, Object body, Class<T> responseClass);

    <T> T post(String endpoint, HashMap<String, String> queryParams, Object body, Class<T> responseClass);

    <T> T get(String endpoint, Class<T> responseClass);

    <T> T get(String endpoint, HashMap<String, String> queryParameters, Class<T> responseClass);
}
