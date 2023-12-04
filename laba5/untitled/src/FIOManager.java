// Импорты необходимых пакетов
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

// Основной класс FIOManager, расширяющий JFrame
public class FIOManager extends JFrame {
    // Поля для хранения списка фамилий, имен и отчеств
    private DefaultListModel<String> surname;
    private DefaultListModel<String> name;
    private DefaultListModel<String> patronymic;

    // Поле для ввода ФИО
    private JTextField textField;

    // Диалоговое окно для вывода сообщений
    private JDialog messageDialog;

    // Конструктор класса FIOManager
    public FIOManager() {
        // Инициализация моделей списка фамилий, имен и отчеств
        surname = new DefaultListModel<>();
        name = new DefaultListModel<>();
        patronymic = new DefaultListModel<>();


        JList<String> surnameList = new JList<>(surname);
        JList<String> nameList = new JList<>(name);
        JList<String> patronymicList = new JList<>(patronymic);


        textField = new JTextField(20);
        JButton addButton = new JButton("Add");


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("FIO:"), c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(textField, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        panel.add(addButton, c);


        getContentPane().add(panel, BorderLayout.NORTH);


        JPanel listsPanel = new JPanel();
        listsPanel.setLayout(new GridBagLayout());
        GridBagConstraints listConstraints = new GridBagConstraints();


        listConstraints.fill = GridBagConstraints.HORIZONTAL;
        listConstraints.weightx = 0.5;
        listConstraints.gridx = 0;
        listConstraints.gridy = 0;
        listsPanel.add(new JLabel("Surname"), listConstraints);

        listConstraints.fill = GridBagConstraints.HORIZONTAL;
        listConstraints.weightx = 0.5;
        listConstraints.gridx = 1;
        listConstraints.gridy = 0;
        listsPanel.add(new JLabel("Name"), listConstraints);

        listConstraints.fill = GridBagConstraints.HORIZONTAL;
        listConstraints.weightx = 0.5;
        listConstraints.gridx = 2;
        listConstraints.gridy = 0;
        listsPanel.add(new JLabel("Patronymic"), listConstraints);


        listConstraints.fill = GridBagConstraints.HORIZONTAL;
        listConstraints.weightx = 0.5;
        listConstraints.gridx = 0;
        listConstraints.gridy = 1;
        listsPanel.add(new JScrollPane(surnameList), listConstraints);

        listConstraints.fill = GridBagConstraints.HORIZONTAL;
        listConstraints.weightx = 0.5;
        listConstraints.gridx = 1;
        listConstraints.gridy = 1;
        listsPanel.add(new JScrollPane(nameList), listConstraints);

        listConstraints.fill = GridBagConstraints.HORIZONTAL;
        listConstraints.weightx = 0.5;
        listConstraints.gridx = 2;
        listConstraints.gridy = 1;
        listsPanel.add(new JScrollPane(patronymicList), listConstraints);


        getContentPane().add(listsPanel, BorderLayout.CENTER);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fio = textField.getText().trim();
                if (!fio.isEmpty()) {
                    if (fio.length() <= 30) {
                        if (surname.size() < 10) {
                            if (!fio.contains(" ")) {
                               /* List<String> parts = new ArrayList<>();
                                int i = 0;
                                for (int k = 1; k < fio.length(); k++) {
                                    if (Character.isUpperCase(fio.charAt(k))) {
                                        parts.add(fio.substring(i, k));
                                        i = k;
                                    }
                                }
                                parts.add(fio.substring(i));
                                if (parts.size() == 3) {
                                    surnameListModel.addElement(parts.get(0).substring(0, 1).toUpperCase() + parts.get(0).substring(1).toLowerCase());
                                    nameListModel.addElement(parts.get(1).substring(0, 1).toUpperCase() + parts.get(1).substring(1).toLowerCase());
                                    patronymicListModel.addElement(parts.get(2).substring(0, 1).toUpperCase() + parts.get(2).substring(1).toLowerCase());
                                }*/
                                String[] words = fio.split("(?=[А-Я])");
                                surname.addElement(words[0]);
                                name.addElement(words[1]);
                                patronymic.addElement(words[2]);
                            } else if (fio.contains(" ")) {
                                String[] parts = fio.split(" ", 3);
                                // Добавление фамилии, имени и отчества в соответствующие списки
                                surname.addElement(parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase());
                                name.addElement(parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1).toLowerCase());
                                patronymic.addElement(parts[2].substring(0, 1).toUpperCase() + parts[2].substring(1).toLowerCase());
                            }
                            else {


                                showMessageDialog("Please enter a valid FIO in the format 'Surname Name Patronymic'.");
                            }
                        } else {

                            showMessageDialog("Maximum number of entries exceeded (10).");
                        }
                    } else {

                        showMessageDialog("Input is too long. Please enter a maximum of 30 characters.");
                    }
                } else {

                    showMessageDialog("Input is empty. Please enter a valid FIO.");
                }

                textField.setText("");
            }
        });

        // Настройка основных параметров окна приложения
        setTitle("FIO Manager");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Метод для отображения диалогового окна с сообщением
    private void showMessageDialog(String message) {
        if (messageDialog == null) {
            messageDialog = new JDialog(this, "Message");
            messageDialog.setSize(300, 100);
            messageDialog.add(new JLabel(message));
            messageDialog.setVisible(true);
        } else {
            messageDialog.add(new JLabel(message));
            messageDialog.setVisible(true);
        }
    }

    // Метод main для запуска приложения
    public static void main(String[] args) {
        new FIOManager();
    }
}
