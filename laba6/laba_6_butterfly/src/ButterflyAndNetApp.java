import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ButterflyAndNetApp extends JFrame {
    private JLabel butterflyLabel;
    private JLabel netLabel;
    private JButton startButton;
    private JButton catchButton;
    private JButton stopButton;
    private int catchCount = 0;
    private int missCount = 0;
    private boolean isRunning = false;
    private int butterflySpeed = 2500;
    private int catchSpeed = 20000;

    public ButterflyAndNetApp() {
        setTitle("Butterfly and Net App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());
        setContentPane(new JPanel() {
            Image img = new ImageIcon("D:\\khachatriantigran\\5 term\\Псп\\laba6\\laba_6_butterfly\\src\\backgr.jpg").getImage();

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        });

        ImageIcon butterflyIcon = new ImageIcon("D:\\khachatriantigran\\5 term\\Псп\\laba6\\laba_6_butterfly\\src\\butterfly.png");
        Image butterflyImage = butterflyIcon.getImage();
        Image scaledButterflyImage = butterflyImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        butterflyIcon = new ImageIcon(scaledButterflyImage);
        butterflyLabel = new JLabel(butterflyIcon);

        ImageIcon netIcon = new ImageIcon("D:\\khachatriantigran\\5 term\\Псп\\laba6\\laba_6_butterfly\\src\\net1.png");
        Image netImage = netIcon.getImage();
        Image scaledNetImage = netImage.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        netIcon = new ImageIcon(scaledNetImage);
        netLabel = new JLabel(netIcon);

        startButton = new JButton("Start");
        catchButton = new JButton("Catch");
        stopButton = new JButton("Stop");

        startButton.setPreferredSize(new Dimension(80, 30));
        catchButton.setPreferredSize(new Dimension(80, 30));
        stopButton.setPreferredSize(new Dimension(80, 30));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = true;
                catchSpeed=2500;
                butterflySpeed=20000;
                moveButterfly();
                moveCatch();
            }
        });

        catchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    checkCatch();
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRunning = false;
                displayResult();
            }
        });

        add(butterflyLabel);
        add(netLabel);
        add(startButton);
        add(catchButton);
        add(stopButton);
        addSpeedDropdown();

        setVisible(true);
    }

    private void moveButterfly() {
        Thread butterflyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                while (isRunning) {
                    int x = random.nextInt(getWidth() - butterflyLabel.getWidth());
                    int y = random.nextInt(getHeight() - butterflyLabel.getHeight());
                    butterflyLabel.setLocation(x, y);
                    try {
                        Thread.sleep(butterflySpeed / 30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        butterflyThread.start();
    }

    private void moveCatch() {
        Thread catchThread = new Thread(new Runnable() {
            int yDirection = 1;

            @Override
            public void run() {
                while (isRunning) {
                    int x = netLabel.getX();
                    int y = netLabel.getY();
                    if (y >= getHeight() - netLabel.getHeight() || y <= 0) {
                        yDirection *= -1;
                    }
                    y += 5 * yDirection;
                    netLabel.setLocation(x, y);
                    try {
                        Thread.sleep(catchSpeed / 120);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        catchThread.start();
    }

    private void checkCatch() {
        int butterflyX = butterflyLabel.getX();
        int butterflyY = butterflyLabel.getY();
        int netX = netLabel.getX();
        int netY = netLabel.getY();

        if (Math.abs(butterflyX - netX) < 300 && Math.abs(butterflyY - netY) < 300) {
            catchCount++;
        } else {
            missCount++;
        }
    }

    private void displayResult() {
        JOptionPane.showMessageDialog(this, "Catch count: " + catchCount + "\nMiss count: " + missCount);
    }

    private void addSpeedDropdown() {
        String[] speedValues = {"Slow", "Medium", "Fast"};
        JComboBox<String> speedDropdown = new JComboBox<>(speedValues);
        speedDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSpeed = (String) speedDropdown.getSelectedItem();
                if (selectedSpeed.equals("Slow")) {
                    setSpeed(20000,2500);
                } else if (selectedSpeed.equals("Medium")) {
                    setSpeed(18000,2000);
                } else if (selectedSpeed.equals("Fast")) {
                    setSpeed(16000,1500);
                }
            }
        });
        add(speedDropdown);
    }

    private void setSpeed(int butspeed,int chespeed) {
        butterflySpeed = butspeed;
        catchSpeed = chespeed;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ButterflyAndNetApp());
    }
}
