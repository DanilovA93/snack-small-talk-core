package ru.egpt.core.service;

import java.io.InputStream;

@FunctionalInterface
public interface ChatService {
  InputStream chat(InputStream speechInputStream);
}
