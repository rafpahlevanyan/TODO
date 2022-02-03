package todo;

public interface Commands {


    String EXIT = "0";
    String LOGIN = "1";
    String REGISTER = "2";


    String LOG_OUT = "0";
    String ADD_NEW_TODO = "1";
    String MY_ALL_LIST = "2";
    String MY_TODO_LIST = "3";
    String MY_IN_PROGRESS_LIST = "4";
    String MY_FINISHED_LIST = "5";
    String CHANGED_TODO_STATUS = "6";
    String DELETE_TODO = "7";


//    static void printCommandsAdmin() {
//        System.out.println("Please input " + LOG_OUT + " for log out");
//        System.out.println("Please input " + ADD_LESSON + " for add lesson ");
//        System.out.println("Please input " + ADD_STUDENT + " for add student ");
//        System.out.println("Please input " + PRINT_STUDENT + " for print student ");
//        System.out.println("Please input " + SEARCH_STUDENT_BY_LESSON + " for search student by lesson ");
//        System.out.println("Please input " + PRINT_LESSONS + " for print lesson ");
//        System.out.println("Please input " + DELETE_LESSONS_BY_NAME + " for delete lessons by name ");
//        System.out.println("Please input " + DELETE_STUDENTS_BY_EMAIL + " for delete students by email ");
//        System.out.println("Please input " + CHANGE_LESSON + " for change lesson ");
//    }

    static void printCommandsUser() {
        System.out.println("Please input " + LOG_OUT + " for log out");
        System.out.println("Please input " + ADD_NEW_TODO + " for ADD_NEW_TODO ");
        System.out.println("Please input " + MY_ALL_LIST + " for MY_ALL_LIST ");
        System.out.println("Please input " + MY_TODO_LIST + " for MY_TODO_LIST ");
        System.out.println("Please input " + MY_IN_PROGRESS_LIST + " for MY_IN_PROGRESS_LIST ");
        System.out.println("Please input " + MY_FINISHED_LIST + " for MY_FINISHED_LIST ");
        System.out.println("Please input " + CHANGED_TODO_STATUS + " for CHANGED_TODO_STATUS ");
        System.out.println("Please input " + DELETE_TODO + " for DELETE_TODO ");

    }

    static void printCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + LOGIN + " for login ");
        System.out.println("Please input " + REGISTER + " for register ");

    }

}
