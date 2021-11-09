package io.skyhive.veeneer.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping("/{id}")
    public Object getEmployee(@PathVariable String id) throws JsonProcessingException {
        return jacksonObjectMapper.readValue(buildEmployee(), Object.class);
    }

    private String buildEmployee() {
        String employee = "{" +
                "    \"resumeParsingKey\": \"string\"," +
                "\"name\":{" +
                "\"firstname\": \"Pierre\"," +
                "\"lastname\": \"Mosquito\"" +
                "}," +
                "\"accountInformation\": {" +
                "\"email\": \"pierre.mo@skyhive.io\"," +
                "\"type\": \"employee\"" +
                "}," +
                "\"location\": \"Vancouver, BC\"," +
                "\"departmentId\": \"5e06af44da9a8e5c0cf2c05b\"," +
                "\"officeId\": \"5c2f37e8a6cb1234306dd51c\"," +
                "\"countryId\": \"5a606d1dcf24617287200757\"," +
                "\"careerId\": \"605e26a836f15635301d3c75\"," +
                "\"careerTitle\": null," +
                "\"jobTitle\": {" +
                "\"id\": null," +
                "\"title\": \"Customer Support Manager\"," +
                "\"definition\": null" +
                "}," +
                "\"positionId\": null," +
                "\"directManagerAccountId\": \"5d004a82a0e58773c5d87d83\"," +
                "\"directManagerName\": \"Which Botulich\"," +
                "\"skills\": [" +
                "{" +
                "\"level\": \"entry\"," +
                "\"skillAcquisitionInfo\": [" +
                "{" +
                "\"source\": \"manager\"," +
                "\"sourceId\": \"5d004a82a0e58773c5d87d83\"," +
                "\"date\": \"2020-10-19T22:56:23Z\"," +
                "\"level\": \"entry\"," +
                "\"previousLevel\": \"\"" +
                "}" +
                "]," +
                "\"skillType\": \"currentJob\"," +
                "\"id\": \"5a99c33dfc0da84a0c16c261\"," +
                "\"title\": \"Sales\"," +
                "\"definition\": \"Sales are activities related to selling or the number of goods sold in a given targeted time period. The delivery of a service for a cost is also considered a sale.\"" +
                "}," +
                "{" +
                "\"level\": \"entry\"," +
                "\"skillAcquisitionInfo\": [" +
                "{" +
                "\"source\": \"manager\"," +
                "\"sourceId\": \"5d004a82a0e58773c5d87d83\"," +
                "\"date\": \"2020-10-19T22:56:23Z\"," +
                "\"level\": \"entry\"," +
                "\"previousLevel\": \"\"" +
                "}" +
                "]," +
                "\"skillType\": \"currentJob\"," +
                "\"id\": \"5a99c2dffc0da84a0c16c117\"," +
                "\"title\": \"Management\"," +
                "\"definition\": \"Management is the administration of an organization, whether it is a business, a not-for-profit organization, or government body.\"" +
                "}," +
                "{" +
                "\"level\": \"entry\"," +
                "\"skillAcquisitionInfo\": [" +
                "{" +
                "\"source\": \"manager\"," +
                "\"sourceId\": \"5d004a82a0e58773c5d87d83\"," +
                "\"date\": \"2020-10-19T22:56:23Z\"," +
                "\"level\": \"entry\"," +
                "\"previousLevel\": \"\"" +
                "}" +
                "]," +
                "\"skillType\": \"currentJob\"," +
                "\"id\": \"5bea124b3d514b720cb01ff6\"," +
                "\"title\": \"Responsibility\"," +
                "\"definition\": \"being accountable and acting independently to make decisions without authorization\"" +
                "}" +
                "]," +
                "\"roles\": [" +
                "\"enterpriseSuperadmin\"" +
                "]," +
                "\"dateHired\": \"2020-10-19T22:56:23Z\"," +
                "\"registrationDate\": \"2020-10-05T22:56:53Z\"," +
                "\"preferences\": {" +
                "\"compensation\": {" +
                "\"ratePerHour\": null," +
                "\"annualSalary\": null," +
                "\"noMinimum\": true," +
                "\"currency\": null" +
                "}," +
                "\"remote\": \"none\"," +
                "\"relocate\": \"none\"," +
                "\"isPrivate\": false" +
                "}," +
                "\"studies\": [" +
                "{" +
                "\"institution\": {" +
                "\"id\": \"5cb4ed11e5b7c842e7273158\"," +
                "\"title\": \"The University of British Columbia\"," +
                "\"definition\": null" +
                "}," +
                "\"areaOfStudy\": {" +
                "\"id\": \"5a72f8a440dbac1fbc0aa087\"," +
                "\"title\": \"Marketing\"," +
                "\"definition\": null" +
                "}," +
                "\"degree\": {" +
                "\"id\": \"5a7cd00f88e7f43784122dd5\"," +
                "\"title\": \"Associate's Degree\"," +
                "\"definition\": null" +
                "}," +
                "\"startDate\": \"1992-09-01T00:00:00Z\"," +
                "\"endDate\": \"1996-06-01T00:00:00Z\"," +
                "\"isStudyNotCompleted\": null," +
                "\"noDatesProvided\": false," +
                "\"current\": false" +
                "}" +
                "]," +
                "\"trainings\": [" +
                "" +
                "]," +
                "\"licenses\": [" +
                "" +
                "]," +
                "\"patents\": null," +
                "\"publications\": null," +
                "\"hobbies\": [" +
                "{" +
                "\"id\": \"5c61b7d80311ec042102af36\"," +
                "\"title\": \"Trail Running\"," +
                "\"definition\": null" +
                "}," +
                "{" +
                "\"id\": \"5a72f2a90a38e025607de7d9\"," +
                "\"title\": \"Kayaking\"," +
                "\"definition\": null" +
                "}" +
                "]," +
                "\"experience\": [" +
                "{" +
                "\"occupation\": {" +
                "\"id\": null," +
                "\"title\": \"Customer Service Aftersales Supervisor\"," +
                "\"skillIds\": null" +
                "}," +
                "\"company\": \"Cisco\"," +
                "\"startDate\": \"2019-10-01T00:00:00Z\"," +
                "\"endDate\": \"2020-10-01T00:00:00Z\"," +
                "\"current\": false," +
                "\"description\": \"Supervise and coordinate the day-to-day operations of the Customer Service Aftersales department\"" +
                "}," +
                "{" +
                "\"occupation\": {" +
                "\"id\": null," +
                "\"titleValue\": null," +
                "\"title\": \"Worldwide Customer Experience Product Support Manager\"," +
                "\"skillIds\": null" +
                "}," +
                "\"company\": \"Mindscape\"," +
                "\"startDate\": \"2010-08-01T00:00:00Z\"," +
                "\"endDate\": \"2019-03-01T00:00:00Z\"," +
                "\"current\": false," +
                "\"description\": \"Managed a team of game product experts both on-site and US location\"" +
                "}" +
                "]," +
                "\"title\": \"Sales Account Manager\"," +
                "\"privacyPolicyAccepted\": true," +
                "\"dateStarted\": \"2021-03-26T18:23:36.427Z\"," +
                "\"dateCompleted\": null" +
                "}";
        return employee;
    }
}
