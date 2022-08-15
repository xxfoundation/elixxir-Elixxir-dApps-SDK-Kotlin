package io.elixxir.dapp.api.model

interface DappApi {
    val accountApi: AccountApi
    val directoryApi: DirectoryApi
    val messagesApi: MessagesApi
    val networkApi: NetworkApi
    val requestsApi: RequestsApi
    val groupsApi: GroupsApi
}