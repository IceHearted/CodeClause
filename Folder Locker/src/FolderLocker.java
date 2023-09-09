import java.io.*;
import java.util.Scanner;

public class FolderLocker {

    private static final String SECRET_FOLDER = "secret";
    private static final String LOCKED_FOLDER = "secret.locked";
    private static final String PASSWORD = "123"; // Replace with your desired password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Lock Folder");
            System.out.println("2. Unlock Folder");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    lockFolder();
                    break;
                case 2:
                    unlockFolder();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void lockFolder() {
        File secretFolder = new File(SECRET_FOLDER);
        if (!secretFolder.exists() || !secretFolder.isDirectory()) {
            System.out.println("Folder not found.");
            return;
        }

        System.out.print("Enter password to lock the folder: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        if (password.equals(PASSWORD)) {
            File lockedFolder = new File(LOCKED_FOLDER);
            if (!lockedFolder.exists()) {
                lockedFolder.mkdir();
            }

            File[] filesToLock = secretFolder.listFiles();
            if (filesToLock != null) {
                for (File file : filesToLock) {
                    File newFile = new File(LOCKED_FOLDER, file.getName());
                    file.renameTo(newFile);
                }
            }

            secretFolder.delete();
            System.out.println("Folder locked successfully.");
        } else {
            System.out.println("Incorrect password.");
        }
    }

    private static void unlockFolder() {
        System.out.print("Enter password to unlock the folder: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        if (password.equals(PASSWORD)) {
            File lockedFolder = new File(LOCKED_FOLDER);
            if (!lockedFolder.exists() || !lockedFolder.isDirectory()) {
                System.out.println("Locked folder not found.");
                return;
            }

            File[] filesToUnlock = lockedFolder.listFiles();
            if (filesToUnlock != null) {
                for (File file : filesToUnlock) {
                    File newFile = new File(SECRET_FOLDER, file.getName());
                    file.renameTo(newFile);
                }
            }

            lockedFolder.delete();
            System.out.println("Folder unlocked successfully.");
        } else {
            System.out.println("Incorrect password.");
        }
    }
}
