package ru.egpt.core.dao.asr;

import java.io.InputStream;

@FunctionalInterface
public interface ASRDao {
  String getText(InputStream in);
}
