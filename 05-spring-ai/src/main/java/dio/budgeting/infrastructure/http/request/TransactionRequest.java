package dio.budgeting.infrastructure.http.request;

import dio.budgeting.application.input.PersistTransactionInput;
import dio.budgeting.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
        @NotBlank(message = "Descrição inválida.")
        String description,

        @NotNull(message = "Categoria inválida.")
        Category category,

        @Positive(message = "Valor inválido! Verifique se o valor é positivo e maior que zero.")
        Long amount
) {
    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}
