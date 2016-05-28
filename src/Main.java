import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main
{

	private JTFtS JTFtS1;

	public static void main (String[] args)
	{
		JFrame  frame   = new JFrame("Simplex");

		frame.setSize(200,150);
		frame.setMinimumSize(new Dimension(200,150));
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

		while(jtFtS.result < 2)
		{
			try
			{
				TimeUnit.NANOSECONDS.sleep(1);
			}
			catch (InterruptedException e) {}
		}

		String Titles[] = new String[jtFtS.result * 2 - 1];
		for (byte i = 0; i < Titles.length; ++i)
			if (i == 0)
				Titles[i] = "Y";
			else if (i < jtFtS.result)
				Titles[i] = "x" + i;
			else
				Titles[i] = "s" + (i - jtFtS.result +1);

		SmplxTbl smplxTbl = new SmplxTbl(jtFtS.result);
		JTable table = new JTable(smplxTbl.table, Titles);
		JScrollPane scrollPane = new JScrollPane(table);

		frame.setVisible(false);
		frame.getContentPane().removeAll();
		frame.setSize(800,600);
		frame.setMaximumSize(new Dimension(1366,768));
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);
		while (true);
	}
}
