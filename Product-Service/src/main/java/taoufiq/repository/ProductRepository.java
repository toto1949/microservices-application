package taoufiq.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import taoufiq.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
