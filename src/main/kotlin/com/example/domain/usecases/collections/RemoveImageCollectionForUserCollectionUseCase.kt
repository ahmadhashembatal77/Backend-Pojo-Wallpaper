package com.example.domain.usecases.collections

import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class RemoveImageCollectionForUserCollectionUseCase(
        private val collectionDao: CollectionDao
) {
    suspend operator fun invoke(collectionId: Int, imageId: Int): BaseResponse<Boolean> {
        if (collectionId <= 0 || imageId <= 0) {
            BaseResponse.WithIssueResponse(
                    message = ResponseMessages.EmptyField.message,
                    data = false
            )
        }

        if(!collectionDao.checkIfImageAlreadyExistInTheCollection(collectionId = collectionId, imageId = imageId)){
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.ImageCollectionNotExist.message,
                    data = false
            )
        }

        if(collectionDao.removeImageCollectionForUserCollection(collectionId = collectionId,imageId = imageId) != 1){
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.UnKnowFail.message,
                    data = false
            )
        }

        return BaseResponse.SuccessResponse(
                message = ResponseMessages.Success.message,
                data = collectionDao.updateImageNumberToUserCollection(
                        collectionId = collectionId,
                        isIncrease = false
                ) == 1
        )
    }
}