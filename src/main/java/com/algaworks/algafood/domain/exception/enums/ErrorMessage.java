package com.algaworks.algafood.domain.exception.enums;

/**
 * Constantes de String que representam a mensagem de erro de exceptions.
 * O método get() retorna a string que é utilizada em um
 * String.format().
 * O 1º argumento recebe um valor em String,
 * o 2º argumento recebe um valor númerico inteiro (Long, int, Integer).
 * Ex: String.format(ENTIDADE_NOT_FOUND.get(), entidade, entidadeId)
 * -> A entidade Entidade de codigo x não pôde ser encontrada.
 */
public enum ErrorMessage {
    ENTIDADE_NOT_FOUND("A entidade %s de código %d não pôde ser encontrada"),
    ENTIDADE_EM_USO("A entidade %s de código %d não pôde ser excluída, pois está em uso"),
    SENHA_NAO_COINCIDE("Senha atual informada não coincide com a senha do usuário"),
    EMAIL_JA_CADASTRADO("Já existe um usuário cadstrado com o e-mail %s"),
    PRODUTO_NAO_VINCULADO("Não existe um cadastro de produto com código %d para o restaurante de código %d"),
    PERMISSAO_NAO_ENCONTRADA("Não existe permissão de código %d"),
    PRODUTO_NAO_ENCONTRADO("Não existe produto de código %d"),
    PEDIDO_NAO_ENCONTRADO("Não existe um pedido de código %s"),
    FORMA_PAGAMENTO_NAO_ACEITA("Forma de pagamento %s não é aceita pelo restaurante %s."),
    STATUS_PEDIDO("Status do pedido %s não pode ser alterado de %s para %s"),
    RELATORIO_INDISPONIVEL("Não foi possível emitir relatório de vendas diárias"),
    FOTO_PRODUTO_NAO_ENCONTRADO("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d");

    private String mensagem;

    private ErrorMessage(String mensagem){
        this.mensagem = mensagem;
    }

    public String get() {
        return mensagem;
    }
}
