package io.skyhive.veneer.parsers.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.skyhive.veneer.common.config.FeignSupportConfig;
import io.skyhive.veneer.parsers.dto.employee.ResumeExtractionResponse;

/**
 * The interface Employee service.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@FeignClient(name = "employee", url = "${enterprise.api.url}", configuration = FeignSupportConfig.class)
public interface EmployeeService {
	/**
	 * Parse resume resume extraction response.
	 *
	 * @param file the file
	 * @return the resume extraction response
	 */
	@PostMapping(value = "/api/v1.0/employee/getProfileWithSkillsFromResumeExtraction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResumeExtractionResponse parseResume(@RequestPart(value = "file") MultipartFile file);

}
