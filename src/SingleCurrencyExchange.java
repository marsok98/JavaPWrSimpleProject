public class SingleCurrencyExchange
{
    public int ID;
    public int timeStamp;
    public String nameOfCurrency;
    public double exchangeRate;
    public int amountToExchange;
    public double resultOfCalculating;

    public SingleCurrencyExchange(int time, String name,double exchangeRate,int amount,double result)
    {
        this.timeStamp = time;
        this.nameOfCurrency = name;
        this.exchangeRate = exchangeRate;
        this.amountToExchange = amount;
        this.resultOfCalculating = result;
    }
    public SingleCurrencyExchange()
    {

    }
}
