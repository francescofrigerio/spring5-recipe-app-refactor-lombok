package guru.springframework.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by jt on 6/13/17.
 * LEZIONE 165 166 Sostituisco @Data
 * e EqualsAndHashCode su consiglio di IntellJ
 */
/*
@Getter
@Setter
@ToString
@RequiredArgsConstructor
*/
@Data
// Escludo le ricette altrimenti
// genero un riferimento circolare
// per via della relazione many2many
// che determina un ciclo infinito
// sovrascriviamo quanto specificato
// dalla precedente annotazione
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    // LEZIONE 165 CI DEVE ESSERE
    // ALTRIMETNI HI UN ERRORE QUANDO
    // SI ACCEDE ALLA PAGINA WEB
    // No default constructor for entity: :
    // guru.springframework.domain.Ingredient
    // org.hibernate.InstantiationException:
    // No default constructor for entity: : guru.springframework.domain.Ingredient at

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ingredient that = (Ingredient) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
   */
}
