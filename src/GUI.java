
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;




public class GUI
{

    ExchangeRateFromApi currentRate;
    DefaultTableModel model = new DefaultTableModel();
    JTable listCurrency = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(listCurrency);
    //DefaultListModel<   Map<String, BigDecimal> > model = new DefaultListModel<>();


    DefaultTableModel dataBaseModel = new DefaultTableModel();
    JTable dataBaseTable = new JTable(dataBaseModel);
    JScrollPane scrollPane2 = new JScrollPane(dataBaseTable);

    JFrame mainFrame = new JFrame();
    JButton btnGetDataFromApi = new JButton();//full
    JButton btnDoExchange = new JButton(); //uzyto ale wrzucono na panel
    JButton btnSaveToDatabase = new JButton();
    JButton btnShowHistory = new JButton();//

    JLabel labelAboutListCurrency = new JLabel();
    JLabel labelWriteCurrency = new JLabel();
    JLabel labelWriteAmount = new JLabel();
    JLabel labelShowResult = new JLabel();
    JLabel labelShowRate = new JLabel();
    JLabel labelShowTimeStamp = new JLabel();

    JPanel panelAboutListCurrency = new JPanel();

    JTextField fieldToWriteCurrency  = new JTextField();//
    JTextField fieldToWriteAmount  = new JTextField();//
    JTextField fieldToShowResult = new JTextField();//
    JTextField fieldToShowRate = new JTextField(); //
    JTextField fieldToShowTimeStamp = new JTextField();//





    GUI()
    {
        createLists();
        createButtons();
        createFields();
        createLabels();
        createFrame();
    }

    void createLabels()
    {
        labelWriteCurrency.setBounds(250,10,100,50);
        labelWriteCurrency.setText("Write Currency");

        labelWriteAmount.setBounds(550,10,100,50);
        labelWriteAmount.setText("Write Amount");

        labelShowResult.setBounds(700,10,100,50);
        labelShowResult.setText("Result");

        labelShowRate.setBounds(400,10,100,50);
        labelShowRate.setText("Rate");

        labelShowTimeStamp.setBounds(50,100,100,50);
        labelShowTimeStamp.setText("Current Timestamp");
    }
    void createFrame()
    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setSize(1000,500);
        mainFrame.setVisible(true);

        mainFrame.add(btnGetDataFromApi);
        mainFrame.add(btnDoExchange);
        mainFrame.add(btnSaveToDatabase);

        mainFrame.add(scrollPane);
        mainFrame.add(scrollPane2);
        mainFrame.add(fieldToWriteCurrency);
        mainFrame.add(fieldToShowRate);
        mainFrame.add(fieldToWriteAmount);
        mainFrame.add(fieldToShowResult);
        mainFrame.add(fieldToShowTimeStamp);

        mainFrame.add(labelWriteCurrency);
        mainFrame.add(labelWriteAmount);
        mainFrame.add(labelShowResult);
        mainFrame.add(labelShowRate);
        mainFrame.add(labelShowTimeStamp);
        //mainFrame.add();


    }

    void createFields()
    {
        fieldToWriteCurrency.setBounds(250,50,100,50);

        fieldToShowRate.setBounds(400,50,100,50);
        fieldToShowRate.setEditable(false);

        fieldToWriteAmount.setBounds(550,50,100,50);
        fieldToShowResult.setBounds(700,50,100,50);
        fieldToShowResult.setEditable(false);

        fieldToShowTimeStamp.setBounds(50,150,100,50);
        fieldToShowTimeStamp.setEditable(false);

    }
    void createLists()
    {
        model.addColumn("Key");
        model.addColumn("Value");
        listCurrency.setBounds(20,250,140,200);
        listCurrency.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane.setBounds(20,250,140,200);
    }
    void createButtons()
    {
        btnGetDataFromApi.setBounds(50,50,100,50);
        btnGetDataFromApi.setText("Fetch Data");
        btnGetDataFromApi.setFocusable(false);
        btnGetDataFromApi.setHorizontalTextPosition(JButton.CENTER);
        btnGetDataFromApi.setVerticalTextPosition(JButton.BOTTOM);
        btnGetDataFromApi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDataFromApi();
                fillJList();
            }
        });

        btnDoExchange.setBounds(250,150,200,100);
        btnDoExchange.setText("Do exchange");
        btnDoExchange.setFocusable(false);
        btnDoExchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doExchange();
            }
        });

        btnSaveToDatabase.setBounds(550,150,200,100);
        btnSaveToDatabase.setText("Save to database");
        btnSaveToDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TO DO wpisywanie do bazy danych
            }
        });
    }

    ExchangeRateFromApi getDataFromApi()
    {
        HttpRequest request = new HttpRequest();
        ExchangeRateFromApi rateFromApi = request.getCurrentInfoFromApi();
        currentRate = rateFromApi;
        fieldToShowTimeStamp.setText(Integer.toString(currentRate.timestamp));
        return rateFromApi;
    }
    //to nie dziala
    void fillJList()
    {

        for (Map.Entry<String, BigDecimal> entry : currentRate.rates.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            model.addRow(new Object[]{key,value});
        }
        listCurrency.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listCurrency.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String key = listCurrency.getValueAt(listCurrency.getSelectedRow(), 0).toString();
                String Value= listCurrency.getValueAt(listCurrency.getSelectedRow(), 1).toString();
                fieldToWriteCurrency.setText(key);
                fieldToShowRate.setText(Value);

            }
        });

    }

    void doExchange()
    {
        try {
        String currencyInString = fieldToWriteCurrency.getText();
        BigDecimal rate = currentRate.rates.get(currencyInString.toUpperCase(Locale.ROOT));
        fieldToShowRate.setText(rate.toString());

        String writtenAmountInString = fieldToWriteAmount.getText();
            int amount = Integer.parseInt(writtenAmountInString);
            Double result = rate.doubleValue() * amount;
            fieldToShowResult.setText(result.toString());
        }catch (Exception e){
            fieldToWriteAmount.setText("0");
            fieldToShowResult.setText("0");
        }
    }
}
