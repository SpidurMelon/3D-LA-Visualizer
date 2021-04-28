package swing.panels;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import main.Main;
import opengl.util.Tickable;
import org.joml.Matrix3f;
import org.joml.Vector3f;

public class OptionPanel extends JPanel implements Tickable {
    @Getter
    private MatrixPanel matrix1Panel, vector1Panel, matrix2Panel;
    private ButtonPanel buttonPanel;

    private Matrix3f matrix1 = new Matrix3f();
    private Vector3f vector1 = new Vector3f();

    public OptionPanel() {
        setLayout(new BorderLayout());
        add(matrix1Panel = new MatrixPanel(3,3), BorderLayout.WEST);
        add(vector1Panel = new MatrixPanel(1,3), BorderLayout.CENTER);
        add(matrix2Panel = new MatrixPanel(3,3), BorderLayout.EAST);

        add(buttonPanel = new ButtonPanel(this), BorderLayout.SOUTH);

        matrix1Panel.setAllValues(0);
        vector1Panel.setAllValues(0);
        matrix2Panel.setAllValues(0);
    }

    @Override
    public Object init() {
        return null;
    }

    @Override
    public boolean tick(double delta) {
        float[][] newVectorValues = vector1Panel.getValues();
        if (!Main.graph.getVector().equals(newVectorValues[0][0], newVectorValues[1][0], newVectorValues[2][0])) {
            vector1.set(newVectorValues[0][0], newVectorValues[1][0], newVectorValues[2][0]);
            updateGraph();
        }

        float[][] newMatrixValues = matrix1Panel.getValues();
        for (int y = 0; y < newMatrixValues.length; y++) {
            for (int x = 0; x < newMatrixValues[y].length; x++) {
                matrix1.set(x,y,newMatrixValues[y][x]);
            }
        }

        return false;
    }

    public void applyMatrix1() {
        vector1 = vector1.mul(matrix1);
        vector1Panel.setValues(vector1.x, vector1.y, vector1.z);
        updateGraph();
    }

    private void updateGraph() {
        Main.graph.getVector().set(vector1);
        Main.graph.update();
    }

    @Override
    public void destroy() {

    }
}
