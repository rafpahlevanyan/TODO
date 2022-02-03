package todo;

import todo.manager.ToDoManager;
import todo.manager.UserManager;
import todo.model.ToDo;
import todo.model.ToDoStatus;
import todo.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ToDoMain implements Commands {

    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;
    static UserManager userManager = new UserManager();
    static ToDoManager toDoManager = new ToDoManager();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command!");

            }
        }
    }

    private static void isUser() {
        System.out.println("Welcome " + currentUser.getName() + " ))");
        boolean isRun = true;
        while (isRun) {
            Commands.printCommandsUser();
            String command = scanner.nextLine();


            switch (command) {
                case LOG_OUT -> isRun = false;
                case ADD_NEW_TODO -> addNewTodo();
                case MY_ALL_LIST -> printToDos(toDoManager.getAllToDosByUser(currentUser.getId()));
                case MY_TODO_LIST ->printToDos( toDoManager.getAllToDosByUserAndStatus(currentUser.getId(), ToDoStatus.TODO));
                case MY_IN_PROGRESS_LIST -> printToDos(toDoManager.getAllToDosByUserAndStatus(currentUser.getId(),
                        ToDoStatus.IN_PROGRESS));
                case MY_FINISHED_LIST -> printToDos(toDoManager.getAllToDosByUserAndStatus(currentUser.getId(),
                        ToDoStatus.FINISHED));
                case CHANGED_TODO_STATUS -> changeToDoStatus();
                case DELETE_TODO -> deleteToDo();
                default -> System.err.println("invalid command");
            }
        }
    }

    private static void printToDos(List<ToDo> allToDosByUser) {
        for (ToDo toDo : allToDosByUser) {
            System.out.println(toDo);
        }
    }

    private static void deleteToDo() {
        System.out.println("Please choose TODO From Lis");
        List<ToDo> list = toDoManager.getAllToDosByUser(currentUser.getId());
        for (ToDo toDo : list) {
            System.out.println(toDo);
        }
        long id = Long.parseLong(scanner.nextLine());
        ToDo byId = toDoManager.getById(id);
        if (byId.getUser().getId() == currentUser.getId()) {
            toDoManager.delete(id);
            System.out.println("ToDos was removed");
        } else {
            System.err.println("Wrong id");
        }

    }

    private static void changeToDoStatus() {
        System.out.println("Please choose TODO From Lis");
        List<ToDo> list = toDoManager.getAllToDosByUser(currentUser.getId());
        for (ToDo toDo : list) {
            System.out.println(toDo);
        }
        long id = Long.parseLong(scanner.nextLine());
        ToDo byId = toDoManager.getById(id);
        if (byId.getUser().getId() == currentUser.getId()) {
            System.out.println("Please choose status");
            System.out.println(Arrays.toString(ToDoStatus.values()));
            ToDoStatus status = ToDoStatus.valueOf(scanner.nextLine());
            if (toDoManager.update(id, status)) {
                System.out.println("Stats changed");
            } else {
                System.err.println("Smt went wrong");
            }
        } else {
            System.err.println("Wrong id");
        }
    }

    private static void addNewTodo() {
        System.out.println("PLease input title, deadline ");
        String toDoDataStr = scanner.nextLine();
        String[] split = toDoDataStr.split(",");
        ToDo toDo = new ToDo();
        try {
            String title = split[0];
            toDo.setTitle(title);
            try {
                if (split[1] != null) {
                    toDo.setDeadline(sdf.parse(split[1]));
                }
            } catch (IndexOutOfBoundsException e) {
            } catch (ParseException e) {
                System.err.println("Please input yyyy-MM-dd HH:mm:ss format");
            }
            toDo.setStatus(ToDoStatus.TODO);
            toDo.setUser(currentUser);
            if (toDoManager.create(toDo)) {
                System.out.println("ToDo was added");
            } else {
                System.err.println("smt went wrong");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Wrong data");
        }
    }


    private static void login() {
        System.out.println("Please input email, password");
        try {
            String loginStr = scanner.nextLine();
            String[] loginArr = loginStr.split(",");
            User user = userManager.getByEmailAndPassword(loginArr[0], loginArr[1]);
            if (user != null) {
                currentUser = user;
                isUser();
            } else {
                System.err.println("User with " + loginArr[1] + " or password " +
                        "" + " does not exists");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong data");
        }
    }


    private static void register() {
        System.out.println("Please input user data " +
                "name,surname,email,password");
        try {
            String userDataStr = scanner.nextLine();
            String[] userDataArr = userDataStr.split(",");
            User userFromStorage = userManager.getByEmail(userDataArr[2]);
            if (userFromStorage == null) {
                User user = new User();
                user.setName(userDataArr[0]);
                user.setSurname(userDataArr[1]);
                user.setEmail(userDataArr[2]);
                user.setPassword(userDataArr[3]);
                if (userManager.register(user)) {
                    System.out.println("User was added");
                } else {
                    System.err.println("Smt went wrong");
                }
            } else {
                System.err.println("User already exists");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong Data");
        }
    }


}

