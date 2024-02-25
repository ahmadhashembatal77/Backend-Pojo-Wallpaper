package com.example.domain.endpoints

sealed class PojoEndPoint(val path: String) {
    /**
     * Images
     */
    object ThreeWeeksAgoTopRatedImages : PojoEndPoint(path = "/top_rated_recently")
    object ListTenTopRated : PojoEndPoint(path = "/list_top_rated")
    object AllLiteImagesByCategory : PojoEndPoint(path = "/lite_images_by_category")
    object PojoCompleteDetails : PojoEndPoint(path = "/images_complete_details")
    object TryEncodeImages : PojoEndPoint(path = "/tryEncodeImages")
    object LiteImagesByDetails: PojoEndPoint(path = "/images_by_date")
    object SearchPojoByText: PojoEndPoint(path = "/search_image")
    object LastUserLikePojo: PojoEndPoint(path = "/user_images_like")
    object GetAllOwnedPrimaryImage : PojoEndPoint(path = "/owned_primary_images")
    object ImageTagsForImage: PojoEndPoint(path = "/image_tags_for_image")
    object DeveloperMoodEndPoint: PojoEndPoint(path = "/developer_mood_xd")

    /**
     * Update Image
     * */
    object UpdateUserLikedPojo : PojoEndPoint(path = "/update_liked_image")
    object UpdateWatchPojo: PojoEndPoint(path = "/update_watch_image")
    object UpdatePrimaryImageOwnedUser: PojoEndPoint(path = "/add_user_primary_image")

    /**
     * Categories
     */
    object SevenCategories : PojoEndPoint(path = "/seven_category")
    object AllCategories : PojoEndPoint(path = "/categories")
    object AllLiteCategories : PojoEndPoint(path = "/lite_categories")
    object EncodeBlurHashCategories : PojoEndPoint(path = "/encode_blurHash_categories")

    /**
     * Collections
     */
    object UsersCollections: PojoEndPoint(path = "/users_collections")
    object ImagesByUsersCollections : PojoEndPoint(path = "/images_users_collections")
    object UserCollectionDetails : PojoEndPoint(path = "/user_collection_details")
    object SpecificUserCollectionsByUserId : PojoEndPoint(path = "/specific_user_collections")
    object UpdateUserLikeUserCollection : PojoEndPoint(path = "/update_liked_user_collection")
    object GetAllCollectionTitleImagesEndPoint : PojoEndPoint(path = "/all_collection_title_images")
    object RemoveImageCollectionForUserCollectionEndPoint : PojoEndPoint(path = "/remove_image_collection")
    object UpdateImageNumberToUserCollectionEndPoint : PojoEndPoint(path = "/update_image_collection_number")
    object UpdateUserCollectionTitleImageEndPoint : PojoEndPoint(path = "/update_collection_title_image")
    object UpdateUserCollectionTitleTextEndPoint : PojoEndPoint(path = "/update_collection_text")
    object CreateUserCollectionEndPoint : PojoEndPoint(path = "/create_collection")
    object AddImageToCollectionForUserCollectionsEndPoint : PojoEndPoint(path = "/add_image_to_collection")
    object UpdateCollectionVisibilityEndPoint : PojoEndPoint(path = "/update_collection_visibility")
    object DeleteCollectionEndPoint : PojoEndPoint(path = "/delete_collection")
    object DeleteImageFromCollectionEndPoint : PojoEndPoint(path = "/delete_image_from_icon")
    object GetCollectionImagesWithOneImageDetails : PojoEndPoint(path = "/collection_images_with_image")

    /**
     * Stickers
     */
    object GetStickersByDateEndPoint : PojoEndPoint(path = "/stickers_by_date")
    object UpdateStickerLikeEndPoint : PojoEndPoint(path = "/update_sticker_like")
    // Must Edit This To Insert Sticker User Owned
    object InsertStickerToUserOwnedEndPoint : PojoEndPoint(path = "/sticker_user_owned")
    object GetAllStickerCollectionsEndPoint : PojoEndPoint(path = "/get_sticker_collections")
    object GetAllStickersFromCollectionsEndPoint : PojoEndPoint(path = "/stickers_from_collections")
    object GetLimitedUserOwnedStickers : PojoEndPoint(path = "/user_owned_stickers")
}