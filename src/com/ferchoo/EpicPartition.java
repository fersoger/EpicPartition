package com.ferchoo;

import java.lang.reflect.Array;

public class EpicPartition {
    public String createPartition(int N) {

        if (N<1) {
            return "";
        }

        String placeholder = new String();

        return permutation(placeholder, N,' ');
    }

    public String permutation (String permute, int N, char next){

        char localPermute[] = permute.toCharArray();
        String newPermute = new String (permute.toString());

        // count a's b's and c's
        int aCount=0;
        int bCount=0;
        int cCount=0;

        for (char element:localPermute) {
            if (element == 'a') aCount++;
            else if (element == 'b') bCount++;
            else if (element == 'c') cCount++;
            else return "FALLA LOCA";
        }

        // System.out.printf("%d %d %d \n",aCount,bCount,cCount);
        // System.out.println(newPermute.toString());

        ;
        if ((aCount+bCount+cCount) < (6*N)) {
            switch (next){
                case 'a': newPermute = newPermute.concat("a");aCount++;
                    break;
                case 'b': newPermute = newPermute.concat("b");bCount++;
                    break;
                case 'c': newPermute = newPermute.concat("c");cCount++;
                    break;
            }
            int MAX = 6*N/3;

            //System.out.printf("%d %d %d> " + newPermute.toString() + "\n",aCount,bCount,cCount);

            if (aCount < MAX) permutation(newPermute, N,'a');
            if (bCount < MAX) permutation(newPermute, N,'b');
            if (cCount < MAX) permutation(newPermute, N,'c');

            if ( (aCount+bCount+cCount) == 6*N){

                //Combination is maxed so evaluate for success criteria

                int index = 1;
                int sumA = 0;
                int sumB = 0;
                int sumC = 0;

                //make the sum based on the permutation string
                for (char element : localPermute) {
                    if (element == 'a') sumA = +index++;
                    else if (element == 'b') sumB = +index++;
                    else if (element == 'c') sumC = +index++;
                }
                // for debugging
                //System.out.printf("SUMS: %d %d %d >" + newPermute.toString() + "\n", sumA, sumB, sumC);

                // Additionally, this partition must be such that sum(C) = 2 * sum(A) = 2 * sum(B).
                if ((sumC == 2 * sumA) && (sumC == 2 * sumB)) {
                    System.out.printf("SUMS: %d %d %d >" + newPermute.toString() + "\n", sumA, sumB, sumC);
                    System.out.println("Bingo!!!"+newPermute.toString());
                    return permute;
                } else
                    return ""; // not a good case
            }
        }

        return "";
    }
}

/*
 Problem Statement for EpicPartition


Problem Statement

Ramanujan loved partitions. And he also loved natural numbers. So, yay! He has a problem for you.
The set S consists of the first 6*N natural numbers. That is, S = {1, 2, ..., 6*N}. You have to partition S into three equally-large sets A, B, C. Additionally, this partition must be such that sum(C) = 2 * sum(A) = 2 * sum(B).
If there is no solution, return an empty String. Otherwise, return a String of length 6*N that describes any one valid solution. For each i, the i-th character (1-based index) of the return value should be 'a' if value i belongs to A, 'b' for B, or 'c' for C.

Definition

Class:
EpicPartition
Method:
createPartition
Parameters:
int
Returns:
String
Method signature:
String createPartition(int N)
(be sure your method is public)



Constraints
-
N will be between 1 and 100, inclusive.

Examples
0)


1
Returns: ""

1)


2
Returns: ""

2)


4
Returns: "aaabababbbbabbcccccccaac"
A = {1,2,3,4,6,18,19,22}. Sum(A) = 75.
B = {5,7,8,9,10,11,12,13}. Sum(B) = 75
C = {14,15,16,17,20,21,23,24}. Sum(C) = 150

Satisfies size(A) = 2 * 4, size(B) = 2 * 4, size(C) = 2 * 4.
And union of A,B,C = {1,2,3,...6*4}.
And sum(C) = 2 * sum(A) = 2 * sum(B)
3)


8
Returns: "abaaaaaabaabbaabbbbbbbbbbbbccccccccaaccccccacaac"
 */
