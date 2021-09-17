package zefanya.denny.githubuseruiuxdanapi.data.source.remote.response

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> loading(msg: String, body: T?): ApiResponse<T> = ApiResponse(StatusResponse.LOADING, body,msg)

        fun <T> error(msg: String, body: T?): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, msg)
    }

}