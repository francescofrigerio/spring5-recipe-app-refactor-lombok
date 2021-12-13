package guru.springframework.controllers;

import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// LEZIONE 175 e 176
public class IndexControllerTest_176 {

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

    @Test
    public void getIndexPage() throws Exception  {

        String viewName = controller.getIndexPage(model);
        System.out.println("getIndexPage -> " + viewName);
        // Verifico che il metodo getIndexPage
        // restituisca la stringa corretta
        assertEquals("index", viewName);

        // Verifico che il metodo getRecipes
        // sia chiamato una volta sola
        verify(recipeService, times(1)).getRecipes();

        // Verifico che il model contengo l'attributo richiesto
        //verify(model, times(1)).containsAttribute("recipes");

        // Verifico che il metodo addAttribute
        // sia chiamato una volta sola
        // e che il nome dell'attributo sia "recipes"

        // Il metodo addAttributes come si vede anche nella classe
        // IndexController ha due parametri in input

        // 1)
        // il primo valore che sarà uguale alla stringa
        // usando il metodo matcher eq di Mock
        // per assicurarci che stiamo passando il valore corretto
        // 2)
        // mentre il secondo valore  della chiamata
        // sara il metodo anySet che ci da hashset del servizio
        // Ci aspettiamo quindi che addAttributes aggiunga
        // questo attributo String
        // e con anyset come valore recuperato dal model effettivo
        // Confronando con il codice di IndexController
        //  model.addAttribute("recipes", recipeService.getRecipes());
        // Stiamo passando come primo argomento la chiave String della mappa
        // e come secondo argomento la mappa stessa
        // quindi qualsiasi valore che ritorna da RecipeService
        // sara' un set e per questo che viene chiamato anySet
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }



}
