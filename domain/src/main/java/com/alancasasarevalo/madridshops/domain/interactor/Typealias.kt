package com.alancasasarevalo.madridshops.domain.interactor

import com.alancasasarevalo.madridshops.domain.model.Shops

typealias SuccessClosure = ( shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias CodeClousure = () -> Unit