/*
 * THIS CODE IS IMPLEMENTED BASED ON THE webapp0 TEMPLATE.
 */
package com.takashiharano.webapp0.action.system.users;

import com.takashiharano.webapp0.ProcessContext;
import com.takashiharano.webapp0.action.Action;
import com.takashiharano.webapp0.user.UserManager;
import com.takashiharano.webapp0.util.Log;

public class UpdateUserAction extends Action {

  @Override
  public void process(ProcessContext context) throws Exception {
    String username = context.getRequestParameter("username");
    String pwHash = context.getRequestParameter("pw");
    String fullname = context.getRequestParameter("fullname");
    String adminFlg = context.getRequestParameter("isadmin");
    String privileges = context.getRequestParameter("privileges");
    String userStatus = context.getRequestParameter("status");

    String currentUsername = context.getUsername();
    if (!context.isAdministrator() && !currentUsername.equals(username)) {
      context.sendJsonResponse("FORBIDDEN:UpdateUser", null);
      return;
    }

    String status = "OK";
    try {
      UserManager userManager = context.getUserManager();
      userManager.updateUser(username, pwHash, fullname, privileges, adminFlg, userStatus);
    } catch (Exception e) {
      status = e.getMessage();
      Log.e("User update error: " + status);
    }

    context.sendJsonResponse(status, null);
  }

}
