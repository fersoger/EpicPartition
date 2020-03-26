package com.ferchoo;

import com.ferchoo.EpicPartition;

public class Main {

    public static void main(String[] args) {
	// write your code here
        EpicPartition epic = new EpicPartition();

        int n[] = {3};
        for (int i:n
             ) {
            System.out.printf("Partition N = %d \n",i);
            System.out.println("Result: " + epic.createPartition(i));
        }

    }
}
