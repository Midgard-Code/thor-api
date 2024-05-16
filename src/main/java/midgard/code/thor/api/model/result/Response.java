package midgard.code.thor.api.model.result;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response<T> {
    private List<ErrorResult> errors = new ArrayList<>();

    private T data;

    private boolean success = true;

    public Response(T data) {
        this.data = data;
    }

    public Response(List<ErrorResult> errors) {
        this.errors = errors;
        this.success = false;
    }
}
