package io.elixxir.xxclient.callbacks

import bindings.Processor as ProcessorBindings

interface E2eProcessor {
    val name: String

    fun process(
        message: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    )
}

open class ProcessorAdapter(
    protected val processor: E2eProcessor
) : ProcessorBindings {

    override fun process(
        message: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    ) {
        processor.process(message, receptionId, ephemeralId, roundId)
    }

    override fun string(): String = processor.name
}