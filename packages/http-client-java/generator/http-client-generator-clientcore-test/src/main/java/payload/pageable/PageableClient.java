// Code generated by Microsoft (R) TypeSpec Code Generator.

package payload.pageable;

import io.clientcore.core.annotation.Metadata;
import io.clientcore.core.annotation.ServiceClient;
import io.clientcore.core.http.exception.HttpResponseException;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import payload.pageable.implementation.ServerDrivenPaginationsImpl;
import payload.pageable.serverdrivenpagination.LinkResponse;

/**
 * Initializes a new instance of the synchronous PageableClient type.
 */
@ServiceClient(builder = PageableClientBuilder.class)
public final class PageableClient {
    @Metadata(generated = true)
    private final ServerDrivenPaginationsImpl serviceClient;

    /**
     * Initializes an instance of PageableClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Metadata(generated = true)
    PageableClient(ServerDrivenPaginationsImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * The link operation.
     * <p><strong>Response Body Schema</strong></p>
     * 
     * <pre>
     * {@code
     * {
     *     pets (Required): [
     *          (Required){
     *             id: String (Required)
     *             name: String (Required)
     *         }
     *     ]
     *     links (Required): {
     *         next: String (Optional)
     *         prev: String (Optional)
     *         first: String (Optional)
     *         last: String (Optional)
     *     }
     * }
     * }
     * </pre>
     * 
     * @param requestOptions The options to configure the HTTP request before HTTP client sends it.
     * @throws HttpResponseException thrown if the service returns an error.
     * @return the response.
     */
    @Metadata(generated = true)
    public Response<LinkResponse> linkWithResponse(RequestOptions requestOptions) {
        return this.serviceClient.linkWithResponse(requestOptions);
    }

    /**
     * The link operation.
     * 
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Metadata(generated = true)
    public LinkResponse link() {
        // Generated convenience method for linkWithResponse
        RequestOptions requestOptions = new RequestOptions();
        return linkWithResponse(requestOptions).getValue();
    }
}
