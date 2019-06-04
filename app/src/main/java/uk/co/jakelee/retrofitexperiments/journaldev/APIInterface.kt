package uk.co.jakelee.retrofitexperiments.journaldev

import retrofit2.Call
import retrofit2.http.*
import uk.co.jakelee.retrofitexperiments.journaldev.pojo.MultipleResource
import uk.co.jakelee.retrofitexperiments.journaldev.pojo.User
import uk.co.jakelee.retrofitexperiments.journaldev.pojo.UserList


interface APIInterface {

    @GET("/api/unknown")
    fun doGetListResources(): Call<MultipleResource>

    @POST("/api/users")
    fun createUser(@Body user: User): Call<User>

    @GET("/api/users?")
    fun doGetUserList(@Query("page") page: String): Call<UserList>

    @FormUrlEncoded
    @POST("/api/users?")
    fun doCreateUserWithField(@Field("name") name: String, @Field("job") job: String): Call<UserList>
}