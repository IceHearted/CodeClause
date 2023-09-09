import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TimerAndStopwatch {
    private static int secondsPassed = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Start Timer");
            System.out.println("2. Start Stopwatch");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    startTimer();
                    break;
                case 2:
                    startStopwatch();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void startTimer() {
        System.out.print("Enter the timer duration in seconds: ");
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int remainingSeconds = seconds;

            @Override
            public void run() {
                System.out.println("Timer: " + remainingSeconds + " seconds remaining");
                remainingSeconds--;

                if (remainingSeconds < 0) {
                    System.out.println("Time's up!");
                    timer.cancel();
                }
            }
        }, 0, 1000); // Schedule the task to run every 1000 milliseconds (1 second)

        // Wait for the timer to complete
        try {
            Thread.sleep((seconds + 1) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startStopwatch() {
        secondsPassed = 0;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                System.out.println("Stopwatch: " + formatTime(secondsPassed));
            }
        }, 0, 1000); // Schedule the task to run every 1000 milliseconds (1 second)

        System.out.println("Stopwatch started. Press Enter to stop.");
        new Scanner(System.in).nextLine(); // Wait for user input to stop
        timer.cancel(); // Stop the stopwatch
        System.out.println("Stopwatch stopped.");
    }

    private static String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}
