package ejercicio3;

import java.io.*;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first command:");
        String cmd1 = scanner.nextLine();
        System.out.println("Enter second command:");
        String cmd2 = scanner.nextLine();


        ProcessBuilder pb1 = new ProcessBuilder(cmd1.split(" "));
        ProcessBuilder pb2 = new ProcessBuilder(cmd2.split(" "));
        try {
            // Executing first command
            Process process1 = pb1.start();
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));

            // Saving p1 output on a variable
            String line;
            String process1Output = "";
            while ((line = reader1.readLine()) != null) {
                process1Output+=line+"\r\n";
            }

            Process p2 = pb2.start();

            // Writting p1 output on p2 input
            BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));
            writer2.write(process1Output);
            writer2.close();

            // Showing p2 output on console
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            while ((line = reader2.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        }
    }
}
