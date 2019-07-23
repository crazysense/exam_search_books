package myyuk.exam.component;

import myyuk.exam.model.ApiRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "search-books")
public class SearchBooksApiProvider {
    @NestedConfigurationProperty
    private List<ApiRequest> apis;

    public List<ApiRequest> getApis() {
        return apis;
    }

    public void setApis(List<ApiRequest> apis) {
        this.apis = apis;
    }
}
