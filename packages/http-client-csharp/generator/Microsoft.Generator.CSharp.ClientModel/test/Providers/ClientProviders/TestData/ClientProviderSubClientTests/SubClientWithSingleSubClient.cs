// <auto-generated/>

#nullable disable

using System.Threading;

namespace sample.namespace
{
    /// <summary></summary>
    public partial class Dog
    {
        /// <summary> Initializes a new instance of Husky. </summary>
        public virtual global::sample.namespace.Husky GetHuskyClient()
        {
            return (global::System.Threading.Volatile.Read(ref _cachedHusky) ?? (global::System.Threading.Interlocked.CompareExchange(ref _cachedHusky, new global::sample.namespace.Husky(), null) ?? _cachedHusky));
        }
    }
}