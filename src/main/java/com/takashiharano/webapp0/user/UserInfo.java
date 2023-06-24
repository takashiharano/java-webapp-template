/*
 * THIS CODE IS IMPLEMENTED BASED ON THE WEBAPP0 TEMPLATE.
 */
package com.takashiharano.webapp0.user;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserInfo {
  public static final int STATE_NONE = 0;
  public static final int STATE_DISABLED = 1;
  public static final int STATE_LOCKED = 1 << 1;
  public static final int STATE_PW_CHANGE = 1 << 2;

  private String username;
  private String fullname;
  private boolean admin;
  private Set<String> privileges;
  private int status;

  public UserInfo(String username, boolean isAdmin) {
    this.username = username;
    this.admin = isAdmin;
    this.privileges = new LinkedHashSet<>();
    this.status = STATE_NONE;
  }

  public UserInfo(String username, String fullname, boolean isAdmin, String privileges, int status) {
    this.username = username;
    this.fullname = fullname;
    this.admin = isAdmin;
    setPrivileges(privileges);
    this.status = status;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
    return fullname;
  }

  public void setFullName(String fullname) {
    this.fullname = fullname;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean isAdmin) {
    this.admin = isAdmin;
  }

  public String[] getPrivileges() {
    return privileges.toArray(new String[0]);
  }

  public void setPrivileges(LinkedHashSet<String> privileges) {
    this.privileges = privileges;
  }

  public void setPrivileges(String privileges) {
    this.privileges = new LinkedHashSet<>();
    if (privileges == null) {
      return;
    }
    String[] p = privileges.trim().split(" ");
    for (int i = 0; i < p.length; i++) {
      String privilege = p[i];
      this.privileges.add(privilege);
    }
  }

  public void addPrivileges(String privilege) {
    privileges.add(privilege);
  }

  public void removePrivilege(String privilege) {
    privileges.remove(privilege);
  }

  public boolean hasPrivilege(String privilege) {
    if (isAdmin()) {
      return true;
    }
    return privileges.contains(privilege);
  }

  public String getPrivilegesInOneLine() {
    return getPrivilegesInOneLine(" ");
  }

  public String getPrivilegesInOneLine(String separator) {
    StringBuilder sb = new StringBuilder();
    int cnt = 0;
    for (String p : privileges) {
      if (cnt > 0) {
        sb.append(separator);
      }
      sb.append(p);
      cnt++;
    }
    return sb.toString();
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

}
