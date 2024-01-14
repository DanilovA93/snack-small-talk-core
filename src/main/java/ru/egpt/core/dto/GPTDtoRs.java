package ru.egpt.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GPTDtoRs {
  private String model;
  private String created_at;
  private String response;
}
