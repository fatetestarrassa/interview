package com.icyfate.interview.test.core.enumeration;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 枚举的使用
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/6/15 17:27
 */
public class Pizza {

    private static EnumSet<PizzaStatus> unDeliveredPizzaStatusSet = EnumSet.of(PizzaStatus.ORDERED,PizzaStatus.READY);
    private PizzaStatus status;

    public enum PizzaStatus{
        ORDERED(5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY(2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        }
        ;
        private int timeToDElivery;

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDElivery() {
            return timeToDElivery;
        }


        PizzaStatus(int timeToDElivery) {
            this.timeToDElivery = timeToDElivery;
        }
    }

    public boolean isDelivered(){
        return this.status.isDelivered();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDElivery());
    }

    public static List<Pizza> getAllUndeliveredPizzas(List<Pizza> list){
        return list.stream().filter(s -> unDeliveredPizzaStatusSet.contains(s.getStatus())).collect(Collectors.toList());
    }

    public static EnumMap<PizzaStatus,List<Pizza>> groupPizzaByStatus(List<Pizza> list){
        EnumMap<PizzaStatus,List<Pizza>> map = new EnumMap<PizzaStatus, List<Pizza>>(PizzaStatus.class);

        Iterator<Pizza> iterator = list.iterator();
        while(iterator.hasNext()){
            Pizza pz = iterator.next();
            PizzaStatus status = pz.getStatus();
            if(map.containsKey(status)){
                map.get(status).add(pz);
            }else{
                List<Pizza> newPzList = new ArrayList<>();
                newPzList.add(pz);
                map.put(status, newPzList);
            }
        }

        return map;
    }

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public static void setUnDeliveredPizzaStatusSet(EnumSet<PizzaStatus> unDeliveredPizzaStatusSet) {
        Pizza.unDeliveredPizzaStatusSet = unDeliveredPizzaStatusSet;
    }

    public static void main(String[] args) {
        System.out.println(PizzaStatus.ORDERED.name());
        System.out.println(PizzaStatus.ORDERED);
        System.out.println(PizzaStatus.ORDERED.name().getClass());
        System.out.println(PizzaStatus.ORDERED.getClass());

        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setStatus(Pizza.PizzaStatus.DELIVERED);

        Pizza pz2 = new Pizza();
        pz2.setStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz3 = new Pizza();
        pz3.setStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz4 = new Pizza();
        pz4.setStatus(Pizza.PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        List<Pizza> undeliveredPzs = Pizza.getAllUndeliveredPizzas(pzList);
        System.out.println(undeliveredPzs.size());


        EnumMap<Pizza.PizzaStatus,List<Pizza>> map = Pizza.groupPizzaByStatus(pzList);
        System.out.println(map);

    }
}
