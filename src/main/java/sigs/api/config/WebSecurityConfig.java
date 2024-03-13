package sigs.api.config;


import org.springframework.http.HttpMethod;
import sigs.api.jwt.JwtAuthenticationEntryPoint;
import sigs.api.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import static org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.RequestMatchers.antMatchers;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN");


        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests()

                //Here starts Admin permissions
                .antMatchers("/admin", "/add", "/delete/{id}").hasRole("ADMIN")
               // .antMatchers("/user-update/{id}", "/user-add", "/user-delete/{id}").hasRole("ADMIN")
                .antMatchers("/produit-update/{id}", "/produit-add", "/produit-delete/{id}").hasRole("ADMIN")
                .antMatchers("/fournisseur-update/{id}", "/fournisseur-add", "/fournisseur-delete/{id}").hasRole("ADMIN")
                .antMatchers("/demande-update/{id}", "/demande-add", "/demande-delete/{id}").hasRole("ADMIN")
                .antMatchers("/pret-update/{id}", "/pret-add", "/pret-delete/{id}").hasRole("ADMIN")
                .antMatchers("/commande-update/{id}", "/commande-add", "/commande-delete/{id}").hasRole("ADMIN")
                .antMatchers("/stock-update/{id}", "/stock-add", "/stock-delete/{id}").hasRole("ADMIN")
                .antMatchers("/entrepot-update/{id}", "/entrepot-add", "/entrepot-delete/{id}").hasRole("ADMIN")
                .antMatchers("/mouvement-stock-update/{id}", "/mouvement-stock-add", "/mouvement-stock-delete/{id}").hasRole("ADMIN")
                .antMatchers("/compte-fournisseur-update/{id}", "/compte-fournisseur-add", "/compte-fournisseur-delete/{id}").hasRole("ADMIN")
                .antMatchers("/categorie-update/{id}", "/categorie-add", "/categorie-delete/{id}").hasRole("ADMIN")
                .antMatchers("/sous-categorie-update/{id}", "/sous-categorie-add", "/sous-categorie-delete/{id}").hasRole("ADMIN")




                // Here starts Users and Admin permissions
                .antMatchers("/employees", "/update/{id}").hasAnyRole("USER", "ADMIN")
               // .antMatchers("/users", "/user/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/produits", "/produit/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/fournisseurs", "/fournisseur/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/demandes", "/demande/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/prets", "/pret/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/commandes", "/commande/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/stocks", "/stock/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/entrepots", "/entrepot/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/mouvement-stocks", "/mouvement-stock/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/compte-fournisseurs", "/compte-fournisseur/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/categories", "/categorie/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/sous-categories", "/sous-categorie/{id}").hasAnyRole("USER", "ADMIN")


                // Only for supreme admin
                .antMatchers("/authenticate", "/register", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**" ).permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/role-update/{id}", "/role-add", "/role-delete/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/users", "/user/{id}").permitAll()//.hasRole("ADMIN")//.permitAll()
                .antMatchers("/permission-update/{id}", "/permission-add", "/permission-delete/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/permissions", "/permission/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/authentic-update/{id}", "/authentic-add", "/authentic-delete/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/authentic", "/authentic/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/module-update/{id}", "/module-add", "/module-delete/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/modules", "/module/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/privilege-update/{id}", "/privilege-add", "/privilege-delete/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/privileges", "/privilege/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/roles", "/role/{id}").hasRole("ADMIN")//.permitAll()
                .antMatchers("/user-update/{id}", "/user-add", "/user-delete/{id}").hasRole("ADMIN").//.permitAll().
                // all other requests need to be authenticated
                        anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }






}