/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bit.encoding.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author crane
 */
public class BitEncodingGui extends JFrame{

    private static final int WIDTH = 850;
    private static final int HEIGHT = 500;
    private static final int PADDING = 50; // Padding from the edges of the window
    private static final int BOX_HEIGHT = 40;

    private String data;
    private int yOffset;
    private int yOffset1;
    

    private JTextField bitTextField;
    private JButton encodeButton;

    public BitEncodingGui() {
        this.data = "";
        this.yOffset = 400;
        this.yOffset1 = 200;

        setTitle("Waveform Display");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a panel to contain the text field and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Create and add the bit text field
        bitTextField = new JTextField(20);
        inputPanel.add(bitTextField);

        // Create and add the encode button
        encodeButton = new JButton("Encode");
        inputPanel.add(encodeButton);

        // Add the input panel to the top of the frame
        add(inputPanel, BorderLayout.NORTH);

        // Add action listener to the encode button
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data = bitTextField.getText();
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawAxis(g);
        
        drawWaveform(g, "NRZ-L", Color.RED);
        
        drawWaveform1(g, "NRZ-I", Color.BLUE);
       
    }

    private void drawAxis(Graphics g) {
        g.setColor(Color.BLACK);

        // Draw x-axis
        g.drawLine(PADDING, yOffset, WIDTH - PADDING, yOffset);
        
        // Draw x-axis
        g.drawLine(PADDING, yOffset1, WIDTH - PADDING, yOffset1);

        // Draw y-axis
        g.drawLine(PADDING, PADDING, PADDING, HEIGHT - PADDING);
    }
//NRZ-L encoding illustration
private void drawWaveform(Graphics g, String encoding, Color color) {
    g.setColor(color);
    g.drawString(encoding, PADDING, PADDING + 20);
     int labelY = (encoding.equals("NRZ-I")) ? PADDING + 20 : yOffset1 + BOX_HEIGHT + 40;
g.drawString(encoding, PADDING, labelY); // move label to the top or bottom of the waveform

    int x = PADDING;
    int y = yOffset;

    int bitWidth = (WIDTH - 2 * PADDING) / data.length();

    for (char bit : data.toCharArray()) {
        if (bit == '1') {
            g.drawRect(x - bitWidth/2, y - BOX_HEIGHT, bitWidth, BOX_HEIGHT);
        } else {
            g.drawRect(x - bitWidth/2, y, bitWidth, BOX_HEIGHT);
        }

        x += bitWidth;
    }
}

//NRZ-I encoding illustration
private void drawWaveform1(Graphics g, String encoding, Color color) {
g.setColor(color);
g.drawString(encoding, PADDING, PADDING + 20);

int x = PADDING;
int y = yOffset1;

int bitWidth = (WIDTH - 2 * PADDING) / data.length();
boolean polarity = false;

for (char bit : data.toCharArray()) {
    if (bit == '1') {
        if (polarity) {
            g.drawRect(x - bitWidth/2, y - BOX_HEIGHT, bitWidth, BOX_HEIGHT);
        } else {
            g.drawRect(x - bitWidth/2, y, bitWidth, BOX_HEIGHT);
        }
        polarity = !polarity;
    } else {
        if (polarity) {
            g.drawRect(x - bitWidth/2, y, bitWidth, BOX_HEIGHT);
        } else {
            g.drawRect(x - bitWidth/2, y - BOX_HEIGHT, bitWidth, BOX_HEIGHT);
        }
    }

    x += bitWidth;
}
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BitEncodingGui bitEncodingGui = new BitEncodingGui();
            bitEncodingGui.setVisible(true);
        });
    }
}

