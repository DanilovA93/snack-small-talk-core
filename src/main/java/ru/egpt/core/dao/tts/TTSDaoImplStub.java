package ru.egpt.core.dao.tts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
@Slf4j
@ConditionalOnProperty(name="chat.stub.enabled", havingValue="true")
public class TTSDaoImplStub implements TTSDao {

  private final String path;

  public TTSDaoImplStub(@Value("${chat.stub.file}") String path) {
    this.path = path;
  }

  @Override
  public InputStream getAudio(HttpHeaders headers, String prompt) {
    try {
      File file = ResourceUtils.getFile(path);
      return new FileInputStream(file);
    } catch (FileNotFoundException e) {
      return InputStream.nullInputStream();
    }
  }
}
