package PokerCombo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClientTestTest {

	private String comboName;
	private Card[] combo;
	
	@Parameters
	public static Collection<Object[]> data() {
		
		return Arrays.asList(new Object[][] {
				
{"Royal Flush", new Card[] {new Card(0,0), new Card(0,9), new Card(0,10), new Card(0,11), new Card(0,12)}},
{"Straight Flush", new Card[] {new Card(0,1), new Card(0,2), new Card(0,3), new Card(0,4), new Card(0,5)}},
{"Care", new Card[] {new Card(0,9), new Card(1,9), new Card(2,9), new Card(3,9), new Card(0,5)}},
{"Full House", new Card[] {new Card(0,10), new Card(1,10), new Card(2,10), new Card(3,6), new Card(0,6)}},
{"Flush", new Card[] {new Card(0,2), new Card(0,3), new Card(0,5), new Card(0,9), new Card(0,11)}},
{"Straight", new Card[] {new Card(0,0), new Card(0,1), new Card(0,2), new Card(0,3), new Card(1,4)}},
{"Three", new Card[] {new Card(0,11), new Card(3,11), new Card(1,11), new Card(0,6), new Card(1,10)}},
{"TwoPair", new Card[] {new Card(0,12), new Card(3,12), new Card(1,8), new Card(0,8), new Card(1,0)}},
{"Pair", new Card[] {new Card(0,7), new Card(3,7), new Card(1,10), new Card(0,5), new Card(1,1)}},
				
				
		});
	}
	
	public ClientTestTest(final String result, final Card[] combo) {
		this.comboName = result;
		this.combo = combo;
	}
	
	@Test
	public void test() {
		assertEquals(comboName, ComboHelper.comboName(combo));
	}

}
