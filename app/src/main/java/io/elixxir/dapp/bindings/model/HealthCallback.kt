package io.elixxir.dapp.bindings.model

internal interface NetworkHealthListener {
    fun onHealthChanged(isHealthy: Boolean)
}

internal class HealthCallbackAdapter(
    private val listener: NetworkHealthListener
) : NetworkHealthListener by listener,
    bindings.NetworkHealthCallback
{
    override fun callback(isHealthy: Boolean) {
        onHealthChanged(isHealthy)
    }

    companion object {
        val placeholder: HealthCallbackAdapter =
            HealthCallbackAdapter(
                object : NetworkHealthListener {
                    override fun onHealthChanged(isHealthy: Boolean) { }
                }
            )
    }
}