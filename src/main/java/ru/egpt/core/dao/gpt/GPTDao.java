package ru.egpt.core.dao.gpt;

@FunctionalInterface
public interface GPTDao {
  String getAnswer(String text);
}
