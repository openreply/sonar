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
package org.sonar.batch.bootstrap;

import org.junit.Test;
import org.sonar.api.config.Settings;
import org.sonar.home.cache.FileCache;

import static org.fest.assertions.Assertions.assertThat;

public class FileCacheProviderTest {
  @Test
  public void provide() {
    FileCacheProvider provider = new FileCacheProvider();
    FileCache cache = provider.provide(new Settings());

    assertThat(cache).isNotNull();
    assertThat(cache.getDir()).isNotNull().exists();
  }

  @Test
  public void keep_singleton_instance() {
    FileCacheProvider provider = new FileCacheProvider();
    Settings settings = new Settings();
    FileCache cache1 = provider.provide(settings);
    FileCache cache2 = provider.provide(settings);

    assertThat(cache1).isSameAs(cache2);
  }
}
