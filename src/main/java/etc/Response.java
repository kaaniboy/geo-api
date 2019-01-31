package etc;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Response {
    private String status;
    private JsonElement data;

    private Response (String status, JsonElement data) {
        this.status = status;
        this.data = data;
    }

    public static String success(JsonElement data) {
        Response res = new Response("success", data);
        return new Gson().toJson(res);
    }

    public static String fail(String message) {
        Response res = new Response("fail", new Gson().toJsonTree(message));
        return new Gson().toJson(res);
    }

    public static String fail(JsonElement data) {
        Response res = new Response("fail", data);
        return new Gson().toJson(res);
    }

    public static <T> List<String> getValidationMessages(Set<ConstraintViolation<T>> issues) {
        return issues.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}
