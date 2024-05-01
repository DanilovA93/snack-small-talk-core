package ru.egpt.core.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.egpt.core.dao.model.ServiceType;

@Getter
@Setter
@Builder
public class SSTBusinessExceptionRs {
    private ServiceType serviceType;
    private String description;
    private String message;
}
