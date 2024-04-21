package ru.egpt.core.dao.gpt;

@FunctionalInterface
public interface GPTDao {
  String getText(String username, String text);
}
