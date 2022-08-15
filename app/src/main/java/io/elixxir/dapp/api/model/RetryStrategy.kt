package io.elixxir.dapp.api.model

interface RetryStrategy {
    val maxRetries: Int
    val retryDelayMs: Long
}

/**
 * Remote data that is critical to the function of the SDK.
 * Generally means many retries and reasonable delay to not
 * overwhelm network resources.
 */
data class CriticalRemoteDataStrategy(
    override val maxRetries: Int = 29,
    override val retryDelayMs: Long = 1000L
) : RetryStrategy