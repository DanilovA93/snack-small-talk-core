package ru.egpt.core.dao.tts;

import java.io.InputStream;

@FunctionalInterface
public interface TTSDao {
  InputStream getAudio(String text);
}
