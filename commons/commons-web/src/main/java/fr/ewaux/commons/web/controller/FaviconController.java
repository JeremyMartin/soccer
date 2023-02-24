package fr.ewaux.commons.web.controller;

import fr.ewaux.commons.utilities.exception.FileException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {

	@GetMapping(path = "/favicon.ico", produces = "image/x-icon")
	public byte[] getFavicon() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/static/favicon.ico");
			if (Objects.nonNull(inputStream)) {
				return IOUtils.toByteArray(inputStream);
			}
			throw new FileException("Favicon not found", "favicon.ico");
		} catch (Exception e) {
			throw new FileException(e.getMessage(), "favicon.ico");
		}
	}

}
