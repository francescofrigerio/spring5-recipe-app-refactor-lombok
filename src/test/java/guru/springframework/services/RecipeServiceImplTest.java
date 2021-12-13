package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by jt on 6/17/17.
 * LEZIONE 174
 * SE SI VUOLE IL METODO getRecipes
 * NON VADO IN EXCEPTION COME NELLA CLASSE TestCategory
 * E' NECESSARIO FORNIRE UN OGGETTO RecipeRepository
 * PERCHE' E' UNA DIPENDENZA RICHIESTA DA QUESTA CLASSE
 * SERVE QUINI INIETTARE  UN ISTANZA DI RecipeRepository
 * BISOGNA RICORDARE CHE IN FASE DI ESECUZIONE
 * SPRING DATA JPA FORNIRA' QUELL'OGGETTO
 * MA SE SI VUOLE INIETTARLO ANCHE DURANTE UN TEST
 */
public class RecipeServiceImplTest {

    // PER TESTARE IL METODO getRecipe
    // ABBIAMO BISOGNO DUE COSE
    // 1) ABBIAMO BISOGNO IL SERVIZIO
    // QUINDI LO DICHIARIAMO COME PROPRIETA
    // DELLA CLASSE
    RecipeServiceImpl recipeService;

    // 1) ABBIAMO BISOGNO DI INIETTARE LA
    // DIPENDENZA DEL REPOSITORY
    // QUINDI LO DICHIARIAMO COME PROPRIETA
    // DELLA CLASSE E LO ANNOTIANO COME Mock
    // MA ABBIAMO BISOGNO IL METODO DI PROVA
    // ALL'INTERNO DEL QUALE USEREMO IL REPOSITORY
    @Mock
    RecipeRepository recipeRepository;

    // PRIMA DEL TEST QUINDI ANNOTATO COME BEFORE
    // DEVO INIZIALIZZERA SERVIZIO E REPOSITORY
    // E INIZILIZZERA LA LIB MOCK(BEFFARDA)
    // IN QUESTO MODO CHIEDIAMO A MOCKITO DI DARCI UN FINTO
    // REPOSITORY
    @Before
    public void setUp() throws Exception {
        // SERVE PER INIZIALIZZARE IL TEST
        // IN CORRISPONDENZA DELL'ANNOTAZIOBE MOck
        MockitoAnnotations.initMocks(this);

        // ISTANZIO IL SERVIZIO DI RICETTE
        // PASSANDO IN INPUT L'OGGETTO REPOSITORY
        recipeService = new RecipeServiceImpl(recipeRepository);
    }


    @Test
    public void getRecipes() throws Exception {

        if (false ) {
            Recipe recipe = new Recipe();
            HashSet recipesData = new HashSet();
            recipesData.add(recipe);

            when(recipeRepository.findAll()).thenReturn(recipesData);

            Set<Recipe> recipes = recipeService.getRecipes();

            assertEquals(recipes.size(), 1);
            System.out.println("START TEST1");
            verify(recipeRepository, times(1)).findAll();
            System.out.println("END TEST1");
        }
        else {
            // TEST1
            // COSI CI DA UN ERRORE PERCHE
            // IL SERVIZIO DI RICETTA E VUOTO
            // E NOI CONTROLLIAMO CHE CONTENGA UNA RICETTA
        /*
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        */

            // TEST2 NESSUN ERRORE
            Set<Recipe> recipes0 = recipeService.getRecipes();
            assertEquals(recipes0.size(), 0);


            // TEST3 PER FAR RESTITUIRE QUALCOSA A MOCKITO
            // CREIAMO UN NUOCO METODO DI TEST
            // IN CUI CREIAMO UNA RICETTA
            // CHE AGGIUNGIAMO AD UN NUOVO HASHSET DI RICETTE
            Recipe recipe1 = new Recipe();
            HashSet recipesData = new HashSet();
            recipesData.add(recipe1);

            // DICIAMO A MOCKITO TRAMITE I METODI STATICO
            // when e thenReturn
            // PER RESTITUIRE QUESTO
            // VETTORE recipesData DI RICETTE QUANDO VIENE CHIAMATO
            // IL METODO recipeRepository.findAll
            // CIOE' QUANDO VIENE CHIAMATO IL METODO getRecipes SUL SERVIZIO
            //ERRATO -> when(recipeService.getRecipes()).thenReturn(recipesData);
            when(recipeRepository.findAll()).thenReturn(recipesData);

            // A QUESTO PUNTO POSSIAMO RISCRIVERE
            // IL CODICE CHE PRIMA ANADAVA IN ERRORE
            Set<Recipe> recipes = recipeService.getRecipes();
            assertEquals(recipes.size(), 1);

            // QUANDO LAVORIAMO CON MOCK POSSIAMO ANCHE VERIFICARE
            // LE INTERAZIONI TRAMITE IL METODO verify CHE COME
            // when thenReturn VIENE FORNITO DALLA LIB BEFFARDA
            // CHE LAVORA SEMPRE SUL REPOSITORY GRAZIE
            // ALL'ANNOTAZIONE INIZIALE
            // USIAMO I TEMPI DI MOCKITO  CON IL METODO DI VERIFICA DI MOCKITO
            // E CIOE' DI VERIFICARE CHE SIA CHIAMATO IL METODO findALl UNA SOLA VOLTA
            // NON DUE NON ZERO MA UNA SOLA VOLTA
            // PARTO DA 2 PER TENERE CONTO DELLA PRIMA CHIAMATA A getRecipes
            // QUINDI E' UN BUON METODO PER VERIFICARE
            // LE INTERAZIONI ALL'INTERNO DELLA CLASSE
            System.out.println("START TEST2");
            verify(recipeRepository, times(2)).findAll();
            System.out.println("END TEST2");

            boolean hideError = false ;
            if (hideError) {
                System.out.println("START TEST3");
                verify(recipeRepository, times(3)).findAll();
                System.out.println("END TEST3");
            }


        }


    }

}
