// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT License.

// <auto-generated/>

#nullable disable

using System;
using System.ClientModel;
using System.ClientModel.Primitives;

namespace SampleTypeSpec
{
    /// <summary></summary>
    public partial class SampleTypeSpecClient
    {
        private static PipelineMessageClassifier _pipelineMessageClassifier200;
        private static PipelineMessageClassifier _pipelineMessageClassifier204;

        private static PipelineMessageClassifier PipelineMessageClassifier200 => _pipelineMessageClassifier200 = PipelineMessageClassifier.Create(stackalloc ushort[] { 200 });

        private static PipelineMessageClassifier PipelineMessageClassifier204 => _pipelineMessageClassifier204 = PipelineMessageClassifier.Create(stackalloc ushort[] { 204 });

        internal PipelineMessage CreateSayHiRequest(string headParameter, string queryParameter, string optionalQuery, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/hello", false);
            uri.AppendQuery("queryParameter", queryParameter, true);
            if (optionalQuery != null)
            {
                uri.AppendQuery("optionalQuery", optionalQuery, true);
            }
            request.Uri = uri.ToUri();
            request.Headers.Set("head-parameter", headParameter);
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateHelloAgainRequest(string p2, string p1, BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/againHi/", false);
            uri.AppendPath(p2, true);
            request.Uri = uri.ToUri();
            request.Headers.Set("p1", p1);
            request.Headers.Set("Content-Type", "text/plain");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateNoContentTypeRequest(string p2, string p1, BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/noContentType/", false);
            uri.AppendPath(p2, true);
            request.Uri = uri.ToUri();
            request.Headers.Set("p1", p1);
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateHelloDemo2Request(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/demoHi", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateCreateLiteralRequest(BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/literal", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateHelloLiteralRequest(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/helloLiteral/", false);
            uri.AppendPath(123.ToString(), true);
            uri.AppendQuery("p3", TypeFormatters.ConvertToString(true, null), true);
            request.Uri = uri.ToUri();
            request.Headers.Set("p1", "test");
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateTopActionRequest(DateTimeOffset action, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/top/", false);
            uri.AppendPath(action.ToString("O"), true);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateTopAction2Request(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/top2", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreatePatchActionRequest(BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "PATCH";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/patch", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateAnonymousBodyRequest(BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/anonymousBody", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateFriendlyModelRequest(BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/friendlyName", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateAddTimeHeaderRequest(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier204;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Repeatability-First-Sent", TypeFormatters.ConvertToString(DateTimeOffset.Now, "R"));
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateProjectedNameModelRequest(BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/projectedName", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateReturnsAnonymousModelRequest(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/returnsAnonymousModel", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateGetUnknownValueRequest(string accept, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/unknown-value", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", accept);
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateInternalProtocolRequest(BinaryContent content, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/internalProtocol", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Content-Type", "application/json");
            request.Headers.Set("Accept", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateStillConvenientRequest(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier204;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/stillConvenient", false);
            request.Uri = uri.ToUri();
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateHeadAsBooleanRequest(string id, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier204;
            PipelineRequest request = message.Request;
            request.Method = "HEAD";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/headAsBoolean/", false);
            uri.AppendPath(id, true);
            request.Uri = uri.ToUri();
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateWithApiVersionRequest(string p1, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier204;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/WithApiVersion", false);
            uri.AppendQuery("apiVersion", _apiVersion, true);
            request.Uri = uri.ToUri();
            request.Headers.Set("p1", p1);
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateGetWithNextLinkRequest(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/link", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateNextGetWithNextLinkRequest(Uri nextPage, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(nextPage);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateGetWithContinuationTokenRequest(string token, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/continuation", false);
            if (token != null)
            {
                uri.AppendQuery("token", token, true);
            }
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateGetWithContinuationTokenHeaderResponseRequest(string token, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/continuation/header", false);
            if (token != null)
            {
                uri.AppendQuery("token", token, true);
            }
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateGetWithPagingRequest(RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier200;
            PipelineRequest request = message.Request;
            request.Method = "GET";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/list/paging", false);
            request.Uri = uri.ToUri();
            request.Headers.Set("Accept", "application/json");
            message.Apply(options);
            return message;
        }

        internal PipelineMessage CreateEmbeddedParametersRequest(string requiredHeader, string requiredQuery, BinaryContent content, string optionalHeader, string optionalQuery, RequestOptions options)
        {
            PipelineMessage message = Pipeline.CreateMessage();
            message.ResponseClassifier = PipelineMessageClassifier204;
            PipelineRequest request = message.Request;
            request.Method = "POST";
            ClientUriBuilder uri = new ClientUriBuilder();
            uri.Reset(_endpoint);
            uri.AppendPath("/embeddedParameters", false);
            uri.AppendQuery("requiredQuery", requiredQuery, true);
            if (optionalQuery != null)
            {
                uri.AppendQuery("optionalQuery", optionalQuery, true);
            }
            request.Uri = uri.ToUri();
            request.Headers.Set("required-header", requiredHeader);
            if (optionalHeader != null)
            {
                request.Headers.Set("optional-header", optionalHeader);
            }
            request.Headers.Set("Content-Type", "application/json");
            request.Content = content;
            message.Apply(options);
            return message;
        }
    }
}
