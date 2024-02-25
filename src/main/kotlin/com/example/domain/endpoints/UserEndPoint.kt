package com.example.domain.endpoints

sealed class UserEndPoint(val path:String){
    object SignUp: UserEndPoint(path = "/add_user")
    object SignIn: UserEndPoint(path = "/sign_in")
    object Authenticate: UserEndPoint(path = "/authenticate")
    object Secret: UserEndPoint(path = "/secret")
    object UpdatePassword: UserEndPoint(path = "/update_password")
    object DeleteUserByUsernameAndPassword: UserEndPoint(path = "/delete_user")
    object UserDetailsByUserID: UserEndPoint(path = "/user_details")
    object GetOwnedUserProfileImagesEndPoint: UserEndPoint(path = "/owned_user_profile_images")
    object GetOwnedUserBackgroundImagesEndPoint: UserEndPoint(path = "/owned_user_background_images")
    object GetAllUsersProfileImagesEndPoint: UserEndPoint(path = "/all_users_profile_images")
    object GetAllUsersBackgroundImagesEndPoint: UserEndPoint(path = "/all_users_background_images")
    object UpdateUserNameEndPoint: UserEndPoint(path = "/update_user_name")
    object UpdateUserImageProfileEndPoint: UserEndPoint(path = "/update_user_profile")
    object UpdateUserImageBackgroundEndPoint: UserEndPoint(path = "/update_user_background")
    object MakeUserBuyCollectionTitleImageEndPoint: UserEndPoint(path = "/buy_collection")
    object MakeUserBuyProfileImageEndPoint: UserEndPoint(path = "/buy_profile_image")
    object MakeUserBuyUserBackgroundImageEndPoint: UserEndPoint(path = "/buy_background")
    object UpdateUserPojoPriceEndPoint: UserEndPoint(path = "/update_price")
}