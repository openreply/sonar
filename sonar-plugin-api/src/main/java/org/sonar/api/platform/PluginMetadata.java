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
package org.sonar.api.platform;

import java.io.File;
import java.util.List;

/**
 * @since 2.8
 */
public interface PluginMetadata {
  File getFile();

  List<File> getDeployedFiles();

  String getKey();

  String getName();

  String getMainClass();

  String getDescription();

  String getOrganization();

  String getOrganizationUrl();

  String getLicense();

  String getVersion();

  String getHomepage();

  /**
   * @since 3.6
   */
  String getIssueTrackerUrl();

  boolean isUseChildFirstClassLoader();

  String getBasePlugin();

  String getParent();

  List<String> getRequiredPlugins();

  boolean isCore();
}
