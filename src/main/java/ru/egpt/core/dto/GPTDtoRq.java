package ru.egpt.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GPTDtoRq {
  private final String model = "llama2";
  private final boolean stream = false;
  private String prompt;
  private Options options = new Options();

  private static class Options {

    @JsonProperty("mirostat_eta")
    float mirostatEta = 0.1f;

    @JsonProperty("mirostat_tau")
    float mirostatTau = 1.0f;

    @JsonProperty("num_ctx")
    int numCtx = 1024;

    @JsonProperty("num_thread")
    int numThread = 4;

    @JsonProperty("repeat_last_n")
    int repeatLastN = 0;

    @JsonProperty("tfs_z")
    int tfsZ = 10;

    @JsonProperty("num_predict")
    int numPredict = 256;

    @JsonProperty("top_k")
    int topK = 10;

    @JsonProperty("top_p")
    float topP = 0.2f;
  }
}
