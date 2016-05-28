import java.util.*;

/**
 * Fraction implements a way to work with mathematical fractions.
 */
class Fraction implements Comparable<Fraction>
{
	private long    num,        //The numerator of the fraction.
					den = 1;    //The denominator, defaulted to 1.

	/**
	 * Simplifies this Fraction
	 * (i.e. transforms (n*a)/(n*b) into a/b, for a and b coprimes, n in the whole numbers and a/b not in the whole numbers.)
	 * WARNING this may take a long time for large numbers, especially if a or b are semiprimes.
	 */
	private void simplify ()
	{
		long    A = num,
				B = den;

		for(long C; B != 0; A = C)
		{
			C = B;
			B = A % B;
		}

		num /= A;
		den /= A;
	}

	/**
	 * Constructor for a fraction of the form n/d.
	 * @param n The new numerator
	 * @param d The new denominator.
	 * @throws IllegalArgumentException when it tries to set the denominator to 0.
	 */
	public Fraction (final long n, final long d) throws IllegalArgumentException
	{
		if (d == 0)
			throw new IllegalArgumentException("Denominator can't be zero.");
		else
		{
			/* Is n times d less than zero?
			   if it is then the numerator is the additive inverse of the absolute value of n,
			   otherwise it is just the absolute value of n. */
			num = (n * d < 0 ? n < 0 ? n : -n : n < 0 ? -n : n);
			den = (d < 0 ? -d : d);
		}
		simplify();
	}

	/**
	 * Constructor for whole numbers
	 * @param n The new Fraction and by extension, the new numerator.
	 */
	public Fraction (final long n)
	{
		num = n;
	}

	/**
	 * Void constructor, defaults to 0.
	 */
	public Fraction ()
	{
		num=0;
	}

	/**
	 * Constructor for Strings
	 * @param param A string of the form "numerator[separator]denominator", many separators are supported.
	 * @throws IllegalArgumentException if param doesn't match the prototype.
	 */
	public Fraction (final String param) throws IllegalArgumentException
	{
		if (param.matches("[ \\t\\x0B]*-?[ \\t\\x0B]*\\d+[ \\t\\x0B]*[/|\\\\:;.,][ \\t\\x0B]*-?[ \\t\\x0B]*\\d+[ \\t\\x0B]*"))
		{
			long tmp;
			if (param.matches("[ \\t\\x0B]*-.*") ^ param.matches(".*[/|\\\\:;.,][ \\t\\x0B]*-.*[ \\t\\x0B]*"))
			{
				Scanner scanner = new Scanner(param);
				num = ((tmp = scanner.nextLong()) < 0) ? tmp : -tmp;
				den = ((tmp = scanner.nextLong()) < 0) ? -tmp : tmp;
			}
			else
			{
				Scanner scanner = new Scanner(param);
				num = ((tmp = scanner.nextLong()) < 0) ? -tmp : tmp;
				den = ((tmp = scanner.nextLong()) < 0) ? -tmp : tmp;
			}
		}
		else
			throw new IllegalArgumentException("String format mismatch.");
	}

	/**
	 * Copy constructor
	 * @param other the Fraction to copy.
	 */
	public Fraction (Fraction other)
	{
		num = other.num;
		den = other.den;
	}

	/**
	 * Method used when the decimal approximation of this Fraction is needed.
	 * @return The decimal approximation of this Fraction.
	 */
	public double toDouble ()
	{
		return (double) num / (double) den;
	}

	public Fraction add (final Fraction other)
	{
		num = num * other.den + other.num * den;
		den *= other.den;
		simplify();
		return this;
	}

	/**
	 * Adds an integer.
	 * @param param integer to add.
	 * @return the resulting Fraction.
	 */
	public Fraction add (final long param)
	{
		num += param * den;
		return this;
	}

	/**
	 * Adds a String formatted Fraction.
	 * @param param the Fraction in String format.
	 * @return the resulting Fraction.
	 * @throws IllegalArgumentException when param doesn't match the prototype.
	 */
	public Fraction add (final String param) throws IllegalArgumentException
	{
		Fraction other = new Fraction(param);
		return add(other);
	}

	/**
	 * Inverse of add.
	 * @param other ditto.
	 * @return ditto.
	 * @see #add(Fraction).
	 */
	public Fraction subtract (final Fraction other)
	{
		num = num * other.den - other.num * den;
		den *= other.den;
		simplify();
		return this;
	}

	/**
	 * Inverse of add.
	 * @param param ditto.
	 * @return ditto.
	 * @see #add(long).
	 */
	public Fraction subtract (final long param)
	{
		num -= param * den;
		return this;
	}

	/**
	 * Inverse of add.
	 * @param param ditto.
	 * @return ditto.
	 * @throws IllegalArgumentException ditto.
	 * @see #add(String).
	 */
	public Fraction subtract (final String param) throws IllegalArgumentException
	{
		Fraction other = new Fraction(param);
		return subtract(other);
	}

	/**
	 * Repetitive add.
	 * @param other ditto.
	 * @return ditto.
	 * @see #add(Fraction).
	 */
	public Fraction multiply (final Fraction other)
	{
		num *= other.num;
		den *= other.den;
		simplify();
		return this;
	}

	/**
	 * Repetitive add.
	 * @param param ditto.
	 * @return ditto.
	 * @see #add(long).
	 */
	public Fraction multiply (final long param)
	{
		num *= param;
		simplify();
		return this;
	}

	/**
	 * Repetitive add.
	 * @param param ditto.
	 * @return ditto.
	 * @throws IllegalArgumentException ditto.
	 * @see #add(String).
	 */
	public Fraction multiply (final String param) throws IllegalArgumentException
	{

		Fraction other = new Fraction(param);
		return multiply(other);
	}

	/**
	 * Inverse of multiply.
	 * @param other ditto.
	 * @return ditto.
	 * @see #add(Fraction).
	 */
	public Fraction divide (final Fraction other)
	{
		num *= other.den;
		den *= other.num;
		simplify();
		return this;
	}

	/**
	 * Inverse of multiply.
	 * @param param ditto.
	 * @return ditto.
	 * @see #add(long).
	 */
	public Fraction divide (final long param)
	{
		den *= param;
		simplify();
		return this;
	}

	/**
	 * Inverse of multiply.
	 * @param param ditto.
	 * @return ditto.
	 * @throws IllegalArgumentException ditto.
	 * @see #add(String).
	 */
	public Fraction divide (final String param) throws IllegalArgumentException
	{
		Fraction other = new Fraction(param);
		return divide(other);
	}

	/**
	 * Provides a human readable form.
	 * @return a string containing such form.
	 */
	@Override
	public String toString ()
	{
		if (den < -1 || den > 1)
			if (num * den < 0)
				return ((num < 0) ? num : -num) + "/" + ((den < 0) ? -den : den);
			else
				return ((num < 0) ? -num : num) + "/" + ((den < 0) ? -den : den);
		else if (num * den < 0)
			return ((num < 0) ? num : -num) + "";
		else
			return ((num < 0) ? -num : num) + "";
	}

	/**
	 * Compares this object with the specified object for order.
	 * Returns a negative integer, zero, or a positive integer as this object is
	 * less than, equal to, or greater than the specified object.
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 * less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it from being compared to this object.
	 */
	@Override
	public int compareTo (Fraction o)
	{
		return (new Long(num * o.den)).compareTo(o.num * den);
	}
}