package com.ferchoo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class EpicPartition {
    public String createPartition(int N) {

        if (N<1) {
            return "";
        }

        String str = "";

        for (int i = 0; i<N*6/3; i++){
            str = str.concat("abc");
        }

        char[] sortedStr = str.toCharArray();

        sortedPermutations(sortedStr);

        str = Arrays.toString(sortedStr);

        return str;
    }

    public String checkString (char[] permute){

        char[] localPermute = permute;

        // count a's b's and c's
        int index = 1;
        int sumA = 0;
        int sumB = 0;
        int sumC = 0;
        int aCount=0;
        int bCount=0;
        int cCount=0;

        for (char element:localPermute) {
            if (element == 'a') {
                aCount++;
                sumA =+ index;
            }
            else if (element == 'b') {
                bCount++;
                sumB =+ index;
            }
            else if (element == 'c') {
                cCount++;
                sumC =+ index;
            }
            else return "FALLA LOCA";
            index++;
        }

        // System.out.printf("%d %d %d \n",aCount,bCount,cCount);
        // System.out.println(newPermute.toString());
        // System.out.printf("%d %d %d> " + newPermute.toString() + "\n",aCount,bCount,cCount);
        // System.out.printf("COUNTS: %d %d %d SUMS: %d %d %d >" + permute.toString() + "\n", aCount,bCount,cCount,sumA, sumB, sumC);

        // Additionally, this partition must be such that sum(C) = 2 * sum(A) = 2 * sum(B).
        if ((sumC == 2 * sumA) && (sumC == 2 * sumB)) {
            System.out.printf("SUMS: %d %d %d >" + Arrays.toString(permute) + "\n", sumA, sumB, sumC);
            System.out.println("Bingo!!!" + Arrays.toString(permute));
            return Arrays.toString(permute);
        }

        return "";
    }

    /**
     * permutation function
     * @param str string to calculate permutation for
     * @param l starting index
     * @param r end index
     */
    private void permute(String str, int l, int r)
    {
        if (l == r)
            checkString(str.toCharArray());
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }

    /**
     * Swap Characters at position
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    // A utility function two swap two characters
    // a and b
    void swap(char str[], int a, int b)
    {
        char t = str[a];
	    str[a] = str[b];
	    str[b] = t;
    }

    // This function finds the index of the
    // smallest character which is greater
    // than 'first' and is present in str[l..h]
    int findCeil(char str[], char first, int l, int h)
    {
        // initialize index of ceiling element
        int ceilIndex = l;

        // Now iterate through rest of the
        // elements and find the smallest
        // character greater than 'first'
        for (int i = l + 1; i <= h; i++)
            if (str[i] > first && str[i] < str[ceilIndex])
                ceilIndex = i;

        return ceilIndex;
    }

    // Print all permutations of str in sorted order
    void sortedPermutations(char str[])
    {
        // Get size of string
        int size = str.length;

        // Sort the string in increasing order
        //qsort(str, size, sizeof(str[0]), compare);
        Arrays.parallelSort(str);

        // Print permutations one by one
        boolean isFinished = false;


        long x = 1;

        while (!isFinished) {

            // print this permutation if matches the criteria
            String result = checkString(str);
            System.out.printf("%d \r",x++);
            //System.out.printf("%d "+Arrays.toString(str)+"\n", x++);

            // Find the rightmost character
            // which is smaller than its next
            // character. Let us call it 'first
            // char'
            int i;
            for (i = size - 2; i >= 0; --i)
                if (str[i] < str[i + 1])
                    break;

            // If there is no such character, all
            // are sorted in decreasing order,
            // means we just printed the last
            // permutation and we are done.
            if (i == -1)
                isFinished = true;
            else {

                // Find the ceil of 'first char'
                // in right of first character.
                // Ceil of a character is the
                // smallest character greater
                // than it
                int ceilIndex = findCeil(str,
                        str[i], i + 1, size - 1);

                // Swap first and second characters
                swap(str,i, ceilIndex);

                // Sort the string on right of 'first char'
                Arrays.parallelSort(str, i+1, str.length);
                // in C: qsort(str + i + 1, size - i - 1,sizeof(str[0]), compare);
            }
        }
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
