package io.elixxir.dapp.bindings.data

import bindings.Cmix as CoreCmix

@JvmInline
internal value class CmixAdapter(private val cmix: CoreCmix) : Cmix