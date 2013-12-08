package oop.asg04;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: giapnv
 * Date: 12/1/13
 * Time: 12:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class JBrainTetris extends JTetris {
    protected JCheckBox brainMode;
    protected JSlider adversary;
    private DefaultBrain dfBrain = new DefaultBrain();;
    private int crCount = 0;
    private Brain.Move crMove = new Brain.Move();
    protected JPanel little;
    protected JLabel advStt;

    JBrainTetris(int pixels) {
        super(pixels);
    }

    @Override
    public void tick(int verb) {
//        System.out.println(verb);
        if (verb == DOWN && brainMode.isSelected()) {
            if (crCount != count) {
                board.undo();
                crMove = dfBrain.bestMove(board, currentPiece, board.getHeight()-TOP_SPACE, crMove);
                crCount = count;
            }
            if (crMove != null) {
                if (currentX < crMove.x) {
                    super.tick(RIGHT);
                } else if (currentX > crMove.x) {
                    super.tick(LEFT);
                }

                if (currentPiece != crMove.piece) {
                    super.tick(ROTATE);
                }
            }
        }
        super.tick(verb);
    }

    @Override
    public JComponent createControlPanel() {
        JPanel panel = new JPanel();
        panel.add(super.createControlPanel());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Brain:"));
        brainMode = new JCheckBox("Brain active");
        panel.add(brainMode);
        // make a little panel, put a JSlider in it. JSlider responds to getValue()
        little = new JPanel();
        little.add(new JLabel("Adversary:"));
        adversary = new JSlider(0, 100, 0); // min, max, current
        adversary.setPreferredSize(new Dimension(100,15));
        panel.add(adversary);
        panel.add(little);
        advStt = new JLabel("ok");
        panel.add(advStt);
        return panel;
    }

    @Override
    public Piece pickNextPiece() {
        double randNum = new Random().nextDouble()*98+1;
        if (randNum >= adversary.getValue()) {
            advStt.setText("ok");
            return super.pickNextPiece();
        }
        else {
            advStt.setText("*ok*");
            Brain.Move a, b = null;

            int res = 0;
            for(int i = 0; i < pieces.length; ++i) {
                a = dfBrain.bestMove(board, pieces[i], board.getHeight()-TOP_SPACE, null);
                if (a == null)
                    return pieces[i];
                if (i == 0)
                   b = a;
                if (a.score >= b.score) {
                    b = a;
                    res = i;
                }
            }
            return pieces[res];
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        JBrainTetris brainTetris = new JBrainTetris(16);
        JFrame frame = JBrainTetris.createFrame(brainTetris);
        frame.setVisible(true);
    }
}
