package com.ferchoo;

public class Main {
    public static void main(String[] args)
    {
        EpicPartition epic = new EpicPartition();

        int n[] = {4};
        for (int i:n) {
            System.out.printf("Partition N = %d \n",i);
            System.out.println("Result: " + epic.createPartition(i));
        }
    }
}
