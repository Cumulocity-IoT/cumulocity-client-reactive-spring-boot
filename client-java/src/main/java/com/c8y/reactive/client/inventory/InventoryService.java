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
import com.c8y.reactive.client.core.ManagedObjectSource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class allows for managing managed objects and different child types.
 * 
 * @author alexander.pester@softwareag.com
 *
 */
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
		Mono<ManagedObject> result = webClient.get().uri("/inventory/managedObjects/{id}", id).retrieve()
				.bodyToMono(ManagedObject.class);
		return result;
	}

	/**
	 * Gets the details of managed object and returns complete ResponseEntity.
	 * 
	 * @param id managed object id, can be a string or a number
	 * @return ResponseEntity
	 */
	public Mono<ResponseEntity<ManagedObject>> detailRE(Object id) {
		Mono<ResponseEntity<ManagedObject>> result = webClient.get().uri("/inventory/managedObjects/{id}", id)
				.exchange().flatMap(response -> response.toEntity(ManagedObject.class));
		return result;
	}

	/**
	 * Creates a new managed object and returns it.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ManagedObject> create(ManagedObject managedObject) {
		Mono<ManagedObject> result = webClient.post().uri("/inventory/managedObjects")
				.accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).retrieve().bodyToMono(ManagedObject.class);
		return result;
	}

	/**
	 * Creates a new managed object and returns complete ResponseEntity.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ResponseEntity<ManagedObject>> createRE(ManagedObject managedObject) {
		Mono<ResponseEntity<ManagedObject>> result = webClient.post().uri("/inventory/managedObjects")
				.accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).exchange()
				.flatMap(response -> response.toEntity(ManagedObject.class));
		return result;
	}

	/**
	 * Updates managed object and returns it.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ManagedObject> update(ManagedObject managedObject, Object id) {
		Mono<ManagedObject> result = webClient.put().uri("/inventory/managedObjects/{id}", id)
				.accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).retrieve().bodyToMono(ManagedObject.class);
		return result;
	}

	/**
	 * Updates managed object and returns complete ResponseEntity.
	 * 
	 * @param managedObject
	 * @return
	 */
	public Mono<ResponseEntity<ManagedObject>> updateRE(ManagedObject managedObject, Object id) {
		Mono<ResponseEntity<ManagedObject>> result = webClient.put().uri("/inventory/managedObjects/{id}", id)
				.accept(MediaType.APPLICATION_JSON).bodyValue(managedObject).exchange()
				.flatMap(response -> response.toEntity(ManagedObject.class));
		return result;
	}

	/**
	 * Removes managed object with given id.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<Void> delete(Object id) {
		Mono<Void> result = webClient.delete().uri("/inventory/managedObjects/{id}", id).retrieve()
				.bodyToMono(Void.class);
		return result;
	}

	/**
	 * Removes managed object with given id.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<ResponseEntity<Void>> deleteRE(Object id) {
		Mono<ResponseEntity<Void>> result = webClient.delete().uri("/inventory/managedObjects/{id}", id).exchange()
				.flatMap(response -> response.toEntity(Void.class));
		return result;
	}

	/**
	 * Gets the list of managed objects using query parameters.
	 * 
	 * @return
	 */
	public Flux<ManagedObject> listQuery(Map<String, List<String>> queryParamsMap) {
		return getManagedObjectFluxByQuery(queryParamsMap, 5);
	}
	
	/**
	 * Gets the list of all managed objects.
	 * 
	 * @return
	 */
	public Flux<ManagedObject> list() {
		return getManagedObjectFluxByQuery(null, 5);
	}

	/**
	 * Gets a list of child devices from a given managed object (parent)
	 * 
	 * @param parentId
	 * @return
	 */
	public Flux<ManagedObjectReference> childDevicesList(Object parentId) {
		return getManagedObjectFluxByReference(parentId, ManagedObjectReferenceEnum.CHILD_DEVICES, 5);
	}
	
	/**
	 * Creates a new managed object as child device to another managed object (parent)
	 * 
	 * @param managedObject
	 * @param parentId
	 * @return
	 */
	public Mono<ManagedObject> childDevicesCreate(ManagedObject managedObject, Object parentId) {
		return null;
	}
	
	/**
	 * Adds an existing managed object as child device to another managed object (parent)
	 * 
	 * @param childId
	 * @param parentId
	 * @return
	 */
	public Mono<Void> childDevicesAdd(Object parentId, Object childId) {
		return childReferencesAdd(parentId, childId, ManagedObjectReferenceEnum.CHILD_DEVICES);
	}
	
	/**
	 * Removes an existing managed object as child device from another managed object (parent)
	 * 
	 * @param childId
	 * @param parentId
	 * @return
	 */
	public Mono<Void> childDevicesRemove(Object childId, Object parentId) {
		return null;
	}
	
	/**
	 * Gets a list of child assets from a given managed object (parent)
	 * 
	 * @param parentId
	 * @return
	 */
	public Flux<ManagedObjectReference> childAssetsList(Object parentId) {
		return getManagedObjectFluxByReference(parentId, ManagedObjectReferenceEnum.CHILD_ASSETS, 5);
	}
	
	/**
	 * Gets a list of child additions from a given managed object (parent)
	 * 
	 * @param parentId
	 * @return
	 */
	public Flux<ManagedObjectReference> childAdditionsList(Object parentId) {
		return getManagedObjectFluxByReference(parentId, ManagedObjectReferenceEnum.CHILD_ADDITIONS, 5);
	}
	
	private Flux<ManagedObject> getManagedObjectFluxByQuery(Map<String, List<String>> queryParamsMap, Integer pageSize) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
		if (queryParamsMap != null) {
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
		return webClient.get().uri(uri).retrieve().bodyToMono(ManagedObjectCollection.class);
	}
	
	private Flux<ManagedObjectReference> getManagedObjectFluxByReference(Object parentId, ManagedObjectReferenceEnum reference, Integer pageSize) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();

		DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory();

		queryParams.put("pageSize", Arrays.asList(String.valueOf(pageSize)));
		UriBuilder uriBuilder = uriBuilderFactory.builder().path("/inventory/managedObjects/{parentId}/{references}").queryParams(queryParams);

		return fetchCompleteReferenceCollection(uriBuilder, 1, parentId, reference.getReferenceName()).expand(managedObjectCollection -> {
			if (managedObjectCollection.getReferences().isEmpty()) {
				return Mono.empty();
			}
			return fetchCompleteReferenceCollection(uriBuilder, managedObjectCollection.getStatistics().getCurrentPage() + 1, parentId, reference.getReferenceName());
		}).flatMap(managedObjectCollection -> Flux.fromIterable(managedObjectCollection.getReferences()));
	}
	
	private Mono<ManagedObjectReferenceCollectionPage> fetchCompleteReferenceCollection(UriBuilder uriBuilder, Integer currentPage, Object parentId, Object reference) {
		uriBuilder.replaceQueryParam("currentPage", currentPage);
		String uri = uriBuilder.build(parentId, reference).toString();
		return webClient.get().uri(uri).retrieve().bodyToMono(ManagedObjectReferenceCollectionPage.class);
	}
	
	private Mono<Void> childReferencesAdd(Object parentId, Object childId, ManagedObjectReferenceEnum reference) {
		ManagedObjectSource childSource = new ManagedObjectSource();
		childSource.setId(String.valueOf(childId));
		
		ManagedObjectReference managedObjectReference = new ManagedObjectReference();
		managedObjectReference.setManagedObject(childSource);
		
		Mono<Void> result = webClient.post().uri("/inventory/managedObjects/{parentId}/{reference}", parentId, reference.getReferenceName()).accept(MediaType.APPLICATION_JSON).bodyValue(managedObjectReference).retrieve().bodyToMono(Void.class);
		return result;
	}


}
