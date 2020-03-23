package com.java.spring.hibernate.controller;

import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.transaction.Transactions;

import java.util.Scanner;

public class Controller {
    private int option;
    private Transactions transactions;
    private Scanner sc = new Scanner(System.in);

    public Controller() {
        transactions = new Transactions();
    }

    public void performOperations(){
        while (true) {
            printOptions();
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {

                case 1:
                       System.out.println(transactions.addBranch(takeBranch()));
                       break;
                case 2:
                      System.out.println(transactions.removeBranch(takeId()));
                      break;
                case 3:
                     System.out.println( transactions.addCustomer(createCustomer(),branchID()));
                      break;
                case 4:
                       System.out.println(transactions.removeCustomer(takeId()));
                       break;
                case 5:
                        System.out.println(transactions.debitBalance(Integer.parseInt(takeId()),takeBalance()));
                        break;
                case 6:
                       System.out.println(transactions.creditBalance(Integer.parseInt(takeId()),takeBalance()));
                       break;
                case 7:
                       System.out.println(transactions.showCustomers());
                       break;
                case 8:
                       System.out.println(transactions.showBranches());
                       break;
                case 9:
                      System.out.println(transactions.updateEmployee(Integer.parseInt(takeId()),updateCustomer()));
                      break;
                default:
                        return;
            }
        }
    }
    private void  printOptions() {
        System.out.println("1.Add Branch\n2.Remove Branch\n3.Add Customer\n4.Remove Customer\n5.Debit Balance\n6.Credit Balance");
        System.out.println("7.Show all customer\n8.Show all branches ");
        System.out.println("9. Update Customer !!!");
    }
    private String takeId() {
        System.out.println("Enter  id :: ");
        String id = sc.nextLine();
        return id;
    }
    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setBalance(12000);
        customer.setName("Ankit");
        return customer;
    }
    private String branchID(){
        System.out.println("Enter Bracnch ID : ");
        String branchCode = sc.nextLine();
        return branchCode;
    }
    private double takeBalance(){
        System.out.println("Enter balance to be processed :: ");
        double bal = sc.nextDouble();
        return bal;
    }
    private String takeBranch(){
        System.out.println("Enter branch Name :: ");
        String branch = sc.nextLine();
        return branch;
    }
    private Customer updateCustomer() {

        Customer customer = new Customer();
        System.out.println("Enter balance ");
        customer.setBalance(sc.nextDouble());
        System.out.println("Enter Name : ");
        customer.setName(sc.nextLine());
        return customer;
    }
}
