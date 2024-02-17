package ru.egpt.core.service;

import java.io.InputStream;
import org.springframework.http.HttpHeaders;

public interface ChatService {
  InputStream audioChat(HttpHeaders headers, InputStream speechInputStream);
  InputStream chat(HttpHeaders headers, String userText);
}
