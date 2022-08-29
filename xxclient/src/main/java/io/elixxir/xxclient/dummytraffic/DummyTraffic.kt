package io.elixxir.xxclient.dummytraffic

import bindings.DummyTraffic as DummyTrafficBindings

interface DummyTraffic {
    var enabled: Boolean
}

open class DummyTrafficAdapter(protected val dt: DummyTrafficBindings) : DummyTraffic {
    override var enabled: Boolean by dt::status
}