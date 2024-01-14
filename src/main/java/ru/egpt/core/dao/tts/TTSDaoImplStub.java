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
import ru.egpt.core.dto.TTSDtoRq;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub", havingValue="true")
public class TTSDaoImplStub implements TTSDao {

  @Override
  public InputStream getAudio(String text) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    return InputStream.nullInputStream();
  }
}
