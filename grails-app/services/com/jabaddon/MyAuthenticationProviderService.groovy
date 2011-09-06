package com.jabaddon

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import groovy.sql.Sql
import com.burtbeckwith.grails.plugin.datasources.DatasourcesUtils
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.BadCredentialsException

/**
 * Custom Authentication Provider
 */
class MyAuthenticationProviderService implements AuthenticationProvider {

    def passwordEncoder

    Authentication authenticate(Authentication authentication) {
        // Se crea el sql a partir del datasource que apunta a la otra base de datos
        Sql sql = new Sql(DatasourcesUtils.getDataSource('prestoReadOnly'))
        // La tabla IDENTITY almacena (en este caso) la info sobre le usuario, username y password
        def row = sql.firstRow("select * from IDENTITY where name = ?", authentication.principal)
        if (row) {
            // Debido a que el password se almacena codificado en la tabla se usa el passwordEncoder para poder hacer
            // la comparacion entre el password codificado y el password plano que tecleo el usuario
            if (this.passwordEncoder.isPasswordValid(row.password, authentication.credentials, null)) {
                List authorities = obtenerRolesUsuarioPresto(row.name, sql)
                def userDetails = new GrailsUser(row.name, row.password, true, true, true, true, authorities, row.id)
                def token = new UsernamePasswordAuthenticationToken(userDetails, row.password, userDetails.authorities)
                token.details = authentication.details
                return token
            }
            else {
                throw new BadCredentialsException("Log in failed");
            }
        }
        return null
    }

    private List obtenerRolesUsuarioPresto(String username, Sql sql) {
        def list = [] as List
        // La tabla de AUTHORITIES (en este caso) es la que contien los nombres de los roles
        sql.eachRow("select * from AUTHORITIES where username = ?", [username]) {
            // Para respetar el standard para nombrar los roles de Spring Security se concatena el prefijo: ROLE_
            // a cada nombre de rol
            list.add(new GrantedAuthorityImpl("ROLE_" + it.authority))
        }
        list
    }

    boolean supports(Class<? extends Object> aClass) {
        return true
    }
}
