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

  private final ASRDao ASRDao;
  private final GPTDao gptDao;
  private final TTSDao ttsDao;

  @Override
  public InputStream audioChat(HttpHeaders headers, InputStream userSpeechInputStream) {
    String userText = ASRDao.getText(userSpeechInputStream);
    return chat(headers, userText);
  }

  @Override
  public InputStream chat(HttpHeaders headers, String userText) {
    String botText = gptDao.getAnswer(userText);
    return ttsDao.getAudio(headers, botText);
  }
}
