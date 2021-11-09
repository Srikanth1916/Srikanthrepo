package io.skyhive.veeneer.docs.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "springdoc.swagger-ui.services")
public class ServiceProperties {
    private List<Service> urls;

    public List<Service> getUrls() {
        return urls;
    }

    public void setUrls(List<Service> urls) {
        this.urls = urls;
    }

    public static class Service{
        private String url;
        private String path;
        private String name;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
