package guru.springframework.domain;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jt on 6/17/17.
 * LEZIONE 173
 * Si VUOLE CREARE UN SETUP DI CONFIGURAZIONE
 * PER UNA CATEGORIA CHE VERRA' ESEGUITO OGNI VOLTA
 * PRIMA DEL TEST MEDIANTE l'ANNOTAZIONE Before
 * IN CUI INIZILIZZIAMO LE PROPRIETA'
 * OTTENENDO UNA NUOVA CATEGORIA
 * E POI IMPLEMENTIAMO TRE METODI IN
 * FACCIAMO IL NOSTRO TEST PRINA DEL QUALE
 * VIENE SEMPRE CREATO UN NUOVO OGGETTO CATEGORIA
 * IL TEST UNITARIO COME SI PUO NOTARE
 * E' MOLTO PIU VELOCE DEL TEST D'INTEGRAZIONE
 * DELLA CLASSE MAIN
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception {

        System.out.println("START TEST getId");
        // Definiamo il valore di ID per la nostra proprieta
        Long idValue = 4L;

        category.setId(idValue);

        // CONTROLLO CHE I DUE OGGETTI IN INPUT
        // SIANO UGUALI
        // QUINDI AFFERMO L'UGUAGLIANZA TRA I DUE VALORI
        assertEquals(idValue, category.getId());
        System.out.println("END TEST getId " + category.getId());
    }

    @Test
    public void getDescription() throws Exception {
        System.out.println("TEST getDescription " + category.getDescription());
    }

    @Test
    public void getRecipes() throws Exception {
        // Va in NullPoninterException
        //System.out.println("TEST getRecipes " + category.getRecipes().size());
    }

}