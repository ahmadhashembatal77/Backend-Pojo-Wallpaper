package com.example.db

import com.example.data.source.dao.*
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.category.GetAllLiteCategoriesUserCase
import com.example.domain.usecases.collections.*
import com.example.domain.usecases.image.*
import com.example.domain.usecases.stickers.*
import com.example.domain.usecases.user.*
import com.example.utils.Constants
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect

val mainModule = module {

    single {
        Database.connect(
                url = Constants.DATABASE_URL,
                driver = Constants.DATABASE_DRIVER,
                user = System.getenv("dbname") ?: "pojo_user_database",
                password = System.getenv("dbpassword") ?: "HrnPurZE4XtnrKjHW7B4wHJmSZiwZgIx",
                dialect = PostgreSqlDialect()
        )
    }
    /**
    Dao
     */
    single<UserDao> {
        UserDaoImpl(get())
    }

    single<ImageDao> {
        ImageDaoImpl(get())
    }

    single<CollectionDao> {
        CollectionDaoImpl(get())
    }

    single<StickersDao> {
        StickersDaoImpl(get())
    }

    /**
    Users
     */
    /*User*/
    single {
        UpdateUserPasswordUseCase(get())
    }

    single {
        AuthUserUserCase()
    }

    single {
        DeleteUserUseCase(get())
    }

    single {
        UserSignInByUserNameUseCase(get())
    }

    single {
        SignUpUseCase(get())
    }

    single {
        GetUserDetailsByIDUserCase(get())
    }

    single {
        UserSignInByUserEmailUserCase(get())
    }

    single {
        GetAllUsersProfileImagesUseCase(get())
    }

    single {
        GetAllUsersBackgroundImagesUseCase(get())
    }

    single {
        GetOwnedUserProfileImagesUseCase(get())
    }

    single {
        GetOwnedUserBackgroundImagesUseCase(get())
    }

    single {
        UpdateUserImageBackgroundUseCase(get())
    }

    single {
        UpdateUserImageProfileUseCase(get())
    }

    single {
        UpdateUserNameUseCase(get())
    }

    single {
        MakeUserBuyProfileImageUseCase(get())
    }

    single {
        MakeUserBuyCollectionTitleImageUseCase(get())
    }

    single {
        MakeUserBuyUserBackgroundImageUseCase(get())
    }

    single {
        UpdateUserPojoPriceUseCase(get())
    }
    /**
     * Others
     */

    single {
        GetLiteImagesOrderByDateUseCase(get())
    }

    /* Categories */
    single {
        GetAllCategoriesUseCase(get())
    }

    single {
        GetTopRatedLiteImagesThreeWeeksAgoUseCase(get())
    }

    single {
        GetListOfTopRatedLiteImages(get())
    }

    /*Categories*/

    single {
        GetAllLiteCategoriesUserCase(get())
    }

    single {
        GetAllLiteImagesByCategory(get())
    }

    single {
        Get60ImagesDependOnCategory(get())
    }

    /**
    Collections
     */

    single {
        GetUsersCollectionsUseCase(get())
    }

    single {
        GetImagesUserCollectionsUseCase(get())
    }

    single {
        GetUserCollectionDetailsUseCase(get())
    }

    single {
        GetUserCollectionsByUserIdUseCase(get())
    }

    single {
        UpdateUserLikeUserCollection(get())
    }

    single {
        GetALlCollectionTitleProfileImagesUseCase(get())
    }

    single {
        GetCollectionImagesWithImageOneImageDetailsUseCase(get(), get())
    }
    //___________________________________________
    single {
        AddImageToCollectionForUserCollectionsUseCase(get())
    }

    single {
        CreateUserCollectionUseCase(get())
    }

    single {
        RemoveImageCollectionForUserCollectionUseCase(get())
    }

    single {
        UpdateImageNumberToUserCollectionUseCase(get())
    }

    single {
        UpdateUserCollectionTitleImageUseCase(get())
    }

    single {
        UpdateUserCollectionTitleTextUseCase(get())
    }

    single {
        UpdateCollectionVisibilityUseCase(get())
    }

    single {
        DeleteUserCollectionUseCase(get())
    }
    /**
     * Images
     */

    single {
        UpdateUserLikedImage(get())
    }

    single {
        DeleteImageFromCollectionUseCase(get(),get())
    }

    single {
        AddUserLikeImageUseCase(get())
    }

    single {
        UpdateWatchImageCountUseCase(get())
    }

    single {
        GetSearchImageByTextUseCase(get())
    }

    single {
        GetLastUserImagesLikesUseCase(get())
    }

    single {
        GetListOfTagsImageForImageUseCase(get())
    }

    single {
        AddPrimaryImageToUserOwnedUserCase(get(), get())
    }

    single {
        GetAllUserOwnedPrimaryImageUseCase(get())
    }

    single {
        DeveloperMoodUseCase(get())
    }
    /**
     * Stickers
     */
    single { GetStickersByDateUseCase(get()) }

    single { GetStickersFromCollectionUseCase(get()) }

    single { GetLimitAllStickerCollectionsUseCase(get()) }

    single { InsertStickerToUserStickerOwnedUseCase(get(), get()) }

    single { UpdateStickerLikeBasedOnLikeTypeUseCase(get()) }

    single { GetLimitAllOwnedUserStickersUseCase(get()) }

    single { DeveloperMoodStickerUseCase(get()) }
}