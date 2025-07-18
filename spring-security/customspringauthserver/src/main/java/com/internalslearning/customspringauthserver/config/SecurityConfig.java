package com.internalslearning.customspringauthserver.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

// => Wins: Spring takes the incoming request (e.g., a call to /oauth2/token) and checks it against the highest priority filter chain (@Order(1) in your code).
// => If it matches, that specific filter chain is used, and Spring stops looking. The other filter chains are ignored for this request.
// => If it does not match, Spring moves to the next filter chain in the order (@Order(2)) and repeats the process.

@Configuration // Spring ki chepthundi, “ee class lo beans create cheyyali.”
@EnableWebSecurity // Spring Security ni activate chesthundi. Ante, security filters,
                   // authentication, authorization logic anni enable avutayi.
public class SecurityConfig {

    // -----------------> Authorization Server Security Filter Chain
    // Ee filter chain valla, authorization server endpoints (tokens, consent, etc.)
    // secure ga untayi. User login lekapote, login page chupistundi.

    @Bean // Spring container lo object create cheyyadam kosam.
    @Order(1) // Ee filter chain ki high priority (first ga apply avutundi).
              // SecurityFilterChain - HTTP requests ki security rules set cheyyadam.
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer
                .authorizationServer();

        http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher()) // Ee filter chain only
                                                                                      // authorization server endpoints
                                                                                      // ki matrame apply avutundi
                                                                                      // (e.g., /oauth2/authorize,
                                                                                      // /oauth2/token).
                .with(authorizationServerConfigurer, (authorizationServer) -> authorizationServer
                        .oidc(Customizer.withDefaults()) // Enable OpenID Connect 1.0. OpenID Connect (OIDC) endpoints
                                                         // activate chesthundi (e.g., /userinfo).
                ) // Authorization server configuration apply chesthundi.
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()) // Ee endpoints ki access kavali ante, user login ayyi undali.
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML))); // User login lekapote, /login page
                                                                                    // ki redirect chesthundi (HTML
                                                                                    // requests ki matrame).

        return http.build();
    }

    // Why this ? -> App lo migilina (non-authorization server) URLs ki kuda
    // security apply cheyyali. User login lekapote, default login page chupistundi.

    @Bean
    @Order(2) // Ee filter chain ki second priority.
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()) // Migilina anni requests ki authentication avasaram.
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults()); // Default Spring Security login page activate chesthundi.

        return http.build();
    }

    // => UserDetailsService : User details (username, password, roles) ni manage
    // chesthundi.
    // => InMemoryUserDetailsManager: User data -> memory lo store chesthundi
    // (demo/testing ki baguntundi).

    // => Why this ? - Ee bean valla, authentication ki user data
    // (username/password)
    // ready ga untundi. Production lo database nundi fetch cheyyali.

     @Bean
     public UserDetailsService userDetailsService() {
     UserDetails userDetails = User.withDefaultPasswordEncoder()
     // => User.withDefaultPasswordEncoder(): Idi demo/testing kosam matrame. Ee
     // method
     // password ni {bcrypt} format lo store chestundi. Most importantly, ee method
     // ippudu deprecated (ante, deenini vadanivvaru) endukante developer production
     // code lo porapatuna kuda vadakudadu ani. Real projects lo, manam eppudu oka
     // PasswordEncoder bean (for example, BCryptPasswordEncoder) ni create chesi,
     // daanne use cheyyali.
     .username("user")
     .password("password")
     .roles("USER")
     .build();

     return new InMemoryUserDetailsManager(userDetails);
     }

    // => RegisteredClient: Mee authorization server ni vadadaniki register chesina
    // app
    // (client) details. ( eg: bookmyshow, some other app which use "login with
    // google" )
    // => clientId/clientSecret: Client app ki unique id, secret (password laanti
    // di).
    // => authorizationGrantType: Ee client ki ye grant types support cheyyalo
    // (e.g., authorization code,
    // refresh token).
    // => redirectUri: Login ayyaka, code ni pampadaniki ee URL vadatam.
    // => scope: Client app request cheyyagalige permissions (e.g., openid,
    // profile).
    // => requireAuthorizationConsent(true): User ki consent screen compulsory ga
    // chupistaru.

    // => Why this ? - Ee bean valla, mee authorization server ki ye apps (clients)
    // access cheyyalo, vati details store avutayi. Production lo database lo store
    // cheyyali.
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient clientCredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scopes(scopeConfig -> scopeConfig.addAll(List.of(OidcScopes.OPENID, "ADMIN", "USER")))
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofMinutes(10)).accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED).build())
                .build();

        // => InMemoryRegisteredClientRepository: As ours is small application so
        // storing the clients in memory is enough
        // => but when our application is big then we need to store in DB and fetch from
        // there
        return new InMemoryRegisteredClientRepository(clientCredClient);
    }

    // => JWKSource: JWT tokens sign cheyyadaniki, verify cheyyadaniki keys ni
    // provide
    // chesthundi.
    // => generateRsaKey(): RSA key pair (public/private) generate chesthundi.
    // => JWKSet: JSON Web Key format lo keys ni expose chesthundi (e.g.,
    // /oauth2/jwks
    // endpoint).

    // Why this ? - JWT tokens ni sign cheyyali ante private key avasaram. Verify
    // cheyyali ante public key avasaram. Ee keys ni JWK format lo expose
    // chesthundi.

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    // => RSA 2048 bits: Strong cryptography ki 2048 bits key vadatam
    // => Production lo: Ee keys ni file/database lo store cheyyali, every time
    // deenini
    // generate cheyyakudadhu.
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    // => JwtDecoder: JWT tokens ni decode cheyyadam, verify cheyyadaniki vadatam.
    // => jwkSource: Public keys ni vadukoni, signature verify chesthundi.

    // => Why ? - Authorization server JWT tokens issue chesthundi, so verify
    // cheyyadaniki decoder avasaram.

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    // => AuthorizationServerSettings: Authorization server endpoints, issuer URL,
    // etc. configure cheyyadaniki.
    // => Default settings: Ikkada default values vadutunnaru.

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}