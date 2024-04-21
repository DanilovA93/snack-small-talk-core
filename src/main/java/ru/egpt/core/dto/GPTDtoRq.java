package ru.egpt.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class GPTDtoRq {

  @JsonProperty("username")
  private String username;

  @JsonProperty("prompt")
  private String prompt;

  @JsonProperty("max_new_tokens")
  private final int maxNewTokens = 100;

  public GPTDtoRq(
          String username,
          String prompt
  ) {
    this.username = username;
    this.prompt = prompt;
  }
}
