package io.skyhive.veneer.common.services;

import io.skyhive.veneer.common.db.mongo.entity.DictionaryWithComplexity;
import io.skyhive.veneer.common.jpa.DictionaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author krishna
 * @created 21/12/21
 * @project skyhive-veeneer
 */
@Service
@Slf4j
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    @Autowired
    public DictionaryService(@Lazy DictionaryRepository dictionaryRepository){
        this.dictionaryRepository = dictionaryRepository;
    }

    public Map<String, DictionaryWithComplexity> getByIds(Set<String> ids){
        List<DictionaryWithComplexity> dictionaryWithComplexities =
                dictionaryRepository.findByIdIn(ids);
        log.info("Response of getByIds from MongoDB "+dictionaryWithComplexities);
        return dictionaryWithComplexities.stream().collect(Collectors.toMap(DictionaryWithComplexity::getId, Function.identity()));
    }

}
