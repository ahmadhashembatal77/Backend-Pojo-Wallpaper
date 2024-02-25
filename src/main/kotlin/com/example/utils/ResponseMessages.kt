package com.example.utils

sealed class ResponseMessages(val message: String) {
    /**
     * User Response Messages
     */
    object SuccessAuthentication : ResponseMessages("SUCCESS AUTHENTICATION")
    object FailAuthentication : ResponseMessages("FAIL AUTHENTICATION")
    object EmptyField : ResponseMessages("EMPTY FILED")
    object SuccessSignup : ResponseMessages("SUCCESS SIGNUP")
    object IncorrectPassword : ResponseMessages("INCORRECT USER PASSWORD!")
    object SuccessSignIn : ResponseMessages("SUCCESS SIGN IN")
    object UserNameAlreadyExist : ResponseMessages("USER ALREADY EXIST!")
    object NotFoundUserEmail : ResponseMessages("NOT FOUND USER EMAIL!")
    object NotFoundUser : ResponseMessages("NOT FOUND USERNAME!")
    object UserEmailIsAlreadyExist : ResponseMessages("USER EMAIL ALREADY EXIST!")
    object SuccessDeleteUser : ResponseMessages("SUCCESS DELETE USER")
    object SuccessUpdatePassword : ResponseMessages("SUCCESS UPDATE USER")
    object NotFoundUserID : ResponseMessages("Not Found User With This UserID")
    object SuccessFetchUserByID : ResponseMessages("SUCCESS FITCH USER")
    object EmptyParameter : ResponseMessages("EMPTY PARAMETER")
    object UnKnowFail : ResponseMessages("IN KNOW FAIL")
    //object Success : ResponseMessages("SUCCESS")
    object SuccessAddLikeImage : ResponseMessages("SUCCESS_ADD_LIKE_IMAGE")
    object SuccessRemoveLikeImage : ResponseMessages("SUCCESS_REMOVE_LIKE_IMAGE")
    object SuccessUpdateWatchImageCount : ResponseMessages("SUCCESS_UPDATE_WATCH_COUNT")
    object SuccessAddLikeUserCollection : ResponseMessages("SUCCESS_ADD_LIKE_USER_COLLECTION")
    object SuccessRemoveLikeUserCollection : ResponseMessages("SUCCESS_REMOVE_LIKE_USER_COLLECTION")
    object SuccessAddLikeAdminCollection : ResponseMessages("SUCCESS_ADD_LIKE_ADMIN_COLLECTION")
    object SuccessRemoveLikeAdminCollection : ResponseMessages("SUCCESS_REMOVE_LIKE_ADMIN_COLLECTION")
    object Success : ResponseMessages("Success")
    /**
     * Image Response Messages
     */
    object SuccessFetchImageDetails : ResponseMessages("Success_Fetch_Image_Details")
    object FailFetchImageDetails : ResponseMessages("Fail_Fetch_Image_Details")
    object FailFetchMorePages : ResponseMessages("Fail_Fetch_More_Pages")
    object EmptyFetchImages : ResponseMessages("Empty_Fetch_Image")
    object NotFoundImageByImageID : ResponseMessages("Not_Found_Image_By_ImageID :")
    object SuccessFetchList : ResponseMessages("Success_Fetch_list")

    /**
     * Category Response Messages
     */
    object EmptyFetchCategoryImages : ResponseMessages("Empty_Fetch_Category_Image")
    object EmptyFetchColorsImages : ResponseMessages("Empty_Fetch_Colors_Image")

    /**
     * Collections Response Messages
     */
    object SuccessFetchCollections : ResponseMessages("Success_Fetch_Collections")
    object EmptyFetchCollections : ResponseMessages("Empty_Fetch_Collections")
    object ImageCollectionAlreadyExist : ResponseMessages("Image_Already_Exist_In_Collection")
    object ImageCollectionNotExist : ResponseMessages("Image_Already_Not_Exist_In_Collection")

    /**
     *  Others Response Messages
     */
    object EmptyFetchList : ResponseMessages("Empty_list")
    object EmptyFetchData : ResponseMessages("Empty_data")
    object WrongUserId: ResponseMessages("Wrong_User_Id")
    object EmptyFetchTags : ResponseMessages("Empty_Fetch_Image_Tags")
    object SuccessFetchImageTags : ResponseMessages("Success_Fetch_Image_Tags")

    /**
     *  Sticker Response Messages
     */
    object SuccessUpdateStickerLike : ResponseMessages("Success_Update_Sticker_Like")
    object FailUpdateStickerLike : ResponseMessages("Fail_Update_Sticker_Like")
    object FailInsertStickerToOwned : ResponseMessages("Fail_Insert_Sticker_To_Owned")
    object SuccessInsertStickerToOwned : ResponseMessages("Success_Insert_Sticker_To_Owned")
    object FailGetStickers : ResponseMessages("Fail_Get_Stickers")
    object SuccessGetStickers : ResponseMessages("Success_Get_Stickers")

}