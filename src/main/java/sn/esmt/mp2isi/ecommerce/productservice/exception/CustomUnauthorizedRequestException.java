package sn.esmt.mp2isi.ecommerce.productservice.exception;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@Getter
public class CustomUnauthorizedRequestException extends AbstractThrowableProblem {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";

    private final String entityName;

    private final String errorKey;

    public CustomUnauthorizedRequestException(String title, String detail) {
        super(
            URI.create(PROBLEM_BASE_URL + "/problem-with-message"),
            title == null ? "Requête non autorisée " : title,
            Status.UNAUTHORIZED,
            detail,
            null,
            null,
            getAlertParameters()
        );
        this.entityName = "PRODUCT_SERVICE";
        this.errorKey = "UNAUTHORIZED";
    }

    public CustomUnauthorizedRequestException(String detail) {
        this(null, detail);
    }

    private static Map<String, Object> getAlertParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + "UNAUTHORIZED");
        parameters.put("params", "PRODUCT_SERVICE");
        return parameters;
    }
}
