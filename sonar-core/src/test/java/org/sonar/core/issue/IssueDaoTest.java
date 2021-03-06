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

package org.sonar.core.issue;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.issue.IssueQuery;
import org.sonar.api.utils.DateUtils;
import org.sonar.core.persistence.AbstractDaoTestCase;

import java.util.Collection;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;


public class IssueDaoTest extends AbstractDaoTestCase {

  private IssueDao dao;

  @Before
  public void createDao() {
    dao = new IssueDao(getMyBatis());
  }

  @Test
  public void should_insert() {
    setupData("insert");

    IssueDto issueDto = new IssueDto();
    issueDto.setUuid("100");
    issueDto.setResourceId(400);
    issueDto.setRuleId(12);
    issueDto.setSeverity("BLOCKER");
    issueDto.setLine(200);
    issueDto.setStatus("OPEN");
    issueDto.setAssigneeLogin("user");
    issueDto.setMessage("message");
    issueDto.setCost(10.0);
    issueDto.setChecksum("checksum");
    issueDto.setPersonId(100L);

    Date today = new Date();
    issueDto.setCreatedAt(today);
    issueDto.setUpdatedAt(today);
    issueDto.setClosedAt(today);

    dao.insert(issueDto);

    checkTables("insert", new String[] {"id", "created_at", "updated_at", "closed_at"}, "issues");
  }

  @Test
  public void update() {
    setupData("update");
    Collection<IssueDto> issues = newArrayList(dao.findById(100L));
    IssueDto issue = issues.iterator().next();
    issue.setLine(1000);
    issue.setResolution("NEW_RESOLUTION");
    issue.setStatus("NEW_STATUS");
    issue.setSeverity("NEW_SEV");
    issue.setAssigneeLogin("new_user");
    issue.setManualSeverity(true);
    issue.setManualIssue(false);
    issue.setTitle("NEW_TITLE");
    issue.setCreatedAt(DateUtils.parseDate("2012-05-18"));
    issue.setUpdatedAt(DateUtils.parseDate("2012-07-01"));
    issue.setData("big=bang");

    dao.update(issues);

    checkTables("update", "issues");
  }

  @Test
  public void should_find_issue_by_id() {
    setupData("shared");

    IssueDto issue = dao.findById(100L);
    assertThat(issue.getId()).isEqualTo(100L);
    assertThat(issue.getUuid()).isEqualTo("100");
    assertThat(issue.getResourceId()).isEqualTo(400);
    assertThat(issue.getRuleId()).isEqualTo(500);
    assertThat(issue.getSeverity()).isEqualTo("BLOCKER");
    assertThat(issue.isManualSeverity()).isFalse();
    assertThat(issue.isManualIssue()).isFalse();
    assertThat(issue.getTitle()).isNull();
    assertThat(issue.getMessage()).isNull();
    assertThat(issue.getLine()).isEqualTo(200);
    assertThat(issue.getCost()).isNull();
    assertThat(issue.getStatus()).isEqualTo("OPEN");
    assertThat(issue.getResolution()).isNull();
    assertThat(issue.getChecksum()).isNull();
    assertThat(issue.getAssigneeLogin()).isEqualTo("user");
    assertThat(issue.getPersonId()).isNull();
    assertThat(issue.getUserLogin()).isNull();
    assertThat(issue.getData()).isNull();
    assertThat(issue.getCreatedAt()).isNull();
    assertThat(issue.getUpdatedAt()).isNull();
    assertThat(issue.getClosedAt()).isNull();
  }

  @Test
  public void should_find_issue_by_uuid() {
    setupData("shared");

    IssueDto issue = dao.findByUuid("100");
    assertThat(issue).isNotNull();
  }

  @Test
  public void should_select() {
    setupData("shared");

    IssueQuery issueQuery = new IssueQuery.Builder().resolution("FALSE-POSITIVE").build();
    Collection<IssueDto> issues = dao.select(issueQuery);
    assertThat(issues).hasSize(1);

    issueQuery = new IssueQuery.Builder().componentKeys("400").build();
    issues = dao.select(issueQuery);
    assertThat(issues).hasSize(2);
  }

}
