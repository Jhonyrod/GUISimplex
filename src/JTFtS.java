import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JTFtS JTextField to String class, provides a way to retrieve a string from a JTextField once enter is pressed.
 */
public class JTFtS extends JPanel implements ActionListener
{
	private String string = "";                 //Holds the resultant text after enter is pressed.
	private JTextField textField;               //The JTextField that receives the input.

	public JTFtS ()
	{
		super(new GridBagLayout());             //Constructor for the JPanel superclass with the specified layout.
		textField = new JTextField(15);
		textField.setVisible(true);
		textField.addActionListener(this);
		add(textField);
	}

	public String getString ()
	{
		return string;
	}

	@Override
	public void actionPerformed (ActionEvent e) //This method is triggered every time enter is pressed.
	{
		string = textField.getText();           //Save the current text in textField.
		textField.setText("Invalid, try again");//Prompt for another user input. The caller of this class handles the input checking.
		textField.selectAll();                  //Prepare textField to immediately receive new input,
	}
}