package br.com.saks.biblioteca.model.Emprestimo.java;



import br.com.saks.biblioteca.model.Livro;
import br.com.saks.biblioteca.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
public class Emprestimo {
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToMany
    @JoinTable(
    name= "item_emprestimo",
    joinColumns= @JoinColumn(name = "id_emprestimo"),
    inverseJoinColumns= @JoinColumn(name = "id_livro")
    )
    private List<Livro> livros;        
}
