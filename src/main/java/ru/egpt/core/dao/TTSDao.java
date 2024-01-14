package ru.egpt.core.dao;

import java.io.InputStream;

@FunctionalInterface
public interface TTSDao {
  InputStream getAudio(String text);
}
