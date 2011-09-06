import com.jabaddon.MyAuthenticationProviderService
import org.springframework.security.authentication.encoding.Md5PasswordEncoder

// Place your Spring DSL code here
beans = {
    // Al authenticator se le inyecta el passwordEncoder
    myAuthenticationProvider(MyAuthenticationProviderService) {
        passwordEncoder = ref("passwordEncoder")
    }

    // Password encoder utilizado para comparar el password almacenado en la base de datos
    passwordEncoder(Md5PasswordEncoder) {        
    }
}
