package maze;

import java.util.Stack;

import javax.xml.transform.Templates;

/**
 * Notes:
 * 
 * The start and end position are contained within the maze object. Use the
 * getStart() and getEnd() methods.
 * 
 * @author Brian Rogers
 *
 */
public class MazeSolver {

    private static Position start;
    private static Position current;
    private static Position previous;
    private static Position end;
    private static Stack<Position> path = new Stack<>();

    /**
     * You need to implement this method
     * 
     * @param maze: The maze to be solved.
     * @return An array of Position which stores a solution to the maze. If a
     *         solution is not found a null value should be returned.
     */
    public static Position[] solve(Maze maze) {
        // get the preliminary values assigned to variable for use
        start = maze.getStart();
        end = maze.getEnd();

        maze.setAt(start, 'V');// since we begin at the first position we must set it as visited
        path.push(start);
        current = start;

        while (!current.equals(end) && !path.isEmpty()) {
            // System.out.println("=============== current state of maze
            // ==================");
            // System.out.println(maze);
            // System.out.println("========================================================");

            for (int i = 1; i <= 4; i++) {
                boolean goodDirection = checkPos(maze, i, current);
                if (goodDirection) {
                    Position nextPos = newPosition(current, i);
                    maze.setAt(nextPos, 'V');
                    path.push(nextPos);
                    previous = current;
                    current = path.peek();
                    break;

                }

                if (!goodDirection && i == 4 && !current.equals(end)) {

                    Position temp = path.pop();
                    maze.setAt(temp, '*');

                    current = path.pop();
                    if (current.equals(start)) {
                        return null;
                    }
                    previous = path.peek();
                    path.push(current);
                }
            }
        }

        return stackToArray(path);
    }

    /**
     * Will return a Position array given a Stack object where
     * the first value in the Stack is the first index
     * of the array.
     * 
     * @param stk
     * @return
     */
    private static Position[] stackToArray(Stack<Position> stk) {

        Stack<Position> tempStack = new Stack<>();
        while (!path.isEmpty()) {
            tempStack.push(stk.pop());
        }
        Position posList[] = new Position[tempStack.size()];
        for (int i = 0; i < posList.length; i++) {
            posList[i] = tempStack.pop();
        }
        return posList;
    }

    /**
     * Given the maze, current position, and the direction you would like to check
     * this
     * will return a boolean value if that position is available to occupy
     * 
     * @param maze
     * @param direction
     * @param current
     * @return
     */
    private static boolean checkPos(Maze maze, int direction, Position current) {// 1 is up, 2 is right, 3 is down, 4 is
                                                                                 // left

        Position checkPos = newPosition(current, direction);

        if (maze.validPosition(checkPos)) {
            if (maze.getAt(checkPos) == ' ') {
                return true;
            }

        }

        return false;

    }

    /**
     * With the current Position and a integer representing a direction, this
     * will create a position that would occupy that space relative to the current
     * position.
     * 
     * @param current
     * @param direction
     * @return
     */
    private static Position newPosition(Position current, int direction) {
        int row = current.getRow();
        int col = current.getColumn();
        Position newPos;

        switch (direction) {
            case 1: {
                row--;
                break;
            }
            case 2: {
                col++;
                break;
            }
            case 3: {
                row++;
                break;
            }
            case 4: {
                col--;
                break;
            }
        }

        newPos = new Position(row, col);
        return newPos;

    }
}
