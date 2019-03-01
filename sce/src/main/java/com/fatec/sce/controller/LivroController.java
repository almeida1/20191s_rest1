package com.fatec.sce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sce.model.Livro;
 

@RestController
public class LivroController {
 
  private Map<Integer, Livro> livros;
 
  public LivroController() {
    livros = new HashMap<Integer, Livro>();
 
    Livro c1 = new Livro("1111", "Engenharia de Software", "Pressman");
    Livro c2 = new Livro("2222", "Engenharia de Software", "Sommerville");
    Livro c3 = new Livro("3333", "Testes de Software", "Delamaro");
 
    livros.put(1, c1);
    livros.put(2, c2);
    livros.put(3, c3);
   
  }
  //Essa anotação, determina a URI /livros, a partir do método GET, 
  //poderá listar todos os livros registrados no HashMap.
  //Observe que na linha de retorno do dados, foi adicionado um HttpStatus.OK. 
  //Essa opção indica que o Spring, ao montar a resposta, deve enviar um “200 OK” para o app cliente.
  @RequestMapping(value = "/livros", method = RequestMethod.GET)
  public ResponseEntity<List<Livro>> listar() {
    return new ResponseEntity<List<Livro>>(new ArrayList<Livro>(livros.values()), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/livros/{id}", method = RequestMethod.GET)
  public ResponseEntity<Livro> buscar(@PathVariable("id") int id) {
	System.out.println(id);
    Livro livro = livros.get(id);
   
    if (livro == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
    return new ResponseEntity<Livro>(livro, HttpStatus.OK);
  }
 
}