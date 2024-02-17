package ru.egpt.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TTSDtoRq {

  @JsonProperty("speaker_id")
  private Long speakerId;

  private String text;
}
