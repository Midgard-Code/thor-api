package midgard.code.thor.api.utils;

import midgard.code.thor.api.exception.InternalServerException;
import midgard.code.thor.api.exception.InvalidRequestException;

import java.util.HashMap;

public interface HttpClientManager {
    <T> T post(String endpoint, Object body, Class<T> responseClass) throws InternalServerException, InvalidRequestException;

    <T> T post(String endpoint, HashMap<String, String> queryParams, Object body, Class<T> responseClass) throws InternalServerException, InvalidRequestException;

    <T> T get(String endpoint, Class<T> responseClass) throws InternalServerException, InvalidRequestException;

    <T> T get(String endpoint, HashMap<String, String> queryParameters, Class<T> responseClass) throws InternalServerException, InvalidRequestException;
}
