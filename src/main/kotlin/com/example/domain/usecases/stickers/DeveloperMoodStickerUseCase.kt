package com.example.domain.usecases.stickers

import com.example.data.source.dao.StickersDao

/**
 * This Class For Developer Mood :)
 */
class DeveloperMoodStickerUseCase(
        private val imageDao: StickersDao
) {
    /**
     * Insert Stickers
     */
    suspend operator fun invoke() {
        val f = 1
        val s = 148
        for (i in f..s) {
            imageDao.insertStickerImage(
                    codeName = "pojo#stickers#$i.png",
                    url = "https://ik.imagekit.io/zyvpmxkx6/stickers/pojo_stickers_$i.png",
                    id = i
            )
        }
    }
}