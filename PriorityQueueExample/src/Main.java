import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> pq1 = new PriorityQueue<Integer>();

        pq1.add(5);
        pq1.add(2);
        pq1.add(1);
        pq1.add(10);
        pq1.add(3);

        System.out.println("PriorityQueue: " + pq1);

        System.out.println("PriorityQueue 本身的遍历是无序的：-----------------------------------");
        // Creating an iterator
        Iterator value = pq1.iterator();

        // Displaying the values after iterating through the queue
        System.out.println("The iterator values are: ");
        while (value.hasNext()) {
            System.out.println(value.next());
        }


        System.out.println("-----------------------------");
        while (!pq1.isEmpty()) {
            System.out.print(pq1.poll() + ",");
        }
        System.out.println();
        System.out.println("-----------------------------");
        // 自定义的比较器，可以让我们自由定义比较的顺序 Comparator<Integer> cmp;
        Comparator cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        };
        Queue<Integer> pq2 = new PriorityQueue<Integer>(5, cmp);
        pq2.add(2);
        pq2.add(8);
        pq2.add(9);
        pq2.add(1);
        while (!pq2.isEmpty()) {
            System.out.print(pq2.poll() + ",");
        }

    }
}
