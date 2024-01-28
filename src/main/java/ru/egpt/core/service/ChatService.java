package ru.egpt.core.service;

import java.io.InputStream;

public interface ChatService {
  InputStream audioChat(InputStream speechInputStream);
  InputStream chat(String userText);
}
