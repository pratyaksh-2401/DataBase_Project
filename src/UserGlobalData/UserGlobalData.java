package UserGlobalData;

import java.util.List;

public class UserGlobalData {
    private static int userEmployeeID;
    private static String userName;
    private static String userFullName;
    private static String userBranch;
    private static boolean isAdmin;
    private static List<String> userTasks;

    public static void setUserEmployeeID(int userEmployeeID) {
        UserGlobalData.userEmployeeID = userEmployeeID;
    }

    public static int getUserEmployeeID() {
        return UserGlobalData.userEmployeeID;
    }

    public static void setUserName(String userName) {
        UserGlobalData.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserFullName(String userFullName) {
        UserGlobalData.userFullName = userFullName;
    }

    public static String getUserFullName() {
        return userFullName;
    }

    public static void setUserBranch(String userBranch) {
        UserGlobalData.userBranch = userBranch;
    }

    public static String getUserBranch() {
        return userBranch;
    }

    public static void setIsAdmin(boolean isAdmin) {
        UserGlobalData.isAdmin = isAdmin;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setUserTasks(List<String> tasks) {
        UserGlobalData.userTasks = tasks;
    }

    public static List<String> getUserTasks() {
        return UserGlobalData.userTasks;
    }

    public static String getFirstTaskDeadlineFromUserTasks() {
        List<String> userTasks = UserGlobalData.getUserTasks();

        if (userTasks != null && !userTasks.isEmpty()) {
            String firstTask = userTasks.get(0);

            String[] parts = firstTask.split(", ");
            for (String part : parts)
                if (part.startsWith("Deadline:"))
                    return part.substring("Deadline: ".length());
        }

        return null;
    }

    public static String getFirstTaskNameFromUserTasks() {
        if (userTasks != null && !userTasks.isEmpty()) {
            String firstTask = userTasks.get(0);

            String[] parts = firstTask.split(", ");
            for (String part : parts)
                if (part.startsWith("Task Name:"))
                    return part.substring("Task Name: ".length());
        }
        return null;
    }

    public static void eraseData() {
        userEmployeeID = 0;
        userName = null;
        userFullName = null;
        userBranch = null;
        isAdmin = false;
        userTasks = null;
    }
}

