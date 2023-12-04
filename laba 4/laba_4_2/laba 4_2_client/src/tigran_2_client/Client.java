package tigran_2_client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        try {

            // Создание сокета клиента для отправки и получения данных по протоколу UDP
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData = new byte[1024];
            System.out.println("Подключение к серверу...");
            // Чтение переменных с клавиатуры и отправка на сервер
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Введите первую переменную x: ");
            String var1 = inFromUser.readLine();
            sendData = var1.getBytes();
            DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket1);

            System.out.print("Введите вторую переменную y: ");
            String var2 = inFromUser.readLine();
            sendData = var2.getBytes();
            DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket2);

            System.out.print("Введите третью переменную z: ");
            String var3 = inFromUser.readLine();
            sendData = var3.getBytes();
            DatagramPacket sendPacket3 = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket3);

            // Получение результата от сервера
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Вывод результата
            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Результат: " + modifiedSentence);
            clientSocket.close();
        }catch (SocketException e){
            System.out.println("Ошибка при создании сокета сервера: " + e.getMessage());
        }catch (IOException e){
            System.out.println("Ошибка при вводе или выводе: " + e.getMessage());
        }
    }
}