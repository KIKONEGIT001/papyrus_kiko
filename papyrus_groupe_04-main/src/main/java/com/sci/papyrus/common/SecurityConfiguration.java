/**
 * package com.sci.papyrus.common;
 * <p>
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.http.HttpHeaders;
 * import org.springframework.http.HttpMethod;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.web.cors.CorsConfiguration;
 * import org.springframework.web.cors.CorsConfigurationSource;
 * import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 * <p>
 * import java.util.Arrays;
 * import java.util.Collections;
 *
 * @Configuration
 * @EnableWebSecurity
 * @RequiredArgsConstructor
 * @EnableMethodSecurity public class SecurityConfiguration {
 * <p>
 * private final UserDetailsServiceImpl userDetailsService;
 * private final AuthFilter jwtFilter;
 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 * <p>
 * AuthenticationManagerBuilder authenticationManagerBuilder =
 * http.getSharedObject(AuthenticationManagerBuilder.class);
 * authenticationManagerBuilder.userDetailsService(this.userDetailsService);
 * AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
 * <p>
 * http
 * .cors(customizer -> customizer
 * .configurationSource(corsConfigurationSource()))
 * .csrf(AbstractHttpConfigurer::disable)
 * .authorizeHttpRequests(requests -> requests
 * .requestMatchers(HttpMethod.POST, "/users").permitAll()
 * .requestMatchers(HttpMethod.POST, "/users/init-reset-password").permitAll()
 * .requestMatchers(HttpMethod.PUT, "/users/reset-password").permitAll()
 * .requestMatchers(HttpMethod.GET, USER_RESOURCES).permitAll()
 * .requestMatchers(SWAGGER_RESOURCES).permitAll()
 * .requestMatchers(AUTH_RESOURCES).permitAll()
 * .requestMatchers(HttpMethod.GET, IGNORE_URIS).permitAll()
 * .anyRequest().fullyAuthenticated()
 * )
 * .authenticationManager(authenticationManager)
 * .sessionManagement(sessionManagement ->
 * sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 * .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
 * .exceptionHandling(exceptionHandling ->
 * exceptionHandling
 * .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
 * );
 * <p>
 * <p>
 * return http.build();
 * }
 * @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
 * return authenticationConfiguration.getAuthenticationManager();
 * }
 * @Bean public WebSecurityCustomizer webSecurityCustomizer() {
 * return web -> web.ignoring().requestMatchers("/error");
 * }
 * @Bean public PasswordEncoder passwordEncoder() {
 * return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
 * }
 * @Bean public CorsConfigurationSource corsConfigurationSource() {
 * final var cors = new CorsConfiguration();
 * cors.setAllowedOriginPatterns(Collections.singletonList("*"));
 * cors.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),
 * HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(),
 * HttpMethod.PUT.name(), HttpMethod.HEAD.name(), HttpMethod.PATCH.name()));
 * cors.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION,
 * HttpHeaders.CONTENT_TYPE));
 * cors.setExposedHeaders(Collections.singletonList(HttpHeaders.LOCATION));
 * cors.setAllowCredentials(true);
 * <p>
 * final var source = new UrlBasedCorsConfigurationSource();
 * <p>
 * source.registerCorsConfiguration("/**", cors);
 * return source;
 * }
 * <p>
 * }
 **/
