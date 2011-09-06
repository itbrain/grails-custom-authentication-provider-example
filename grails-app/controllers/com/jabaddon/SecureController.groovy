package com.jabaddon

import grails.plugins.springsecurity.Secured

/**
 * Controller seguro donde solo pueden usarlo aquellos usuarios con el rol: ROLE_ADMIN
 */
class SecureController {

    @Secured(['ROLE_ADMIN'])
    def index = {
        render "Hey, este mensaje es seguro y solo lo puede ver los usuarios con el rol: ROLE_ADMIN"
    }        
}
