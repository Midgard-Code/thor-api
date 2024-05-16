package midgard.code.thor.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import midgard.code.thor.api.exception.InternalServerException;
import midgard.code.thor.api.exception.InvalidRequestException;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpClientManagerImpl implements HttpClientManager {
    public static final String SPACE = " ";

    @Override
    public <T> T post(String endpoint, Object body, Class<T> responseClass) throws InternalServerException, InvalidRequestException {
        HttpRequest request = createRequest(HttpMethod.POST, endpoint, null, body);

        return handleRequest(request, responseClass);
    }

    @Override
    public <T> T post(String endpoint, HashMap<String, String> queryParams, Object body, Class<T> responseClass) throws InternalServerException, InvalidRequestException {
        HttpRequest request = createRequest(HttpMethod.POST, endpoint, queryParams, body);

        return handleRequest(request, responseClass);
    }

    @Override
    public <T> T get(String endpoint, Class<T> responseClass) throws InternalServerException, InvalidRequestException {
        HttpRequest request = createRequest(HttpMethod.GET, endpoint, null, null);

        return handleRequest(request, responseClass);
    }

    @Override
    public <T> T get(String endpoint, HashMap<String, String> queryParameters, Class<T> responseClass) throws InternalServerException, InvalidRequestException {
        HttpRequest request = createRequest(HttpMethod.GET, endpoint, queryParameters, null);

        return handleRequest(request, responseClass);
    }

    public <T> T handleRequest(HttpRequest request, Class<T> responseClass) throws InvalidRequestException, InternalServerException {
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new InvalidRequestException("The call to the endpoint '" + request.uri().toString() +
                        "' gave a response with a status code '" + response.statusCode() + "'");
            }

            return new ObjectMapper().readValue(response.body(), responseClass);
        } catch (IOException | InterruptedException e) {
            throw new InternalServerException("An error occurred while reading the response from the API." +
                    " Error message: " + e.getMessage());
        }
    }

    public HttpRequest createRequest(HttpMethod method, String endpoint, HashMap<String, String> queryParameters, Object body) throws InvalidRequestException, InternalServerException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .header("Content-Type", "application/json");

        String endpointUrl = addQueryParameters(endpoint, queryParameters);

        try {
            requestBuilder = requestBuilder.uri(new URI(endpointUrl));
        } catch (URISyntaxException e) {
            throw new InvalidRequestException("The URL '" + endpointUrl + "' could not be converted to an URI. " +
                    "Error message: " + e.getMessage());
        }

        if (method.equals(HttpMethod.GET)) {
            requestBuilder.GET();
        } else if (method.equals(HttpMethod.POST)) {
            requestBuilder.POST(HttpRequest.BodyPublishers.ofString(getJsonBody(body)));
        } else if (method.equals(HttpMethod.PUT)) {
            requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(getJsonBody(body)));
        } else if (method.equals(HttpMethod.DELETE)) {
            requestBuilder.DELETE();
        }

        return requestBuilder.build();
    }

    public String getJsonBody(Object body) throws InternalServerException {
        try {
            return new ObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new InternalServerException("The body of the request could not be converted into a JSON string. " +
                    "Error message: " + e.getMessage());
        }
    }

    public String addQueryParameters(String endpoint, HashMap<String, String> queryParameters) {
        if (queryParameters == null || queryParameters.isEmpty()) {
            return endpoint;
        }

        StringBuilder endpointWithParamsBuilder = new StringBuilder(endpoint + "?");

        for (Map.Entry<String, String> queryParameter : queryParameters.entrySet()) {
            endpointWithParamsBuilder.append(queryParameter.getKey())
                    .append("=")
                    .append(queryParameter.getValue())
                    .append("&");
        }

        String endpointWithParams = endpointWithParamsBuilder.toString();

        endpointWithParams = endpointWithParams.replace(SPACE, "%20");

        return endpointWithParams.substring(0, endpointWithParams.length() - 1);
    }
}
