package ru.egpt.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TTSDtoRq {

  @JsonProperty("speaker_id")
  private Long speakerId;

  private String text;
}
