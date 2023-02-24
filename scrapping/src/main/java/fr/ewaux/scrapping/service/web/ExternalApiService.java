package fr.ewaux.scrapping.service.web;

import fr.ewaux.commons.utilities.model.response.exception.ExpectedResponse;
import java.util.Set;

public interface ExternalApiService {

	Set<ExpectedResponse> callEndpoint(String url, Class<?> target);

	void generateReport(Class<?>... targets);

}
