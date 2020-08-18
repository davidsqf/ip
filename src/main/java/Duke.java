import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greeting = "Hello, I'm Duke, your personal assistant. \n What can I do for you?";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        while(sc.hasNextLine()) {
            String order = sc.nextLine();
            String reply = process(order, list);
            System.out.println(reply);
        }
    }

    public static String process(String order, List<Task> list) {
        if (order.equals("bye")) {
            return "    Bye-bye, see you next time!";
        } else if (order.equals("list")) {
            if (list.size() == 0) {
                return "    Well done! You've completed all your tasks.";
            } else {
                return printList(list);
            }
        } else if (order.substring(0, 4).equals("done")) {
            Integer index = Integer.valueOf(order.substring(5))-1;
            Task temp = list.get(index);
            temp.done();
            list.set(index, temp);
            return "    Great! I have marked this task as done:\n" + temp;
        } else if (order.substring(0, 4).equals("todo")) {
            String content = order.substring(5);
            list.add(new Todo(content));
            return "    added:" + content + "\n" + "    Now you have " + list.size() + " task(s) in the list";
        } else if (order.substring(0, 8).equals("deadline")) {
            Integer indexOfSlash = order.indexOf('/');
            String content = order.substring(9, indexOfSlash);
            String due = order.substring(indexOfSlash+1);
            list.add(new Deadline(content, due));
            return "    added:" + content + "\n" + "    Now you have " + list.size() + " task(s) in the list";
        } else if (order.substring(0, 5).equals("event")) {
            Integer indexOfSlash = order.indexOf('/');
            String content = order.substring(6, indexOfSlash);
            String time = order.substring(indexOfSlash+1);
            list.add(new Event(content, time));
            return "    added:" + content + "\n" + "    Now you have " + list.size() + " task(s) in the list";
        } else {
            return "    Sorry, I don't understand.";
        }
    }

    private static String printList(List<Task> list) {
        int size = list.size();
        String str = "";
        for (int i = 1; i <= size; i++) {
            str += "\n"+i+". "+list.get(i-1);
        }
        return str;
    }
}
