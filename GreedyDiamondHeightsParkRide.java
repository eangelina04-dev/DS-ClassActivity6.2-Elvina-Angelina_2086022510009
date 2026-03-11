import java.util.Queue;
import java.util.Scanner;

public class GreedyDiamondHeightsParkRide {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int visitorCount = input.nextInt();
        input.nextLine(); 

        String nameLine = input.nextLine();
        String moneyLine = input.nextLine();

        String[] names = nameLine.split(", ");
        String[] amounts = moneyLine.split(", ");

        String[] namesWithoutJeff = new String[visitorCount];
        int[] moneyWithoutJeff = new int[visitorCount];
        int validCount = 0;

        for (int i = 0; i < visitorCount; i++) {
            if (!names[i].equals("Jeff") && !names[i].equals("jeff")) {
                namesWithoutJeff[validCount] = names[i];
                moneyWithoutJeff[validCount] = Integer.parseInt(amounts[i]);
                validCount++; 
            }
            else {
                System.out.println("Send " + names[i] + " to failure management ");
            }
        }

        for (int i = 0; i < validCount - 1; i++) {
            for (int j = 0; j < validCount - i - 1; j++) {
                
                if (moneyWithoutJeff[j] < moneyWithoutJeff[j + 1]) {
                    
                    int tempMoney = moneyWithoutJeff[j];
                    moneyWithoutJeff[j] = moneyWithoutJeff[j + 1];
                    moneyWithoutJeff[j + 1] = tempMoney;

                    String tempName = namesWithoutJeff[j];
                    namesWithoutJeff[j] = namesWithoutJeff[j + 1];
                    namesWithoutJeff[j + 1] = tempName;
                }
            }
        }

        Queue<String> finalQueue = new java.util.LinkedList<>();
        
        for (int i = 0; i < validCount; i++) {
            finalQueue.add(namesWithoutJeff[i]);
        }
        System.out.println(finalQueue);

        input.close();
    }
}