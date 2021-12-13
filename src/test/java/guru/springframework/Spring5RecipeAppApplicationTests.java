package guru.springframework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// LEZIONE 173
// RunWith Permette di caricare lo SoringContext
@RunWith(SpringRunner.class)
// LEZIONE 173
// SpringBootTest Non è uno UnitTest ma un Test d'integrazione
// Si puo eseguire direttamente con tasto dx + Run
// esegue una veloce build e poi esegue il contesto di Spring
// Se il context non carica per qualsiasi motivo il test fallisce
// IL TEST D'INTEGRAZIONE COME SI PUO' NOTARE
// FACENDO UN CONFRONTO CON LA CLASSE CategoryTest
// E' MOLTO PIU' LENTO DEL TEST UNITARIO
// ANCHE SE ENTRAMBI NON HANNO UNA LOGICA COMPLESSA
// LEZIONE 173
@SpringBootTest
public class Spring5RecipeAppApplicationTests {

	@Test
	public void contextLoads() {
		// LEZIONE 173
		System.out.println("START MYFIRST TEST  ");
		// LEZIONE 173
	}

}
