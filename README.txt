-----------------------------------------------------------------------------------------------------------------------
Ejemplo de una aplicacion Grails usando un Custom Authentication Provider para autenticar usuarios
contra una base de datos distinta (en este caso la base de datos de usuarios en Presto 3.1.1)
-----------------------------------------------------------------------------------------------------------------------

Se esta usando el plugin de Spring Security (http://www.grails.org/plugin/spring-security-core).

Los objetos User, Role y UserRole que se generan con el plugin (leer la doc sobre el plugin) de spring security
ya no son necesarios si se usa un custom authentication provider por lo que las lineas que especifican las clases
de dominio que representan al usuario, rol y los roles del usuario estan comentadas en el grails-app/conf/Config.groovy:

<code>
// Added by the Spring Security Core plugin:
//grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.jabaddon.CustomUser'
//grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.jabaddon.CustomUserCustomRole'
//grails.plugins.springsecurity.authority.className = 'com.jabaddon.CustomRole'
</code>

El Custom Authentication Provider es la clase com.jabaddon.MyAuthenticationProviderService que se encuentra en:
grails-app/services/com/jabaddon/MyAuthenticationProviderService.groovy. Esta clase esta definida en
grails-app/conf/spring/resources.groovy como un bean llamado "myAuthenticationProvider" y se le dice al plugin de
Spring Security que se debe usar ese custom authentication provider usando la propiedad
"grails.plugins.springsecurity.providerNames" que se encuentra en grails-app/conf/Config.groovy.

Se usa el plugin de Datasources (http://www.grails.org/plugin/datasources) para poder definir otro datasource que
apunte a la base de datos desde la cual se obtiene la info del usuario, password y sus roles.
En grails-app/conf/Datasources.groovy esta definido el Datasource extra.

Los controllers: LoginController y LogoutController los genero el plugin de Spring Security pero realmente no se usan
para el proposito del custom authentication provider.

