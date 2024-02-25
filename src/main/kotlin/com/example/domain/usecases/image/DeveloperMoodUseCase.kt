package com.example.domain.usecases.image

import com.example.data.source.dao.ImageDao

/**
 * This Class For Developer Mood :)
 */
class DeveloperMoodUseCase(
        private val imageDao: ImageDao
) {

    /**
     * Insert Image Details Depend On Path Folder
     */
//    suspend fun gg() {
//        val folder = File("C:\\Users\\B.C.S\\Desktop\\Arrangment_Images\\Feeling\\New folder")
//        val width = 4
//        val height = 6
//        val blurHashes: MutableList<String> = mutableListOf()
//
//        folder.listFiles()?.sortedBy { file -> file.name }?.forEachIndexed { index, file ->
//            if (file.extension == "jpg" || file.extension == "png") {
//
//                val image = ImageIO.read(file)
//                val blurHash = BlurHash.encode(image, width, height)
//
//                println("${file.name} -> $blurHash")
//
//                imageDao.insertImageDetails(
//                        blurHash = blurHash,
//                        likeCount = (1000..5000).random(),
//                        categoryId = 8,
//                        imageTitle = file.name
//                )
//
//                blurHashes.add("${file.name} -> $blurHash")
//            }
//        }
//    }

    /**
     * Update ImageDetails
     */
    //    suspend operator fun invoke() {
//        val f = 2562
//        val s = 2574
//        for (i in f..s) {
//            imageDao.updateImageDetails(
//                    imageTitle = "pojo#solder#$i.jpg",
//                    url = "https://ik.imagekit.io/zyvpmxkx6/solder/pojo_solder_$i.jpg"
//            )
//        }
//    }

    /**
     * Insert Collection
     */
//    suspend operator fun invoke() {
//        val f = 1
//        val s = 108
//        for (i in f..s) {
//            imageDao.insertCollectionTitleImage(
//                    codeName = "pojo#profile#$i.jpg",
//                    url = "https://ik.imagekit.io/zyvpmxkx6/profile_image/pojo_profile_image_$i.jpg",
//                    id = i
//            )
//        }
//    }

    /**
     * Insert Background
     */
    suspend operator fun invoke() {
        val f = 1
        val s = 108
        for (i in f..s) {
            imageDao.insertSmallImage(
                    codeName = "pojo#profile#$i.jpg",
                    url = "https://ik.imagekit.io/zyvpmxkx6/profile_image/pojo_profile_$i.jpg",
                    id = i
            )
        }
    }
}