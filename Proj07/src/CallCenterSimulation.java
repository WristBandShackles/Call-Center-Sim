import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Scanner;

public class CallCenterSimulation {
    private Timer timer = new Timer();
    private static int timerCounter = 0;
    private int timeElapse;
    private int reportingInterval;
    private int callRate;
    private double callAverage;
    private double std;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Tech> techs = new ArrayList<>();
    private HashSetQueue<Customer> waitingCustomers = new HashSetQueue<>();
    private HashSetQueue<Tech> waitingTechs = new HashSetQueue<>();
    private HeapPriorityQueue<SupportSession> inProgress = new HeapPriorityQueue<>();

    public CallCenterSimulation(int initialQueue, int techsOnDuty, int timeElapse, int reportingInterval, int callRate, double callAverage, double std) {
        this.timeElapse = timeElapse;
        this.reportingInterval = reportingInterval;
        this.callRate = callRate;
        this.callAverage = callAverage;
        this.std = std;

        try {
            Scanner readFile = new Scanner(new File("Customers.csv"));
            if (readFile.hasNextLine()) {
                readFile.nextLine();
            }
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] customerParam = line.split(",");
                customers.add(new Customer(customerParam[0], customerParam[1], customerParam[2], customerParam[3], customerParam[4]));
            }
        }
        catch (FileNotFoundException e) {}

        try {
            Scanner readFile = new Scanner(new File("Techs.csv"));
            if (readFile.hasNextLine()) {
                readFile.nextLine();
            }
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] techParam = line.split(",");
                techs.add(new Tech(techParam[0], techParam[1], techParam[2], techParam[3]));
            }
        }
        catch (FileNotFoundException e) {}

        while (waitingTechs.size() < techsOnDuty) {
            Random rand = new Random();
            waitingTechs.add(techs.get(rand.nextInt(techs.size() - 1)));
        }

        while (waitingCustomers.size() < initialQueue ) {
            Random rand = new Random();
            waitingCustomers.add(customers.get(rand.nextInt(customers.size() - 1)));
        }

        System.out.println("---------------------------------------------");
        System.out.println("      CALL CENTER SIMULATION PARAMETERS");
        System.out.println("---------------------------------------------");
        System.out.println("Techs on duty:         " + techsOnDuty);
        System.out.println("Initial cal queue:     " + initialQueue);
        System.out.println("Minutes to simulate:   " + timeElapse);
        System.out.println("Call time mean:        " + callAverage);
        System.out.println("Call time std dev:     " + std);
        System.out.println("New call every (min):  " + callRate);
        System.out.println("Report every (min):    " + reportingInterval);
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    public void start() {
        timer.schedule(new Simulation(), 0, 1000);
    }

    private class Simulation extends TimerTask {

        @Override
        public void run() {

            while (waitingTechs.size() > 0 && waitingCustomers.size() > 0) {
                Random rand = new Random();
                double callLength = rand.nextGaussian() * std + callAverage;
                if (callLength < 1) {
                    callLength = 1;
                }
                inProgress.add(new SupportSession(waitingCustomers.remove(), waitingTechs.remove(), callLength, timerCounter + callLength));
            }

            if (timerCounter != 0 && timerCounter % callRate == 0) {
                Random rand = new Random();
                waitingCustomers.add(customers.get(rand.nextInt(customers.size() - 1)));
            }

            while (inProgress.size() > 0 && Math.floor(inProgress.peek().getCallFinish()) <= timerCounter) {
                System.out.println("Call finished: " + inProgress.peek().toString());
                waitingTechs.add(inProgress.remove().getTech());
            }

            if (timerCounter % reportingInterval == 0) {
                System.out.println();
                System.out.println("STATUS @ minute " + timerCounter + ":   techs/custs: " + waitingTechs.size() + "/" + waitingCustomers.size());
            }

            if (timerCounter >= timeElapse) {
                timer.cancel();
            }

            timerCounter++;
        }
    }
}
