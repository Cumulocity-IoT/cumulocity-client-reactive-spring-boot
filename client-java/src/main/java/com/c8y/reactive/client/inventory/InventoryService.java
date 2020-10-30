package com.c8y.reactive.client.inventory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import com.c8y.reactive.client.core.C8yWebClientFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService {

	private static final Logger LOG = LoggerFactory.getLogger(InventoryService.class);
	
	private final WebClient webClient;
			
	public InventoryService(C8yWebClientFactory c8yWebClientFactory) {
		webClient = c8yWebClientFactory.buildC8yWebClient();
	}
	
	/**
	 * Gets the details of managed object and resturns it.
	 * 
	 * @param id managed object id, can be a string or a number
	 * @return ManagedObject
	 */
	public Mono<ManagedObject> detail(Object id) {		
		Mono<ManagedObject> result = webClient.get().uri("/inventory/managedObjects/{id}", id).retrieve().bodyToMono(ManagedObject.class);
		return result;
	}
	
	/**
	 * Gets the details of managed object and returns complete ResponseEntity.
	 * 
	 * @param id managed object id, can be a string or a number
	 * @return ResponseEntity
	 */
	public Mono<ResponseEntity<ManagedObject>> detailRE(Object id) {		
		Mono<ResponseEntity<ManagedObject>> result = webClient.get().uri("/inventory/managedObjects/{id}", id).exchange().flatMap(response -> response.toEntity(ManagedObject.class));
		return result;
	}

	/**
	 * Creates a new managed object and returns it.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ManagedObject> create(ManagedObject managedObject) {
		Mono<ManagedObject> result = webClient.post().uri("/inventory/managedObjects").accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).retrieve().bodyToMono(ManagedObject.class);
		return result;
	}
	
	/**
	 * Creates a new managed object and returns complete ResponseEntity.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ResponseEntity<ManagedObject>> createRE(ManagedObject managedObject) {
		Mono<ResponseEntity<ManagedObject>> result = webClient.post().uri("/inventory/managedObjects").accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).exchange().flatMap(response -> response.toEntity(ManagedObject.class));
		return result;
	}
	
	/**
	 * Updates managed object and returns it.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ManagedObject> update(ManagedObject managedObject, Object id) {
		Mono<ManagedObject> result = webClient.put().uri("/inventory/managedObjects/{id}", id).accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).retrieve().bodyToMono(ManagedObject.class);
		return result;
	}
	
	/**
	 * Updates managed object and returns complete ResponseEntity.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ResponseEntity<ManagedObject>> updateRE(ManagedObject managedObject, Object id) {
		Mono<ResponseEntity<ManagedObject>> result = webClient.put().uri("/inventory/managedObjects/{id}", id).accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).exchange().flatMap(response -> response.toEntity(ManagedObject.class));
		return result;
	}
	
	/**
	 * Removes managed object with given id.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<Void> delete(Object id) {
		Mono<Void> result = webClient.delete().uri("/inventory/managedObjects/{id}", id).retrieve().bodyToMono(Void.class);
		return result;
	}
	
	/**
	 *  Removes managed object with given id.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<ResponseEntity<Void>> deleteRE(Object id) {
		Mono<ResponseEntity<Void>> result = webClient.delete().uri("/inventory/managedObjects/{id}", id).exchange().flatMap(response -> response.toEntity(Void.class));
		return result;
	}
	
	/**
	 * Gets the list of managed objects use query parameters.
	 * 
	 * @return
	 */
	public Flux<ManagedObject> list(Map<String, List<String>> queryParamsMap) {
		return getManagedObjectFlux(queryParamsMap, 5);
	}
	
	/**
	 * Gets the list of managed objects use query parameters.
	 * 
	 * @return
	 */
	public Flux<ManagedObject> list() {
		return getManagedObjectFlux(null, 5);
	}
	
	private Flux<ManagedObject> getManagedObjectFlux(Map<String, List<String>> queryParamsMap, Integer pageSize) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		if(queryParamsMap != null) {
			queryParams.putAll(queryParamsMap);			
		}
	
		DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory();
		
		queryParams.put("pageSize", Arrays.asList(String.valueOf(pageSize)));
		UriBuilder uriBuilder = uriBuilderFactory.builder().path("/inventory/managedObjects").queryParams(queryParams);
		
	    return fetchCollection(uriBuilder, 1).expand(managedObjectCollection -> {
	        if (managedObjectCollection.getManagedObjects().isEmpty()) {
	            return Mono.empty();
	        }
	        return fetchCollection(uriBuilder, managedObjectCollection.getStatistics().getCurrentPage() + 1);
	    }).flatMap(managedObjectCollection -> Flux.fromIterable(managedObjectCollection.getManagedObjects()));
		
	}
	
	private Mono<ManagedObjectCollection> fetchCollection(UriBuilder uriBuilder, Integer currentPage) {
		uriBuilder.replaceQueryParam("currentPage", currentPage);
		String uri = uriBuilder.build().toString();
        return webClient.get().uri(uri).retrieve()
                .bodyToMono(ManagedObjectCollection.class);
	}
	
}
