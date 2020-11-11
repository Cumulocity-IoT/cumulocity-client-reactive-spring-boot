package com.c8y.reactive.client.measurement;

import java.util.List;
import java.util.Map;

import com.c8y.reactive.client.core.C8yWebClientFactory;
import com.c8y.reactive.client.inventory.ManagedObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class allows for managing measurements.
 */
@Service
public class MeasurementService {

	private final WebClient webClient;

	public MeasurementService(C8yWebClientFactory c8yWebClientFactory) {
		webClient = c8yWebClientFactory.buildC8yWebClient();
	}

	/**
	 * Gets the details of selected measurement.
	 * 
	 * @param id measurement id, can be a string or a number
	 * @return Measurement
	 */
	public Mono<Measurement> detail(Object id) {
		Mono<Measurement> result = webClient.get().uri("/measurement/measurements/{id}", id).retrieve()
				.bodyToMono(Measurement.class);
		return result;
	}

	/**
	 * Gets the details of selected measurement and returns complete ResponseEntity.
	 *
	 * @param id measurement id, can be a string or a number
	 * @return ResponseEntity
	 */
	public Mono<ResponseEntity<Measurement>> detailRE(Object id) {
		Mono<ResponseEntity<Measurement>> result = webClient.get().uri("/measurement/measurements/{id}", id)
				.exchange().flatMap(response -> response.toEntity(Measurement.class));
		return result;
	}

	/**
	 * Creates a new measurement.
	 * 
	 * @param measurement
	 * @return Measurement
	 */
	public Mono<Measurement> create(Measurement measurement) {
		Mono<Measurement> result = webClient.post().uri("/measurement/measurements")
				.accept(MediaType.APPLICATION_JSON).bodyValue(measurement).retrieve().bodyToMono(Measurement.class);
		return result;
	}

	/**
	 * Creates a new measurement.
	 *
	 * @param measurement
	 * @return ResponseEntity
	 */
	public Mono<ResponseEntity<Measurement>> createRE(Measurement measurement) {
		Mono<ResponseEntity<Measurement>> result = webClient.post().uri("/measurement/measurements")
				.accept(MediaType.APPLICATION_JSON).bodyValue(measurement).exchange()
				.flatMap(response -> response.toEntity(Measurement.class));
		return result;
	}
	
	/**
	 * Removes a measurement with given id.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<Void> delete(Object id) {
		Mono<Void> result = webClient.delete().uri("/measurement/measurements/{id}", id).retrieve()
				.bodyToMono(Void.class);
		return result;
	}
	
	/**
	 * Gets the list of measurements filtered by query parameters
	 * 
	 * @param queryParamsMap
	 * @return
	 */
	public Flux<Measurement> measurementListByQuery(Map<String, List<String>> queryParamsMap) {
		return null;
	}
	
	/**
	 * Gets the list of series in a measurement filtered by query parameters.
	 * 
	 * @param queryParamsMap
	 * @return
	 */
	public Flux<Series> seriesListByQuery(Map<String, List<String>> queryParamsMap) {
		return null;
	}
}
