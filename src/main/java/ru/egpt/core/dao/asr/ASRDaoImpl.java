package ru.egpt.core.dao.asr;

import java.io.IOException;
import java.io.InputStream;
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

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub.enabled", havingValue="false")
public class ASRDaoImpl implements ASRDao {

  private final RestTemplate restTemplate;

  @Value("${chat.services.asr.endpoint}")
  private String endpoint;

  @Override
  @MeasureTime("ASR")
  public String getText(InputStream in) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
      HttpEntity<byte[]> requestEntity = new HttpEntity<>(in.readAllBytes(), headers);
      ResponseEntity<String> responseEntity = restTemplate
          .exchange(endpoint, HttpMethod.POST, requestEntity, String.class);
      String text = responseEntity.getBody();
      if (Objects.isNull(text)) {
        throw new RuntimeException("[ASR] Ошибка: в ответе отсутствует тело");
      }
      return text;
    } catch (IOException e) {
      throw new RuntimeException("[ASR] Ошибка: невозможно прочитать аудио файл", e);
    }
  }
}
