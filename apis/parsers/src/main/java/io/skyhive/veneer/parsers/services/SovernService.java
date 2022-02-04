package io.skyhive.veneer.parsers.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sovren.DataCenter;
import com.sovren.SovrenClient;
import com.sovren.exceptions.SovrenException;
import com.sovren.models.Document;
import com.sovren.models.api.parsing.ParseOptions;
import com.sovren.models.api.parsing.ParseRequest;
import com.sovren.models.api.parsing.ParseResumeResponse;

import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.rest.LocationRepository;
import io.skyhive.veneer.parsers.dto.employee.Contact;

/**
 * The type Sovern service.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Component
public class SovernService {

    @Value("${accountId}")
    private String accountId;

    @Value("${serviceKey}")
    private String serviceKey;

    private SovrenClient client;
    private LocationRepository locationService;

    /**
     * Instantiates a new Sovern service.
     *
     * @param locationService the location service
     */
    @Autowired
    public SovernService(LocationRepository locationService) {
        this.locationService = locationService;
    }

    /**
     * Initalize.
     */
    @PostConstruct
    public void initalize() {
        this.client = new SovrenClient(accountId, serviceKey, DataCenter.EU);
    }

    /**
     * Parse parse resume response.
     *
     * @param file the file
     * @return the parse resume response
     * @throws IOException     the io exception
     * @throws SovrenException the sovren exception
     */
    public ParseResumeResponse parse(MultipartFile file) throws IOException, SovrenException {
        Document document = new Document(file.getBytes(), LocalDate.now());
        ParseRequest request = new ParseRequest(document, new ParseOptions());
        ParseResumeResponse response = this.client.parseResume(request);
        return response;
    }

    /**
     * Parse for location location.
     *
     * @param response the response
     * @return the location
     */
    public LocationDto parseForLocation(ParseResumeResponse response) {
        return getLocation(response.Value.ResumeData.ContactInformation.Location);
    }

    /**
     * Parse for contact info contact.
     *
     * @param response the response
     * @return the contact
     */
    public Contact parseForContactInfo(ParseResumeResponse response) {
        var contactInfo = response.Value.ResumeData.ContactInformation;
        Contact contact = Contact.builder().email(contactInfo.EmailAddresses)
                .phone(contactInfo.Telephones.stream().map(tele -> tele.Normalized).collect(Collectors.toList())).build();
        return contact;
    }

    private LocationDto getLocation(com.sovren.models.Location location) {
        LocationDto skyhiveLocation = new LocationDto();
        if (location.GeoCoordinates != null) {
            skyhiveLocation = locationService.getLocation(location.GeoCoordinates.Latitude, location.GeoCoordinates.Latitude);
        } else if (location.StreetAddressLines != null) {
            skyhiveLocation = locationService.getLocation(location.CountryCode, Stream.of(String.join(", ", location.StreetAddressLines), location.Municipality, location.PostalCode)
                    .filter(s -> StringUtils.isNotEmpty(s))
                    .collect(Collectors.joining(",")));
        }
        skyhiveLocation.setCountry(location.CountryCode);
        return skyhiveLocation;
    }
}

