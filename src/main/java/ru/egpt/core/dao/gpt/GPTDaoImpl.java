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
import ru.egpt.core.aop.MeasureTime;
import ru.egpt.core.dto.GPTDtoRq;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub.enabled", havingValue="false")
public class GPTDaoImpl implements GPTDao {

  private final RestTemplate restTemplate;

  @Value("${chat.services.gpt.endpoint}")
  private String endpoint;

  @Override
  @MeasureTime("GPT")
  public String getText(String username, String text) {
    if (text.isBlank()) {
      log.warn("[GPT] Предупреждение: пустой текст запроса");
      return getBaseAnswer();
    }
    return getGeneratedAnswer(username, text);
  }

  private String getBaseAnswer() {
    return "Please repeat the question";
  }

  private String getGeneratedAnswer(String username, String text) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
      GPTDtoRq rq = new GPTDtoRq(username, text);
      HttpEntity<GPTDtoRq> requestEntity = new HttpEntity<>(rq, headers);
      ResponseEntity<String> responseEntity = restTemplate
              .exchange(endpoint, HttpMethod.POST, requestEntity, String.class);
      String rs = responseEntity.getBody();
      if (Objects.isNull(rs)) {
        log.error("[GPT] Ошибка: в ответе отсутствует тело");
        return getBaseAnswer();
      }
      return rs;
    } catch (Exception e) {
      throw new RuntimeException(
              "[GPT] " + e.getMessage(),
              e
      );
    }
  }
}
