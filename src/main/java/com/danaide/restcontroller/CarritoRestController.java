package com.danaide.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaide.model.Carrito;
import com.danaide.model.CarritoItem;
import com.danaide.model.service.ICarritoService;
import com.danaide.model.service.IUsuarioService;

@RestController
@RequestMapping("/carritos")
public class CarritoRestController {
	@Autowired
	private ICarritoService carritoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public List<Carrito> getCarritos(){
		return carritoService.getCarritos();
	}
	
	@GetMapping (value = "/usuarios/{idUsuario}")
	public List<Carrito> findByUsuario(@PathVariable("idUsuario") Long idUsuario){
		return carritoService.findByUsuario(usuarioService.findById(idUsuario));
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody Carrito carrito) {
		carritoService.save(carrito);
		return new ResponseEntity<>("El carrito fue creado con éxito", HttpStatus.CREATED);
	}
	
	@DeleteMapping (value = "/{idCarrito}")
	public ResponseEntity<Object> delete(@PathVariable("idCarrito") Long idCarrito){
		try {
			carritoService.delete(idCarrito);
			return new ResponseEntity<>("El carrito fue eliminado con éxito", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping (value = "/{idCarrito}/items")
	public ResponseEntity<Object> addItem(@PathVariable("idCarrito") Long idCarrito, @RequestBody CarritoItem item){
		try {
			carritoService.addItem(idCarrito, item);
			return new ResponseEntity<>("Se agregó el item al carrito con éxito", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping (value = "/{idCarrito}/items/{idItem}")
	public ResponseEntity<Object> deleteItem(@PathVariable("idCarrito") Long idCarrito, @PathVariable("idItem") Long idItem){
		try {
			carritoService.deleteItem(idCarrito, idItem);
			return new ResponseEntity<>("El item fue eliminado con éxito", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping (value = "/{idCarrito}/cerrar")
	public ResponseEntity<Object> cerrarCarrito(@PathVariable("idCarrito") Long idCarrito){
		Carrito carrito = carritoService.findById(idCarrito);
		if(carrito != null) {
			carritoService.cerrarCarrito(carrito);
			return new ResponseEntity<>("El carrito fue cerrado con éxito", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El carrito con id " + idCarrito + " no existe", HttpStatus.NOT_FOUND);
		}
	}
}
