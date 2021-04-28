package swing.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import lombok.Getter;
import lombok.Setter;
import swing.util.DefaultColor;

public class MatrixPanel extends JPanel  {
    private static final int textAreaSize = 50;
    private int width, height;
    private JTextField[][] fields;
    private float[][] values;
    public MatrixPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width*textAreaSize, height*textAreaSize));
        setLayout(new GridLayout(height, width));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, DefaultColor.DARKER.getColor(), DefaultColor.DARK.getColor()));

        fields = new JTextField[height][width];
        values = new float[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                JTextField newField = new JTextField();
                add(newField);
                fields[y][x] = newField;
            }
        }
    }

    public void setValues(float... newValues) {
        if (newValues.length != width*height) {
            System.err.println("Wrong number of values given to matrixPanel");
            return;
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fields[y][x].setText(String.valueOf(newValues[y*width+x]));
                values[y][x] = newValues[y*width+x];
            }
        }
    }

    public void setAllValues(float value) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fields[y][x].setText(String.valueOf(value));
                values[y][x] = value;
            }
        }
    }

    public float[][] getValues() {
        float[][] newValues = new float[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    newValues[y][x] = Float.parseFloat(fields[y][x].getText());
                } catch (NumberFormatException e) {
                    return values;
                }
            }
        }
        values = newValues;
        return newValues;
    }

}
