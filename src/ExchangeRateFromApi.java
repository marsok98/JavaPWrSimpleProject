import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Map;

public class ExchangeRateFromApi
{
    public String disclaimer;
    public String license;
    public int timestamp;
    public String base;
    public Map<String, BigDecimal> rates;
    //public Dictionary<String, BigDecimal>

    ExchangeRateFromApi(String disclaimer, String license, int timestamp, String base, Map<String, BigDecimal> rates)
    {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }
}


