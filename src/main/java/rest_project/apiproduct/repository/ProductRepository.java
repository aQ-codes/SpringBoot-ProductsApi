package rest_project.apiproduct.repository;




import  rest_project.apiproduct.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1%")
    public List<Product> findAll(String keyword);
}