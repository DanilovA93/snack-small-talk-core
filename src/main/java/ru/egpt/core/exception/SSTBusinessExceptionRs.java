package ru.egpt.core.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SSTBusinessExceptionRs {
    private String error;
}
