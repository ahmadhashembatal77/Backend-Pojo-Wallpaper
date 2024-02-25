package com.example.domain.usecases.stickers

import com.example.data.source.dao.StickersDao
import com.example.data.source.queries.StickerLikeType
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class UpdateStickerLikeBasedOnLikeTypeUseCase(
        private val stickerDao: StickersDao
) {
    suspend operator fun invoke(likeType: Int, stickerId: Int): BaseResponse<Boolean> {
        val updateSticker = stickerDao.updateStickerLikeCount(
                likeType = likeType.chooseStickerTypeBasedOnTypeNumber(),
                stickerId = stickerId
        )
        if (updateSticker == 0) {
            return BaseResponse.WithIssueResponse(
                    message = ResponseMessages.FailUpdateStickerLike.message,
                    data = false
            )
        }
        return BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessUpdateStickerLike.message,
                data = true
        )
    }
}

fun Int.chooseStickerTypeBasedOnTypeNumber(): StickerLikeType {
    return when(this){
        1 -> StickerLikeType.FirstLikeCount
        2 -> StickerLikeType.SecondLikeCount
        3 -> StickerLikeType.ThirdLikeCount
        4 -> StickerLikeType.FourthLikeCount
        5 -> StickerLikeType.FifthLikeCount
        else -> StickerLikeType.FifthLikeCount
    }
}