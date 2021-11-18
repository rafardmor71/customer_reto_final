package com.retofinal.servereureka.customereureka.reto.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.retofinal.servereureka.customereureka.reto.dto.ClienteDTO;

@RestController
public class ClienteController {

	private RestTemplate template = new RestTemplate();
	private String url = "http://localhost:8761/";

	@RequestMapping(path = "/getClientes")
	public String getClientes() {
		String urlTemplate = url + "listarClientes";
		ResponseEntity<String> responseEntity = template.getForEntity(urlTemplate, String.class);
		return responseEntity.getBody();
	}

	@RequestMapping(path = "/getClientesStarter")
	public String getClientesStarter() {
		String urlTemplate = url + "listarClientesStarter";
		ResponseEntity<String> responseEntity = template.getForEntity(urlTemplate, String.class);
		return responseEntity.getBody();
	}

	@RequestMapping(path = "/addClientes", method = RequestMethod.POST)
	public String addClientes(@RequestBody ClienteDTO clienteDTO) {
		String urlTemplate = url + "addClientes";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> mapObject = new HashMap<>();
		mapObject.put("id", clienteDTO.getId());
		mapObject.put("nombre", clienteDTO.getNombre());
		mapObject.put("apellidos", clienteDTO.getApellidos());
		mapObject.put("edad", clienteDTO.getEdad());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(mapObject, headers);
		ResponseEntity<String> response = template.postForEntity(urlTemplate, entity, String.class);
		return response.getBody();
		
	}
	
	@RequestMapping(path = "/updateClientes", method = RequestMethod.PUT)
	public String updateClientes(@RequestBody ClienteDTO clienteDTO) {
		String urlTemplate = url + "updateClientes";
	    HttpHeaders headers = new HttpHeaders();
	    Map<String, Object> mapObject = new HashMap<String, Object>();
	    mapObject.put("id", clienteDTO.getId());
		mapObject.put("nombre", clienteDTO.getNombre());
		mapObject.put("apellidos", clienteDTO.getApellidos());
		mapObject.put("edad", clienteDTO.getEdad());
	    HttpEntity<ClienteDTO> requestEntity = new HttpEntity<ClienteDTO>(clienteDTO, headers);
	    HttpEntity<String> response = template.exchange(urlTemplate, HttpMethod.PUT, requestEntity, String.class, mapObject);

	   return response.getBody();
		
	}
	
	@RequestMapping(path = "/deleteClientes", method = RequestMethod.DELETE)
	public String deleteClientes(@RequestBody ClienteDTO clienteDTO) {
		String urlTemplate = url + "deleteClientes";
	    HttpHeaders headers = new HttpHeaders();
	    Map<String, Object> mapObject = new HashMap<String, Object>();
	    mapObject.put("id", clienteDTO.getId());
		mapObject.put("nombre", clienteDTO.getNombre());
		mapObject.put("apellidos", clienteDTO.getApellidos());
		mapObject.put("edad", clienteDTO.getEdad());
	    HttpEntity<ClienteDTO> requestEntity = new HttpEntity<ClienteDTO>(clienteDTO, headers);
	    HttpEntity<String> response = template.exchange(urlTemplate, HttpMethod.DELETE, requestEntity, String.class, mapObject);
		return response.getBody();
		
	}
}
