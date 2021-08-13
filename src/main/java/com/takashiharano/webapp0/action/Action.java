package com.takashiharano.webapp0.action;

import com.takashiharano.webapp0.AppManager;
import com.takashiharano.webapp0.ProcessContext;
import com.takashiharano.webapp0.util.Log;

public abstract class Action {

  public static void exec(ProcessContext context, String name) throws Exception {
    Action action = getActionInstance(name);
    if (action == null) {
      Log.e("ACTION_NOT_FOUND: " + name);
      context.sendJson("ACTION_NOT_FOUND", name);
      return;
    }
    action.process(context);
  }

  private static Action getActionInstance(String actionName) {
    String basePkgName = AppManager.getBasePackageName();
    String pkgName = basePkgName + ".action";
    String[] packages = { "", "system" };

    actionName = actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
    for (int i = 0; i < packages.length; i++) {
      String subPackage = packages[i];
      if (!subPackage.equals("")) {
        subPackage += ".";
      }
      String classFullName = pkgName + "." + subPackage + actionName + "Action";
      Action action = getBean(classFullName);
      if (action != null) {
        return action;
      }
    }
    return null;
  }

  private static Action getBean(String className) {
    try {
      Class<?> c = Class.forName(className);
      Action bean = (Action) c.getDeclaredConstructor().newInstance();
      return bean;
    } catch (Exception e) {
      return null;
    }
  }

  public abstract void process(ProcessContext context) throws Exception;

}
