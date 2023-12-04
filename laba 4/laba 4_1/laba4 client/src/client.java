import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", 4444);
            System.out.println("Подключено к серверу: " + echoSocket.getInetAddress());// Подключение к localhost на порт 4444
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Не удалось установить соединение с сервером: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите максимальную стоимость квартиры: ");
        String userInput = stdIn.readLine();

        out.println(userInput); // Отправляем серверу предельную стоимость

        String fromServer;
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Список квартир, подходящих под критерии: ");
            System.out.println(fromServer); // Получаем и выводим адреса квартир
            break;
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}