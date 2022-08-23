package io.elixxir.dapp.user.model

interface UserQuery {
    val query: String
    val queryType: QueryType

    enum class QueryType {
        USERNAME, USERID, PHONE, EMAIL
    }
}