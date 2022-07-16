
package br.com.saks.biblioteca.controller;


import br.com.saks.biblioteca.model.Emprestimo.java.Emprestimo;
import br.com.saks.biblioteca.model.Usuario;
import br.com.saks.biblioteca.repository.EmprestimoRepository;
import br.com.saks.emprestimoservice.model.Emprestimo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import br.com.saks.emprestimoservice.repository.EmprestimoRepository;
//import br.com.saks.emprestimoservice.service.UsuarioService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private Usuario usuarioService;  
    
    @GetMapping
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Emprestimo listarPeloId(@PathVariable Long id) {
        Optional<Emprestimo> emprestimoResponse = emprestimoRepository.findById(id);
        Emprestimo emprestimo = emprestimoResponse.get();
        //emprestimo.setUsuario(usuarioService.setId(listarPeloId(emprestimo.getUsuario())));
        return emprestimo;
    }
    
    @PostMapping
    public Emprestimo adicionar(@RequestBody Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        return emprestimoRepository.findById(id)
                .map(record -> {
                    record.setUsuario(emprestimo.getUsuario());
                    Emprestimo emprestimoUpdated = emprestimoRepository.save(record);
                    return ResponseEntity.ok().body(emprestimoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return emprestimoRepository.findById(id)
                .map(record-> {
                    emprestimoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
