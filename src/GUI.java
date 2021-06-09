import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GUI
{
    JFrame mainFrame = new JFrame();
    JButton btnGetDataFromApi = new JButton();
    ExchangeRateFromApi currentRate;
    JList<  Map<String, BigDecimal> > listCurrency = new JList<>();
    DefaultListModel<   Map<String, BigDecimal> > model = new DefaultListModel<>();

    JLabel labelAboutListCurrency = new JLabel();
    JPanel panelAboutListCurrency = new JPanel();

    GUI()
    {
        createLists();
        createButtons();
        createFrame();
    }

    void createFrame()
    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setSize(500,500);
        mainFrame.setVisible(true);

        mainFrame.add(btnGetDataFromApi);
        mainFrame.add(listCurrency);
    }
    void createLists()
    {
        listCurrency.setBounds(200,200,500,1500);
        listCurrency.setVisible(true);
    }
    void createButtons()
    {
        btnGetDataFromApi.setBounds(50,100,50,50);
        btnGetDataFromApi.setText("Fetch Data");
        btnGetDataFromApi.setFocusable(false);
        btnGetDataFromApi.setHorizontalTextPosition(JButton.CENTER);
        btnGetDataFromApi.setVerticalTextPosition(JButton.BOTTOM);
        btnGetDataFromApi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDataFromApi();
            }
        });
    }

    ExchangeRateFromApi getDataFromApi()
    {
        HttpRequest request = new HttpRequest();
        ExchangeRateFromApi rateFromApi = request.getCurrentInfoFromApi();
        currentRate = rateFromApi;

        return rateFromApi;
    }

    void fillJList() {
        listCurrency.setModel(model);
        for (Map.Entry<String, BigDecimal> entry : currentRate.rates.entrySet())
        {
            String key = entry.getKey();
            BigDecimal value = entry.getValue();

        }

    }

}
