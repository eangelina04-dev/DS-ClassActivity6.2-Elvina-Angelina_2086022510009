import java.util.Queue;
import java.util.Scanner;

public class DoorResponse {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int queueLength = input.nextInt();
        
        String[] names = new String[queueLength];
        for (int i = 0; i < queueLength; i++) {
            names[i] = input.next(); 
        }
        
        int[] chances = new int[queueLength];
        for (int i = 0; i < queueLength; i++) {
            chances[i] = input.nextInt(); 
        }

        Queue<String> nameQueue = new java.util.LinkedList<>();
        Queue<Integer> chanceQueue = new java.util.LinkedList<>();

        for (int i = 0; i < queueLength; i++) {
            nameQueue.add(names[i]);
            chanceQueue.add(chances[i]);
        }

        while (!nameQueue.isEmpty()) {
            
            String currentName = nameQueue.remove();
            int currentChance = chanceQueue.remove();

            int remainingChance = currentChance - 1;

            if (remainingChance > 0) {
                System.out.println(currentName + "|Try Again|" + remainingChance);
                
                nameQueue.add(currentName);
                chanceQueue.add(remainingChance);
                
            } else {
                System.out.println(currentName + "|Get Out|0");
                
            }
        }

        input.close();
    }
}