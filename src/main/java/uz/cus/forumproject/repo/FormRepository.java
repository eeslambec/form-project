package uz.cus.forumproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.cus.forumproject.model.Form;

import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
}
