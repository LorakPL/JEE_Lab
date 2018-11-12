package pl.gda.pg.eti.kask.javaee.jsf.api;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Map;

public class UriUtils {
    public static URI uri(Class<?> clazz, String method, Object... vals) {
        return UriBuilder.fromResource(clazz)
                .path(clazz, method)
                .build(vals);
    }

    public static URI uriWithParams(Class<?> clazz, String method, Map<String, String> params) {
        UriBuilder uriBuilder = UriBuilder.fromResource(clazz);
        //uriBuilder.fromResource(clazz);
        uriBuilder.path(clazz, method);
        for(Map.Entry<String, String> map : params.entrySet()) {
            uriBuilder.queryParam(map.getKey(), map.getValue());
        }
        //uriBuilder.build();
        return uriBuilder.build();
    }
}
