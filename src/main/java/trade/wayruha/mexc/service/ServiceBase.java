package trade.wayruha.mexc.service;

import trade.wayruha.mexc.MexcConfig;
import trade.wayruha.mexc.client.ApiClient;

public abstract class ServiceBase {
    protected final ApiClient client;

    public ServiceBase(ApiClient client) {
        this.client = client;
    }

    public ServiceBase(MexcConfig config) {
        this(new ApiClient(config));
    }

    protected <T> T createService(Class<T> apiClass) {
        return client.createService(apiClass);
    }

    protected long now() {
        return System.currentTimeMillis();
    }
}
