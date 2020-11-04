package com.c8y.reactive.client.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.c8y.reactive.client.core.C8yWebClientFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Disabled("Set baseUrl, user and password to run this test with your specific cumulocity instance!")
class InventoryServiceTest {

	private InventoryService inventoryService;
	
	private final String baseUrl = "";
	private final String user = "";
	private final String password = "";
	
	@BeforeEach
	void setUp() throws Exception {
		Builder builder = WebClient.builder();
		C8yWebClientFactory webClientFactory = new C8yWebClientFactory(baseUrl, user, password, builder);
		inventoryService = new InventoryService(webClientFactory);
	}

	@Test
	void testCreateUpdateDetailDelete() {
		/* create */
		ManagedObject newManagedObject = new ManagedObject();
		newManagedObject.set("name", "myManagedObject");
		newManagedObject.set("type", "myType");
		newManagedObject.set("c8y_IsDevice");
		Mono<ManagedObject> createdManagedObject = this.inventoryService.create(newManagedObject);
		ManagedObject createResult = createdManagedObject.block();
		assertNotNull(createResult);
		
		/* detail */
		Mono<ManagedObject> managedObject = this.inventoryService.detail(createResult.getId());
		ManagedObject detailResult = managedObject.block();
		assertEquals(createResult.getId(), detailResult.getId());
	
		/* update */
		ManagedObject updatedManagedObject = new ManagedObject();
		updatedManagedObject.set("name", "myUpdatedManagedObject");
		Mono<ManagedObject> updateManagedObject = this.inventoryService.update(updatedManagedObject, detailResult.getId());
		ManagedObject updatedResult = updateManagedObject.block();
		assertNotNull(updatedResult);
		assertEquals("myUpdatedManagedObject", updatedResult.get("name"));
		
		/* delete */
		Mono<Void> monoVoid = this.inventoryService.delete(detailResult.getId());		
		Void deleteResult = monoVoid.block();
		assertNull(deleteResult);
	}
	
	@Test
	void testCreateUpdateDetailDeleteRE() {
		/* create */
		ManagedObject newManagedObject = new ManagedObject();
		newManagedObject.set("name", "myManagedObject");
		newManagedObject.set("type", "myType");
		newManagedObject.set("c8y_IsDevice");
		Mono<ResponseEntity<ManagedObject>> createdManagedObjectRE = this.inventoryService.createRE(newManagedObject);
		ResponseEntity<ManagedObject> createResult = createdManagedObjectRE.block();
		assertNotNull(createResult);
		assertEquals(HttpStatus.CREATED, createResult.getStatusCode());
		
		/* detail */
		Mono<ResponseEntity<ManagedObject>> managedObjectRE = this.inventoryService.detailRE(createResult.getBody().getId());
		ResponseEntity<ManagedObject> detailResult = managedObjectRE.block();
		assertEquals(HttpStatus.OK, detailResult.getStatusCode());
		assertEquals(createResult.getBody().getId(), detailResult.getBody().getId());
	
		/* update */
		ManagedObject updatedManagedObject = new ManagedObject();
		updatedManagedObject.set("name", "myUpdatedManagedObject");
		Mono<ResponseEntity<ManagedObject>> updatedManagedObjectRE = this.inventoryService.updateRE(updatedManagedObject, detailResult.getBody().getId());
		ResponseEntity<ManagedObject> updatedResult = updatedManagedObjectRE.block();
		assertNotNull(updatedResult);
		assertEquals("myUpdatedManagedObject", updatedResult.getBody().get("name"));
		assertEquals(HttpStatus.OK, updatedResult.getStatusCode());
		
		/* delete */
		Mono<ResponseEntity<Void>> monoVoidRE = this.inventoryService.deleteRE(detailResult.getBody().getId());		
		ResponseEntity<Void> deleteResult = monoVoidRE.block();
		assertEquals(HttpStatus.NO_CONTENT, deleteResult.getStatusCode());
	}

	@Test
	void testList() {
		/* create */
		List<ManagedObject> testItems = new ArrayList<ManagedObject>();
		
		for(int i = 1; i<=4; i++) {
			ManagedObject newManagedObject = new ManagedObject();
			newManagedObject.set("name", "myManagedObject"+i);
			newManagedObject.set("type", "myType");
			newManagedObject.set("c8y_JUnitTest");
			Mono<ManagedObject> createdManagedObject = this.inventoryService.create(newManagedObject);
			testItems.add(createdManagedObject.block());
		}
		
		Map<String, List<String>> queryParamsMap = new HashMap<>();
		queryParamsMap.put("fragmentType", Arrays.asList("c8y_JUnitTest"));

		Flux<ManagedObject> managedObjectsFlux = this.inventoryService.listQuery(queryParamsMap);
		StepVerifier.create(managedObjectsFlux.log()).expectNextCount(4).verifyComplete();
		
		for(ManagedObject managedObject: testItems) {
			this.inventoryService.delete(managedObject.getId()).block();
		}
	}
	
	@Test
	void testEmptyList() {

		Map<String, List<String>> queryParamsMap = new HashMap<>();
		queryParamsMap.put("fragmentType", Arrays.asList("c8y_JUnitTestEmpty"));

		Flux<ManagedObject> managedObjectsFlux = this.inventoryService.listQuery(queryParamsMap);
		StepVerifier.create(managedObjectsFlux.log()).expectNextCount(0).verifyComplete();
	}
	
	@Test
	void testListRE() {
		
	}
	
	@Test
	void testChildDevicesList() {
		Flux<ManagedObjectReference> childDevicesListFlux = this.inventoryService.childDevicesList("1551728");
		ManagedObjectReference blockFirst = childDevicesListFlux.blockFirst();
		assertEquals("1576784", blockFirst.getManagedObject().getId());
		StepVerifier.create(childDevicesListFlux.log()).expectNextCount(1).verifyComplete();
	}
	
	@Test
	void testChildAssetsList() {
		Flux<ManagedObjectReference> childDevicesListFlux = this.inventoryService.childAssetsList("1473519");
		ManagedObjectReference blockFirst = childDevicesListFlux.blockFirst();
		assertEquals("1473520", blockFirst.getManagedObject().getId());
		StepVerifier.create(childDevicesListFlux.log()).expectNextCount(1).verifyComplete();
	}
	
	@Test
	void testChildReferencesAdd() {
		Mono<Void> childDevicesAdd = this.inventoryService.childDevicesAdd("520", "13417");
		childDevicesAdd.block();
	}
}