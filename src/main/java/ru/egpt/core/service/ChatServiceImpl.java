package ru.egpt.core.service;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egpt.core.dao.tts.TTSDao;
import ru.egpt.core.dao.gpt.GPTDao;
import ru.egpt.core.dao.asr.ASRDao;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

  private final ASRDao ASRDao;
  private final GPTDao gptDao;
  private final TTSDao ttsDao;

  @Override
  public InputStream chat(InputStream userSpeechInputStream) {
    long start = System.nanoTime();
    String userText = ASRDao.getText(userSpeechInputStream);
    long asr = System.nanoTime() - start;
    log.info("ASR time: " + asr / 1_000_000_000);
    String botText = gptDao.getAnswer(userText);
    long gpt = System.nanoTime() - start;
    log.info("GTP time: " + gpt / 1_000_000_000);
    InputStream in = ttsDao.getAudio(botText);
    long tts = System.nanoTime() - start;
    log.info("TTS time: " + tts / 1_000_000_000);

    return in;
  }
}
