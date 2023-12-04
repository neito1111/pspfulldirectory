import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class visitor extends JFrame {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel serviceLabel;
    private JComboBox<String> serviceComboBox;
    private JCheckBox membershipCheckBox;
    private JRadioButton regularRadioButton;
    private JRadioButton premiumRadioButton;
    private JButton registerButton;
    private JTextArea outputTextArea;
    private JList<String> visitorsList;
    private DefaultListModel<String> visitorsModel;

    public visitor() {
        setTitle("Salon Visitor Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(8, 2));

        nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();

        serviceLabel = new JLabel("Service:");
        String[] services = {"Haircut", "Massage", "Manicure", "Pedicure", "Facial"};
        serviceComboBox = new JComboBox<>(services);

        membershipCheckBox = new JCheckBox("Membership");

        ButtonGroup group = new ButtonGroup();
        regularRadioButton = new JRadioButton("Regular");
        premiumRadioButton = new JRadioButton("Premium");
        group.add(regularRadioButton);
        group.add(premiumRadioButton);

        registerButton = new JButton("Register");
        registerButton.setBackground(Color.GREEN); // Изменение цвета кнопки
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String service = (String) serviceComboBox.getSelectedItem();
                String hasMembership = String.valueOf(membershipCheckBox.isSelected() ? "Membership" : "No membership");
                String membershipType = regularRadioButton.isSelected() ? "Regular" : "Premium";
                if (hasMembership.equals("Membership")) {
                    saveVisitorData(name, service, hasMembership, membershipType);
                    visitorsModel.addElement("Name: " + name + ", Service: " + service + ", Membership: " + hasMembership + ", Membership Type: " + membershipType + "\n");
                } else {
                    outputTextArea.append("Visitor data is not saved because he did not want to..\n");
                }
                nameTextField.setText("");
            }
        });

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        visitorsModel = new DefaultListModel<>();
        visitorsList = new JList<>(visitorsModel);
        visitorsList.setForeground(Color.BLUE); // Изменение цвета текста в JList
        visitorsList.setBackground(Color.LIGHT_GRAY); // Изменение цвета фона JList

        loadVisitorData(); // загрузка данных из файла

        add(nameLabel);
        add(nameTextField);
        add(serviceLabel);
        add(serviceComboBox);
        add(new JLabel());
        add(membershipCheckBox);
        add(new JLabel());
        add(regularRadioButton);
        add(new JLabel());
        add(premiumRadioButton);
        add(new JLabel());
        add(registerButton);
        add(new JLabel());
        add(new JScrollPane(visitorsList));
        add(outputTextArea);

        setVisible(true);
    }

    private void loadVisitorData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("visitors.txt"))) {
            String line;
            if ((line = reader.readLine()) == null) {
                outputTextArea.append("File is empty");
            } else {
                do {
                    visitorsModel.addElement(line);
                } while ((line = reader.readLine()) != null);
                outputTextArea.append("Visitor data loaded successfully.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveVisitorData(String name, String service, String hasMembership, String membershipType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("visitors.txt", true))) {
            writer.write("Name: " + name + ", Service: " + service + ", Membership: " + hasMembership + ", Membership Type: " + membershipType + "\n");
            writer.flush();
            outputTextArea.append("Visitor data saved successfully.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new visitor());
    }
}
