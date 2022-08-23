package io.elixxir.dapp.request.model

interface Request {
}

interface OutgoingRequest : Request
interface IncomingRequest: Request
interface RequestConfirmation: Request