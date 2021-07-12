package com.compasso.productsms.resource.product;

import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.resource.product.filter.ProductFilterDTO;
import com.compasso.productsms.resource.product.request.ProductRequest;
import com.compasso.productsms.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
@Api(value = "Product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @ApiOperation("Search All Products")
    @GetMapping
    private ResponseEntity<Page<ProductDTO>> searchAll(@PageableDefault(page = 0, size = 10, sort = "idProduct", direction = Sort.Direction.ASC) final Pageable pageable) {
        return new ResponseEntity<>(productService.searchAll(pageable), HttpStatus.OK);
    }

    @ApiOperation("Search Product by ID")
    @GetMapping("/{id}")
    private ResponseEntity<ProductDTO> searchById(@PathVariable final Long id)  {
        return new ResponseEntity<>(productService.searchById(id), HttpStatus.OK);
    }

    @ApiOperation("Search Products by params values")
    @GetMapping("/search")
    private ResponseEntity<Page<ProductDTO>> searchByParamsFilter(
            @PageableDefault(page = 0, size = 10, sort = "idProduct", direction = Sort.Direction.ASC) final Pageable pageable,
            @RequestParam(value = "q", required = false) final String criteriaFilter,
            @RequestParam(value = "min_price", required = false) final BigDecimal minimumPrice,
            @RequestParam(value = "max_price", required = false) final BigDecimal maximumPrice) {

        return new ResponseEntity<>(productService.searchByParamsFilter(pageable, ProductFilterDTO.builder()
                                                                                                  .criteriaFilter(criteriaFilter)
                                                                                                  .maximumPrice(maximumPrice)
                                                                                                  .minimumPrice(minimumPrice)
                                                                                                  .build()), HttpStatus.OK);
    }

    @ApiOperation("Create new Product")
    @PostMapping
    private ResponseEntity<ProductDTO> create(@Valid @RequestBody final ProductRequest request) {
        return new ResponseEntity<>(productService.create(request.toDTO()), HttpStatus.CREATED);
    }

    @ApiOperation("Delete Product by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteById(@PathVariable final Long id) {
        productService.deleteById(id);
    }

    @ApiOperation("Update Product by ID")
    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> update(@PathVariable final Long id, @Valid @RequestBody final ProductRequest request ) {
        return new ResponseEntity<>(productService.update(id, request.toDTO()), HttpStatus.OK);
    }
}
