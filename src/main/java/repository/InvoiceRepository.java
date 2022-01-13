package repository;

import model.entity.InvoiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<InvoiceEntity, String> {
}
