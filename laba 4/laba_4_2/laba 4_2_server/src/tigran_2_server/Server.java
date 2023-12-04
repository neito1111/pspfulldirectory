package tigran_2_server;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Server {
    public static void main(String[] args) throws IOException {
        try {


        // Создание сокета сервера для обработки входящих пакетов UDP
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Сервер запущен. Ожидание подключения клиента...");
        // Буферы для получения и отправки данных
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        // Бесконечный цикл для прослушивания входящих сообщений
        while (true) { System.out.println("Ожидание данных от клиента...");
            // Получение первой переменной от клиента
            DatagramPacket receivePacket1 = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket1);
            String x = new String(receivePacket1.getData(), 0, receivePacket1.getLength());
            System.out.println("Первая переменная принята-"+x);

            // Получение второй переменной от клиента
            DatagramPacket receivePacket2 = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket2);
            String y = new String(receivePacket2.getData(), 0, receivePacket2.getLength());
            System.out.println("Вторая переменная принята-"+y);
            // Получение третьей переменной от клиента
            DatagramPacket receivePacket3 = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket3);
            String z = new String(receivePacket3.getData(), 0, receivePacket3.getLength());
            System.out.println("Третья переменная принята-"+z);
            if(x!=null && y!=null && z!=null){
                // Вычисление значения функции на основе полученных переменных
                double result = calculateFunction(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
                System.out.println("Результат -"+result);
                // Получение адреса клиента и порта для отправки данных обратно
                InetAddress IPAddress = receivePacket1.getAddress();
                int port = receivePacket1.getPort();
                saveToLogFile(x,y,z,result);
                // Преобразование результата в массив байтов и отправка обратно клиенту
                sendData = Double.toString(result).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
            else{
                System.out.println("Ошибка: Получены некорректные данные от клиента.");
            }


        }}
        catch (SocketException e){
            System.out.println("Ошибка при создании сокета сервера: " + e.getMessage());
        }catch (IOException e){
            System.out.println("Ошибка вводы вывода: " + e.getMessage());
        }
    }

    // Метод для вычисления функции на основе трех входных переменных
    private static double calculateFunction(double x, double y, double z) {
        return y + Math.exp(x - y) / (y + Math.pow(x, 2) / (y + Math.pow(x, 3) / y))
                * Math.pow((1 + Math.pow(Math.tan(z / 3), 2)), Math.sqrt(Math.abs(y) + 7));
    }
    private static void saveToLogFile(String var1, String var2,String var3, double result) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
            writer.append("Исходные данные: " + var1 + "+" +var2+"+" +var3+" Результат: " + result + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
