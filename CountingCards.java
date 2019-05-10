/**
 * <p>
 * This problem takes 4 hands of 13 cards, outputs value and sorted suits
 * </p>
 * @author Nayaab Ali, Marina Semenova, Alyssa Wang
 * @version 1.0, Mar. 26, 2019
 */

import java.util.*;
import java.io.*;
public class CountingCards
{
    String[] hand = new String [4];
    int score = 0;
    int spades = 0;
    int hearts = 0;
    int diamonds = 0;
    int clubs = 0;
    ArrayList < Integer > spadesIndex;
    ArrayList < Integer > heartsIndex;
    ArrayList < Integer > diamondsIndex;
    ArrayList < Integer > clubsIndex;

    /**
     * reads input
     */
    private void readInput ()
    {
	try
	{
	    File file = new File ("cards.txt");
	    Scanner input = new Scanner (file);
	    for (int x = 0 ; x < 4 ; x++)
	    {
		hand [x] = input.nextLine ();
	    }
	}
	catch (IOException e)
	{
	    System.out.println ("Not valid input. Bye!");
	    System.exit (0);
	}
    }


    /** deals with void, singletons and doubletons
     * @param c number of suit
     * @return how many points to add
     */
    private int specialCase (int c)
    {
	int add = 0;
	if (c == 2)
	{
	    add += 1;
	}
	else if (c == 1)
	{
	    add += 2;
	}
	else if (c == 0)
	{
	    add += 3;
	}
	return add;
    }


    /**
     * @param s pass string with all cards of hand[x], where x is 0-3 and this method is called 4 times
     * @return string with score and sorted suits, formatted using \n
     */
    private String solution (String s)
    {
	String solution = "";
	score = 0;
	spades = 0;
	hearts = 0;
	diamonds = 0;
	clubs = 0;
	spadesIndex = new ArrayList < > ();
	heartsIndex = new ArrayList < > ();
	diamondsIndex = new ArrayList < > ();
	clubsIndex = new ArrayList < > ();
	if (s.length () != 26)
	{
	    System.out.println ("Invalid hand!");
	    System.exit (0);
	}
	for (int x = 0 ; x < 13 ; x++)
	{
	    String current = s.substring (x * 2, x * 2 + 2);
	    if (current.charAt (0) == 'A')
	    {
		score += 4;
	    }
	    else if (current.charAt (0) == 'K')
	    {
		score += 3;
	    }
	    else if (current.charAt (0) == 'Q')
	    {
		score += 2;
	    }
	    else if (current.charAt (0) == 'J')
	    {
		score += 1;
	    }
	    else if (current.charAt (0) != '2' && current.charAt (0) != '3' && current.charAt (0) != '4' && current.charAt (0) != '5' && current.charAt (0) != '6' &&
		    current.charAt (0) != '7' && current.charAt (0) != '8' && current.charAt (0) != '9' && current.charAt (0) != 'T')
	    {
		System.out.println ("Invalid hand!");
		System.exit (0);
	    }
	    if (current.charAt (1) == 'C')
	    {
		clubs++;
		clubsIndex.add (x * 2);
	    }
	    else if (current.charAt (1) == 'D')
	    {
		diamonds++;
		diamondsIndex.add (x * 2);
	    }
	    else if (current.charAt (1) == 'H')
	    {
		hearts++;
		heartsIndex.add (x * 2);
	    }
	    else if (current.charAt (1) == 'S')
	    {
		spades++;
		spadesIndex.add (x * 2);
	    }
	    else
	    {
		System.out.println ("Invalid hand!");
		System.exit (0);
	    }
	}
	score += specialCase (clubs);
	score += specialCase (diamonds);
	score += specialCase (hearts);
	score += specialCase (spades);
	String sorted;
	String sortClubs = sortSuits (s, clubsIndex);
	String sortDiamonds = sortSuits (s, diamondsIndex);
	String sortHearts = sortSuits (s, heartsIndex);
	String sortSpades = sortSuits (s, spadesIndex);
	sorted = sortClubs + "\n" + sortDiamonds + "\n" + sortHearts + "\n" + sortSpades + "\n";
	solution += score + "\n" + sorted;
	return solution;
    }


    /** method that sorts all cards of a particular suit, called 4 times
     * @param s s is the original string containing all cards that was passed into solution
     * @param i contains indexes where a suit occurs
     * @return sorted string of a particular suit
     */
    private String sortSuits (String s, ArrayList < Integer > i)
    {
	int length = i.size ();
	String sorted = "";
	String[] arr = new String [length];
	for (int x = 0 ; x < length ; x++)
	{
	    arr [x] = s.substring (i.get (x), i.get (x) + 2);
	}
	for (int x = 0 ; x < length - 1 ; x++)
	{
	    int index = x;
	    int value, otherValue;
	    for (int j = x + 1 ; j < length ; j++)
	    {
		if (arr [j].charAt (0) - 48 == 17)
		{
		    value = 1000;
		}
		else if (arr [j].charAt (0) - 48 == 27)
		{
		    value = 500;
		}
		else if (arr [j].charAt (0) - 48 == 26)
		{
		    value = 250;
		}
		else if (arr [j].charAt (0) - 48 == 36)
		{
		    value = 100;
		}
		else
		{
		    value = arr [j].charAt (0) - 48;
		}
		if (arr [index].charAt (0) - 48 == 17)
		{
		    otherValue = 1000;
		}
		else if (arr [index].charAt (0) - 48 == 27)
		{
		    otherValue = 500;

		}
		else if (arr [index].charAt (0) - 48 == 26)
		{
		    otherValue = 250;
		}
		else if (arr [index].charAt (0) - 48 == 36)
		{
		    otherValue = 100;
		}
		else
		{
		    otherValue = arr [index].charAt (0) - 48;
		}
		if (value > otherValue)
		{
		    index = j;
		}
	    }
	    String larger = arr [index];
	    arr [index] = arr [x];
	    arr [x] = larger;
	}
	for (int x = 0 ; x < length ; x++)
	{
	    sorted += arr [x] + " ";
	}
	return sorted;
    }


    /** main method
     * @param args args
     */
    public static void main (String[] args)
    {
	CountingCards m = new CountingCards ();
	m.readInput ();
	for (int x = 0 ; x < 4 ; x++)
	{
	    System.out.print (m.solution (m.hand [x]));
	}

    }
}
