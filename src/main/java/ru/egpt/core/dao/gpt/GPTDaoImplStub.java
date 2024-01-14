package ru.egpt.core.dao.gpt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name="chat.stub", havingValue="true")
public class GPTDaoImplStub implements GPTDao {

  @Override
  public String getAnswer(String text) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    return "";
  }
}
