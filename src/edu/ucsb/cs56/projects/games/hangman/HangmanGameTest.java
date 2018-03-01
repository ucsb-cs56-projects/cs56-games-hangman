package edu.ucsb.cs56.projects.games.hangman;
import java.io.*;
import java.util.*;

import org.junit.* ;
import static org.junit.Assert.* ;

// Test HangmanGame
public class HangmanGameTest {
    @Test
    public void rightLengthOfUnknownWord()
    {
        HangmanGame hg = new HangmanGame("magics");
        assertEquals("******", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test
    public void singleLetterTest1() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        hg.guessLetter('m');
        assertEquals("m****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test
    public void singleLetterTest2() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        hg.guessLetter('g');
        assertEquals("**g**", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test
    public void singleLetterTest3() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        hg.guessLetter('c');
        assertEquals("****c", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test
    public void singleLetterTest4() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        hg.guessLetter('x');
        assertEquals("*****", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test
    public void singleLetterDuplicateTest1() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        hg.guessLetter('m');
        assertEquals("m****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        boolean thrown = false;
	    try    
        {
	        hg.guessLetter('m');
	    } catch(AlreadyTriedException e) 
        {
	        thrown = true;
	    }
	    assertTrue(thrown);
        assertEquals("m****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    public void singleLetterDuplicateTest2() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        hg.guessLetter('x');
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        boolean thrown = false;
	    try    
        {
	        hg.guessLetter('x');
	    } catch(AlreadyTriedException e) 
        {
	        thrown = true;
	    }
	    assertTrue(thrown);
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test   
    public void completeWinningGame() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        hg.guessLetter('m');
        assertEquals("m****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        hg.guessLetter('a');
        assertEquals("ma***", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        hg.guessLetter('g');
        assertEquals("mag**", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        hg.guessLetter('i');
        assertEquals("magi*", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        hg.guessLetter('c');
        assertEquals("magic", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        assertTrue(hg.hasWon());
        assertFalse(hg.hasLost());
    }
    @Test   
    public void completeLosingGame() throws AlreadyTriedException
    {
        HangmanGame hg = new HangmanGame("magic");
        assertEquals("*****", hg.getBoard());
        assertEquals(6, hg.getWrongAttemptsLeft());
        hg.guessLetter('x');
        assertEquals("*****", hg.getBoard());
        assertEquals(5, hg.getWrongAttemptsLeft());
        hg.guessLetter('w');
        assertEquals("*****", hg.getBoard());
        assertEquals(4, hg.getWrongAttemptsLeft());
        hg.guessLetter('q');
        assertEquals("*****", hg.getBoard());
        assertEquals(3, hg.getWrongAttemptsLeft());
        hg.guessLetter('t');
        assertEquals("*****", hg.getBoard());
        assertEquals(2, hg.getWrongAttemptsLeft());
        hg.guessLetter('y');
        assertEquals("*****", hg.getBoard());
        assertEquals(1, hg.getWrongAttemptsLeft());
        hg.guessLetter('u');
        assertEquals("*****", hg.getBoard());
        assertEquals(0, hg.getWrongAttemptsLeft());
        assertFalse(hg.hasWon());
        assertTrue(hg.hasLost());
    }
}