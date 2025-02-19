// <auto-generated/>

#nullable disable

using System.ClientModel;
using System.ClientModel.Primitives;
using System.Threading;
using System.Threading.Tasks;

namespace _Type.Scalar
{
    public partial class DecimalType
    {
        protected DecimalType() => throw null;

        public ClientPipeline Pipeline => throw null;

        public virtual ClientResult ResponseBody(RequestOptions options) => throw null;

        public virtual Task<ClientResult> ResponseBodyAsync(RequestOptions options) => throw null;

        public virtual ClientResult<decimal> ResponseBody(CancellationToken cancellationToken = default) => throw null;

        public virtual Task<ClientResult<decimal>> ResponseBodyAsync(CancellationToken cancellationToken = default) => throw null;

        public virtual ClientResult RequestBody(BinaryContent content, RequestOptions options = null) => throw null;

        public virtual Task<ClientResult> RequestBodyAsync(BinaryContent content, RequestOptions options = null) => throw null;

        public virtual ClientResult RequestBody(decimal body, CancellationToken cancellationToken = default) => throw null;

        public virtual Task<ClientResult> RequestBodyAsync(decimal body, CancellationToken cancellationToken = default) => throw null;

        public virtual ClientResult RequestParameter(decimal value, RequestOptions options) => throw null;

        public virtual Task<ClientResult> RequestParameterAsync(decimal value, RequestOptions options) => throw null;

        public virtual ClientResult RequestParameter(decimal value, CancellationToken cancellationToken = default) => throw null;

        public virtual Task<ClientResult> RequestParameterAsync(decimal value, CancellationToken cancellationToken = default) => throw null;
    }
}
