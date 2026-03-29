package pro1;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel;

    public MainFrame() {
        setTitle("Druhý domácí úkol - Kreslení šipek");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        drawingPanel = new DrawingPanel();
        JPanel leftPanel = createOptionsPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, drawingPanel);
        splitPane.setDividerLocation(300);
        splitPane.setEnabled(false);

        add(splitPane);
    }

    private JPanel createOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel angleLabel = new JLabel("Směr nové šipky (úhel):");
        angleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel angleInputPanel = new JPanel();
        angleInputPanel.setLayout(new BoxLayout(angleInputPanel, BoxLayout.X_AXIS));
        angleInputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSlider angleSlider = new JSlider(0, 360, 0);
        JTextField angleTextField = new JTextField("0", 4);
        angleTextField.setMaximumSize(new Dimension(60, 30));

        angleSlider.addChangeListener(e -> {
            int val = angleSlider.getValue();
            angleTextField.setText(String.valueOf(val));
            drawingPanel.setCurrentAngle(val);
        });

        angleTextField.addActionListener(e -> {
            try {
                int val = Integer.parseInt(angleTextField.getText());
                if (val < 0) val = 0;
                if (val > 360) val = 360;
                angleSlider.setValue(val);
            } catch (NumberFormatException ex) {
                angleTextField.setText(String.valueOf(angleSlider.getValue()));
            }
        });

        angleInputPanel.add(angleSlider);
        angleInputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        angleInputPanel.add(angleTextField);

        JCheckBox colorCheckBox = new JCheckBox("Změna všech šipek na červené");
        colorCheckBox.addActionListener(e -> drawingPanel.setUseRedColor(colorCheckBox.isSelected()));

        JButton resetButton = new JButton("Reset plochy");
        resetButton.addActionListener(e -> drawingPanel.clearArrows());

        panel.add(angleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(angleInputPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(colorCheckBox);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(resetButton);

        return panel;
    }
}