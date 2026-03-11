import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;

public class AloveletterfromGibek{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        if (!input.hasNextInt()) return;
        int n = input.nextInt();
        
        Letter[] allLetters = new Letter[n];
        
        Comparator<Letter> priorityStyle = new Comparator<Letter>() {
            @Override
            public int compare(Letter a, Letter b) {
                if (a.priority < b.priority) return -1;
                if (a.priority > b.priority) return 1;
                if (a.id < b.id) return -1;
                return 1;
            }
        };

        Queue<Letter> pendingQueue = new PriorityQueue<>(priorityStyle);

        for (int i = 0; i < n; i++) {
            String name = input.next();
            int duration = input.nextInt();
            int priority = input.nextInt();
            allLetters[i] = new Letter(name, duration, priority, i);
            pendingQueue.add(allLetters[i]);
        }

        boolean[] isQueued = new boolean[n];
        boolean[] isSent = new boolean[n];
        int totalSent = 0;

        if (!pendingQueue.isEmpty()) {
            int firstPriority = pendingQueue.peek().priority;
            for (int i = 0; i < n; i++) {
                if (!pendingQueue.isEmpty() && pendingQueue.peek().priority == firstPriority) {
                    Letter top = pendingQueue.poll();
                    isQueued[top.id] = true;
                }
            }
        }

        int currentTime = 1;

        while (totalSent < n) {
            System.out.print(currentTime + " ");
            
            for (int i = 0; i < n; i++) {
                if (!isQueued[allLetters[i].id] && !isSent[allLetters[i].id]) {
                    System.out.print(allLetters[i].name + " ");
                }
            }
            System.out.print("| ");

            for (int i = 0; i < n; i++) {
                if (isQueued[allLetters[i].id]) {
                    System.out.print(allLetters[i].name + " ");
                }
            }
            System.out.print("| ");

            for (int i = 0; i < n; i++) {
                if (isSent[allLetters[i].id]) {
                    System.out.print(allLetters[i].name + " ");
                }
            }
            System.out.println();

            boolean triggerNext = false;
            for (int i = 0; i < n; i++) {
                if (isQueued[allLetters[i].id]) {
                    allLetters[i].duration--;
                    if (allLetters[i].duration == 0) {
                        isQueued[allLetters[i].id] = false;
                        isSent[allLetters[i].id] = true;
                        totalSent++;
                        triggerNext = true; 
                    }
                }
            }

            if (triggerNext && !pendingQueue.isEmpty()) {
                int nextPriority = pendingQueue.peek().priority;
                while (!pendingQueue.isEmpty() && pendingQueue.peek().priority == nextPriority) {
                    Letter nextLetter = pendingQueue.poll();
                    isQueued[nextLetter.id] = true;
                }
            }

            currentTime++;
        }

        System.out.print(currentTime + " | | ");
        for (int i = 0; i < n; i++) {
            System.out.print(allLetters[i].name + " ");
        }
        System.out.println();

        input.close();
    }
}

class Letter {
    String name;
    int duration;
    int priority;
    int id; 

    Letter(String name, int duration, int priority, int id) {
        this.name = name;
        this.duration = duration;
        this.priority = priority;
        this.id = id;
    }
}