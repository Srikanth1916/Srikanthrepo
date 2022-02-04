package io.skyhive.veneer.typeahead.controller;

import io.skyhive.veneer.typeahead.dto.DictionaryType;
import io.skyhive.veneer.typeahead.dto.TypeaheadResponse;
import io.skyhive.veneer.typeahead.mongo.Dictionary;
import io.skyhive.veneer.typeahead.services.DictionaryCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Type Training Controller.
 *
 * @author jayaram
 * @created 03/12/21
 * @project skyhive -veeneer
 */
@RestController
@Slf4j
public class TypeaheadController {
    private final DictionaryCrudRepository dictionaryCrudRepository;
    @Autowired
    public TypeaheadController(DictionaryCrudRepository dictionaryCrudRepository) {
        this.dictionaryCrudRepository = dictionaryCrudRepository;
    }
    @GetMapping("/certification")
    public Mono<TypeaheadResponse> certification(@RequestParam(
            "query") String query, @RequestParam(value = "limit", defaultValue = "5") int limit){
        Pageable pageable = Pageable.ofSize(limit);
        return dictionaryCrudRepository.findByQuery(query,
                DictionaryType.Certification.getValue(), pageable).collectList().map(dictionaries ->
            new TypeaheadResponse(dictionaries)
        );
    }

    @GetMapping("/jobtitle")
    public Mono<TypeaheadResponse> jobTitle(@RequestParam("query") String query,
                                     @RequestParam(value = "limit", defaultValue = "5") int limit){
        Pageable pageable = Pageable.ofSize(limit);
        return dictionaryCrudRepository.findByQuery(query,
                DictionaryType.JobTitle.getValue(), pageable).collectList().map(dictionaries ->
                new TypeaheadResponse(dictionaries)
        );
    }

    @GetMapping("/institution")
    public Mono<TypeaheadResponse> institution(@RequestParam("query") String query,
                                            @RequestParam(value = "limit", defaultValue = "5") int limit){
        Pageable pageable = Pageable.ofSize(limit);
        return dictionaryCrudRepository.findByQuery(query,
                DictionaryType.Institution.getValue(), pageable).collectList().map(dictionaries ->
                new TypeaheadResponse(dictionaries)
        );
    }

    @GetMapping("/degree")
    public Mono<TypeaheadResponse> degree(@RequestParam("query") String query,
                                               @RequestParam(value = "limit", defaultValue = "5") int limit){
        Pageable pageable = Pageable.ofSize(limit);
        return dictionaryCrudRepository.findByQuery(query,
                DictionaryType.DegreeType.getValue(), pageable).collectList().map(dictionaries ->
                new TypeaheadResponse(dictionaries)
        );
    }


    @GetMapping("/hobby")
    public Mono<TypeaheadResponse> hobby(@RequestParam("query") String query,
                                          @RequestParam(value = "limit", defaultValue = "5") int limit){
        Pageable pageable = Pageable.ofSize(limit);
        return dictionaryCrudRepository.findByQuery(query,
                DictionaryType.Hobby.getValue(), pageable).collectList().map(dictionaries ->
                new TypeaheadResponse(dictionaries)
        );
    }

    @GetMapping("/skills")
    public Mono<TypeaheadResponse> skills(@RequestParam("query") String query,
                                         @RequestParam(value = "limit", defaultValue = "5") int limit){
        Pageable pageable = Pageable.ofSize(limit);
        return dictionaryCrudRepository.findByQuery(query,
                DictionaryType.Skill.getValue(), pageable).collectList().map(dictionaries ->
                new TypeaheadResponse(dictionaries)
        );
    }

}
