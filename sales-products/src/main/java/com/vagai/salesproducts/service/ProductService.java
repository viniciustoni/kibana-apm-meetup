package com.vagai.salesproducts.service;

import com.vagai.salesproducts.dto.ProductDto;
import com.vagai.salesproducts.dto.ReserveProductDto;
import com.vagai.salesproducts.entity.Product;
import com.vagai.salesproducts.exception.ProductNotFoundException;
import com.vagai.salesproducts.mapper.ProductMapper;
import com.vagai.salesproducts.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductDto)
                .collect(toList());
    }

    public List<ProductDto> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds)
                .stream()
                .map(productMapper::mapToProductDto)
                .collect(toList());
    }

    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::mapToProductDto)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Transactional
    public ProductDto reserveProduct(ReserveProductDto reserveProductDto) {
        return productRepository.findById(reserveProductDto.productId())
                .map(product -> updateQtdReserved(product, reserveProductDto.qtdReserved()))
                .map(productMapper::mapToProductDto)
                .orElseThrow(() -> new ProductNotFoundException(reserveProductDto.productId()));
    }

    @Transactional
    public List<ProductDto> reserveProductFixingNPlusOne(List<ReserveProductDto> reserveProductDtos) {
        // TODO and FIXME: Still have a N + 1 here, but the idea of the fix was to show the fix on consumer side.
        return reserveProductDtos.stream()
                .map(this::reserveProduct)
                .toList();
    }

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);

        return productMapper.mapToProductDto(savedProduct);
    }

    private Product updateQtdReserved(Product product, BigDecimal qtdReserved) {
        // TODO: check if we have qtd to reserve
        if (product.getQtdReserved() != null) {
            product.setQtdReserved(product.getQtdReserved().add(qtdReserved));
        } else {
            product.setQtdReserved(qtdReserved);
        }

        return product;
    }
}
