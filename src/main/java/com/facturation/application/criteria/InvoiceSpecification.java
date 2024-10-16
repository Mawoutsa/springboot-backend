package com.facturation.application.criteria;

import com.facturation.application.entities.Invoice;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
@Data
@AllArgsConstructor
public class InvoiceSpecification {

    public static Specification<Invoice> referenceIs(String ref) {
        return new Specification<Invoice>() {
            @Override
            public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("reference"), ref);
            }
        };
    }

    public static Specification<Invoice> userIdIs(Long userId) {
        return new Specification<Invoice>() {
            @Override
            public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user").get("id"), userId);
            }
        };
    }

    public static Specification<Invoice> customerIdIs(Long customerId) {
        return new Specification<Invoice>() {
            @Override
            public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("customer").get("id"), customerId);
            }
        };
    }

    public static Specification<Invoice> addressIdIs(Long addressId) {
        return new Specification<Invoice>() {
            @Override
            public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("address").get("id"), addressId);
            }
        };
    }

    public static Specification<Invoice> companyIdIs(Long companyId) {
        return new Specification<Invoice>() {
            @Override
            public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("company").get("id"), companyId);
            }
        };
    }

    public static Specification<Invoice> customerFullNameIs(String fullName) {
        return (Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (fullName != null && !fullName.isEmpty()) {
                String pattern = "%" + fullName.toLowerCase() + "%";
                Predicate firstNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("customer").get("firstName")), pattern);
                Predicate lastNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("customer").get("lastName")), pattern);
                return criteriaBuilder.or(firstNamePredicate, lastNamePredicate);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Invoice> invoiceDateBetween(Date fromDate, Date toDate) {
        return (Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (fromDate != null && toDate != null) {
                return criteriaBuilder.between(root.get("invoiceDate"), fromDate, toDate);
            } else if (fromDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("invoiceDate"), fromDate);
            } else if (toDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("invoiceDate"), toDate);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

//    public static Specification<Invoice> productContainIs(String productName) {
//        return (Root<Invoice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
//            if (productName != null && !productName.isEmpty()) {
//                query.distinct(true);
//                Join<Invoice, Product> productJoin = root.join("products", JoinType.INNER);
//                return criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("reference")), "%" + productName.toLowerCase() + "%");
//            }
//            return criteriaBuilder.conjunction();
//        };
//    }
}