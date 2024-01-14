package ru.egpt.core.service;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egpt.core.dao.TTSDao;
import ru.egpt.core.dao.GPTDao;
import ru.egpt.core.dao.ASRDao;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ASRDao ASRDao;
  private final GPTDao gptDao;
  private final TTSDao ttsDao;

  @Override
  public InputStream chat(InputStream userSpeechInputStream) {
    String userText = ASRDao.getText(userSpeechInputStream);
    String botText = gptDao.getAnswer(userText);
    return ttsDao.getAudio(botText);
  }
}
