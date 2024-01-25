package ru.egpt.core.dao.asr;

import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub.enabled", havingValue="true")
public class ASRDaoImplStub implements ASRDao {

  @Override
  public String getText(InputStream in) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    return "";
  }
}
