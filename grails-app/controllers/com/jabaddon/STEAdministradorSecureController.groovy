package com.jabaddon

import grails.plugins.springsecurity.Secured

/**
 * Controller seguro donde solo pueden usarlo los usuarios con el rol: ROLE_STE_Administrador
 */
class STEAdministradorSecureController {

    @Secured(['ROLE_STE_Administrador'])
    def index = {
        render "Hey, este mensaje es seguro y solo lo pueden ver los usuarios con el rol: STE_Administrador"
    }
}
