package Tigran;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
       List<apartment> apart=new ArrayList<>();
        // Инициализация данных квартир на сервере
        apart.add(new apartment("Very Khoryzhey 14",1000));
        apart.add(new apartment("Very Khoryzhey 16",600));
        apart.add(new apartment("Very Khoryzhey 15",2000));

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Сервер запущен. Ожидание подключения...");// Порт для прослушивания
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept(); // Ожидание подключения клиента
                System.out.println("Клиент подключился: " + clientSocket);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                int maxPrice = Integer.parseInt(in.readLine());// Получение предельной суммы от клиента
                System.out.println(maxPrice);

                // Поиск адресов квартир с ценой не выше maxPrice
                StringBuilder result = new StringBuilder();
                apart.stream().forEach(obj->{
                    if (obj.getCost() >= maxPrice){
                        result.append(obj.getStreet()).append("/n");

                    }
                });
                /*for (int i = 0; i < apart.size(); i++) {
                    if (apart.get <= maxPrice) {
                        result.append(addresses.get(i)).append("\n");
                    }
                }*/
                /*for ( apartment obj : apart) {
                    System.out.println(obj.getCost());
                    if (obj.getCost() <= maxPrice) {
                        result.append(obj.getStreet());
                        System.out.println(obj.getStreet());
                    }
                }*/
               /* System.out.println(2);
                apartment.searchApartment(apart,maxPrice);
                System.out.println(3);*/
                out.println(result.toString()); // Отправка адресов клиенту

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }
    }
}