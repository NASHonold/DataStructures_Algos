package test;

import ds.priorityqueue.HeapPQ;

import ds.priorityqueue.PriorityType;

public class PQTest {

    public static void main(String[] args) {
        HeapPQ<Integer> pq = new HeapPQ<>(PriorityType.MAX);

        pq.add(10, 100);
        pq.add(5, 110);
        pq.add(15, 105);
        pq.add(20, 150);
        pq.add(25, 103);
        pq.add(2, 150);
        pq.add(2, 150);
        pq.add(2, 150);
        pq.add(2, 150);
        pq.add(2, 150);

        System.out.println(pq);

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

}
