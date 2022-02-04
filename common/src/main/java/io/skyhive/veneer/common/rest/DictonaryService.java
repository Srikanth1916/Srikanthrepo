package io.skyhive.veneer.common.rest;

import io.skyhive.veneer.common.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * The interface Dictonary service.
 *
 * @author krishna
 * @created 01 /11/21
 * @project skyhive -veeneer
 */
@FeignClient(name = "dictonary", url = "${enterprise.api.url}", configuration = FeignSupportConfig.class)
public interface DictonaryService {
    /**
     * Gets dictonary values.
     *
     * @param ids the ids
     * @return the dictonary values
     */
//    @PostMapping(value = "/api/v1.0/dictionary/ByIds",
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    DictonaryServiceResponse getDictonaryValues(@RequestBody String[] ids);

}