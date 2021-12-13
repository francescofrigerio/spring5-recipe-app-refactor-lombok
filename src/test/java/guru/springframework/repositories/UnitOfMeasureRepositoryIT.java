package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

// LEZIONE 179
// Test d'integrazione di Spring
// incorporando il context e il database
// per la nostra applicazione
// Creiamo un cercatore dinamico
// definito dalla descrizione in primavera dati
// e testiamolo e assicuriamoci che funzioni
// correttamente
// Nel nome della classe mettiamo IT per Integration test
// Per integrare lo Spring Context usiamo l'annotazione
// RunWith e per integrare il database incorporato usiaomo l'annotazione DataJpaTest
// Si potrebbe anche usare l'annotazione SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    // Utilizziamo il repository delle unita di misura
    // autocablandolo nello Spring Context
    // Tramite Spring iniettiamo la dipendeza
    // in questo testo d'integrazione
    // dicendo di andare a prenderla dallo Spring Context
    // in modo da avere un istanza dell'oggetto Repository
    // iniettata qui
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    // Vogliamo testare il metodo findByDescription
    //
    @Test
    public void findByDescription() throws Exception {

        System.out.println("Start findByDescription Teaspoon");
        // il metodo findBy ritornano oggetti di tipo Optional
        // proviamo a caricare uno dei deti che sono inseriti
        // all'avvio tramite il file data.sql
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        // Verifico il risultato ritornato
        // dalla query per descrizione effettuata
        // dall'oggetto repository
        // Quindi verifichiamo di aver caricato il dato corretto
        // controllando il valore rispetto a quello contenuto
        // nell'oggetto Optional che contiene la risposta
        assertEquals("Teaspoon", uomOptional.get().getDescription());
        System.out.println("End findByDescription Teaspoon");
    }

    // La seconda chiamata e' piu veloce
    // perche' non deve nuovamente caricare
    // lo Spring Context quindi deve solo eseguire il test
    // con l'annotazione DirtiesContext
    // si potrebbe caricare una seconda volta ma non ce nè bisogno
    //@DirtiesContext
    @Test
    public void findByDescriptionCup() throws Exception {

        // il metodo findBy ritornano oggetti di tipo Optional
        // proviamo a caricare uno dei deti che sono inseriti
        // all'avvio tramite il file data.sql
        System.out.println("Start findByDescriptionCup");
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

        // Verifico il risultato ritornato
        // dalla query per descrizione effettuata
        // dall'oggetto repository
        // Quindi verifichiamo di aver caricato il dato corretto
        // controllando il valore rispetto a quello contenuto
        // nell'oggetto Optional che contiene la risposta
        assertEquals("Cup", uomOptional.get().getDescription());
        System.out.println("End findByDescriptionCup");
    }
}
