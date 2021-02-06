package com.danaide.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaide.model.Producto;
import com.danaide.model.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	@Autowired
	private IProductoService productoService;
	
	@GetMapping
	public List<Producto> getProductos(){
		return productoService.getProductos();
	}
	
//	private List<Meetup> lsMeetups;
//	
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public ResponseEntity<Object> getMeetup() {
//		setLsMeetups(MeetupPersistentUtil.readFileMeetupData());
//		Collections.sort(getLsMeetup());
//		return new ResponseEntity<>(getLsMeetup(), HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ResponseEntity<Object> insert(@RequestBody Meetup meetup) throws JsonIOException, IOException {
//		setLsMeetups(MeetupPersistentUtil.readFileMeetupData());
//
//		System.out.println(meetup);
//		try {
////			meetup.getWeather().setTemperature(RapidApi.obtenerTemperatura());
//			MeetupPersistentUtil.insert(meetup);
//			return new ResponseEntity<>("Meetup registrada con éxito", HttpStatus.CREATED);
//		} catch (Exception e) {
//			System.out.println("Se produjo un error al insertar la meetup. " + e.getMessage());
//			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@RequestMapping(value = "/{idMeetup}", method = RequestMethod.GET)
//	public ResponseEntity<Object> update(@PathVariable("idMeetup") Integer id) {
//		setLsMeetups(MeetupPersistentUtil.readFileMeetupData());
//		Meetup xMeetup = MeetupPersistentUtil.find(id, lsMeetups);
//		
//		return 
//			xMeetup != null ? 
//				new ResponseEntity<>(xMeetup, HttpStatus.OK) : 
//				new ResponseEntity<>(HttpStatus.NOT_FOUND); 
//	}
//
//	@RequestMapping(value = "/{idMeetup}", method = RequestMethod.DELETE)
//	public ResponseEntity<Object> delete(@PathVariable("idMeetup") Integer id) {
//		try {
//			MeetupPersistentUtil.delete(id);
//			return new ResponseEntity<>("La meetup fue eliminada con éxito", HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	@RequestMapping(value = "/{idMeetup}/calcBirras", method = RequestMethod.GET)
//	public ResponseEntity<Object> calcBirras(@PathVariable("idMeetup") Integer id) {
//		setLsMeetups(MeetupPersistentUtil.readFileMeetupData());
//		Meetup xMeetup = MeetupPersistentUtil.find(id, lsMeetups);
//		
//		return 
//			xMeetup != null ? 
//				new ResponseEntity<>(xMeetup.getBirras(), HttpStatus.OK) : 
//				new ResponseEntity<>(HttpStatus.NOT_FOUND); 
//	}
//
//	public List<Meetup> getLsMeetup() {
//		if (lsMeetups == null) {
//			lsMeetups = new ArrayList<Meetup>();
//		}
//		return lsMeetups;
//	}
//
//	public void setLsMeetups(List<Meetup> lsMeetups) {
//		this.lsMeetups = lsMeetups;
//	}
}
