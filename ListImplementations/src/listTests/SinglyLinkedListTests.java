package listTests;

import listi.SinglyLinkedList;

public class SinglyLinkedListTests {
    public static void main(String[] args) {
        SinglyLinkedList<Character> sll = new SinglyLinkedList<Character>();

        sll.append('i');
        sll.append('a');
        sll.append('b');
        sll.append('c');
        sll.append('d');
        sll.append('e');
        sll.append('f');
        sll.append('g');
        System.out.println(sll);
        sll.set(5, 'q');
        System.out.println(sll);
        sll.set(0, 'z');
        sll.indexOf('z');
        sll.indexOf('e');
        System.out.println(sll.indexOf('r'));
        sll.remove(6);
        System.out.println(sll);
        System.out.println(sll.get(2));

    }
}
