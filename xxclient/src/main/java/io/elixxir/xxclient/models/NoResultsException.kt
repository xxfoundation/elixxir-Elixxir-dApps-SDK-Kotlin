package io.elixxir.xxclient.models

class NoResultsException(message: String = "Query returned no results.") : Exception(message)