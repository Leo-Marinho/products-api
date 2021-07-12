package com.compasso.productsms.resource;

import com.compasso.productsms.ProductsMsApplicationTests;
import com.compasso.productsms.dto.ProductDTO;
import com.compasso.productsms.factory.ProductFactory;
import com.compasso.productsms.factory.ProductRequestFactory;
import com.compasso.productsms.helper.RestResponsePage;
import com.compasso.productsms.model.product.ProductEntity;
import com.compasso.productsms.repository.product.ProductRepository;
import com.compasso.productsms.resource.product.request.ProductRequest;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Testes API de produtos")
public class ProductTest extends ProductsMsApplicationTests {

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductRequestFactory procuctRequestFactory;

    @Autowired
    private ProductRepository productRepository;

    private ResponseEntity<RestResponsePage<ProductDTO>> pageProductDTO;
    private ResponseEntity<ProductDTO> productDTOResponse;

    private ProductEntity product;
    private ProductRequest request;

    private String BASE_PATH_API = "/products";

    @Dado("^que exista um produto$")
    public void queExistaUmProduto() throws Throwable {
        this.product = productFactory.createDefault();
        productRepository.save(product);
    }

    @Quando("^realizar a busca pelo pruduto$")
    public void realizarABuscaPeloPruduto() {
        this.productDTOResponse = restTemplate.exchange(BASE_PATH_API + "/1", HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });
    }

    @Então("^deve retornar o produto cadastrado$")
    public void deveRetornarOProdutoCadastrado() {
        final var body = this.productDTOResponse.getBody();
        final var statusCode = this.productDTOResponse.getStatusCode();

        assertAll("Assert em produto response", () -> assertNotNull(body));
        assertAll("Assert em status code do GET produto", () -> assertEquals(HttpStatus.OK, statusCode));
    }

    @Dado("^que tenha um novo produto$")
    public void queEuTenhaUmNovoProduto() {
        this.request = procuctRequestFactory.createDefault();
    }

    @Quando("^cadastrar um novo produto$")
    public void cadastrarUmNovoProduto() {
        this.productDTOResponse = restTemplate.postForEntity(BASE_PATH_API, this.request, ProductDTO.class);
    }

    @Então("^deve retornar o produto criado$")
    public void deveRetornarOProdutoCriado() {
        final var body = this.productDTOResponse.getBody();
        final var statusCode = this.productDTOResponse.getStatusCode();

        assertAll("Assert em produto response", () -> assertNotNull(body));
        assertAll("Assert em status code do POST produto", () -> assertEquals(HttpStatus.CREATED, statusCode));
    }

    @Dado("^que remova um produto existente$")
    public void queEuRemovaUmProdutoExistente() {
        this.restTemplate.delete(BASE_PATH_API + "/2");
    }

    @Quando("^listar pagina de produtos$")
    public void listarPaginaDeProdutos() {
        this.pageProductDTO = restTemplate.exchange(BASE_PATH_API, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });
    }

    @Entao("^o tamanho da pagina de produtos deve estar correto$")
    public void oTamanhoDaPaginaDeProdutosDeveEstarCorreto() {
        final var size = this.pageProductDTO.getBody().getSize();
        final var statusCode = this.pageProductDTO.getStatusCode();

        assertAll("Assert o tamanho da lista de produtos ", () -> assertEquals(10, size));
        assertAll("Assert em status code do POST de produto ", () -> assertEquals(HttpStatus.OK, statusCode));
    }

    @Dado("^que atualize um produto existente$")
    public void queEuAtualizeUmProdutoExistente() {
        this.request = procuctRequestFactory.createDefault();
        this.restTemplate.put(BASE_PATH_API + "/1", this.request);
    }

    @Quando("^listar o produto atualizado$")
    public void listarOProdutoAtualizado() {
        this.productDTOResponse = this.restTemplate.getForEntity(BASE_PATH_API + "/1", ProductDTO.class);
    }

    @Entao("^o produto deve conter novos valores$")
    public void oProdutoDeveConterNovosValores() {
        final var name = this.productDTOResponse.getBody().getName();
        final var statusCode = this.productDTOResponse.getStatusCode();

        assertAll("Assert o nome do produto atualizado", () -> assertEquals(name, this.request.getName()));
        assertAll("Assert em status code do GET por Id do produto", () -> assertEquals(HttpStatus.OK, statusCode));
    }
}
