package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

// LEZIONE 178
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// LEZIONE 177
public class IndexControllerTest_177 {

    IndexController controller;

    // RecipeService è un interfaccia per cui e' facile
    // da verificare con Mock
    @Mock
    RecipeService recipeService;

    // Model è un interfaccia per cui e' facile
    // da verificare con Mock per cui non si
    // ha bisogno di una vera implementazione di Mockito Mock
    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        // SERVE PER INIZIALIZZARE IL TEST
        // IN CORRISPONDENZA DELL'ANNOTAZIOBE MOck
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);
    }

    // LEZIONE 178
    // How to run Spring MockMvc Test
    // Come Testare Spring Mvc Controller
    // tramite unit test e un contesto Mock servlet finto
    // Controller sono piccole classi difficili da testare
    // Quando stiamo testando codici di risposta media type ecc.
    // per testare come mappare il percorso delle richieste
    // Nel vecchio modo si dovrebbe usare un intero web server
    // per testare queste cose.
    // Ora possiamo usare un web server finto ma in realtà
    // non è neanche un web server
    // Attraversiamo un derisore Mock Dispatcher Servlet
    // che Spring Boot mette a disposizione
    // che noi possiamo utilizzare e far diventare il test
    // sulla classe Controller qualcosa di molto leggero
    // Non è necessario infatti chiamare e inserire lo Spring Context
    // per testare in modo unitario la classe.
    // Vediamo come usare lo Spring Mock Mvc in una
    // configurazione autonoma
    // Usiamo qui un finto mock servlet context
    // messo a disposizione da Spring che offre
    // molte potenzialità
    // Non è l'unico modo per testare gli Spring Mvc COntroller
    // ma è quello preferito da Thompson
    // perchè mantiene il test leggero e non abbiamo bisogno
    // di far comparire il contesto di Spring
    @Test
    public void testMockMVC() throws Exception {
        // Creo un oggetto MockMvc
        // tramite il corrispondente MockMvcBuilders
        // Ci sono 2 opzioni primo usare lo standaloneSetup
        // per fare  test standalone(auotnomi)
        // oppure usare un installazione dal contesto web
        // con webAppContextSetup che a sua volta
        // fara apparire un contesto di Spring
        // per cui in questo secondo caso i nostri test
        // non saranno piu' di tipo unitario
        // Quindi scegliamo di fare un test
        // standalone autofunzionante e veloce
        // passando in input l'oggetto controller
        // oppure un set di Controller
        // Noi utilizziamo quello già configurato nella classe
        // Essendo poi la chiamata ad un costruttore alla fine
        // bisogna chiamare il metodo build
        // Quindi è un esecuzione per costruire un modello
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        /* Dalla documentazione
        public ResultActions perform(RequestBuilder requestBuilder)
                      throws Exception
        Perform a request and return a type that allows chaining further actions, such as asserting expectations, on the result.
        Parameters:
        requestBuilder - used to prepare the request to execute; see static factory methods in MockMvcRequestBuilders
        Returns:
        an instance of ResultActions (never null)
        Throws:
        Exception
        See Also:
        MockMvcRequestBuilders, MockMvcResultMatchers
         */
        // Quindi viene chiamato il metodo perform sull'oggetto
        // MockMvc appena costruito
        // All'interno della perform eseguo una get su
        // un determinato url in questo caso il contesto di root
        // Il risultato della perforn aspettato
        // sono uno stato OK=200 del controller
        // e un determinato nome ritornato cioè la stringa "index"
        // Il metodo get è un metodo statico della classe
        // MockMvcRequestBuilders
        // I metodi status e view sono metodi statici della
        // classe MockMvcResultMatchers
        // Quindi con esse effettuiamo un doppio controllo
        // Nell'output dobbiamo pertanto vedere
        // Forwarding to [index]
        // Completed 200 OK
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }


    // LA DIFFERENZA RISPETTO ALLA LEZIONE 177
    // E' TUTTA NEL TEST
    @Test
    public void getIndexPage() throws Exception  {

        //start given
        // Creiamo un nuovo HashSet di Ricette
        // a cui aggiungiamo 2 ricette di cui una vuota
        // e ad una assegniamo id_value = 1
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        // Senza questo setter la verifica
        // finale su 2 record va in errore
        // Puo capitare che IntellI non trovi
        // il metodo setId anche se è effettivamente
        // presente . In questo caso si puo togliere
        // e rimettere le annotazione dal preprocessore del compilatore
        // Enable Annotazione Process oppure ripulire la cache
        // di IntellJ
        recipe.setId(1L);
        //log.debug("Getting RecipeId " + recipe.getId());

        recipes.add(recipe);
        //end given

        //start when
        // sintassi standard ogni volta che chiamo il metodo
        // del servizio ritorna hashset definito localmente
        // test guidato dal comportamento
        when(recipeService.getRecipes()).thenReturn(recipes);

        // Creo un intercettore di comandi un matcher di argomenti
        // per il set di classi
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);

        //then le prime righe sono uguali alla lezione 176
        // a parte la sostituzione di anySet con Hashset valore ritornato
        // dal metodo argumentCaptor.capture()

        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        // Aggiunte rispetto alla lezione 177 le seguenti
        // righe di verifica per assicurarci che ci siano
        // due ricette dentro HashSet quindi recupero
        // HashSet da ArgumentCaptor e poi verifico
        // solo la dimensione del vettore
        // Per cui vogliamo essere sicuri di recupere
        // due oggetti al suo interno
        // Il valore ritornato sara un HashSet di
        // un solo elemento quando non si assegna il valore di id
        // almeno per un elemento del HashSet
        // perchè in quel caso aggiungiamo due elementi
        // uguali e unom dei due non viene considerato
        // Quindi perche' non dia errori la seguente verifica
        // rispetto ai 2 record si deve aggiungere il setId
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());


    }




}
