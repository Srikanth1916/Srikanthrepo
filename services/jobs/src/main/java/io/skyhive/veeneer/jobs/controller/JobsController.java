package io.skyhive.veeneer.jobs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobsController {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping("/{id}")
    public Object getJobs(@PathVariable String id) throws JsonProcessingException {
        return jacksonObjectMapper.readValue(buildJobs(), Object.class);
    }

    @PostMapping("/search")
    public Object searchJobs(Object requestObject) throws JsonProcessingException {
        return jacksonObjectMapper.readValue(searchJobs(), Object.class);
    }

    private String searchJobs() {
        String searchJobs = "{" +
                "  \"page\": 1," +
                "  \"searchToken\": \"4cdd2917e8404aa7a78fa19a8e9420cd\"," +
                "  \"resultsPerPage\": 20," +
                "  \"totalResults\": 1," +
                "  \"sortBy\": \"matchrate\"," +
                "  \"searchResults\":[{" +
                "  \"id\": \"f0cad02bbecca613191eddd\"," +
                "  \"matchrate\": 80," +
                "\"distance\": 0," +
                "\"country\": {" +
                "\"twoLetterName\": \"CA\"," +
                "\"currencySymbol\": \"$\"," +
                "\"currencyCode\": \"CAD\"," +
                "\"title\": \"Canadian Dollar\"" +
                "}," +
                "\"details\": {" +
                "\"title\": \"Project manager\"," +
                "\"language\": \"en-ca\"," +
                "\"type\": \"fulltime\"," +
                "\"departmentId\": \"\"," +
                "\"skillLevel\": \"experienced\"," +
                "\"skills\": [" +
                "{" +
                "\"requirement\": \"required\"," +
                "\"id\": \"5a99c5bffc0da84a0c16c6b8\"" +
                "}," +
                "{" +
                "\"requirement\": \"required\"," +
                "\"id\": \"5a99ebc9fc0da84a0c170e3e\"" +
                "}," +
                "{" +
                "\"requirement\": \"optional\"," +
                "\"id\": \"5baec51e25d3ac67648c2f71\"" +
                "}," +
                "{" +
                "\"requirement\": \"optional\"," +
                "\"id\": \"5c352aaceebc546524b704b5\"" +
                "}," +
                "{" +
                "\"requirement\": \"optional\"," +
                "\"id\": \"5d82d3d4042fc1114f75d61d\"" +
                "}" +
                "]," +
                "\"description\": \"This is a job offer we created for the API\"," +
                "\"referenceCode\": \"string\"," +
                "\"referencePlatform\": \"enterprise\"" +
                "}," +
                "\"locationTime\": {" +
                "\"location\": {" +
                "\"formattedAddress\": \"333 Seymour Street, Vancouver, BC V6B 5A7, Canada\"," +
                "\"addressLine1\": \"333 Seymour Street\"," +
                "\"addressLine2\": \"\"," +
                "\"municipality\": \"Vancouver\", " +
                "\"postcode\": \"V6B 5A7\"," +
                "\"country\": \"Canada\"," +
                "\"country2\": \"CA\"," +
                "\"country3\": \"CAN\"," +
                "\"state\": \"British Columbia\"," +
                "\"stateShort\": \"BC\"" +
                "}," +
                "\"extraInstructions\": null," +
                "\"remote\": true," +
                "\"type\": \"asap\"," +
                "\"startDate\": \"2021-08-30T00:00:00.000Z\"," +
                "\"endDate\": \"2022-08-30T00:00:00.000Z\"," +
                "\"startHour\": 0," +
                "\"duration\": \"hours\"," +
                "\"quantity\": 0" +
                "}," +
                "\"compensation\": {" +
                "\"type\": \"salary\"," +
                "\"payRate\": 100000.00," +
                "\"currency\": \"CAD\"," +
                "\"duration\": \"hours\"," +
                "\"quantity\": 40," +
                "\"hoursPerWeek\": 40," +
                "\"benefits\": true," +
                "\"hidden\": true," +
                "\"relocate\": \"noRelocation\"" +
                "}," +
                "\"jobHistory\": [" +
                "{" +
                "\"date\": \"2020-08-29T20:00:00.000Z\"," +
                "\"state\": \"draft\"" +
                "}," +
                "{" +
                "\"date\": \"2020-08-29T20:00:01.000Z\"," +
                "\"state\": \"published\"" +
                "}" +
                "]" +
                "  }]" +
                "}";
        return searchJobs;
    }

    private String buildJobs() {
        String jobs = "{" +
                "    \"id\": \"f0cad02bbecca613191eddd\"," +
                "\"country\": {" +
                "\"twoLetterName\": \"CA\"," +
                "\"currencySymbol\": \"$\"," +
                "\"currencyCode\": \"CAD\"," +
                "\"title\": \"Canadian Dollar\"" +
                "}," +
                "\"details\": {" +
                "\"title\": \"Project manager\"," +
                "\"language\": \"en-ca\"," +
                "\"type\": \"fulltime\"," +
                "\"departmentId\": \"\"," +
                "\"skillLevel\": \"experienced\"," +
                "\"skills\": [" +
                "{" +
                "\"requirement\": \"required\"," +
                "\"id\": \"5a99c5bffc0da84a0c16c6b8\"" +
                "}," +
                "{" +
                "\"requirement\": \"required\"," +
                "\"id\": \"5a99ebc9fc0da84a0c170e3e\"" +
                "}," +
                "{" +
                "\"requirement\": \"optional\"," +
                "\"id\": \"5baec51e25d3ac67648c2f71\"" +
                "}" +
                "]," +
                "\"description\": \"This is a job offer we created for the API\"," +
                "\"referenceCode\": \"string\"," +
                "\"referencePlatform\": \"enterprise\"" +
                "}," +
                "\"locationTime\": {" +
                "\"location\": {" +
                "\"formattedAddress\": \"333 Seymour Street, Vancouver, BC V6B 5A7, Canada\"," +
                "\"addressLine1\": \"333 Seymour Street\"," +
                "\"addressLine2\": \"\"," +
                "\"municipality\": \"Vancouver\", " +
                "\"postcode\": \"V6B 5A7\"," +
                "\"country\": \"Canada\"," +
                "\"country2\": \"CA\"," +
                "\"country3\": \"CAN\"," +
                "\"state\": \"British Columbia\"," +
                "\"stateShort\": \"BC\"" +
                "}," +
                "\"extraInstructions\": null," +
                "\"remote\": true," +
                "\"type\": \"asap\"," +
                "\"startDate\": \"2021-08-30T00:00:00.000Z\"," +
                "\"endDate\": \"2022-08-30T00:00:00.000Z\"," +
                "\"startHour\": 0," +
                "\"duration\": \"hours\"," +
                "\"quantity\": 0" +
                "}," +
                "\"compensation\": {" +
                "\"type\": \"salary\"," +
                "\"payRate\": 100000.00," +
                "\"currency\": \"CAD\"," +
                "\"duration\": \"hours\"," +
                "\"quantity\": 40," +
                "\"hoursPerWeek\": 40," +
                "\"benefits\": true," +
                "\"hidden\": true," +
                "\"relocate\": \"noRelocation\"" +
                "}," +
                "\"jobHistory\": [" +
                "{" +
                "\"date\": \"2020-08-29T20:00:00.000Z\"," +
                "\"state\": \"draft\"" +
                "}," +
                "{" +
                "\"date\": \"2020-08-29T20:00:01.000Z\"," +
                "\"state\": \"published\"" +
                "}" +
                "]" +
                "}";
        return jobs;
    }

}
