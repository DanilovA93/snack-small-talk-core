package ru.egpt.core.service;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egpt.core.dao.MessageDao;
import ru.egpt.core.dao.UserDao;
import ru.egpt.core.dao.asr.ASRDao;
import ru.egpt.core.dao.gpt.GPTDao;
import ru.egpt.core.dao.tts.TTSDao;
import ru.egpt.core.entity.Message;
import ru.egpt.core.entity.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

  private final ASRDao asrDao;
  private final GPTDao gptDao;
  private final TTSDao ttsDao;
  private final UserDao userDao;
  private final MessageDao messageDao;

  @Override
  @Transactional
  public InputStream audioChat(
          HttpHeaders headers,
          String username,
          InputStream userSpeechInputStream
  ) {
    String userText = asrDao.getText(userSpeechInputStream);
    String botText = gptDao.getText(username, userText);

    User user = userDao.getByUsername(username);

    Message message = new Message(); //todo duplicate
    message.setUser(user);
    message.setRequest(userText);
    message.setResponse(botText);
    messageDao.save(message);

    return ttsDao.getAudio(headers, botText);
  }

  @Override
  @Transactional
  public InputStream chat(
          HttpHeaders headers,
          String username,
          String userText
  ) {
    String botText = gptDao.getText(username, userText);

    User user = userDao.getByUsername(username);

    Message message = new Message(); //todo duplicate
    message.setUser(user);
    message.setRequest(userText);
    message.setResponse(botText);
    messageDao.save(message);

    return ttsDao.getAudio(headers, botText);
  }
}
