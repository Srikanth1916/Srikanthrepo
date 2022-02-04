package io.skyhive.veneer.parsers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.parsers.dto.employee.Contact;
import io.skyhive.veneer.parsers.dto.employee.ResumeExtractionResponse;
import io.skyhive.veneer.parsers.services.EmployeeService;
import io.skyhive.veneer.parsers.services.SovernService;
import lombok.extern.slf4j.Slf4j;
/**
 * The type Employee Controller.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */

@RestController
@Slf4j
public class ParsersResumeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SovernService sovernService;
	
	/**
	 * Parse resume resume extraction response.
	 *
	 * @param file the file
	 * @return the resume extraction response
	 */
	@PostMapping(value = "/resume")
	public ResumeExtractionResponse parseResume(@RequestPart("document") MultipartFile file) {
		log.info("From parse resume function call");
		ResumeExtractionResponse resumeResponse = employeeService.parseResume(file);
		try {
			var sovernParseResponse = sovernService.parse(file);
			LocationDto location = sovernService.parseForLocation(sovernParseResponse);
			Contact contact = sovernService.parseForContactInfo(sovernParseResponse);
			resumeResponse.getExtractedProfile().setLocationObject(location);
			resumeResponse.getExtractedProfile().setContact(contact);
		} catch (Exception ex) {
			log.error("Error in getting location data from sovern", ex);
		}
		return resumeResponse;
	}

}
