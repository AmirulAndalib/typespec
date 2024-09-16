// <auto-generated/>

#nullable disable

using System;
using System.ClientModel.Primitives;
using System.Text.Json;

namespace sample.namespace.Models
{
    /// <summary></summary>
    public partial class TestModel : global::System.ClientModel.Primitives.IJsonModel<global::sample.namespace.Models.TestModel>
    {
        /// <param name="writer"> The JSON writer. </param>
        /// <param name="options"> The client options for reading and writing models. </param>
        protected virtual void JsonModelWriteCore(global::System.Text.Json.Utf8JsonWriter writer, global::System.ClientModel.Primitives.ModelReaderWriterOptions options)
        {
            string format = (options.Format == "W") ? ((global::System.ClientModel.Primitives.IPersistableModel<global::sample.namespace.Models.TestModel>)this).GetFormatFromOptions(options) : options.Format;
            if ((format != "J"))
            {
                throw new global::System.FormatException($"The model {nameof(global::sample.namespace.Models.TestModel)} does not support writing '{format}' format.");
            }
            writer.WritePropertyName("camelCase"u8);
            writer.WriteStringValue(CamelCase);
            if (((options.Format != "W") && (_additionalBinaryDataProperties != null)))
            {
                foreach (var item in _additionalBinaryDataProperties)
                {
                    writer.WritePropertyName(item.Key);
#if NET6_0_OR_GREATER
                    writer.WriteRawValue(item.Value);
#else
                    using (global::System.Text.Json.JsonDocument document = global::System.Text.Json.JsonDocument.Parse(item.Value))
                    {
                        global::System.Text.Json.JsonSerializer.Serialize(writer, document.RootElement);
                    }
#endif
                }
            }
        }
    }
}
