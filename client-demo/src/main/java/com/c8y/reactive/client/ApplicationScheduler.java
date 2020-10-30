package com.c8y.reactive.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.c8y.reactive.client.core.EmptyObject;
import com.c8y.reactive.client.inventory.InventoryService;
import com.c8y.reactive.client.inventory.ManagedObject;

@Service
public class ApplicationScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationScheduler.class);
	
	private InventoryService inventoryService;
	
	public ApplicationScheduler(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@Scheduled(fixedDelayString = "10000")
	public void heartbeat() {
		LOG.info("heartbeat");
		
		ManagedObject managedObject = new ManagedObject();
		managedObject.set("name", "test");
		managedObject.set("type", "test type");
		managedObject.set("c8y_IsDevice", new EmptyObject());
		this.inventoryService.create(managedObject).subscribe(x -> LOG.info("Generated: " + x.toString()));
		
		this.inventoryService.detail("1551728").subscribe(x -> LOG.info(x.get("type").toString()));
	}

}
