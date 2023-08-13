package listTests;

import listi.DoublyLinkedList;

public class DoublyLinkedListTest {
    public static void main(String[] args) {
        DoublyLinkedList<Character> dll = new DoublyLinkedList<>();

        System.out.println(dll);
        dll.add(0, 'a');
        dll.add(0, 'b');
        dll.add(1, 'c');
        dll.append('d');
        dll.prepend('e');
        dll.add(2, 'h');
        System.out.println(dll);
        dll.remove(0);
        System.out.println(dll);
        dll.remove(dll.getSize() - 1);
        System.out.println(dll);
        dll.remove(1);
        System.out.println(dll);

    }
}
