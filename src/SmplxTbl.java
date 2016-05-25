/**
 * Holds and operates on Simplex Tableaus.
 */
public class SmplxTbl
{
	private Object[][] table;   //The Simplex Tableau.

	/**
	 * Constructor for a "naked" tableau.
	 * @param tbl the tableau to copy.
	 */
	public SmplxTbl (Object[][] tbl)
	{
		table = tbl;
	}

	/**
	 * Copy constructor.
	 * @param other SmplxTbl to copy.
	 */
	public SmplxTbl (SmplxTbl other)
	{
		table = other.table;
	}

	/**
	 * Constructor for a given set of linear equations.
	 * @param size the number of equations/variables.
	 */
	public SmplxTbl (byte size)
	{
		table = new Fraction[size][];
		for (byte i = 0; i < table.length; ++i)
		{
			table[i] = new Fraction[size * 2 - 1];
			for (byte j = 0; j < table[i].length; ++j)
				table[i][j] = new Fraction(1L);
		}
	}

	/**
	 * Replaces the contents of a cell.
	 * @param param new content.
	 * @param row   row of the cell to replace.
	 * @param col   column of the cell to replace.
	 * @return      the new value.
	 * @throws ArrayIndexOutOfBoundsException when it tries to read outside of table's bounds.
	 */
	public Fraction setCell (Fraction param, byte row, byte col) throws ArrayIndexOutOfBoundsException
	{
		return (Fraction) (table[row][col] = param);
	}

	/**
	 * Performs the pivot operations.
	 * @param row row of the pivot element.
	 * @param col column of the pivot element
	 * @throws ArrayIndexOutOfBoundsException when row or col are out of bounds.
	 */
	public void perform (byte row, byte col) throws ArrayIndexOutOfBoundsException
	{
		if(col >= (table[row].length + 1) / 2)
			throw new ArrayIndexOutOfBoundsException("Can't perform operation on slack variables/results.");
		Fraction tmp = (Fraction) (table[row][col]);
		for(byte i = 0; i < table[row].length; ++i)
			((Fraction) (table[row][i])).divide(tmp);
		for(byte i = 0; i < table.length; ++i)
			if(i != row)
			{
				tmp = new Fraction (((Fraction) (table[i][col])).divide((Fraction) (table[row][col])));
				for (byte j = 0; j < table[i].length; ++j)
					((Fraction) (table[i][j])).divide(tmp);
			}
	}

	/**
	 * Performs the pivot operations and finds the best candidate.
	 * TODO: finish.
	 * @param col column of the pivot.
	 * @throws ArrayIndexOutOfBoundsException when col is out of bounds.
	 */
	public void perform (byte col) throws ArrayIndexOutOfBoundsException
	{
		if(col >= (table[0].length + 1) / 2)
			throw new ArrayIndexOutOfBoundsException("Can't perform operation on slack variables/results.");
		byte        idx;
		Fraction    tmpF;
		Object[][][]tmpO = new Object[table.length][][];
		for(byte i = 1; i < table.length; ++i)
		{
			tmpO[i] = table;
			tmpF = new Fraction((Fraction) (table[i][col]));
			for (byte j = 0; j < tmpO[i][i].length; ++j)
				((Fraction) (tmpO[i][i][j])).divide(tmpF);
			for (byte j = 0; j < table[i].length; ++j)
				if (j != i)
				{
					tmpF = new Fraction(((Fraction) (tmpO[i][j][col])).divide((Fraction) (tmpO[i][j][col])));
					for (byte k = 0; k < tmpO[i][j].length; ++k)
						((Fraction) (tmpO[i][j][k])).divide(tmpF);
				}
		}
	}
	//TODO: finish.
}
