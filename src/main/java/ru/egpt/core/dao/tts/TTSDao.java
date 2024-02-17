package ru.egpt.core.dao.tts;

import java.io.InputStream;
import org.springframework.http.HttpHeaders;

@FunctionalInterface
public interface TTSDao {
  InputStream getAudio(HttpHeaders headers, String text);
}
