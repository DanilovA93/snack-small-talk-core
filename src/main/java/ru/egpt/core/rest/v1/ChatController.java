package ru.egpt.core.rest.v1;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egpt.core.service.ChatService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;

  @PostMapping(
      value = "/v1/chat/audio"
  )
  public void createTesterProfilesByMails(
      InputStream userSpeechInputStream,
      HttpServletResponse response
  ) throws IOException {
    log.info("[CHAT] получено аудио сообщение на /v1/chat/audio");

    InputStream botSpeechInputStream = chatService.chat(userSpeechInputStream);
    try {
      IOUtils.copy(botSpeechInputStream, response.getOutputStream());
      response.flushBuffer();
    } catch (IOException e) {
      throw new IOException("[CHAT] Ошибка при попытке передачи данных в ответе", e);
    }
  }
}
