package com.c8y.reactive.client.measurement;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This class allows for managing measurements.
 */
@Service
public class MeasurementService {

	/**
	 * Gets the details of selected measurement.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<Measurement> detail(Object id) {
		return null;
	}
	
	/**
	 * Creates a new measurement.
	 * 
	 * @param measurement
	 * @param sourceId
	 * @return
	 */
	public Mono<Measurement> create(Measurement measurement, Object sourceId) {
		return null;
	}
	
	/**
	 * Removes a measurement with given id.
	 * 
	 * @param id
	 * @return
	 */
	public Mono<Void> delete(Object id) {
		return null;
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
