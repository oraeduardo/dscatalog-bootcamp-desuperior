package com.devsuperior.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

//Controladores REST

@RestController
@RequestMapping(value = "/categories") /*Rota rest do recurdo*/
public class CategoryResource {

	/* ResponseEntity
	 * é um objeto generic do spring boot usado para encapsular 
	 * uma resposta do http */
	
	/* List é uma inteface 
	 * interfaces não pode ser instânciada é necessário usar 
	 * uma classe que implementa a interface
	 * por exemplo o ArrayList */
	
	/* ResponseEntity.ok() 
	 * Retorna o código 200, significa que a requisição foi 
	 * realizada com sucesso */
	
	/* @GetMapping
	 * Configurar o metodo como um Web Service 
	 * "End Poind do recurso Category"*/
	
	@Autowired
	private CategoryService service;
	
	@GetMapping  
	public ResponseEntity<List<CategoryDTO>> findAll() {
		/*List<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Books"));
		list.add(new Category(2L, "Electronics"));*/
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		CategoryDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
