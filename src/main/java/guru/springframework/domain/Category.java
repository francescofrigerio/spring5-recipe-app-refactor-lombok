package guru.springframework.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 * LEZIONE 165 166 Sostituisco @Data
 * e EqualsAndHashCode su consiglio di IntellJ
 */
/* @Getter
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
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    // LEZIONE 165 166
    // Exclude Recipe
    // sostiuito in auatomatico da IntellJ
    // @EqualsAndHashCode(exclude = {"recipes"})
    @ManyToMany(mappedBy = "categories")
    /* @ToString.Exclude */
    private Set<Recipe> recipes;

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    */
}
