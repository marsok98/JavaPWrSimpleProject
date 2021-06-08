import javax.swing.*;

public class GUI
{
    JFrame mainFrame = new JFrame();
    JButton btnGetDataFromApi = new JButton();

    GUI()
    {
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
    }
    void createButtons()
    {
        btnGetDataFromApi.setBounds(50,100,50,50);
        btnGetDataFromApi.setText("Fetch Data");
        btnGetDataFromApi.setFocusable(false);
        btnGetDataFromApi.setHorizontalTextPosition(JButton.CENTER);
        btnGetDataFromApi.setVerticalTextPosition(JButton.BOTTOM);
    }
}
