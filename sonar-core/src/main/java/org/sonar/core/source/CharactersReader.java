/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package org.sonar.core.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class CharactersReader {

  private static final int END_OF_STREAM = -1;

  private final BufferedReader stringBuffer;
  private final Deque<String> openTags;

  private int currentValue;
  private int previousValue;
  private int currentIndex = -1;

  public CharactersReader(BufferedReader stringBuffer) {
    this.stringBuffer = stringBuffer;
    this.openTags = new ArrayDeque<String>();
  }

  public boolean readNextChar() throws IOException {
    previousValue = currentValue;
    currentValue = stringBuffer.read();
    currentIndex++;
    return currentValue != END_OF_STREAM;
  }

  public int getCurrentValue() {
    return currentValue;
  }

  public int getPreviousValue() {
    return previousValue;
  }

  public int getCurrentIndex() {
    return currentIndex;
  }

  public void registerOpenTag(String textType) {
    openTags.push(textType);
  }

  public void removeLastOpenTag() {
    openTags.remove();
  }

  public Deque<String> getOpenTags() {
    return openTags;
  }
}
