package edu.ucsb.cs56.projects.games.cs56_games_hangman;

import java.io.*;
import java.util.*;
import java.lang.Class.*;
import java.lang.ClassLoader.*;
import java.net.URLClassLoader.*;

import org.junit.* ;
import static org.junit.Assert.* ;

// Test HangmanGame
public class HangmanGameTest {
   
    @Test
    public void testGameSessionWin() throws AlreadyTriedException {
	HangmanGame hg = new HangmanGame("magic");
	assertEquals("magic", hg.getSecretWord());
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
	ArrayList<Character> testlist = new ArrayList<Character>(Arrays.asList());
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('m');
        assertEquals("m****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('a');
        assertEquals("ma***", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('s');
        assertEquals("ma***", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
	testlist.add('s');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	boolean thrown = false;
	try {
	    hg.guessLetter('s');
	} catch(AlreadyTriedException e) {
	    thrown = true;
	}
	assertTrue(thrown);
        assertEquals("ma***", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft()); // will not take out an attempt if already used letter
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('c');
        assertEquals("ma**c", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('g');
        assertEquals("mag*c", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('i');
        assertEquals("magic", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertTrue(hg.hasWon());
        assertFalse(hg.hasLost());
    }

    @Test
    public void testGameSessionLose() throws AlreadyTriedException {
        HangmanGame hg = new HangmanGame("magic");
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('b');
        assertEquals("*****", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
	ArrayList<Character> testlist = new ArrayList<Character>();
	testlist.add('b');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('d');
        assertEquals("*****", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
	testlist.add('d');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('e');
        assertEquals("*****", hg.getBoard());
        assertEquals(3, hg.getWrongAttemptsLeft());
	testlist.add('e');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('f');
	assertEquals("*****", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
	testlist.add('f');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('h');
        assertEquals("*****", hg.getBoard());
        assertEquals(1, hg.getWrongAttemptsLeft());
	testlist.add('h');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('m');
        assertEquals("m****", hg.getBoard());
        assertEquals(1, hg.getWrongAttemptsLeft());
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('j');
        assertEquals("m****", hg.getBoard());
        assertEquals(0, hg.getWrongAttemptsLeft());
	testlist.add('j');
	assertEquals(testlist, hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertTrue(hg.hasLost());
    }
	@Test
    public void testLongGameSessionWin() throws AlreadyTriedException {
        HangmanGame hg = new HangmanGame("polynomial");
	assertEquals("polynomial", hg.getSecretWord());
        assertEquals("**********", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('b');
        assertEquals("**********", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('i');
        assertEquals("*******i**", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('a');
        assertEquals("*******ia*", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('f');
	assertEquals("*******ia*", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('o');
        assertEquals("*o***o*ia*", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('l');
        assertEquals("*ol**o*ial", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('d');
        assertEquals("*ol**o*ial", hg.getBoard());
        assertEquals(3, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('c');
        assertEquals("*ol**o*ial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('m');
        assertEquals("*ol**omial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('n');
        assertEquals("*ol*nomial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('y');
        assertEquals("*olynomial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('p');
	assertEquals("polynomial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
	ArrayList<Character> testlist = new ArrayList<Character>(Arrays.asList('b','f','d','c'));
	assertEquals(testlist, hg.getWrongLetters());
        assertTrue(hg.hasWon());
        assertFalse(hg.hasLost());
	}
	
	public void testLongGameSessionLose() throws AlreadyTriedException {
        HangmanGame hg = new HangmanGame("polynomial");
	assertEquals("polynomial", hg.getSecretWord());
        assertEquals("**********", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('b');
        assertEquals("**********", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('i');
        assertEquals("*******i**", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('a');
        assertEquals("*******ia*", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('f');
	assertEquals("*******ia*", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('o');
        assertEquals("*o***o*ia*", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('l');
        assertEquals("*ol**o*ial", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('d');
        assertEquals("*ol**o*ial", hg.getBoard());
        assertEquals(3, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('c');
        assertEquals("*ol**o*ial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('m');
        assertEquals("*ol**omial", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('r');
        assertEquals("*ol**omial", hg.getBoard());
        assertEquals(1, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('y');
        assertEquals("*olynomial", hg.getBoard());
        assertEquals(1, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

	hg.guessLetter('z');
        assertEquals("*olynomial", hg.getBoard());
        assertEquals(0, hg.getWrongAttemptsLeft());
	assertEquals("bfdcrz", hg.getWrongLetters());
        assertFalse(hg.hasWon());
        assertTrue(hg.hasLost());
	}	
}

