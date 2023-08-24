package models;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangleApp extends JFrame {

    private JTextField iterationField;
    private JButton drawButton;
    private JPanel trianglePanel;

    public SierpinskiTriangleApp() {
        setTitle("Sierpinski Triangle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        iterationField = new JTextField(10);
        drawButton = new JButton("Dibujar");
        trianglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String iterationText = iterationField.getText();
                if (!iterationText.isEmpty()) {
                    int iterations = Integer.parseInt(iterationText);
                    int centerX = getWidth() / 2;
                    int centerY = getHeight() / 2;
                    int triangleSize = 400;  // Adjust this size as needed
                    drawSierpinski(g, centerX - triangleSize / 2, centerY - triangleSize / 2, triangleSize, iterations);
                }
            }
        };

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Iteraciones:"));
        inputPanel.add(iterationField);
        inputPanel.add(drawButton);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trianglePanel.repaint();
            }
        });

        add(inputPanel, BorderLayout.NORTH);
        add(trianglePanel, BorderLayout.CENTER);
    }

    private void drawSierpinski(Graphics g, int x, int y, int size, int depth) {
        if (depth == 0) {
            int[] xPoints = {x, x + size / 2, x + size};
            int[] yPoints = {y, y + size, y};
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            int halfSize = size / 2;

            drawSierpinski(g, x, y + halfSize, halfSize, depth - 1);
            drawSierpinski(g, x + halfSize, y + halfSize, halfSize, depth - 1);
            drawSierpinski(g, x + halfSize / 2, y, halfSize, depth - 1);
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SierpinskiTriangleApp app = new SierpinskiTriangleApp();
                app.setVisible(true);
            }
        });
    }
}
