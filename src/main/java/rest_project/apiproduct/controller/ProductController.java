package rest_project.apiproduct.controller;


import rest_project.apiproduct.exception.ProductNotFoundException;
import rest_project.apiproduct.model.Product;
import rest_project.apiproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin("http://localhost:3002")
public class ProductController {


    @Autowired
    private ProductRepository productRepository;


    @PostMapping("/addproduct")
    Product newUser(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }


    @GetMapping("/listproduct")
    List<Product> getAllUsers() {
        return productRepository.findAll();
    }


    @GetMapping("/product/{id}")
    Product getUserById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }


    @PutMapping("/updateproduct/{id}")
    Product updateUser(@RequestBody Product newProduct, @PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setExpirydate(newProduct.getExpirydate());
                    return productRepository.save(product);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/deleteproduct/{id}")
    String deleteUser(@PathVariable Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return  "Product with id "+id+" has been deleted success.";
    }
     @GetMapping("/search")
        List<Product> getAllUsers(Model model , @Param("keyword") String keyword) {
            Iterable<Product> product = productRepository.findAll(keyword);
            model.addAttribute("product",product);
            return productRepository.findAll(keyword);
        }

}
