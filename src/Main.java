import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main
{
	public static void main (String[] args)
	{
		byte    size    = 0;
		JFrame  frame   = new JFrame("Simplex");

		frame.setSize(200,150);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel  label   = new JLabel("How many variables?");
		JTFtS   jtFtS   = new JTFtS();
		JPanel  panel   = new JPanel(new GridBagLayout());

		GridBagConstraints  clabel = new GridBagConstraints(),
							cjtFtS = new GridBagConstraints();

		clabel.gridx = 0;
		clabel.gridy = GridBagConstraints.RELATIVE;
		clabel.fill  = GridBagConstraints.BOTH;
		clabel.anchor= GridBagConstraints.PAGE_END;
		cjtFtS.gridx = 0;
		cjtFtS.gridy = GridBagConstraints.RELATIVE;
		cjtFtS.fill  = GridBagConstraints.BOTH;
		cjtFtS.anchor= GridBagConstraints.PAGE_START;

		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.BOTTOM);

		panel.add(label,clabel);
		panel.add(jtFtS,cjtFtS);
		label.setVisible(true);
		jtFtS.setVisible(true);
		panel.setVisible(true);

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		while(size < 2)
			if(jtFtS.getString().matches("\\d+"))
				size = (new Scanner(jtFtS.getString())).nextByte();

		String Titles[] = new String[size * 2 - 1];
		for (byte i = 0; i < Titles.length; ++i)
			if (i == 0)
				Titles[i] = "Y";
			else if (i < size)
				Titles[i] = "x" + i;
			else
				Titles[i] = "s" + (i - size +1);

		frame.getContentPane().removeAll();
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
	}
}