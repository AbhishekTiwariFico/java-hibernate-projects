package com.java.spring.hibernate.transaction;

import com.java.spring.hibernate.exceptions.LowBalance;
import com.java.spring.hibernate.model.Branch;
import com.java.spring.hibernate.model.Customer;
import com.java.spring.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class Transactions {
    public String addBranch(String branchName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Branch branch = new Branch();
        branch.setBranchName(branchName);
        session.persist(branch);
        session.getTransaction().commit();
        session.close();
        return "Branch has been successfully added  " + branch;
    }

    public String removeBranch(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Branch branch = (Branch) session.get(Branch.class,id);

        if(branch != null) {
            session.delete(branch);
            session.getTransaction().commit();
            session.close();
            return "Branch with id : " + id + " has been successfully deleted ";
        }
        session.close();
        return "Error !!!  404 No Branch found with this Id !!!";
    }

    public String addCustomer(Customer customer , String branchCode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Branch branch = (Branch) session.get( Branch.class, branchCode);
        if(branch == null) {
            return "There is no branch with this  branch code " + branchCode;
        }
        customer.setAccountID(branchCode);
        customer.setBranch(branch);
        session.persist(customer);
        session.getTransaction().commit();
        session.close();
        return "Customer has been added successfully : " + customer;
    }

    public String removeCustomer(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);

        if(customer != null) {
            session.delete(customer);
            session.getTransaction().commit();
            session.close();
            return "Customer with id : " + id + " has been successfully deleted ";
        }
        session.close();
        return "Error !!!  404 No Customer found with this Id !!!";
    }

    public String creditBalance(int id, double balance){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);
        customer.setBalance(customer.getBalance()+balance);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return "Balance has been credited : " +customer;
    }

    public String debitBalance(int id, double balance){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class,id);
        if(customer == null) {
            session.close();
            return "Customer with id : " + id + " does not exists ";
        }
        if(customer.getBalance() < balance) {
            throw new LowBalance("!!! Not Enough balance in account ");
        }
        customer.setBalance(customer.getBalance()- balance);
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return "Balance has been credited : " +customer;
    }

    public List<Branch> showBranches(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Branch> branches= session.createQuery("from Branch ").list();
        session.getTransaction().commit();
        session.close();
        return branches;
    }

    public List<Customer> showCustomers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Customer> customers= session.createQuery("from Customer ").list();
        session.getTransaction().commit();
        session.close();
        return customers;
    }
    public String updateEmployee(int id, Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customer oldCustomer = (Customer) session.get(Customer.class,id);
        oldCustomer.setBalance(customer.getBalance());
        oldCustomer.setName(customer.getName());
        session.update(customer);
        session.getTransaction().commit();
        session.close();
        return "Customer  has been updated : " +oldCustomer;
    }
}
