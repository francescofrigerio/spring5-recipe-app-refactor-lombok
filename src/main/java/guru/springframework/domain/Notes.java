package guru.springframework.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by jt on 6/13/17.
 * LEZIONE 167 Escludere proprieta
 * dal HashCode
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
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Notes notes = (Notes) o;
        return id != null && Objects.equals(id, notes.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    */
}
