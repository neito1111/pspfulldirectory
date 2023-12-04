package Tigran;

import java.util.ArrayList;
import java.util.List;

public class apartment {
    private String street;
    private int cost;
   public apartment(String street,int cost){
        this.street=street;

        this.cost=cost;
    }
    public static void searchApartment(List<apartment> list,int cost){
        System.out.println(4);
       list.stream().forEach(obj->{
           System.out.println(5);
           if(obj.getCost()>=cost){
               System.out.println(obj.getStreet());
           }
       });

    }
    public int getCost(){
       return cost;
    }
    public String getStreet(){
        return street;
    }
}
