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
import ru.egpt.core.dto.TTSDtoRq;

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
  public InputStream getAudio(HttpHeaders headers, String text) {
    long speakerId = Long.parseLong(
        Objects.requireNonNull(
            headers.getFirst("speaker_id"),
            "\"speaker_id\" of header is null"
        )
    );
    headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    TTSDtoRq rq = new TTSDtoRq();
    rq.setText(text);
    rq.setSpeakerId(speakerId);
    HttpEntity<TTSDtoRq> requestEntity = new HttpEntity<>(rq, headers);
    log.info("TTS Rq: {}", requestEntity);
    ResponseEntity<Resource> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, requestEntity, Resource.class);
    Resource rs = responseEntity.getBody();
    if (Objects.isNull(rs)) {
      throw new RuntimeException("[TTS] Ошибка: в ответе отсутствует тело");
    }

    try (InputStream is = rs.getInputStream()) {
      return is;
    } catch (IOException e) {
      throw new RuntimeException("[TTS] Ошибка: ", e);
    }
  }
}
