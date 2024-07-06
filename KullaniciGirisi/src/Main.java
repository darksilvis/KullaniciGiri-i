import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "password.txt";

    public static void main(String[] args) {
        String userName, password, newPassword;
        Scanner input = new Scanner(System.in);
        int attempts;

        // Şifreyi dosyadan oku
        password = readPasswordFromFile();

        while (true) {
            attempts = 3;
            while (attempts > 0) {
                System.out.print("Enter your userName: ");
                userName = input.nextLine();

                System.out.print("Enter your password: ");
                String enteredPassword = input.nextLine();

                if (userName.equals("darksilvis") && enteredPassword.equals(password)) {
                    System.out.println("Welcome to the application");
                    return;  // Başarılı giriş yapıldı, programdan çık
                } else {
                    attempts--;
                    if (attempts > 0) {
                        System.out.println("Your information is incorrect. Please try again! You have " + attempts + " attempts left.");
                    } else {
                        System.out.println("Your information is incorrect. You have no attempts left. Do you want to reset your password? (yes/no)");
                        String resetResponse = input.nextLine();

                        if (resetResponse.equalsIgnoreCase("yes")) {
                            while (true) {
                                System.out.print("Enter your new password: ");
                                newPassword = input.nextLine();

                                if (newPassword.equals(password)) {
                                    System.out.println("Password could not be created. Your password cannot be the same as your previous password. Please enter a different password.");
                                } else {
                                    System.out.println("Password created successfully.");
                                    password = newPassword;
                                    // Yeni şifreyi dosyaya yaz
                                    writePasswordToFile(password);
                                    break;
                                }
                            }
                        } else {
                            System.out.println("You need to create a new account.");
                            return;  // Şifre sıfırlanmadı, programdan çık
                        }
                    }
                }
            }

            System.out.println("Please log in with your new password.");
        }
    }

    private static String readPasswordFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.hasNextLine()) {
                    return fileScanner.nextLine();
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "learnJava123";  // Varsayılan şifre
    }

    private static void writePasswordToFile(String password) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(password);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
