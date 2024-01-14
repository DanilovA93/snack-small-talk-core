package ru.egpt.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GPTDtoRq {
  private final String model = "llama2";
  private final boolean stream = false;
  private String prompt;
}
