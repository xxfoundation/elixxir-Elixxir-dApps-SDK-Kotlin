package io.elixxir.xxclient.models

import io.elixxir.xxclient.utils.UserId

data class UdMultiLookupResult(
    val contacts: BindingsList<Contact>,
    val failedIds: BindingsList<UserId>
) : BindingsModel