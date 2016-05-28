import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * JTFtS JTextField to String class, provides a way to retrieve a string from a JTextField once enter is pressed.
 */
public class JTFtS extends JPanel implements ActionListener
{
	public byte result;
	private JTextField textField;               //The JTextField that receives the input.
	private JButton button;

	public JTFtS ()
	{
		super(new GridBagLayout());             //Constructor for the JPanel superclass with the specified layout.

		textField = new JTextField(15);
		button = new JButton("OK");

		GridBagConstraints c = new GridBagConstraints();

		textField.setVisible(true);
		button.setVisible(true);

		textField.addActionListener(this);
		button.addActionListener(this);

		c.gridy = 0;
		add(textField, c);

		c.gridy = 1;
		add(button, c);
	}

	@Override
	public void actionPerformed (ActionEvent actionEvent) //This method is triggered every time enter is pressed.
	{
		if(textField.getText().matches("\\d+"))
		{
			try
			{
				result = (new Scanner(textField.getText()).nextByte());
			}
			catch (InputMismatchException e){}
		}
		textField.setText("Invalid, try again");//Prompt for another user input. The caller of this class handles the input checking.
		textField.requestFocusInWindow();
		textField.selectAll();                  //Prepare textField to immediately receive new input,
	}
}