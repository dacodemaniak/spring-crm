package com.aelion.mycrm.components;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class GraphQLDataFetchers {
	private static List<Map<String, String>> persons = Arrays.asList(
				ImmutableMap.of(
					"id", "person-1",
					"lastName", "Aubert",
					"firstName", "Jean-Luc",
					"occupation", "occupation-1",
					"company", "company-1",
					"zipCode", "31620",
					"streetName", "40, rue des Jardins",
					"city", "Castelnau d'Estrétefonds"
				)
			);
	
	private static List<Map<String, String>> occupations = Arrays.asList(
			ImmutableMap.of(
				"id", "occupation-1",
				"name", "Teacher"
			)
		);
	
	private static List<Map<String, String>> companies = Arrays.asList(
			ImmutableMap.of(
				"id", "company-1",
				"name", "Aélion"
			)
		);
			
}
