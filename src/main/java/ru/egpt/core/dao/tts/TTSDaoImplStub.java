package ru.egpt.core.dao.tts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub", havingValue="true")
public class TTSDaoImplStub implements TTSDao {

  @Override
  public InputStream getAudio(String text) {
    try {
      File file = ResourceUtils.getFile("classpath:tts_stub");
      return new FileInputStream(file);
    } catch (FileNotFoundException e) {
      return InputStream.nullInputStream();
    }
  }
}
