package io.skyhive.veneer.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.skyhive.veneer.common.entities.VeneerMapping;
import io.skyhive.veneer.common.entities.account.LoginRequest;
import io.skyhive.veneer.common.entities.account.UserDto;
import io.skyhive.veneer.common.rest.SkyhiveLogin;
import io.skyhive.veneer.common.rest.cachable.CachableMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Feign request interceptor.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final String API_KEY = "X-API-Key";
    private static final String ENTERPRISE_ID_KEY = "X-Enterprise-Id";
    private static final String MAPPED_ENTERPRISE_ID_KEY = "X-Enterprise-Id";

    @Autowired
    private CachableMappingRepository cachableMappingRepository;

    @Autowired
    private SkyhiveLogin skyhiveLogin;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String lhhEnterpriseId = request.getHeader(MAPPED_ENTERPRISE_ID_KEY);
        VeneerMapping userDetails = cachableMappingRepository.findByTypeAndExternalId("User", lhhEnterpriseId);

        String skyhiveEnterpriseId = cachableMappingRepository.findByTypeAndExternalId("Enterprise", lhhEnterpriseId).getSkyhiveId();
        UserDto loggedInUser = skyhiveLogin.authenticateUser(new LoginRequest(userDetails.getUserName(), userDetails.getPassword()));
        requestTemplate.header(API_KEY, loggedInUser.getApiKey());
        requestTemplate.header(ENTERPRISE_ID_KEY, skyhiveEnterpriseId);
    }
}
