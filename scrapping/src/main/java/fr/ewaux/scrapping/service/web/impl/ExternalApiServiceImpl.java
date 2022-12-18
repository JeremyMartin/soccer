package fr.ewaux.scrapping.service.web.impl;

import fr.ewaux.commons.utilities.exception.ExternalApiException;
import fr.ewaux.commons.utilities.exception.FileException;
import fr.ewaux.commons.utilities.model.response.ExpectedResponse;
import fr.ewaux.scrapping.model.external.club.Club;
import fr.ewaux.scrapping.model.external.club.ClubsResponse;
import fr.ewaux.scrapping.model.external.common.Pagination;
import fr.ewaux.scrapping.model.external.league.League;
import fr.ewaux.scrapping.model.external.league.LeaguesResponse;
import fr.ewaux.scrapping.model.external.nation.Nation;
import fr.ewaux.scrapping.model.external.nation.NationsResponse;
import fr.ewaux.scrapping.service.file.FileService;
import fr.ewaux.scrapping.service.web.ExternalApiService;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service(value = "externalApiService")
public class ExternalApiServiceImpl implements ExternalApiService {

	String apiKey;
	HttpEntity<?> jsonEntity;
	FileService fileService;

	public ExternalApiServiceImpl(@Value("${scrapping.api-key}") final String apiKey, final FileService fileService) {
		HttpHeaders jsonHeader = new HttpHeaders();
		jsonHeader.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		jsonHeader.set("X-AUTH-TOKEN", apiKey);
		this.jsonEntity = new HttpEntity<>(jsonHeader);
		this.apiKey = apiKey;
		this.fileService = fileService;
	}

	@Override
	public Set<ExpectedResponse> callEndpoint(final String url, final Class<?> target) {
		Set<ExpectedResponse> responses = new HashSet<>();
		String targetName = target.getSimpleName();
		if (targetName.equals(NationsResponse.class.getSimpleName())) {
			this.callList(url, target, targetName, responses);
		} else if (targetName.equals(ClubsResponse.class.getSimpleName())) {
			this.callList(url, target, targetName, responses);
		} else if (targetName.equals(LeaguesResponse.class.getSimpleName())) {
			this.callList(url, target, targetName, responses);
		} else {
			responses.add(ExpectedResponse.builder().message("Not implemented").field(targetName).value(url).build());
		}
		return responses;
	}

	private void callList(final String url, final Class<?> target, final String targetName, final Set<ExpectedResponse> responses) {
		if (log.isDebugEnabled()) {
			log.debug("call " + url);
		}
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.GET, this.jsonEntity, target);
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				Set<Object> result = new HashSet<>();
				Pagination pagination = null;
				String filename = null;
				String name = null;
				if (targetName.equals(NationsResponse.class.getSimpleName())) {
					NationsResponse nationsResponse = (NationsResponse) responseEntity.getBody();
					if (nationsResponse != null) {
						pagination = nationsResponse.getPagination();
						result.addAll(nationsResponse.getItems());
						filename = "nations.json";
						name = "nations";
					}
				} else if (targetName.equals(ClubsResponse.class.getSimpleName())) {
					ClubsResponse clubsResponse = (ClubsResponse) responseEntity.getBody();
					if (clubsResponse != null) {
						pagination = clubsResponse.getPagination();
						result.addAll(clubsResponse.getItems());
						filename = "clubs.json";
						name = "clubs";
					}
				} else if (targetName.equals(LeaguesResponse.class.getSimpleName())) {
					LeaguesResponse leaguesResponse = (LeaguesResponse) responseEntity.getBody();
					if (leaguesResponse != null) {
						pagination = leaguesResponse.getPagination();
						result.addAll(leaguesResponse.getItems());
						filename = "leagues.json";
						name = "leagues";
					}
				} else {
					responses.add(ExpectedResponse.builder().message("Not implemented").value(url).build());
				}
				if (pagination != null) {
					int pageCurrent = pagination.getPageCurrent();
					int pageTotal = pagination.getPageTotal();
					int countTotal = pagination.getCountTotal();
					for (int i = pageCurrent; i <= pageTotal; i++) {
						if (i != 1) {
							this.callNextPage(url + "?page=" + i, target, targetName, result, responses);
						}
					}
					if (countTotal != result.size()) {
						responses.add(ExpectedResponse.builder().message("Not found all").field(targetName).value(name).build());
					} else {
						try {
							this.fileService.writeFile("list", filename, result);
						} catch (FileException ex) {
							responses.add(
								ExpectedResponse.builder().message(ex.getMessage()).field(targetName)
									.value("Source : " + ex.getSource().toString() + ", Destination : " + ex.getDestination().toString()).build());
						}
						this.downloadImages(url, targetName, result, responses);
					}
				} else {
					responses.add(ExpectedResponse.builder().message("Not exist pagination").field(targetName).value(url).build());
				}
			} else {
				responses.add(
					ExpectedResponse.builder().message("Invocation target endpoint exception call list").field(targetName).value(url).build());
			}
		} catch (RestClientException ex) {
			throw new ExternalApiException(ex.getMessage(), url, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	private void callNextPage(final String url, final Class<?> target, final String targetName, final Set<Object> result,
		final Set<ExpectedResponse> responses) {
		if (log.isDebugEnabled()) {
			log.debug("call " + url);
		}
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.GET, this.jsonEntity, target);
			if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				if (targetName.equals(NationsResponse.class.getSimpleName())) {
					NationsResponse nationsResponse = (NationsResponse) responseEntity.getBody();
					if (nationsResponse != null) {
						result.addAll(nationsResponse.getItems());
					}
				} else if (targetName.equals(ClubsResponse.class.getSimpleName())) {
					ClubsResponse clubsResponse = (ClubsResponse) responseEntity.getBody();
					if (clubsResponse != null) {
						result.addAll(clubsResponse.getItems());
					}
				} else if (targetName.equals(LeaguesResponse.class.getSimpleName())) {
					LeaguesResponse leaguesResponse = (LeaguesResponse) responseEntity.getBody();
					if (leaguesResponse != null) {
						result.addAll(leaguesResponse.getItems());
					}
				} else {
					responses.add(ExpectedResponse.builder().message("Not implemented").field(targetName).value(url).build());
				}
			} else {
				responses.add(
					ExpectedResponse.builder().message("Invocation target endpoint exception call next page").field(targetName).value(url).build());
			}
		} catch (RestClientException ex) {
			responses.add(ExpectedResponse.builder().message(ex.getMessage()).field(targetName).value(url).build());
		}
	}

	private Integer getId(final Object obj) {
		if (obj instanceof Nation) {
			return ((Nation) obj).getId();
		} else if (obj instanceof Club) {
			return ((Club) obj).getId();
		} else if (obj instanceof League) {
			return ((League) obj).getId();
		}
		return null;
	}

	private void downloadImages(final String url, final String targetName, final Set<Object> result, final Set<ExpectedResponse> responses) {
		String path = null;
		if (targetName.equals(NationsResponse.class.getSimpleName())) {
			path = "images/nations";
		} else if (targetName.equals(ClubsResponse.class.getSimpleName())) {
			path = "images/clubs";
		} else if (targetName.equals(LeaguesResponse.class.getSimpleName())) {
			path = "images/leagues";
		} else {
			responses.add(ExpectedResponse.builder().message("Not implemented").value(url).build());
		}
		if (path != null) {
			int size = result.size();
			int current = 0;
			for (Object obj : result) {
				current++;
				Integer id = this.getId(obj);
				if (id != null) {
					String imageUrl = url + "/" + id + "/image";
					if (log.isDebugEnabled()) {
						log.debug("call " + "(" + current + "/" + size + ") => " + imageUrl);
					}
					try {

						RestTemplate restTemplate = new RestTemplate();
						RequestCallback requestCallback = request -> request.getHeaders().set("X-AUTH-TOKEN", apiKey);
						File file = null;
						try {
							file = restTemplate.execute(imageUrl, HttpMethod.GET, requestCallback, clientHttpResponse -> {
								File ret = File.createTempFile("download", "tmp");
								StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
								return ret;
							});
						} catch (Exception ex) {
							responses.add(
								ExpectedResponse.builder().message("Invocation target endpoint exception download").field(targetName).value(imageUrl)
									.build());
						}
						if (file != null) {
							try {
								this.fileService.writeFile(path, id + ".png", Files.readAllBytes(file.toPath()));
							} catch (Exception ex) {
								responses.add(ExpectedResponse.builder().message(ex.getMessage()).field(targetName).value(imageUrl).build());
							}
						}
					} catch (RestClientException ex) {
						responses.add(ExpectedResponse.builder().message(ex.getMessage()).field(targetName).value(imageUrl).build());
					}
				} else {
					responses.add(ExpectedResponse.builder().message("Id not found").value(obj).build());
				}
			}
		}
	}

	@Override
	public void generateReport(final Class<?>... targets) {
		this.fileService.writeXls(targets);
	}
}
