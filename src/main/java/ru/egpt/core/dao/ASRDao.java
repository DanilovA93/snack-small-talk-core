package ru.egpt.core.dao;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface ASRDao {
  String getText(InputStream in);
}
