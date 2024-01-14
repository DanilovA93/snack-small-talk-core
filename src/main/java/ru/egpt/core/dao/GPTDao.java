package ru.egpt.core.dao;

@FunctionalInterface
public interface GPTDao {
  String getAnswer(String text);
}
