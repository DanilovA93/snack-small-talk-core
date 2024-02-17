package ru.egpt.core.rest.v1;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.egpt.core.service.ChatService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;

  @PostMapping(
      value = "/v1/chat/text"
  )
  public void chatWithAudio(
      @RequestHeader HttpHeaders headers,
      @RequestBody String text,
      HttpServletResponse response
  ) throws IOException {
    log.info("[CHAT] получено текстовое сообщение на /v1/chat/text");

    InputStream botSpeechInputStream = chatService.chat(headers, text);
    try {
      IOUtils.copy(botSpeechInputStream, response.getOutputStream());
      response.flushBuffer();
    } catch (IOException e) {
      throw new IOException("[CHAT] Ошибка при попытке передачи данных в ответе", e);
    }
  }

  @PostMapping(
      value = "/v1/chat/audio"
  )
  public void chatWithText(
      @RequestHeader HttpHeaders headers,
      InputStream userSpeechInputStream,
      HttpServletResponse response
  ) throws IOException {
    log.info("[CHAT] получено аудио сообщение на /v1/chat/audio");

    InputStream botSpeechInputStream = chatService.audioChat(headers, userSpeechInputStream);
    try {
      IOUtils.copy(botSpeechInputStream, response.getOutputStream());
      response.flushBuffer();
    } catch (IOException e) {
      throw new IOException("[CHAT] Ошибка при попытке передачи данных в ответе", e);
    }
  }
}
