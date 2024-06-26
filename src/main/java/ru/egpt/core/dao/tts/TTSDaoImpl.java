package ru.egpt.core.dao.tts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.egpt.core.aop.MeasureTime;
import ru.egpt.core.dao.model.ServiceType;
import ru.egpt.core.dto.TTSDtoRq;
import ru.egpt.core.exception.SSTBusinessException;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub.enabled", havingValue="false")
public class TTSDaoImpl implements TTSDao {

  private final RestTemplate restTemplate;

  @Value("${chat.services.tts.endpoint}")
  private String endpoint;

  @Override
  @MeasureTime("TTS")
  public InputStream getAudio(HttpHeaders headers, String prompt) {
    try {
      headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
      TTSDtoRq rq = new TTSDtoRq();
      rq.setPrompt(prompt);
      HttpEntity<TTSDtoRq> requestEntity = new HttpEntity<>(rq, headers);
      ResponseEntity<Resource> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, Resource.class);
      Resource rs = responseEntity.getBody();
      if (Objects.isNull(rs)) {
        throw new RuntimeException("Ошибка: в ответе отсутствует тело");
      }
      try (InputStream is = rs.getInputStream()) {
        return is;
      } catch (IOException e) {
        throw new RuntimeException("Ошибка: ", e);
      }
    }
    catch (Exception e) {
      throw new SSTBusinessException(
              ServiceType.TTS,
              "Ошибка при обращении к сервису",
              e
      );
    }
  }
}
