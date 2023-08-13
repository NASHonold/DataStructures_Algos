
public class LinkedStackTest {

    public static void main(String[] args) {
        LinkedStack<Integer> iStack = new LinkedStack<>();

        for (int i = 0; i < 100; i++) {
            iStack.push(i);
        }
        System.out.println(iStack);

        System.out.println("Size of iStack: " + iStack.getSize());

        LinkedStack<Character> cStack = new LinkedStack<>();
        for (char c = ' '; c <= '~'; c++) {
            cStack.push(Character.valueOf(c));
        }
        System.out.println(cStack);
        System.out.println("Size of cStack: " + iStack.getSize());

        for (int i = 0; i <= 50; i++) {
            System.out.println(cStack.pop());

        }
        System.out.println("==================");
        System.out.println(cStack);

        System.out.println("Is the iStack empty?: " + iStack.isEmpty());
        System.out.println("Emptying iStack........");
        iStack.clear();
        System.out.println("Is the iStack empty?: " + iStack.isEmpty());
        while (!cStack.isEmpty()) {
            System.out.println("This is the value of peek: " + cStack.peek());
            cStack.pop();
        }
        System.out.println("Is the cStack empty?: " + cStack.isEmpty());
        System.out.println(cStack);
    }
}
