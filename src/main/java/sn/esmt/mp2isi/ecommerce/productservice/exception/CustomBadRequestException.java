package sn.esmt.mp2isi.ecommerce.productservice.exception;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@Getter
public class CustomBadRequestException extends AbstractThrowableProblem {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";

    private final String entityName;

    private final String errorKey;

    public CustomBadRequestException(String title, String detail) {
        super(
            URI.create(PROBLEM_BASE_URL + "/problem-with-message"),
            title == null ? "Requête incorrecte" : title,
            Status.BAD_REQUEST,
            detail,
            null,
            null,
            getAlertParameters(detail)
        );
        this.entityName = "PRODUCT_SERVICE";
        this.errorKey = "BAD_REQUEST";
    }

    public CustomBadRequestException(String detail) {
        this("Erreur Enregistrement", detail);
    }

    private static Map<String, Object> getAlertParameters(String detail) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", detail);
        parameters.put("params", "PRODUCT_SERVICE");
        return parameters;
    }
}
