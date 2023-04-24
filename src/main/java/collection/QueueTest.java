package collection;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        String head = queue.poll();


        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for (String s: queue
             ) {
            System.out.println(s);
        }

        System.out.println(head + "-----");

        for (String s: queue
        ) {
            System.out.println(s);
        }

        System.out.println("iterator");
    }
}
