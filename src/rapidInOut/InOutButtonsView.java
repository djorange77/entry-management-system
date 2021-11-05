package rapidInOut;

import java.awt.*;

import javax.swing.*;

/**
 * This is a View Class that sets up the view for the Signing IN/OUT Buttons.
 * The buttons has different background colours and font colours so that the
 * user can quickly differentiate its functionality. The buttons' size are also
 * finger friendly, so this application can later be implemented on a mobile
 * system.
 *
 * The controller for these buttons are implemented in the DrivesLogPanel and
 * InOutLogPanel.
 *
 * @author Foong : Amos 18044418
 *
 */
@SuppressWarnings("serial")
public class InOutButtonsView extends JPanel {

    private JButton outButton, inButton;

    /**
     * The default constructor for the Panel. Prepares the GUI components for
     * viewing. Combines several BUTTONS together and add some colour to those
     * components for enhanced User Experience (UX).
     *
     * @author Foong : Amos 18044418
     *
     */
    public InOutButtonsView() {
        // Enables the programmer to manually configure  
        // size and location of buttons.
        this.setLayout(null);
        this.setPreferredSize(new Dimension(245, 150));

        // Prepares the SIGN OUT button for use.
        outButton = new JButton("Sign Out");
        outButton.setLocation(5, 10);
        outButton.setSize(235, 60);
        outButton.setBackground(Color.RED);
        outButton.setForeground(Color.WHITE);
        outButton.setFont(new Font("Segoe", Font.BOLD, 28));
        this.add(outButton);

        // Prepares the SIGN IN button for use.
        inButton = new JButton("Sign In");
        inButton.setLocation(5, 80);
        inButton.setSize(235, 60);
        inButton.setBackground(Color.GREEN);
        inButton.setForeground(Color.BLACK);
        inButton.setFont(new Font("Segoe", Font.BOLD, 28));
        this.add(inButton);
    }

    // Getter methods for Object's instance data.
    //-------------------------------------------
    public JButton getOutButton() {
        return outButton;
    }
    //-------------------------------------------

    public JButton getInButton() {
        return inButton;
    }
    //-------------------------------------------
}
