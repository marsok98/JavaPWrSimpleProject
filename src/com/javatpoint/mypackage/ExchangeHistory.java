package com.javatpoint.mypackage;

public class ExchangeHistory {
    private  int id,amount;
    private String key;
    private double value,result;

    public int getAmount(){return amount;}

    public int getId(){return id;}

    public String getKey(){return key;}

    public double getValue(){return value;}
    public double getResult(){return result;}

    public void setAmount(int x){this.amount = x;}

    public void setId(int x){this.id = x;}

    public void setKey(String x){this.key = x;}

    public void setValue(double x){this.value = x;}
    public void setResult(double x){this.result = x;}
}
