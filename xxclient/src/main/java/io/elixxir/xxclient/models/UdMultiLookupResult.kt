package io.elixxir.xxclient.models

import io.elixxir.xxclient.utils.UserId

data class UdMultiLookupResult(
    val contacts: List<Contact>,
    val failedIds: List<UserId>
) : BindingsModel