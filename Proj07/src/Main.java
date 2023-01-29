import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("How many customers for initial queue (0 to 100)? ");
        int initialQueue = inputInt(0, 100);
        System.out.print("How many techs on duty (1 to 50)? ");
        int techsOnDuty = inputInt(1, 50);
        System.out.print("How many hours to simulate (1 to 24)? ");
        int timeElapse = 60 * inputInt(1, 24);
        System.out.print("Reporting interval (in minutes, 1 to 60)? ");
        int reportingInterval = inputInt(1, 60);
        System.out.print("Incoming customer call interval (in minutes, 1 to 60)? ");
        int callRate = inputInt(1, 60);
        System.out.print("Mean call time, in minutes (1.0 to 59.0)? ");
        double callAverage = inputDouble(1.0, 59.0);
        System.out.print("Call time standard deviation, in minutes (0.5 to 59.5)? ");
        double std = inputDouble(0.5, 59.5);
        System.out.println();

        CallCenterSimulation ccs = new CallCenterSimulation(initialQueue, techsOnDuty, timeElapse, reportingInterval, callRate, callAverage, std);
        ccs.start();
    }

    public static int inputInt(int lowerBound, int upperBound) {
        Scanner console = new Scanner(System.in);
        int userInput;
        do {
            while (!console.hasNextInt()) {
                System.out.println("Enter a number");
                console.next();
            }
            userInput = console.nextInt();
            if (userInput < lowerBound || userInput > upperBound) {
                System.out.println("Enter a number between " + lowerBound + " and " + upperBound);
            }
        }
        while (userInput < lowerBound || userInput > upperBound);
        return userInput;
    }

    public static double inputDouble(double lowerBound, double upperBound) {
        Scanner console = new Scanner(System.in);
        double userInput;
        do {
            while (!console.hasNextDouble()) {
                System.out.println("Enter a number");
                console.next();
            }
            userInput = console.nextDouble();
            if (userInput < lowerBound || userInput > upperBound) {
                System.out.println("Enter a number between " + lowerBound + " and " + upperBound);
            }
        }
        while (userInput < lowerBound || userInput > upperBound);
        return userInput;
    }
}
