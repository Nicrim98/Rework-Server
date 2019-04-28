import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

            // Server

            ServerSocket ss = new ServerSocket(4999);
            Socket s = ss.accept();
            System.out.println("Opposite player connected");

            try {
                Scanner scanner = new Scanner(System.in);
                Gameplay gameplay = new Gameplay("Kuba", "Marcin");
                System.out.println("Zaczyna gracz biały");
                while (gameplay.isWinner() == null) {

                    InputStreamReader in = new InputStreamReader(s.getInputStream());
                    BufferedReader bf = new BufferedReader(in);

                    System.out.println(gameplay);

                    String str = bf.readLine();
                    System.out.println("Whites row_from: " + str);
                    int row_from_opposite = Integer.parseInt(str) - 1;


                    String str2 = bf.readLine();
                    System.out.println("Whites column_from: " + str2);
                    int column_from_opposite = Integer.parseInt(str2) - 1;


                    String str3 = bf.readLine();
                    System.out.println("Whites row_to: " + str3);
                    int row_to_opposite = Integer.parseInt(str3) - 1;


                    String str4 = bf.readLine();
                    System.out.println("Whites column_to: " + str4);
                    int column_to_opposite = Integer.parseInt(str4) - 1;


                    try {
                        gameplay.make_move(new Position(row_from_opposite, column_from_opposite), new Position(row_to_opposite, column_to_opposite));
                    } catch (Bad_MoveException exception) {
                        System.out.println("Wprowazaj dobre wspolrzedne bo udusze!!!!!");
                    }


                    PrintWriter pr = new PrintWriter(s.getOutputStream());


                    try {
                        System.out.println(gameplay);
                        System.out.println("Podaj rzad pionka do ruszenia: ");
                        int row_from = scanner.nextInt() - 1;
                        pr.println(row_from + 1);
                        pr.flush();

                        System.out.println("Podaj kolumne pionka do ruszenia: ");
                        int column_from = scanner.nextInt() - 1;
                        pr.println(column_from + 1);
                        pr.flush();

                        System.out.println("Podaj rzad gdzie chesz zeby pionek sie znalazl: ");
                        int row_to = scanner.nextInt() - 1;
                        pr.println(row_to + 1);
                        pr.flush();

                        System.out.println("Podaj kolumne gdzie chesz zeby pionek sie znalazl: ");
                        int column_to = scanner.nextInt() - 1;
                        pr.println(column_to + 1);
                        pr.flush();

                        gameplay.make_move(new Position(row_from, column_from), new Position(row_to, column_to));
                    } catch (Bad_MoveException exception) {
                        System.out.println("Wprowazaj dobre wspolrzedne bo udusze!!!!!");
                    }
                }
            } catch (RuntimeException exception) {
                exception.printStackTrace();
            }
        }
}