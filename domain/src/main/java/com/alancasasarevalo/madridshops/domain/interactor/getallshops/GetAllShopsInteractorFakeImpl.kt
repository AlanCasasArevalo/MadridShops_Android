//package com.alancasasarevalo.madridshops.domain.interactor.getallshops
//
//import com.alancasasarevalo.madridshops.domain.interactor.ErrorClosure
//import com.alancasasarevalo.madridshops.domain.interactor.ErrorCompletion
//import com.alancasasarevalo.madridshops.domain.interactor.SuccessClosure
//import com.alancasasarevalo.madridshops.domain.interactor.SuccessCompletion
//import com.alancasasarevalo.madridshops.domain.model.Shop
//import com.alancasasarevalo.madridshops.domain.model.Shops
//
//class GetAllShopsInteractorFakeImpl : GetAllShopsInteractor {
//    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
//        var allOk = false
//
//        // Connect to the repository
//
//        if (allOk) {
//            val shops = createFakeListOfShops()
//
//            success.successCompletion(shops)
//        } else {
//            error.errorCompletion("Error while accessing the RepositoryInterface")
//        }
//
//    }
//
//    fun execute(success: SuccessClosure, error: ErrorClosure) {
//        var allOk = false
//
//        if (allOk) {
//            val shops = createFakeListOfShops()
//
//            success(shops)
//        } else {
//            error("Error while accessing the RepositoryInterface")
//        }
//    }
//
////    fun createFakeListOfShops(): Shops {
////        val list = ArrayList<Shop>()
////
////        (0..100).forEach { i ->
////            val element = Shop(1,
////                    "Shop $i",
////                    "http://madrid-shops.com/json_new/getShops.php",
////                    "https://madrid-shops.com/media/shops/logoImg-cortefiel-200.jpg",
////                    "Shop $i",
////                    "Shop $i",
////                    "Shop $i",
////                    "Shop $i",
////                    "Shop $i",
////                    "Shop $i",
////                    "40.4180563",
////                    "-3.7010172999999895",
////                    "Shop $i"
////            )
////
////            val shops = Shops(list)
////
////            return shops
////        }
////    }
//
//}