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
package org.sonar.batch.phases;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class PhasesTest {
  @Test
  public void no_phase_should_be_enabled_by_default() {
    Phases phases = new Phases();
    assertThat(phases.isEnabled(Phases.Phase.DECORATOR)).isFalse();
  }

  @Test
  public void phase_should_be_enabled() {
    Phases phases = new Phases();
    phases.enable(Phases.Phase.SENSOR, Phases.Phase.DECORATOR);

    assertThat(phases.isEnabled(Phases.Phase.DECORATOR)).isTrue();
    assertThat(phases.isEnabled(Phases.Phase.SENSOR)).isTrue();
    assertThat(phases.isEnabled(Phases.Phase.POSTJOB)).isFalse();
    assertThat(phases.isFullyEnabled()).isFalse();
  }

  @Test
  public void all_phases_should_be_enabled() {
    Phases phases = new Phases();
    phases.enable(Phases.Phase.values());

    assertThat(phases.isEnabled(Phases.Phase.INIT)).isTrue();
    assertThat(phases.isEnabled(Phases.Phase.MAVEN)).isTrue();
    assertThat(phases.isEnabled(Phases.Phase.DECORATOR)).isTrue();
    assertThat(phases.isEnabled(Phases.Phase.SENSOR)).isTrue();
    assertThat(phases.isEnabled(Phases.Phase.POSTJOB)).isTrue();
    assertThat(phases.isFullyEnabled()).isTrue();
  }
}
