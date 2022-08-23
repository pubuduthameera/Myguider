package com.example.myguider.firebase;

import com.example.myguider.Firebase_database;

public class Firebase_modle {
    private Firebase_database fib;
    private guid_acc_setting gas;

    public Firebase_modle(Firebase_database fib, guid_acc_setting gas) {
        this.fib = fib;
        this.gas = gas;
    }

    public  Firebase_modle(){

    }

    public Firebase_database getFib() {
        return fib;
    }

    public void setFib(Firebase_database fib) {
        this.fib = fib;
    }

    public guid_acc_setting getGas() {
        return gas;
    }

    public void setGas(guid_acc_setting gas) {
        this.gas = gas;
    }

    @Override
    public String toString() {
        return "Firebase_modle{" +
                "fib=" + fib +
                ", gas=" + gas +
                '}';
    }
}
