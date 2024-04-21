package ru.egpt.core.service;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ru.egpt.core.dao.asr.ASRDao;
import ru.egpt.core.dao.gpt.GPTDao;
import ru.egpt.core.dao.tts.TTSDao;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

  private final ASRDao asrDao;
  private final GPTDao gptDao;
  private final TTSDao ttsDao;

  @Override
  public InputStream audioChat(
          HttpHeaders headers,
          String username,
          InputStream userSpeechInputStream
  ) {
    String userText = asrDao.getText(userSpeechInputStream);
    return chat(headers, username, userText);
  }

  @Override
  public InputStream chat(
          HttpHeaders headers,
          String username,
          String message
  ) {
    String botText = gptDao.getText(username, message);
    return ttsDao.getAudio(headers, botText);
  }
}
