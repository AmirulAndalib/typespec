// Code generated by Microsoft (R) TypeSpec Code Generator.

package type.property.valuetypes.implementation;

import io.clientcore.core.annotation.ServiceInterface;
import io.clientcore.core.http.RestProxy;
import io.clientcore.core.http.annotation.BodyParam;
import io.clientcore.core.http.annotation.HeaderParam;
import io.clientcore.core.http.annotation.HostParam;
import io.clientcore.core.http.annotation.HttpRequestInformation;
import io.clientcore.core.http.annotation.UnexpectedResponseExceptionDetail;
import io.clientcore.core.http.exception.HttpResponseException;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.binarydata.BinaryData;
import type.property.valuetypes.DictionaryStringProperty;

/**
 * An instance of this class provides access to all the operations defined in DictionaryStrings.
 */
public final class DictionaryStringsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final DictionaryStringsService service;

    /**
     * The service client containing this operation class.
     */
    private final ValueTypesClientImpl client;

    /**
     * Initializes an instance of DictionaryStringsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    DictionaryStringsImpl(ValueTypesClientImpl client) {
        this.service = RestProxy.create(DictionaryStringsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for ValueTypesClientDictionaryStrings to be used by the proxy service to
     * perform REST calls.
     */
    @ServiceInterface(name = "ValueTypesClientDict", host = "{endpoint}")
    public interface DictionaryStringsService {
        @HttpRequestInformation(
            method = HttpMethod.GET,
            path = "/type/property/value-types/dictionary/string",
            expectedStatusCodes = { 200 })
        @UnexpectedResponseExceptionDetail
        Response<DictionaryStringProperty> getSync(@HostParam("endpoint") String endpoint,
            @HeaderParam("Accept") String accept, RequestOptions requestOptions);

        @HttpRequestInformation(
            method = HttpMethod.PUT,
            path = "/type/property/value-types/dictionary/string",
            expectedStatusCodes = { 204 })
        @UnexpectedResponseExceptionDetail
        Response<Void> putSync(@HostParam("endpoint") String endpoint, @HeaderParam("Content-Type") String contentType,
            @BodyParam("application/json") BinaryData body, RequestOptions requestOptions);
    }

    /**
     * Get call.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     property (Required): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the service returns an error.
     * @return call.
     */
    public Response<DictionaryStringProperty> getWithResponse(RequestOptions requestOptions) {
        final String accept = "application/json";
        return service.getSync(this.client.getEndpoint(), accept, requestOptions);
    }

    /**
     * Put operation.
     * <p><strong>Request Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     property (Required): {
     *         String: String (Required)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param body body.
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the service returns an error.
     * @return the response.
     */
    public Response<Void> putWithResponse(BinaryData body, RequestOptions requestOptions) {
        final String contentType = "application/json";
        return service.putSync(this.client.getEndpoint(), contentType, body, requestOptions);
    }
}
