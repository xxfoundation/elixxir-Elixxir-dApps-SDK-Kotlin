package io.elixxir.dapp.bindings.data

import bindings.UserDiscovery as CoreUserDiscovery

@JvmInline
value class UserDiscoveryAdapter(private val ud: CoreUserDiscovery) : UserDiscovery