#language:pt

Funcionalidade: Produto

  Cenario: Buscar um produto

    Dado que exista um produto
    Quando realizar a busca pelo pruduto
    Então deve retornar o produto cadastrado

  Cenario: Cadastrar um Produto

    Dado  que tenha um novo produto
    Quando cadastrar um novo produto
    Então deve retornar o produto criado

  Cenario: Deletar um produto

    Dado que remova um produto existente
    Quando listar pagina de produtos
    Entao o tamanho da pagina de produtos deve estar correto

  Cenario: Atualizar um produto

    Dado que atualize um produto existente
    Quando listar o produto atualizado
    Entao o produto deve conter novos valores