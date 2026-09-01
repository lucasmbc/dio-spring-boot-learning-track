package dio.budgeting.application;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class GetTotalByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public GetTotalByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "total-amount-by-category", description = "Calcula o valor total das transações financeiras por categoria")
    public long execute(@ToolParam(description = "Categoria de uma transação") Category category) {
        return transactionRepository.findAllByCategory(category)
                .stream()
                .mapToLong(Transaction::getAmount)
                .sum();
    }
}
