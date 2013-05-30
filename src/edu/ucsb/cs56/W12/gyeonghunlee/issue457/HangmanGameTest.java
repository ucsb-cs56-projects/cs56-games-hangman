package edu.ucsb.cs56.S12.davidborden.issue539;
import java.io.*;
import java.util.*;

import org.junit.* ;
import static org.junit.Assert.* ;

// Test HangmanGame
public class HangmanGameTest {
    @Test
    public void testGameSessionWin() throws AlreadyTriedException {
        HangmanGame hg = new HangmanGame("magic");
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('m');
        assertEquals("m****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
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
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('d');
        assertEquals("*****", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('e');
        assertEquals("*****", hg.getBoard());
        assertEquals(3, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('f');
        assertEquals(2, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('h');
        assertEquals("*****", hg.getBoard());
        assertEquals(1, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());

        hg.guessLetter('j');
        assertEquals("*****", hg.getBoard());
        assertEquals(0, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertTrue(hg.hasLost());
    }
}