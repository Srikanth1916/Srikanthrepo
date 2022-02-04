package io.skyhive.veneer.employee.services;

import org.springframework.stereotype.Component;

/**
 * The type Sovern service.
 *
 * @author krishna
 * @created 15 /11/21
 * @project skyhive -veeneer
 */
@Component
public class SovernService {

//    @Value("${accountId}")
//    private String accountId;
//
//    @Value("${serviceKey}")
//    private String serviceKey;
//
//    private SovrenClient client;
//    private LocationService locationService;
//
//    /**
//     * Instantiates a new Sovern service.
//     *
//     * @param locationService the location service
//     */
//    @Autowired
//    public SovernService(LocationService locationService) {
//        this.locationService = locationService;
//    }
//
//    /**
//     * Initalize.
//     */
//    @PostConstruct
//    public void initalize() {
//        this.client = new SovrenClient(accountId, serviceKey, DataCenter.EU);
//    }
//
//    /**
//     * Parse parse resume response.
//     *
//     * @param file the file
//     * @return the parse resume response
//     * @throws IOException     the io exception
//     * @throws SovrenException the sovren exception
//     */
//    public ParseResumeResponse parse(MultipartFile file) throws IOException, SovrenException {
//        Document document = new Document(file.getBytes(), LocalDate.now());
//        ParseRequest request = new ParseRequest(document, new ParseOptions());
//        ParseResumeResponse response = this.client.parseResume(request);
//        return response;
//    }
//
//    /**
//     * Parse for location location.
//     *
//     * @param response the response
//     * @return the location
//     */
//    public Location parseForLocation(ParseResumeResponse response) {
//        return getLocation(response.Value.ResumeData.ContactInformation.Location);
//    }
//
//    /**
//     * Parse for contact info contact.
//     *
//     * @param response the response
//     * @return the contact
//     */
//    public Contact parseForContactInfo(ParseResumeResponse response) {
//        var contactInfo = response.Value.ResumeData.ContactInformation;
//        Contact contact = Contact.builder().email(contactInfo.EmailAddresses)
//                .phone(contactInfo.Telephones.stream().map(tele -> tele.Normalized).collect(Collectors.toList())).build();
//        return contact;
//    }
//
//    private Location getLocation(com.sovren.models.Location location) {
//        Location skyhiveLocation = new Location();
//        if (location.GeoCoordinates != null) {
//            skyhiveLocation = locationService.getLocation(location.GeoCoordinates.Latitude, location.GeoCoordinates.Latitude);
//        } else if (location.StreetAddressLines != null) {
//            skyhiveLocation = locationService.getLocation(location.CountryCode, Stream.of(String.join(", ", location.StreetAddressLines), location.Municipality, location.PostalCode)
//                    .filter(s -> StringUtils.isNotEmpty(s))
//                    .collect(Collectors.joining(",")));
//            skyhiveLocation.setAddressLine1(String.join(", ", location.StreetAddressLines));
//        }
//        skyhiveLocation.setCountry2(location.CountryCode);
//        return skyhiveLocation;
//    }
}
