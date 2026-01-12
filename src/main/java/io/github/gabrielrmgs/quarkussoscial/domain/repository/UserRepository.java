package io.github.gabrielrmgs.quarkussoscial.domain.repository;

import io.github.gabrielrmgs.quarkussoscial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
