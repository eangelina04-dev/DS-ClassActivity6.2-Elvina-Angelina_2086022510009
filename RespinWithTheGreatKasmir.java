import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class RespinWithTheGreatKasmir {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNextInt()) {
            int operationCount = input.nextInt();

            boolean isStack = true;
            boolean isQueue = true;
            boolean isPriorityQueue = true;

            Queue<Integer> queueSim = new java.util.LinkedList<>();
            Queue<Integer> pqSim = new PriorityQueue<>();
            int[] stackArray = new int[operationCount];
            int top = 0;

            for (int i = 0; i < operationCount; i++) {
                int type = input.nextInt();
                int value = input.nextInt();

                if (type == 1) {
                    if (isQueue) queueSim.add(value);
                    if (isPriorityQueue) pqSim.add(-value); 
                    if (isStack) {
                        stackArray[top] = value;
                        top++;
                    }
                } else {
                    
                    if (isQueue) {
                        if (queueSim.isEmpty() || queueSim.poll() != value) {
                            isQueue = false;
                        }
                    }

                    if (isPriorityQueue) {
                        if (pqSim.isEmpty() || -pqSim.poll() != value) {
                            isPriorityQueue = false;
                        }
                    }

                    if (isStack) {
                        if (top == 0) {
                            isStack = false;
                        } else {
                            top--; 
                            if (stackArray[top] != value) {
                                isStack = false;
                            }
                        }
                    }
                }
            }

            int possibleOptions = 0;
            if (isStack) possibleOptions++;
            if (isQueue) possibleOptions++;
            if (isPriorityQueue) possibleOptions++;

            if (possibleOptions == 0) {
                System.out.println("tidak ada");
            } else if (possibleOptions > 1) {
                System.out.println("tidak yakin");
            } else {
                if (isStack) System.out.println("stack");
                else if (isQueue) System.out.println("queue");
                else if (isPriorityQueue) System.out.println("priority queue");
            }
        }
        input.close();
    }
}