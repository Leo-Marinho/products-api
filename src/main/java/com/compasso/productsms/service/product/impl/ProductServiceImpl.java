package com.compasso.productsms.service.product.impl;

import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.exception.NotFoundException;
import com.compasso.productsms.exception.ProductIdNotFount;
import com.compasso.productsms.model.product.ProductEntity;
import com.compasso.productsms.repository.product.ProductRepository;
import com.compasso.productsms.resource.product.filter.ProductFilterDTO;
import com.compasso.productsms.service.product.ProductService;
import com.compasso.productsms.utils.ExceptionMessage;
import com.compasso.productsms.utils.FormatadorUtil;
import com.compasso.productsms.validator.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    @Qualifier("productValidator")
    private ValidatorService<ProductDTO> validator;

    @Override
    public Page<ProductDTO> searchAll(final Pageable pageable) {
        log.info("ProductServiceImpl - Buscando todos os produtos.");
        return repository.findAll(pageable).map(ProductDTO::new);
    }

    @Override
    public ProductDTO searchById(final Long id) {
        return findById(id).toDTO();
    }

    @Override
    public ProductDTO create(final ProductDTO productDTO) {
        log.info("ProductServiceImpl - Salvando novo produto.");
        validator.validate(productDTO);
        return repository.save(productDTO.toEntity()).toDTO();
    }

    @Override
    public void deleteById(final Long id) {
        log.info("ProductServiceImpl - Deletando produto por ID.");
        repository.deleteById(id);
    }

    @Override
    public ProductDTO update(final Long identificador, final ProductDTO productDTO) {
        log.info("ProductServiceImpl - Atualizando produto por ID.");
        final ProductEntity savedProduct = findById(identificador);
        BeanUtils.copyProperties(productDTO, savedProduct, "idProduct");
        return repository.save(savedProduct).toDTO();
    }

    @Override
    public Page<ProductDTO> searchByParamsFilter(final Pageable pageable, final ProductFilterDTO filterDTO) {
        log.info("ProductServiceImpl - Buscando produtos por filtro de paramentros.");
        return repository.findByParamsFilter(FormatadorUtil.toLowerCase(filterDTO.getCriteriaFilter()),
                                                            filterDTO.getMinimumPrice(),
                                                            filterDTO.getMaximumPrice(),
                                                            pageable)
                                                            .map(ProductDTO::new);
    }

    private ProductEntity findById(final Long id) {
        log.info("ProductServiceImpl - Buscando produto por ID.");
        return repository.findById(id)
                         .orElseThrow(() -> new ProductIdNotFount(ExceptionMessage.PRODUCT_NOT_FOUND));
    }
}
