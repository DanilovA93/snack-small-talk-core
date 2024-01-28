package ru.egpt.core.dao.gpt;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egpt.core.dto.GPTDtoRq;
import ru.egpt.core.dto.GPTDtoRs;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub.enabled", havingValue="false")
public class GPTDaoImpl implements GPTDao {

  private final RestTemplate restTemplate;

  @Value("${chat.services.gpt.endpoint}")
  private String endpoint;

  @Override
  public String getAnswer(String text) {
    if (text.isBlank()) {
      log.warn("[GPT] Предупреждение: пустой текст запроса");
      return getBaseAnswer();
    }
    return getGeneratedAnswer(text);
  }

  private String getBaseAnswer() {
    return "Please repeat the question";
  }

  private String getGeneratedAnswer(String text) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    GPTDtoRq rq = new GPTDtoRq();
    rq.setPrompt(text);
    HttpEntity<GPTDtoRq> requestEntity = new HttpEntity<>(rq, headers);
    ResponseEntity<GPTDtoRs> responseEntity = restTemplate
        .exchange(endpoint, HttpMethod.POST, requestEntity, GPTDtoRs.class);
    GPTDtoRs rs = responseEntity.getBody();
    log.info("Ответ GPT: " + rs);
    if (Objects.isNull(rs)) {
      throw new RuntimeException("[GPT] Ошибка: в ответе отсутствует тело");
    }
    return rs.getResponse();
  }
}
