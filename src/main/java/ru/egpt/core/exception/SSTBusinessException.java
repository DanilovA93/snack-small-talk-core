package ru.egpt.core.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.egpt.core.dao.model.ServiceType;

@Getter
@Setter
@Builder
public class SSTBusinessException extends RuntimeException {

    private final ServiceType serviceType;
    private final String description;

    public SSTBusinessException(
            ServiceType serviceType,
            String description,
            Throwable cause
    ) {
        super(cause);
        this.serviceType = serviceType;
        this.description = description;
    }
}
