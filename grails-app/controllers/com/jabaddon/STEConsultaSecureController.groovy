package com.jabaddon

import grails.plugins.springsecurity.Secured

/**
 * Controller seguro donde solo pueden usarlo los usuarios con el rol: ROLE_STE_Consulta
 */
class STEConsultaSecureController {

    @Secured(['ROLE_STE_Consulta'])
    def index = {
        render "Hey, este mensaje es seguro y solo lo pueden ver los usuarios con el rol: STE_Consulta"
    }
}
