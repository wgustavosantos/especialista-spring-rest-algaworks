function consultar() {
    $.ajax({
        url: "http://localhost:8080/formapagamentos",
        type: "get",

        success: function(response) {
            preencherTabela(response);
        }
    });
}

function preencherTabela(formasPagamento) {
    $("#tabela tbody tr").remove();

    $.each(formasPagamento, function(i, formaPagamento) {
        var linha = $("<tr>");

        linha.append(
            $("<td>").text(formaPagamento.id),
            $("<td>").text(formaPagamento.descricao)
        );

        linha.appendTo("#tabela");
    });
}


$("#btn-consultar").click(consultar);