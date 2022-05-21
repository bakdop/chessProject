package model;

import java.util.ArrayList;

public class users implements Comparable<users> {
    private String accountNumber;
    private String password;
    private String name;

    public void setWinningTimes(int winningTimes) {
        this.winningTimes = winningTimes;
    }

    private int winningTimes=0;
    public users(String accountNumber,String password,String name){
        this.setPassword(password);
        this.setAccountNumber(accountNumber);
        this.setName(name);
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(users o) {
        if(this.winningTimes>o.winningTimes){
            return 1;
        }else if(this.winningTimes<o.winningTimes){
            return -1;
        }else {
            return 0;
        }
    }
    @Override
    public String toString(){
        return this.name+"\t\t"+winningTimes+"\t\t";
    }
}
