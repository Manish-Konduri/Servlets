package com.manish;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
        public static void adding(ArrayList<String> ar, String q)
        {
            ar.add(q);
        }
        public static void display(ArrayList<String>  ar)
        {
            if(ar.size()==0){
                System.out.println("No Data");
            }
            else {
                for (int i = 0; i < ar.size(); i++)
                    System.out.println(i+" "+ar.get(i) + " ");
            }
        }
        public static void searching(ArrayList<String>  ar)
        {
            if(ar.size()==0){
                System.out.println("No Data");
            }
            else {
                Scanner sc1 = new Scanner(System.in);
                System.out.println("Enter the String to be searched");
                String q = sc1.next();
                if (ar.contains(q)) {
                    System.out.println("Yes the string is present");
                } else {
                    System.out.println("No the string is not present");
                }
            }
        }
        public static void delete(ArrayList<String> ar)
        {
            Scanner sc1 = new Scanner(System.in);
            if (ar.size() == 0) {
                System.out.println("No Data");
            } else {
                System.out.println("Enter the index of the string to be removed");
                int q = sc1.nextInt();
                if (ar.get(q)!=null) {
                    ar.remove(q);
                    System.out.println("String removed");
                } else {
                    System.out.println("Entered string is not present in the list");
                }
            }
        }
        public static void main(String args[]) {
            Scanner sc = new Scanner((System.in));
            int inputq;
            ArrayList<String> ar = new ArrayList<String>();
            while (true) {
                System.out.println("1-Add Data, 2-Print Data, 3-Search, 4-Delete, 5-Quit");
                inputq = sc.nextInt();
                if (inputq == 1) {
                    System.out.println("Enter Name");
                    String q = sc.next();
                    adding(ar,q);
                } else if (inputq == 2) {
                    display(ar);
                } else if (inputq == 3) {
                    searching(ar);
                } else if (inputq == 4) {
                    delete(ar);
                }
                else if(inputq>=5){
                    break;
                }
            }
        }
    }
